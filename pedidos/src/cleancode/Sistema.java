package cleancode;
import cleancode.desconto.IDesconto;
import cleancode.desconto.DescontoComum;
import cleancode.desconto.DescontoPremium;
import cleancode.desconto.DescontoVip;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    private final Scanner sc = new Scanner(System.in);
    List<Pedido> pedidos = new ArrayList<>();
    DataBase dataBase = new DataBase();

    public void mostrarMenu() {
        int opcao = -1;

        while (opcao != 0) {
            menu();
            opcao = opcaoSegura();

            if (opcao == 1) {
                novoPedido();
            } else if (opcao == 2) {
                listar();
            } else if (opcao == 3) {
                buscar();
            } else if (opcao == 4) {
                criarRelatorio();
            } else if (opcao == 5) {
                cancelar();
            } else if (opcao == 0) {
                System.out.println("Fim");
            } else {
                System.out.println("Opção Invalida");
            }
        }
    }

    public void novoPedido() {
        System.out.println("Nome cliente:");
        String nomeCliente = sc.nextLine();

        System.out.println("Tipo cliente (1- Comum, 2- Premium, 3- Vip):");
        TipoCliente tipoCliente = recebeTipoCliente(opcaoSegura());

        int id = pedidos.size() + 1;
        String emailCliente = nomeCliente.replace(" ", "").toLowerCase() + "@email.com";
        Cliente cliente = new Cliente(id, nomeCliente, emailCliente, tipoCliente);

        Pedido pedido = new Pedido(id, cliente);
        adicionarMaisItens(pedido);

        IDesconto estrategia = switch (tipoCliente) {
            case COMUM -> new DescontoComum();
            case PREMIUM -> new DescontoPremium();
            case VIP -> new DescontoVip();
        };

        double valorFinal = valorFinalcomDescontoEFrete(pedido, estrategia);
        pedido.setFaturamento(valorFinal);

        pedidos.add(pedido);
        dataBase.save(pedido);

        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getGetCliente().nome());
        System.out.println("Total: " + pedido.getFaturamento());

        if (pedido.getFaturamento() > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public TipoCliente recebeTipoCliente(int input){
        return switch (input) {
            case 1 -> TipoCliente.COMUM;
            case 2 -> TipoCliente.PREMIUM;
            case 3 -> TipoCliente.VIP;
            default -> {
                System.out.println("Tipo Inválido. Escolhendo COMUM.");
                yield TipoCliente.COMUM;
            }
        };
    }

    public double valorFinalcomDescontoEFrete(Pedido pedido, IDesconto estrategiaDesconto){
        double total = pedido.getFaturamento();
        total = estrategiaDesconto.calcular(total);

        if (total < 100) {
            total += 25;
        } else if (total >= 100 && total < 300) {
            total += 15;
        }

        return total;
    }

    public void adicionarMaisItens(Pedido pedido){
        String continua = "s";
        while (continua.equalsIgnoreCase("s")) {
            System.out.println("Nome item:");
            String nome = sc.nextLine();

            System.out.println("Preco item:");
            double preco = 0;
            try {
                preco = Double.parseDouble(sc.nextLine());
            } catch (Exception e) {
                preco = 0;
            }

            System.out.println("Quantidades de Itens: ");
            int quantidade = 0;
            try {
                quantidade = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                quantidade = 1;
            }

            pedido.adicionarItem(new Item(nome, preco, quantidade));

            System.out.println("Adicionar mais item? s/n");
            continua = sc.nextLine();
        }
    }

    public void listar() {
        if (pedidos.isEmpty()) {
            System.out.println("Sem pedidos");
        } else {
            for (Pedido pedido : pedidos) {
                System.out.println("---------------");
                System.out.println("Id: " + pedido.getId());
                System.out.println("Cliente: " + pedido.getGetCliente().nome());
                System.out.println("Email: " + pedido.getGetCliente().email());
                System.out.println("Tipo de Cliente: " + pedido.getGetCliente().tipo());
                System.out.println("Status do pedido: " + pedido.getStatus());
                System.out.println("Faturamento Total: " + pedido.getFaturamento());
                System.out.println("Itens do Pedido:");
                for (int j = 0; j < pedido.getItens().size(); j++) {
                    Item item = pedido.getItens().get(j);
                    System.out.println(item.nome() + " - " + item.quantidade() + " - R$" + item.preco());
                }
            }
        }
    }

    public void buscar() {
        System.out.println("Digite o id:");
        int id = Integer.parseInt(sc.nextLine());

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                System.out.println("Pedido encontrado");
                System.out.println("Id: " + pedido.getId());
                System.out.println("Cliente: " + pedido.getGetCliente().nome());
                System.out.println("Status do pedido: " + pedido.getStatus());
                System.out.println("Faturamento Total: " + pedido.getFaturamento());

                System.out.println("Valor Total dos Itens: " + pedido.valorTotalItens());

                TipoCliente tipo = pedido.getGetCliente().tipo();
                if (tipo == TipoCliente.COMUM) {
                    System.out.println("Cliente Comum");
                } else if (tipo == TipoCliente.PREMIUM) {
                    System.out.println("Cliente Premium");
                } else if (tipo == TipoCliente.VIP) {
                    System.out.println("Cliente Vip");
                }

                for (int j = 0; j < pedido.getItens().size(); j++) {
                    Item item = pedido.getItens().get(j);
                    System.out.println("item " + (j + 1) + ": " + item.nome() + " / " + item.quantidade() + " / " + item.preco());
                }
            }
            return;
        }
        System.out.println("Pedido não encontrado");
    }

    public void criarRelatorio() {
        Relatorio relatorio = new Relatorio();
        relatorio.gerar(pedidos);
    }

    public void cancelar() {
        System.out.println("Digite id do pedido");
        int id = Integer.parseInt(sc.nextLine());

        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                if (pedido.getStatus().equals("CANCELADO")) {
                    System.out.println("Já está cancelado");
                } else {
                    pedido.getStatus().equals("NOVO");
                    System.out.println("Cancelado");
                }
                return;
            }
        }

        System.out.println("Pedido não existe");
    }

    public void menu(){
        System.out.println("==== SISTEMA ====");
        System.out.println("1 - Novo pedido");
        System.out.println("2 - Listar pedidos");
        System.out.println("3 - Buscar pedido por id");
        System.out.println("4 - Relatorio");
        System.out.println("5 - Cancelar pedido");
        System.out.println("0 - Sair");
        System.out.print("Opcao: ");
    }

    public int opcaoSegura(){
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (Exception e) {
            System.out.println("Insira um número: ");
            return -1;
        }
    }
}
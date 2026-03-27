package cleancode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sistema {

    Scanner sc = new Scanner(System.in);
    List<Pedido> pedidos = new ArrayList<>();
    DataBase dataBase = new DataBase();

    public void mostrarMenu() {
        int opcao = -1;

        while (opcao != 0) {
            menu();
            try {
                opcao = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Erro");
                opcao = -1;
            }

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

        System.out.println("Tipo cliente (Comum, Premium, Vip):");
        String tipoCliente = sc.nextLine();

        int id = pedidos.size() + 1;
        String emailCliente = nomeCliente.replace(" ", "").toLowerCase() + "@email.com";
        Cliente cliente = new Cliente(id, nomeCliente, emailCliente, tipoCliente);

        Pedido pedido = new Pedido(id, cliente);
        adicionarMaisItens(pedido);

        double valorFinal = valorFinalcomDescontoEFrete(pedido);
        pedido.setFaturamento(valorFinal);

        pedidos.add(pedido);
        dataBase.save(pedido);

        System.out.println("Pedido criado com sucesso");
        System.out.println("Id: " + pedido.getId());
        System.out.println("Cliente: " + pedido.getGetCliente().getNome());
        System.out.println("Total: " + pedido.getFaturamento());

        if (pedido.getFaturamento() > 500) {
            System.out.println("Pedido importante!!!");
        }
    }

    public double valorFinalcomDescontoEFrete(Pedido pedido){
        double total = pedido.getFaturamento();
        Cliente cliente = pedido.getGetCliente();

        if (cliente.getTipo().equals("Comum")) {
            if (total > 300) {
                total = total - (total * 0.05);
            }
        } else if (cliente.getTipo().equals("Premium")) {
            if (total > 200) {
                total = total - (total * 0.10);
            } else {
                total = total - (total * 0.03);
            }
        } else if (cliente.getTipo().equals("Vip")) {
            total -= (total * 0.15);
        }

        if (total < 100) {
            total += 25;
        } else if (total >= 100 && total < 300) {
            total += 15;
        } else {
            total += 0;
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

            System.out.println("Qtd:");
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
                System.out.println("Cliente: " + pedido.getGetCliente().getNome());
                System.out.println("Email: " + pedido.getGetCliente().getEmail());
                System.out.println("Tipo de Cliente: " + pedido.getGetCliente().getTipo());
                System.out.println("Status do pedido: " + pedido.getStatus());
                System.out.println("Faturamento Total: " + pedido.getFaturamento());
                System.out.println("Itens do Pedido:");
                for (int j = 0; j < pedido.getItens().size(); j++) {
                    Item item = pedido.getItens().get(j);
                    System.out.println(item.getNome() + " - " + item.getQuantidade() + " - R$" + item.getPreco());
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
                System.out.println("Cliente: " + pedido.getGetCliente().getNome());
                System.out.println("Status do pedido: " + pedido.getStatus());
                System.out.println("Faturamento Total: " + pedido.getFaturamento());

                System.out.println("Valor Total dos Itens: " + pedido.valorTotalItens());

                if (pedido.getGetCliente().getTipo().equals("Comum")) {
                    System.out.println("Cliente comum");
                } else if (pedido.getGetCliente().getTipo().equals("Premium")) {
                    System.out.println("Cliente premium");
                } else if (pedido.getGetCliente().getTipo().equals("Vip")) {
                    System.out.println("Cliente vip");
                } else {
                    System.out.println("Tipo de cliente desconhecido");
                }

                for (int j = 0; j < pedido.getItens().size(); j++) {
                    Item item = pedido.getItens().get(j);
                    System.out.println("item " + (j + 1) + ": " + item.getNome() + " / " + item.getQuantidade() + " / " + item.getPreco());
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
}
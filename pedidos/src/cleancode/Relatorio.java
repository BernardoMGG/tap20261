package cleancode;
import java.util.List;

public class Relatorio {

    public void gerar(List<Pedido> listaPedidos) {
        System.out.println("======= RELATORIO =======");

        int quantidadePedidos = 0;
        double valorTotalPedidos = 0;
        int pedidosCancelados = 0;
        int clienteComuns = 0;
        int clientePremiums = 0;
        int clienteVips = 0;

        for (Pedido pedido: listaPedidos) {
            quantidadePedidos++;

            valorTotalPedidos = valorTotalPedidos + pedido.getFaturamento();

            if (pedido.getStatus().equals("CANCELADO")) {
                pedidosCancelados++;
            }

            TipoCliente tipo = pedido.getGetCliente().tipo();
            if (tipo == TipoCliente.COMUM) {
                clienteComuns++;
            } else if (tipo == TipoCliente.PREMIUM) {
                clientePremiums++;
            } else if (tipo == TipoCliente.VIP) {
                clienteVips++;
            }

            System.out.println("Pedido " + pedido.getId() + " - " + pedido.getGetCliente().nome() + " - " + pedido.getFaturamento() + " - " + pedido.getStatus());

            for (int j = 0; j < pedido.getItens().size(); j++) {
                Item item = pedido.getItens().get(j);
                System.out.println("   item: " + item.nome() + " quantidade:" + item.quantidade() + " preco:" + item.preco());
            }
        }

        System.out.println("--------------------");
        System.out.println("Quantidade pedidos: " + quantidadePedidos);
        System.out.println("Valor total: " + valorTotalPedidos);
        System.out.println("Pedidos cancelados: " + pedidosCancelados);
        System.out.println("Clientes Comuns: " + clienteComuns);
        System.out.println("Clientes Premium: " + clientePremiums);
        System.out.println("Clientes Vip: " + clienteVips);

        if (quantidadePedidos > 0) {
            System.out.println("Média: " + (valorTotalPedidos / quantidadePedidos));
        } else {
            System.out.println("Média: 0");
        }

        if (valorTotalPedidos > 1000) {
            System.out.println("Resultado Muito Bom!");
        } else if (valorTotalPedidos > 500) {
            System.out.println("Resultado ok");
        } else {
            System.out.println("Resultado fraco");
        }
    }
}
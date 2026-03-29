package cleancode;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int id;
    private final Cliente getCliente;
    private final List<Item> itens;
    private double faturamento;
    private String status;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.getCliente = cliente;
        this.itens = new ArrayList<>();
        this.status = "NOVO";
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public int getId() {
        return id;
    }

    public Cliente getGetCliente() {
        return getCliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public String getStatus() {
        return status;
    }

    public void cancelarPedido() {
        this.status.equals("CANCELADO");
    }

    public double valorTotalItens() {
        double valorItens = 0;
        for (Item item : itens) {
            valorItens += item.valorTotal();
        }
        return valorItens;
    }
}

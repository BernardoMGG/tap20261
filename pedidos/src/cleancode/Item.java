package cleancode;

public record Item(String nome, double preco, int quantidade) {
    public double valorTotal() {
        return preco * quantidade;
    }
}

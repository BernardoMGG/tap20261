package cleancode.desconto;

public class DescontoVip implements IDesconto{
    @Override
    public double calcular(double total) {
        return total - (total * 0.15);
    }
}

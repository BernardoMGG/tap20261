package cleancode.desconto;

public class DescontoComum implements IDesconto{
    @Override
    public double calcular(double total) {
        if (total > 300) {
            return total - (total * 0.05);
        }
        return total;
    }
}

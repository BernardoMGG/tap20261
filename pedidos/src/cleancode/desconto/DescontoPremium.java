package cleancode.desconto;

public class DescontoPremium implements IDesconto{
    @Override
    public double calcular(double total) {
        if (total > 200) {
            return total - (total * 0.10);
        }
        return total - (total * 0.03);
    }
}

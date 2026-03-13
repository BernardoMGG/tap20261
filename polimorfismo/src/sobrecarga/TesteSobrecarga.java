package sobrecarga;

class TesteSobrecarga {
    /**
     * Testanto classe Calcular.
     * @param args
     */
    public static void main(final String[] args) {
        Calculadora calc = new Calculadora();
        System.out.println(calc.somar(2, 2));
        System.out.println(calc.somar(2, 2, 2));
        System.out.println(calc.somar(1.0, 1.0));
    }
}

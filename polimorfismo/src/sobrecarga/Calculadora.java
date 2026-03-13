package sobrecarga;

public final class Calculadora {
    /**
     * @param a
     * @param b
     * @return a+b
     */
    public int somar(final int a, final int b) {
        return a + b;
    }
    /**
     * @param a
     * @param b
     * @param c
     * @return a+b+c
     */
    public int somar(final int a, final int b, final int c) {
        return a + b + c;
    }
    /**
     * @param a
     * @param b
     * @return a+b
     */
    public double somar(final double a, final double b) {
        return a + b;
    }
}

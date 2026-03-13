package sobreescrita;

public class TesteSobreescrita {
    /**
     * Testa a Sobreescrita.
     * @param args
     */
    public static void main(final String[] args) {
        Animal meuAnimal1 = new Cachorro();
        Animal meuAnimal2 = new Gato();

        meuAnimal1.emitirSom();
        meuAnimal2.emitirSom();

    }
}

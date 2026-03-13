package sobreescrita;

public class Gato extends Animal {
    /**
     * Sobreescreve Emite Som.
     */
    @Override
    void emitirSom() {
        System.out.println("Miau");
    }
}

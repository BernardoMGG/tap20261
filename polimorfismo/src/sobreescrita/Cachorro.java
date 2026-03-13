package sobreescrita;

public class Cachorro extends Animal {
    /**
     * Sobreescreve Emite Som.
     */
    @Override
    void emitirSom() {
        System.out.println("Au au");
    }
}

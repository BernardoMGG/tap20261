package cleancode;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    public static List<Pedido> banco = new ArrayList<>();

    public void save(Pedido p) {
        try {
            banco.add(p);
            System.out.println("salvou no banco");
        } catch (Exception e) {
            System.out.println("erro ao salvar");
        }
    }

    public List<Pedido> getAll() {
        return banco;
    }

}


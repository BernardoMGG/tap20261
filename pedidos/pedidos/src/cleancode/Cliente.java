package cleancode;

public class Cliente {
    private final int id;
    private final String nome;
    private final String email;
    private final String tipo;

    public Cliente(int id, String nome, String email, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String formataTipo() {
        if (tipo.equals("Comum")) {
            return "Comum";
        } else if (tipo.equals("Premium")) {
            return "Premium";
        } else if (tipo.equals("Vip")) {
            return "Vip";
        } else {
            return "outro";
        }
    }

    public String getTipo() {
        return tipo;
    }
}

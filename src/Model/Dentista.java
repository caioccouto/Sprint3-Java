package Model;

public class Dentista {
    private String cro;

    public Dentista() {}

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Dentista{" +
                "cro='" + cro + '\'' +
                '}';
    }
}

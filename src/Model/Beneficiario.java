package Model;

public class Beneficiario {
    private Dentista dentistaResp;

    public Beneficiario() {}

    public Dentista getDentistaResp() {
        return dentistaResp;
    }

    public void setDentistaResp(Dentista dentistaResp) {
        this.dentistaResp = dentistaResp;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Beneficiario{" +
                "dentistaResp=" + dentistaResp +
                '}';
    }
}

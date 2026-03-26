package Model;

import java.time.LocalDate;

public class Dentista extends Pessoa{
    private String cro;

    public Dentista(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco, String cro) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
        this.cro = cro;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", CRO: " + cro;
    }
}

package model.vo;

import java.time.LocalDate;

public class Dentista extends Pessoa {
    private int cro;

    public Dentista(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco, int cro) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
        this.cro = cro;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", CRO: " + cro;
    }
}

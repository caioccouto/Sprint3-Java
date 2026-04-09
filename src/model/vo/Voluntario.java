package model.vo;

import java.time.LocalDate;

public class Voluntario extends Pessoa{
    private int cro;
    private LocalDate dtCadastro;

    public Voluntario(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco, int cro, LocalDate dtCadastro) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
        this.cro = cro;
        this.dtCadastro = dtCadastro;
    }

    public int getCro() {
        return cro;
    }

    public void setCro(int cro) {
        this.cro = cro;
    }

    public LocalDate getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(LocalDate dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", CRO: " + cro +
                ", Dt Cadastro: " + dtCadastro;
    }
}

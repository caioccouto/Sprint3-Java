package model.vo;

import java.time.LocalDate;

public class Doador extends Pessoa{
    private int id;
    private static int contador = 1;
    private String tipoPessoa;
    private String cnpj;

    public Doador(String nome, int idade, String tipoPessoa, String cpf, String cnpj, LocalDate dtNasc, String email, String tel, String endereco) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
        this.id = contador++;
        this.tipoPessoa = tipoPessoa;
        this.cnpj = cnpj;
    }

    public static void setContador(int valor){
        contador = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoPessoa() {
        return tipoPessoa;
    }

    public void setTipoPessoa(String tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", ID: " + id +
                ", Tipo: " + tipoPessoa +
                ", CNPJ: " + cnpj;
    }
}

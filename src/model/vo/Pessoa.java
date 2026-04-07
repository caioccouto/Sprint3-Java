package model.vo;

import java.time.LocalDate;

public class Pessoa {
    //Atributos
    private String nome;
    private int idade;
    private String cpf;
    private LocalDate dtNasc;
    private String email;
    private String tel;
    private String endereco;

    //Construtor


    public Pessoa(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.email = email;
        this.tel = tel;
        this.endereco = endereco;
    }

    //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(LocalDate dtNasc) {
        this.dtNasc = dtNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", Idade: " + idade + ", CPF: " + cpf + ", Nasc: " + dtNasc +
                ", Email: " + email + ", Tel: " + tel + ", Endereço: " + endereco;
    }
}

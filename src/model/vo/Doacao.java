package model.vo;

import java.time.LocalDate;

public class Doacao {
    private int id;
    private static int contador = 1;
    private int idDoador;
    private double valor;
    private LocalDate dtDoacao;
    private String descricao;

    public Doacao(int idDoador, double valor, LocalDate dtDoacao, String descricao) {
        this.id = contador++;
        this.idDoador = idDoador;
        this.valor = valor;
        this.dtDoacao = dtDoacao;
        this.descricao = descricao;
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

    public int getIdDoador() {
        return idDoador;
    }

    public void setIdDoador(int idDoador) {
        this.idDoador = idDoador;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDtDoacao() {
        return dtDoacao;
    }

    public void setDtDoacao(LocalDate dtDoacao) {
        this.dtDoacao = dtDoacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", ID Doador: " + idDoador +
                ", Valor: " + valor +
                ", Data: " + dtDoacao +
                ", Descrição: " + descricao;
    }
}

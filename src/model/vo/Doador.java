package model.vo;

import java.time.LocalDate;

public class Doador extends Pessoa{
    private int id;
    private static int contador = 1;

    public Doador(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
        this.id = contador++;
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

    @Override
    public String toString() {
        return super.toString() +
                ", ID: " + id;
    }
}

package Model;

import java.time.LocalDate;

public class Beneficiario extends Pessoa{

    public Beneficiario(String nome, int idade, String cpf, LocalDate dtNasc, String email, String tel, String endereco) {
        super(nome, idade, cpf, dtNasc, email, tel, endereco);
    }
}

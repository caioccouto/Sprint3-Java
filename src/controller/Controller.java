package controller;

import model.vo.Dentista;

import java.util.List;

public class Controller {

    public boolean validarCpf(String cpf){
        return cpf != null && cpf.matches("\\d{11}");
    }

    public boolean validarEmail(String email){
        return email.contains("@");
    }

    public boolean validarIdade(int idade){
        return idade >= 0 && idade <= 120;
    }

    public boolean validarIdadeDent(int idade){
        return idade >= 18 && idade <= 120;
    }

    public boolean validarCro(int cro, List<Dentista> listaDent){
        for (Dentista d : listaDent){
            if (d.getCro() == cro){
                return true;
            }
        }
        return false;
    }
}

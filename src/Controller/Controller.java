package Controller;

public class Controller {

    public boolean validarCpf(String cpf){
        return cpf != null && cpf.matches("\\d{11}");
    }

    public boolean validarEmail(String email){
        return email.contains("@");
    }

    public boolean validarIdadeBenef(int idade){
        return idade >= 0 && idade <= 120;
    }

    public boolean validarIdadeDent(int idade){
        return idade >= 18 && idade <= 120;
    }
}

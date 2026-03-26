package Services;

import Lists.ListaBeneficiario;
import Lists.ListaDentista;
import Model.Beneficiario;
import Model.Dentista;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaCDB {
    ListaBeneficiario lb = new ListaBeneficiario();
    ListaDentista ld = new ListaDentista();

    public void addBenef(Scanner sc){
        System.out.println("===== Cadastrar Beneficiário =====");
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        int idade;
        while (true){
            try{
                System.out.println("Idade: ");
                idade = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        LocalDate dtNasc;
        while (true){
            try{
                System.out.println("Data de nascimento (AAAA-MM-DD): ");
                dtNasc = LocalDate.parse(sc.nextLine());
                break;
            }catch (DateTimeParseException e){
                System.out.println("Digite uma data válida!");
            }
        }

        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Endereço: ");
        String endereco = sc.nextLine();

        Beneficiario b  = new Beneficiario(nome, idade, cpf, dtNasc, email, telefone, endereco);
        lb.addBenef(b);
        System.out.println("Beneficiário cadastrado com sucesso!");
    }

    public void addDent(Scanner sc){
        System.out.println("===== Cadastrar Dentista =====");
        System.out.println("Nome: ");
        String nome = sc.nextLine();

        int idade;
        while (true){
            try{
                System.out.println("Idade: ");
                idade = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

        System.out.println("CPF: ");
        String cpf = sc.nextLine();

        LocalDate dtNasc;
        while (true){
            try{
                System.out.println("Data de nascimento (AAAA-MM-DD): ");
                dtNasc = LocalDate.parse(sc.nextLine());
                break;
            }catch (DateTimeParseException e){
                System.out.println("Digite uma data válida!");
            }
        }

        System.out.println("Email: ");
        String email = sc.nextLine();
        System.out.println("Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Endereço: ");
        String endereco = sc.nextLine();
        System.out.println("CRO: ");
        String cro = sc.nextLine();

        Dentista d  = new Dentista(nome, idade, cpf, dtNasc, email, telefone, endereco, cro);
        ld.addDent(d);
        System.out.println("Dentista cadastrado com sucesso!");
    }

    public void listarBenefs(){
        if (lb.listaBenef().isEmpty()){
            System.out.println("Nenhum beneficiário cadastrado!");
            return;
        }
        for (int i = 0; i < lb.listaBenef().size(); i++){
            System.out.println((i + 1) + ". " + lb.listaBenef().get(i));
        }
    }

    public void listarDents(){
        if (ld.listaDent().isEmpty()){
            System.out.println("Nenhum dentista cadastrado!");
            return;
        }
        for (int i = 0; i < ld.listaDent().size(); i++){
            System.out.println((i + 1) + ". " + ld.listaDent().get(i));
        }
    }
}

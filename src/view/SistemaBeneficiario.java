package view;

import controller.Controller;
import model.dao.BeneficiarioDAO;
import model.repository.ListaBeneficiario;
import model.vo.Beneficiario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaBeneficiario {
    private final ListaBeneficiario lb = new ListaBeneficiario();
    private final BeneficiarioDAO bd = new BeneficiarioDAO();
    private final Controller ct = new Controller();

    public SistemaBeneficiario(){
        List<Beneficiario> benefs = bd.buscarBenefs();
        for (Beneficiario b : benefs){
            lb.addBenef(b);
        }
        Beneficiario.setContador(bd.buscarMaxId() + 1);
    }

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

                if (ct.validarIdade(idade)){
                    break;
                }else {
                    System.out.println("Digite um número válido!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

        String cpf;
        while(true){
            System.out.println("CPF: ");
            cpf = sc.nextLine();

            if (ct.validarCpf(cpf)){
                break;
            }else {
                System.out.println("CPF inválido! Deve conter 11 dígitos numéricos!");
            }
        }

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

        String email;
        while(true){
            System.out.println("Email: ");
            email = sc.nextLine();

            if (ct.validarEmail(email)){
                break;
            }else {
                System.out.println("Digite um email válido!");
            }
        }

        System.out.println("Telefone: ");
        String telefone = sc.nextLine();
        System.out.println("Endereço: ");
        String endereco = sc.nextLine();

        Beneficiario b  = new Beneficiario(nome, idade, cpf, dtNasc, email, telefone, endereco);
        lb.addBenef(b);
        bd.salvarBenef(b);
        System.out.println("Beneficiário cadastrado com sucesso!");
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

    public int buscarBenef(List<Beneficiario> l, int id){
        int indice = -1;
        for (int i = 0; i < l.size(); i++){
            if (l.get(i).getId() == id){
                indice = i;
            }
        }
        return indice;
    }

    public void attBenef(Scanner sc){
        System.out.println("===== Atualizar Beneficiário =====");
        System.out.println("Digite o ID do beneficiário que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarBenef(lb.listaBenef(), id);

        if (indice != -1){
            System.out.println("Novo nome: ");
            String nome = sc.nextLine();

            int idade;
            while (true){
                try{
                    System.out.println("Nova idade: ");
                    idade = sc.nextInt();
                    sc.nextLine();

                    if (ct.validarIdade(idade)){
                        break;
                    }else {
                        System.out.println("Digite um número válido");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Digite um número válido!");
                    sc.nextLine();
                }
            }

            String cpf;
            while(true){
                System.out.println("Novo CPF: ");
                cpf = sc.nextLine();

                if (ct.validarCpf(cpf)){
                    break;
                }else {
                    System.out.println("CPF inválido! Deve conter 11 dígitos numéricos!");
                }
            }

            LocalDate dtNasc;
            while (true){
                try{
                    System.out.println("Nova data de nascimento (AAAA-MM-DD): ");
                    dtNasc = LocalDate.parse(sc.nextLine());
                    break;
                }catch (DateTimeParseException e){
                    System.out.println("Digite uma data válida!");
                }
            }

            String email;
            while(true){
                System.out.println("Novo Email: ");
                email = sc.nextLine();

                if (ct.validarEmail(email)){
                    break;
                }else {
                    System.out.println("Digite um email válido!");
                }
            }

            System.out.println("Novo telefone: ");
            String telefone = sc.nextLine();
            System.out.println("Novo endereço: ");
            String endereco = sc.nextLine();

            lb.listaBenef().get(indice).setNome(nome);
            lb.listaBenef().get(indice).setIdade(idade);
            lb.listaBenef().get(indice).setCpf(cpf);
            lb.listaBenef().get(indice).setDtNasc(dtNasc);
            lb.listaBenef().get(indice).setEmail(email);
            lb.listaBenef().get(indice).setTel(telefone);
            lb.listaBenef().get(indice).setEndereco(endereco);
            bd.attBenef(lb.listaBenef().get(indice));
            System.out.println("Beneficiário atualizado!");
        }
        else {
            System.out.println("Beneficiário não encontrado!");
        }
    }

    public void removerBenef(Scanner sc){
        System.out.println("===== Remover Beneficiário =====");
        System.out.println("Digite o ID do beneficiário que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarBenef(lb.listaBenef(), id);

        if (indice != -1){
            try {
                bd.removerBenef(lb.listaBenef().get(indice));
                lb.listaBenef().remove(indice);
                System.out.println("Beneficiário removido!");
            } catch (RuntimeException e) {
                if ("FK_VIOLATION".equals(e.getMessage())){
                    System.out.println("Erro: Esse beneficiário possui triagens vinvuladas e não pode ser removido!");
                }else {
                    System.out.println("Erro ao remover beneficiario: " + e.getMessage());
                }
            }
        }else {
            System.out.println("Beneficiário não encontrado!");
        }
    }
}
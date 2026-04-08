package view;

import controller.Controller;
import model.dao.DentistaDAO;
import model.repository.ListaDentista;
import model.vo.Dentista;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaDentista {
    private final ListaDentista ld = new ListaDentista();
    private final DentistaDAO dd = new DentistaDAO();
    private final Controller ct = new Controller();

    public SistemaDentista(){
        List<Dentista> dents = dd.buscarDents();
        for (Dentista d : dents){
            ld.addDent(d);
        }
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

                if (ct.validarIdadeDent(idade)){
                    break;
                }else {
                    System.out.println("Dentista deve ser maior de idade!");
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

        int cro;
        while (true){
            try{
                System.out.println("CRO: ");
                cro = sc.nextInt();
                sc.nextLine();

                if (ct.validarCro(cro, ld.listaDent())){
                    System.out.println("CRO já cadastrado!");
                }else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

        Dentista d  = new Dentista(nome, idade, cpf, dtNasc, email, telefone, endereco, cro);
        ld.addDent(d);
        dd.salvarDent(d);
        System.out.println("Dentista cadastrado com sucesso!");
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

    public int buscarDent(List<Dentista> l, int cro){
        int indice = -1;
        for (int i = 0; i < l.size(); i++){
            if (l.get(i).getCro() == cro){
                indice = i;
            }
        }
        return indice;
    }

    public void attDent(Scanner sc){
        System.out.println("===== Atualizar Dentista =====");
        System.out.println("Digite o CRO do dentista que deseja atualizar: ");
        int cro = sc.nextInt();
        sc.nextLine();
        int indice = buscarDent(ld.listaDent(), cro);

        if (indice != -1){
            System.out.println("Novo nome: ");
            String nome = sc.nextLine();

            int idade;
            while (true){
                try{
                    System.out.println("Nova idade: ");
                    idade = sc.nextInt();
                    sc.nextLine();

                    if (ct.validarIdadeDent(idade)){
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

            ld.listaDent().get(indice).setNome(nome);
            ld.listaDent().get(indice).setIdade(idade);
            ld.listaDent().get(indice).setCpf(cpf);
            ld.listaDent().get(indice).setDtNasc(dtNasc);
            ld.listaDent().get(indice).setEmail(email);
            ld.listaDent().get(indice).setTel(telefone);
            ld.listaDent().get(indice).setEndereco(endereco);
            dd.attDent(ld.listaDent().get(indice));
            System.out.println("Dentista atualizado!");
        }
        else {
            System.out.println("Dentista não encontrado!");
        }
    }

    public void removerDent(Scanner sc){
        System.out.println("===== Remover Dentista =====");
        System.out.println("Digite o CRO do dentista que deseja remover: ");
        int cro = sc.nextInt();
        sc.nextLine();
        int indice = buscarDent(ld.listaDent(), cro);

        if (indice != -1){
            dd.removerDent(ld.listaDent().get(indice));
            ld.listaDent().remove(indice);
            System.out.println("Dentista removido!");
        }else {
            System.out.println("Dentista não encontrado!");
        }
    }
}

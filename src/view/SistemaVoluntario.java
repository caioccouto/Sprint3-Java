package view;

import controller.Controller;
import model.dao.VoluntarioDAO;
import model.repository.ListaVoluntario;
import model.vo.Voluntario;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaVoluntario {
    private final ListaVoluntario lv = new ListaVoluntario();
    private final VoluntarioDAO vd = new VoluntarioDAO();
    private final Controller ct = new Controller();

    public SistemaVoluntario(){
        List<Voluntario> vols = vd.buscarVols();
        for (Voluntario v : vols){
            lv.addVol(v);
        }
    }

    public void addVol(Scanner sc){
        System.out.println("===== Cadastrar Voluntário =====");
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
                    System.out.println("Voluntário deve ser maior de idade!");
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

                if (ct.validarCroVol(cro, lv.listaVol())){
                    System.out.println("CRO já cadastrado!");
                }else {
                    break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

        LocalDate dtCadastro;
        while (true){
            try{
                System.out.println("Data do cadastro (AAAA-MM-DD): ");
                dtCadastro = LocalDate.parse(sc.nextLine());
                break;
            }catch (DateTimeParseException e){
                System.out.println("Digite uma data válida!");
            }
        }

        Voluntario v = new Voluntario(nome, idade, cpf, dtNasc, email, telefone, endereco, cro, dtCadastro);
        lv.addVol(v);
        vd.salvarVol(v);
        System.out.println("Voluntário cadastrado com sucesso!");
    }

    public void listarVols(){
        if (lv.listaVol().isEmpty()){
            System.out.println("Nenhum voluntário cadastrado!");
            return;
        }
        for (int i = 0; i < lv.listaVol().size(); i++){
            System.out.println((i + 1) + ". " + lv.listaVol().get(i));
        }
    }

    public int buscarVol(List<Voluntario> l, int cro){
        int indice = -1;
        for (int i = 0; i < l.size(); i++){
            if (l.get(i).getCro() == cro){
                indice = i;
            }
        }
        return indice;
    }

    public void attVol(Scanner sc){
        System.out.println("===== Atualizar Voluntário =====");
        System.out.println("Digite o CRO do voluntário que deseja atualizar: ");
        int cro = sc.nextInt();
        sc.nextLine();
        int indice = buscarVol(lv.listaVol(), cro);

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

            lv.listaVol().get(indice).setNome(nome);
            lv.listaVol().get(indice).setIdade(idade);
            lv.listaVol().get(indice).setCpf(cpf);
            lv.listaVol().get(indice).setDtNasc(dtNasc);
            lv.listaVol().get(indice).setEmail(email);
            lv.listaVol().get(indice).setTel(telefone);
            lv.listaVol().get(indice).setEndereco(endereco);
            vd.attVol(lv.listaVol().get(indice));
            System.out.println("Voluntário atualizado!");
        }
        else {
            System.out.println("Voluntário não encontrado!");
        }
    }

    public void removerVol(Scanner sc){
        System.out.println("===== Remover Voluntário =====");
        System.out.println("Digite o CRO do voluntário que deseja remover: ");
        int cro = sc.nextInt();
        sc.nextLine();
        int indice = buscarVol(lv.listaVol(), cro);

        if (indice != -1){
            vd.removerVol(lv.listaVol().get(indice));
            lv.listaVol().remove(indice);
            System.out.println("Voluntário removido!");
        }else {
            System.out.println("Voluntário não encontrado!");
        }
    }
}

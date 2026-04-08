package view;

import controller.Controller;
import model.dao.DoadorDAO;
import model.repository.ListaDoador;
import model.vo.Doador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaDoador {
    private final ListaDoador ld = new ListaDoador();
    private final DoadorDAO doadorDAO = new DoadorDAO();
    private final Controller ct = new Controller();

    public SistemaDoador(){
        List<Doador> doador = doadorDAO.buscarDoador();
        for (Doador d : doador){
            ld.addDoador(d);
        }
        Doador.setContador(doadorDAO.buscarMaxId() + 1);
    }

    public void addDoador(Scanner sc){
        System.out.println("===== Cadastrar Doador =====");
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

        Doador d = new Doador(nome, idade, cpf, dtNasc, email, telefone, endereco);
        ld.addDoador(d);
        doadorDAO.salvarDoador(d);
        System.out.println("Doador cadastrado com sucesso!");
    }

    public void listarDoador(){
        if (ld.listaDoador().isEmpty()){
            System.out.println("Nenhum doador cadastrado!");
            return;
        }
        for (int i = 0; i < ld.listaDoador().size(); i++){
            System.out.println((i + 1) + ". " + ld.listaDoador().get(i));
        }
    }

    public int buscarDoador(List<Doador> l, int id){
        int indice = -1;
        for (int i = 0; i < l.size(); i++){
            if (l.get(i).getId() == id){
                indice = i;
            }
        }
        return indice;
    }

    public void attDoador(Scanner sc){
        System.out.println("===== Atualizar Doador =====");
        System.out.println("Digite o ID do doador que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarDoador(ld.listaDoador(), id);

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

            ld.listaDoador().get(indice).setNome(nome);
            ld.listaDoador().get(indice).setIdade(idade);
            ld.listaDoador().get(indice).setCpf(cpf);
            ld.listaDoador().get(indice).setDtNasc(dtNasc);
            ld.listaDoador().get(indice).setEmail(email);
            ld.listaDoador().get(indice).setTel(telefone);
            ld.listaDoador().get(indice).setEndereco(endereco);
            doadorDAO.attDoador(ld.listaDoador().get(indice));
            System.out.println("Doador atualizado!");
        }
        else {
            System.out.println("Doador não encontrado!");
        }
    }

    public void removerDoador(Scanner sc){
        System.out.println("===== Remover Doador =====");
        System.out.println("Digite o ID do doador que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarDoador(ld.listaDoador(), id);

        if (indice != -1){
            doadorDAO.removerDoador(ld.listaDoador().get(indice));
            ld.listaDoador().remove(indice);
            System.out.println("Doador removido!");
        }else {
            System.out.println("Doador não encontrado!");
        }
    }
}

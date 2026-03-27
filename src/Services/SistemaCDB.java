package Services;

import Lists.ListaBeneficiario;
import Lists.ListaDentista;
import Model.Beneficiario;
import Model.Dentista;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
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

        int cro;
        while (true){
            try{
                System.out.println("CRO: ");
                cro = sc.nextInt();
                sc.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Digite um número válido!");
                sc.nextLine();
            }
        }

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

    public int buscarBenef(List<Beneficiario> l, int id){
        int indice = -1;
        for (int i = 0; i < l.size(); i++){
            if (l.get(i).getId() == id){
                indice = i;
            }
        }
        return indice;
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
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Digite um número válido!");
                    sc.nextLine();
                }
            }

            System.out.println("Novo CPF: ");
            String cpf = sc.nextLine();

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

            System.out.println("Novo email: ");
            String email = sc.nextLine();
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
            System.out.println("Beneficiário atualizado!");
        }
        else {
            System.out.println("Beneficiário não encontrado!");
        }
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
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Digite um número válido!");
                    sc.nextLine();
                }
            }

            System.out.println("Novo CPF: ");
            String cpf = sc.nextLine();

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

            System.out.println("Novo email: ");
            String email = sc.nextLine();
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
            System.out.println("Dentista atualizado!");
        }
        else {
            System.out.println("Dentista não encontrado!");
        }
    }

    public void removerBenef(Scanner sc){
        System.out.println("===== Remover Beneficiário =====");
        System.out.println("Digite o ID do beneficiário que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarBenef(lb.listaBenef(), id);

        if (indice != -1){
            lb.listaBenef().remove(indice);
            System.out.println("Beneficiário removido!");
        }else {
            System.out.println("Beneficiário não encontrado!");
        }
    }

    public void removerDent(Scanner sc){
        System.out.println("===== Remover Dentista =====");
        System.out.println("Digite o ID do dentista que deseja remover: ");
        int cro = sc.nextInt();
        sc.nextLine();
        int indice = buscarDent(ld.listaDent(), cro);

        if (indice != -1){
            ld.listaDent().remove(indice);
            System.out.println("Dentista removido!");
        }else {
            System.out.println("Dentista não encontrado!");
        }
    }
}

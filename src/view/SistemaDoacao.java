package view;

import controller.Controller;
import model.dao.DoacaoDAO;
import model.dao.DoadorDAO;
import model.repository.ListaDoacao;
import model.vo.Doacao;
import model.vo.Doador;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SistemaDoacao {

    private final ListaDoacao ld = new ListaDoacao();
    private final DoacaoDAO doacaoDAO = new DoacaoDAO();
    private final Controller ct = new Controller();

    public SistemaDoacao(){
        List<Doacao> doacao = doacaoDAO.buscarDoacao();
        for (Doacao d : doacao){
            ld.addDoacao(d);
        }
        Doacao.setContador(doacaoDAO.buscarMaxId() + 1);
    }

    public void addDoacao(Scanner sc) {
        System.out.println("===== Registrar Doação =====");

        List<Doador> doadores = new DoadorDAO().buscarDoador();

        if (doadores.isEmpty()) {
            System.out.println("Nenhum doador cadastrado! Cadastre um doador primeiro.");
            return;
        }

        int idDoador;
        Doador doadorEncontrado = null;

        while (true) {
            System.out.println("ID do Doador: ");
            idDoador = sc.nextInt();
            sc.nextLine();

            for (Doador d : doadores) {
                if (d.getId() == idDoador) {
                    doadorEncontrado = d;
                    break;
                }
            }

            if (doadorEncontrado != null) {
                break;
            } else {
                System.out.println("Doador não encontrado! Tente novamente.");
            }
        }

        double valor;
        while (true) {
            try {
                System.out.println("Valor da doação (R$): ");
                valor = sc.nextDouble();
                sc.nextLine();
                if (ct.validarValor(valor)) {
                    break;
                } else {
                    System.out.println("O valor deve ser positivo!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um valor numérico válido!");
                sc.nextLine();
            }
        }

        LocalDate dtDoacao;
        while (true) {
            try {
                System.out.println("Data da doação (AAAA-MM-DD): ");
                dtDoacao = LocalDate.parse(sc.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Digite uma data válida!");
            }
        }

        System.out.println("Descrição (ex: dinheiro, materiais odontológicos, kits de higiene bucal): ");
        String descricao = sc.nextLine();

        Doacao d = new Doacao(idDoador, valor, dtDoacao, descricao);
        ld.addDoacao(d);
        doacaoDAO.salvarDoacao(d);
        System.out.println("Doação registrada com sucesso!");
    }

    public void listarDoacoes() {
        if (ld.listaDoacao().isEmpty()) {
            System.out.println("Nenhuma doação registrada!");
            return;
        }
        for (int i = 0; i < ld.listaDoacao().size(); i++) {
            System.out.println((i + 1) + ". " + ld.listaDoacao().get(i));
        }
    }

    public int buscarDoacao(List<Doacao> l, int id) {
        int indice = -1;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getId() == id) {
                indice = i;
            }
        }
        return indice;
    }

    public void attDoacao(Scanner sc) {
        System.out.println("===== Atualizar Doação =====");
        System.out.println("Digite o ID da doação que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarDoacao(ld.listaDoacao(), id);

        if (indice != -1) {

            double valor;
            while (true) {
                try {
                    System.out.println("Novo valor (R$): ");
                    valor = sc.nextDouble();
                    sc.nextLine();
                    if (ct.validarValor(valor)) {
                        break;
                    } else {
                        System.out.println("O valor deve ser positivo!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Digite um valor numérico válido!");
                    sc.nextLine();
                }
            }

            LocalDate dtDoacao;
            while (true) {
                try {
                    System.out.println("Nova data da doação (AAAA-MM-DD): ");
                    dtDoacao = LocalDate.parse(sc.nextLine());
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Digite uma data válida!");
                }
            }

            System.out.println("Nova descrição: ");
            String descricao = sc.nextLine();

            ld.listaDoacao().get(indice).setValor(valor);
            ld.listaDoacao().get(indice).setDtDoacao(dtDoacao);
            ld.listaDoacao().get(indice).setDescricao(descricao);
            doacaoDAO.attDoacao(ld.listaDoacao().get(indice));
            System.out.println("Doação atualizada!");
        } else {
            System.out.println("Doação não encontrada!");
        }
    }

    public void removerDoacao(Scanner sc) {
        System.out.println("===== Remover Doação =====");
        System.out.println("Digite o ID da doação que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarDoacao(ld.listaDoacao(), id);

        if (indice != -1) {
            doacaoDAO.removerDoacao(ld.listaDoacao().get(indice));
            ld.listaDoacao().remove(indice);
            System.out.println("Doação removida!");
        } else {
            System.out.println("Doação não encontrada!");
        }
    }
}

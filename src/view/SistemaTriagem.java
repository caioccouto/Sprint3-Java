package view;

import model.dao.*;
import model.repository.ListaTriagem;
import model.vo.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class SistemaTriagem {

    private final ListaTriagem lt = new ListaTriagem();
    private final TriagemDAO tDao = new TriagemDAO();

    public SistemaTriagem(){
        List<Triagem> triagems = tDao.buscarTriagem();
        for (Triagem t : triagems){
            lt.addTria(t);
        }
        Triagem.setContador(tDao.buscarMaxId() + 1);
    }

    public void addTriagem(Scanner sc) {
        System.out.println("===== Registrar Triagem =====");

        List<Beneficiario> benefs = new BeneficiarioDAO().buscarBenefs();
        List<Voluntario> vols = new VoluntarioDAO().buscarVols();

        if (benefs.isEmpty()) {
            System.out.println("Nenhum beneficiário cadastrado! Cadastre um beneficiário primeiro.");
            return;
        } else if (vols.isEmpty()) {
            System.out.println("Nenhum voluntário cadastrado! Cadastre um voluntário primeiro.");
            return;
        }

        int idBenef;
        Beneficiario benefEncontrado = null;

        while (true) {
            System.out.println("ID do Beneficiário: ");
            idBenef = sc.nextInt();
            sc.nextLine();

            for (Beneficiario b : benefs) {
                if (b.getId() == idBenef) {
                    benefEncontrado = b;
                    break;
                }
            }

            if (benefEncontrado != null) {
                break;
            } else {
                System.out.println("Beneficiário não encontrado! Tente novamente.");
            }
        }

        int idVol;
        Voluntario volEncontrado = null;

        while (true) {
            System.out.println("CRO do Voluntário: ");
            idVol = sc.nextInt();
            sc.nextLine();

            for (Voluntario v : vols) {
                if (v.getCro() == idVol) {
                    volEncontrado = v;
                    break;
                }
            }

            if (volEncontrado != null) {
                break;
            } else {
                System.out.println("Voluntário não encontrado! Tente novamente.");
            }
        }

        LocalDate dtTriagem;
        while (true) {
            try {
                System.out.println("Data da triagem (AAAA-MM-DD): ");
                dtTriagem = LocalDate.parse(sc.nextLine());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Digite uma data válida!");
            }
        }

        System.out.println("Resultado (APROVADO OU REPROVADO): ");
        String resultado = sc.nextLine().toUpperCase();

        Triagem t = new Triagem(idBenef, idVol, dtTriagem, resultado);
        lt.addTria(t);
        tDao.salvarTriagem(t);
        System.out.println("Triagem registrada com sucesso!");
    }

    public void listarTriagens() {
        if (lt.listaTriagem().isEmpty()) {
            System.out.println("Nenhuma triagem registrada!");
            return;
        }
        for (int i = 0; i < lt.listaTriagem().size(); i++) {
            System.out.println((i + 1) + ". " + lt.listaTriagem().get(i));
        }
    }

    public int buscarTriagem(List<Triagem> l, int id) {
        int indice = -1;
        for (int i = 0; i < l.size(); i++) {
            if (l.get(i).getId() == id) {
                indice = i;
            }
        }
        return indice;
    }

    public void attTriagem(Scanner sc) {
        System.out.println("===== Atualizar Triagem =====");
        System.out.println("Digite o ID da triagem que deseja atualizar: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarTriagem(lt.listaTriagem(), id);

        if (indice != -1) {

            LocalDate dtTriagem;
            while (true) {
                try {
                    System.out.println("Nova data da triagem (AAAA-MM-DD): ");
                    dtTriagem = LocalDate.parse(sc.nextLine());
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Digite uma data válida!");
                }
            }

            System.out.println("Novo resultado (APROVADO OU REPROVADO): ");
            String resultado = sc.nextLine().toUpperCase();

            lt.listaTriagem().get(indice).setDtTriagem(dtTriagem);
            lt.listaTriagem().get(indice).setResultado(resultado);
            tDao.attTriagem(lt.listaTriagem().get(indice));
            System.out.println("Triagem atualizada!");
        } else {
            System.out.println("Triagem não encontrada!");
        }
    }

    public void removerTriagem(Scanner sc) {
        System.out.println("===== Remover Triagem =====");
        System.out.println("Digite o ID da triagem que deseja remover: ");
        int id = sc.nextInt();
        sc.nextLine();
        int indice = buscarTriagem(lt.listaTriagem(), id);

        if (indice != -1) {
            tDao.removerTriagem(lt.listaTriagem().get(indice));
            lt.listaTriagem().remove(indice);
            System.out.println("Triagem removida!");
        } else {
            System.out.println("Triagem não encontrada!");
        }
    }
}

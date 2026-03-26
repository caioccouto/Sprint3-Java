package Main;

import Services.SistemaCDB;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaCDB sis = new SistemaCDB();

        while(true){
            showMenu();
            int opc = sc.nextInt();
            sc.nextLine();

            if (opc == 1){
                sis.addBenef(sc);
            } else if (opc == 2) {
                sis.addDent(sc);
            } else if (opc == 3) {
                sis.listarBenefs();
            } else if (opc == 4) {
                sis.listarDents();
            } else if (opc == 5) {

            } else if (opc == 6) {

            } else if (opc == 7) {

            } else if (opc == 8) {

            } else if (opc == 0) {
                System.out.println("Encerrando sistema...");
                break;
            }

            System.out.println("\n***** Enter para continuar *****");
            sc.nextLine();
        }

    }
    public static void showMenu(){
        System.out.println("===== Sistema CDB =====");
        System.out.println("1- Adicionar Beneficiário");
        System.out.println("2- Adicionar Dentista");
        System.out.println("3- Listar Beneficiários");
        System.out.println("4- Listar Dentistas");
        System.out.println("5- Atualizar Beneficiário");
        System.out.println("6- Atualizar Dentista");
        System.out.println("7- Remover Beneficiário");
        System.out.println("8- Remover Dentista");
        System.out.println("0- Sair");
        System.out.println("Escolha uma das opções acima: ");
    }
}

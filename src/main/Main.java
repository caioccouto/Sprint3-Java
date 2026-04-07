package main;

import view.SistemaCDB;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaCDB sis = new SistemaCDB();

        while(true){
            int opc;
            while (true){
                try{
                    showMenu();
                    opc = sc.nextInt();
                    sc.nextLine();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Digite um numero válido!");
                    sc.nextLine();
                }
            }

            if (opc == 1){
                sis.addBenef(sc);
            } else if (opc == 2) {
                sis.addDent(sc);
            } else if (opc == 3) {
                sis.listarBenefs();
            } else if (opc == 4) {
                sis.listarDents();
            } else if (opc == 5) {
                sis.attBenef(sc);
            } else if (opc == 6) {
                sis.attDent(sc);
            } else if (opc == 7) {
                sis.removerBenef(sc);
            } else if (opc == 8) {
                sis.removerDent(sc);
            } else if (opc == 0) {
                System.out.println("Encerrando sistema...");
                break;
            }

            System.out.println("\n***** Enter para continuar *****");
            sc.nextLine();
        }

    }
    public static void showMenu(){
        System.out.println("\n====================================");
        System.out.println("           SISTEMA CDB");
        System.out.println("====================================");

        System.out.println("--- CADASTROS ---");
        System.out.println(" 1 - Adicionar Beneficiário");
        System.out.println(" 2 - Adicionar Dentista");

        System.out.println("\n--- LISTAGENS ---");
        System.out.println(" 3 - Listar Beneficiários");
        System.out.println(" 4 - Listar Dentistas");

        System.out.println("\n--- ATUALIZAÇÕES ---");
        System.out.println(" 5 - Atualizar Beneficiário");
        System.out.println(" 6 - Atualizar Dentista");

        System.out.println("\n--- REMOÇÕES ---");
        System.out.println(" 7 - Remover Beneficiário");
        System.out.println(" 8 - Remover Dentista");

        System.out.println("\n 0 - Sair");
        System.out.println("====================================");
        System.out.print("Escolha uma opção: ");
    }
}

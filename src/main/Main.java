package main;

import view.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaBeneficiario sisBenef = new SistemaBeneficiario();
        SistemaDentista sisDent = new SistemaDentista();
        SistemaDoador sisDoador = new SistemaDoador();
        SistemaDoacao sisDoacao = new SistemaDoacao();
        SistemaVoluntario sisVol = new SistemaVoluntario();
        SistemaTriagem sisTriagem = new SistemaTriagem();

        while(true){
            int opc;
            while (true){
                try{
                    showMenuGeral();
                    opc = sc.nextInt();
                    sc.nextLine();
                    break;
                }catch (InputMismatchException e){
                    System.out.println("Digite um numero válido!");
                    sc.nextLine();
                }
            }

            if (opc == 1){
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisBenef.addBenef(sc);
                    }  else if (opc == 2) {
                        sisBenef.listarBenefs();
                    } else if (opc == 3) {
                        sisBenef.attBenef(sc);
                    } else if (opc == 4) {
                        sisBenef.removerBenef(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 2) {
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisDent.addDent(sc);
                    }  else if (opc == 2) {
                        sisDent.listarDents();
                    } else if (opc == 3) {
                        sisDent.attDent(sc);
                    } else if (opc == 4) {
                        sisDent.removerDent(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 3) {
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisDoador.addDoador(sc);
                    }  else if (opc == 2) {
                        sisDoador.listarDoador();
                    } else if (opc == 3) {
                        sisDoador.attDoador(sc);
                    } else if (opc == 4) {
                        sisDoador.removerDoador(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 4) {
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisDoacao.addDoacao(sc);
                    }  else if (opc == 2) {
                        sisDoacao.listarDoacoes();
                    } else if (opc == 3) {
                        sisDoacao.attDoacao(sc);
                    } else if (opc == 4) {
                        sisDoacao.removerDoacao(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 5) {
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisVol.addVol(sc);
                    }  else if (opc == 2) {
                        sisVol.listarVols();
                    } else if (opc == 3) {
                        sisVol.attVol(sc);
                    } else if (opc == 4) {
                        sisVol.removerVol(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 6) {
                while (true){
                    try{
                        showMenuOpcoes();
                        opc = sc.nextInt();
                        sc.nextLine();
                    }catch (InputMismatchException e){
                        System.out.println("Digite um numero válido!");
                        sc.nextLine();
                    }

                    if (opc == 1){
                        sisTriagem.addTriagem(sc);
                    }  else if (opc == 2) {
                        sisTriagem.listarTriagens();
                    } else if (opc == 3) {
                        sisTriagem.attTriagem(sc);
                    } else if (opc == 4) {
                        sisTriagem.removerTriagem(sc);
                    } else if (opc == 0) {
                        break;
                    }
                }
            } else if (opc == 0) {
                System.out.println("Encerrando sistema...");
                break;
            }

            System.out.println("\n***** Enter para continuar *****");
            sc.nextLine();
        }

    }
    public static void showMenuOpcoes(){
        System.out.println("\n====================================");
        System.out.println("           MENU DE OPÇÕES");
        System.out.println("====================================");
        System.out.println("1- Adicionar");
        System.out.println("2- Listar");
        System.out.println("3- Atualizar");
        System.out.println("4- Remover");
        System.out.println("0- Voltar");
        System.out.println("====================================");
        System.out.print("Escolha uma opção: ");
    }

    public static void showMenuGeral(){
        System.out.println("\n====================================");
        System.out.println("           SISTEMA CDB");
        System.out.println("====================================");
        System.out.println("1- Beneficiários");
        System.out.println("2- Dentistas");
        System.out.println("3- Doadores");
        System.out.println("4- Doações");
        System.out.println("5- Voluntários");
        System.out.println("6- Triagens");
        System.out.println("0- Sair");
        System.out.println("====================================");
        System.out.print("Escolha uma opção: ");
    }
}

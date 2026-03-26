package Main;

import Services.SistemaCDB;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SistemaCDB sys = new SistemaCDB();

        while(true){
            showMenu();
            String opc = sc.nextLine();
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

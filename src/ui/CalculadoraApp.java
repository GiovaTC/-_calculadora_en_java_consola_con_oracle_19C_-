package ui;

import servicio.CalculadoraServicio;

import java.util.Scanner;

public class CalculadoraApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CalculadoraServicio calculadora = new CalculadoraServicio();

        int opcion;

        do {

            System.out.println("\n===== CALCULADORA JAVA + ORACLE =====");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");
            System.out.println("3. Multiplicar");
            System.out.println("4. Dividir");
            System.out.println("0. Salir");

            System.out.print("seleccione opcion: ");
            opcion = sc.nextInt();

            if (opcion >= 1 && opcion <= 4) {

                System.out.print("ingrese numero 1: ");
                double n1 = sc.nextDouble();

                System.out.print("ingrese numero 2: ");
                double n2 = sc.nextDouble();

                double resultado = 0;

                switch (opcion) {
                    case 1 -> resultado = calculadora.sumar(n1, n2);
                    case 2 -> resultado = calculadora.restar(n1, n2);
                    case 3 -> resultado = calculadora.multiplicar(n1, n2);
                    case 4 -> resultado = calculadora.dividir(n1, n2);
                }

                System.out.println("Resultado: " + resultado);
            }
        } while (opcion != 0);

        System.out.println("programa finalizado .");
    }
}          
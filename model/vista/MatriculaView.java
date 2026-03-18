package model.vista;

import model.controller.MatriculaController;

import java.util.Scanner;

public class MatriculaView {
    private final Scanner sc;
    private final MatriculaController controller;

    public MatriculaView(Scanner sc, MatriculaController controller) {
        this.sc = sc;
        this.controller = controller;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- MATRÍCULA ---");
            System.out.println("1. Matricular");
            System.out.println("2. Desmatricular");
            System.out.println("3. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> matricular();
                case 2 -> desmatricular();
                case 3 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 3);
    }

    private void matricular() {
        String dniAlumno = pedir("DNI alumno: ");
        int cod = pedirInt("Código asignatura: ");
        System.out.println(controller.matricular(dniAlumno, cod) ? "Matriculado" : "No se pudo");
    }

    private void desmatricular() {
        String dniAlumno = pedir("DNI alumno: ");
        int cod = pedirInt("Código asignatura: ");
        System.out.println(controller.desmatricular(dniAlumno, cod) ? "Desmatriculado" : "No se pudo");
    }

    private String pedir(String msg) {
        while (true) {
            System.out.print(msg);
            String s = sc.nextLine().trim();
            if (!s.isEmpty())
                return s;
            System.out.println("Vacío no.");
        }
    }

    private int pedirInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ser número");
            }
        }
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Número: ");
            }
        }
    }
}
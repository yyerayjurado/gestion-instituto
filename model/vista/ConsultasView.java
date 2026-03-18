package model.vista;

import model.controller.ConsultasController;

import java.util.Scanner;

public class ConsultasView {
    private final Scanner sc;
    private final ConsultasController controller;

    public ConsultasView(Scanner sc, ConsultasController controller) {
        this.sc = sc;
        this.controller = controller;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n===== CONSULTAS =====");
            System.out.println("1. Consultas Profesores");
            System.out.println("2. Consultas Alumnos");
            System.out.println("3. Consultas Asignaturas");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> profesores();
                case 2 -> alumnos();
                case 3 -> asignaturas();
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private void profesores() {
        int op;
        do {
            System.out.println("\n--- Consultas Profesores ---");
            System.out.println("1. Ficha profesor");
            System.out.println("2. Listado profesores");
            System.out.println("3. Asignaturas de un profesor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> controller.fichaProfesor(pedir("DNI profesor: "));
                case 2 -> controller.listadoProfesores();
                case 3 -> controller.asignaturasDeProfesor(pedir("DNI profesor: "));
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private void alumnos() {
        int op;
        do {
            System.out.println("\n--- Consultas Alumnos ---");
            System.out.println("1. Ficha alumno");
            System.out.println("2. Asignaturas de un alumno con profesor");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> controller.fichaAlumno(pedir("DNI alumno: "));
                case 2 -> controller.asignaturasDeAlumnoConProfesor(pedir("DNI alumno: "));
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private void asignaturas() {
        int op;
        do {
            System.out.println("\n--- Consultas Asignaturas ---");
            System.out.println("1. Ficha asignatura");
            System.out.println("2. Nº alumnos por asignatura (ordenado)");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> controller.fichaAsignatura(pedirInt("Código: "));
                case 2 -> controller.numAlumnosPorAsignaturaOrdenado();
                case 0 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
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
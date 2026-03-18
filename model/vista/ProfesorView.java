package model.vista;

import model.controller.ProfesorController;
import model.Profesor;

import java.util.Scanner;

public class ProfesorView {
    private final Scanner sc;
    private final ProfesorController controller;

    public ProfesorView(Scanner sc, ProfesorController controller) {
        this.sc = sc;
        this.controller = controller;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN PROFESOR ---");
            System.out.println("1. Alta");
            System.out.println("2. Consultar (dni)");
            System.out.println("3. Modificar");
            System.out.println("4. Borrar");
            System.out.println("5. Volver");
            System.out.print("Opción: ");
            op = leerEntero();

            switch (op) {
                case 1 -> alta();
                case 2 -> consultar();
                case 3 -> modificar();
                case 4 -> borrar();
                case 5 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 5);
    }

    private void alta() {
        String dni = pedir("DNI: ");
        String nombre = pedir("Nombre: ");
        String apellidos = pedir("Apellidos: ");
        System.out.println(
                controller.altaProfesor(dni, nombre, apellidos) ? "Alta correcta" : "No se pudo (existe?)");
    }

    private void consultar() {
        String dni = pedir("DNI: ");
        Profesor p = controller.consultarProfesor(dni);
        System.out.println(p == null ? "No existe" : "Si existe: " + p);
    }

    private void modificar() {
        String dni = pedir("DNI: ");
        String nombre = pedir("Nuevo nombre: ");
        String apellidos = pedir("Nuevos apellidos: ");
        System.out
                .println(controller.modificarProfesor(dni, nombre, apellidos) ? "Modificado" : "No se pudo modificar");
    }

    private void borrar() {
        String dni = pedir("DNI: ");
        System.out.println(controller.borrarProfesor(dni) ? "Borrado" : "No se pudo borrar");
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
package model.vista;

import model.controller.AsignaturaController;
import model.Asignatura;

import java.util.Scanner;

public class AsignaturaView {

    private final Scanner sc;
    private final AsignaturaController controller;

    public AsignaturaView(Scanner sc, AsignaturaController controller) {
        this.sc = sc;
        this.controller = controller;
    }

    public void menu() {
        int op;
        do {
            System.out.println("\n--- GESTIÓN ASIGNATURA ---");
            System.out.println("1. Alta");
            System.out.println("2. Consultar (código)");
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
        int codigo = pedirInt("Código asignatura: ");
        String nombre = pedirTexto("Nombre: ");
        String dniProfesor = pedirTexto("DNI profesor: ");

        boolean ok = controller.altaAsignatura(codigo, nombre, dniProfesor);
        System.out.println(ok ? "✅ Alta correcta" : "❌ No se pudo (código existe o profesor no existe)");
    }

    private void consultar() {
        int codigo = pedirInt("Código asignatura: ");
        Asignatura a = controller.consultarAsignatura(codigo);

        System.out.println(a == null ? "❌ No existe" : "✅ " + a);
    }

    private void modificar() {
        int codigo = pedirInt("Código asignatura: ");
        String nombre = pedirTexto("Nuevo nombre: ");
        String dniProfesor = pedirTexto("Nuevo DNI profesor: ");

        boolean ok = controller.modificarAsignatura(codigo, nombre, dniProfesor);
        System.out.println(ok ? "✅ Modificada" : "❌ No se pudo");
    }

    private void borrar() {
        int codigo = pedirInt("Código asignatura: ");

        boolean ok = controller.borrarAsignatura(codigo);
        System.out.println(ok ? "✅ Asignatura borrada." : "❌ No se pudo borrar.");
    }

    private String pedirTexto(String msg) {
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
                System.out.println("❌ Debe ser un número.");
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
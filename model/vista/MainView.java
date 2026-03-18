package model.vista;

import model.controller.*;

import java.util.Scanner;

public class MainView {

    private final Scanner sc = new Scanner(System.in);

    private final GestionView gestionView;
    private final ConsultasView consultasView;

    public MainView(ProfesorController profesorController,
            AlumnoController alumnoController,
            AsignaturaController asignaturaController,
            MatriculaController matriculaController,
            ConsultasController consultasController) {

        this.gestionView = new GestionView(sc, profesorController, alumnoController, asignaturaController,
                matriculaController);
        this.consultasView = new ConsultasView(sc, consultasController);
    }

    public void start() {
        int opcion;
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión");
            System.out.println("2. Consultas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> gestionView.menu();
                case 2 -> consultasView.menu();
                case 3 -> System.out.println("👋 ¡Hasta luego!");
                default -> System.out.println("❌ Opción inválida.");
            }
        } while (opcion != 3);

        sc.close();
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("❌ Introduce un número válido: ");
            }
        }
    }
}
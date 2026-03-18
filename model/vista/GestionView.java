package model.vista;

import model.controller.*;

import java.util.Scanner;

public class GestionView {

    private final Scanner sc;

    private final ProfesorView profesorView;
    private final AlumnoView alumnoView;
    private final AsignaturaView asignaturaView;
    private final MatriculaView matriculaView;

    public GestionView(Scanner sc,
            ProfesorController profesorController,
            AlumnoController alumnoController,
            AsignaturaController asignaturaController,
            MatriculaController matriculaController) {

        this.sc = sc;

        // Creamos las Vistas con su Controller
        this.profesorView = new ProfesorView(sc, profesorController);
        this.alumnoView = new AlumnoView(sc, alumnoController);
        this.asignaturaView = new AsignaturaView(sc, asignaturaController);
        this.matriculaView = new MatriculaView(sc, matriculaController);
    }

    public void menu() {
        int opcion;
        do {
            System.out.println("\n===== GESTIÓN =====");
            System.out.println("1. Profesor");
            System.out.println("2. Alumno");
            System.out.println("3. Asignatura");
            System.out.println("4. Matricular / Desmatricular");
            System.out.println("5. Volver");
            System.out.print("Opción: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> profesorView.menu();
                case 2 -> alumnoView.menu();
                case 3 -> asignaturaView.menu();
                case 4 -> matriculaView.menu();
                case 5 -> {
                }
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 5);
    }

    private int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Introduce un número válido: ");
            }
        }
    }
}
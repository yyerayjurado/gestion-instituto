package model.controller;

import model.Alumno;
import model.Asignatura;
import model.Profesor;
import model.repository.CursaRepository;

import java.util.List;

public class ConsultasController {

    private final ProfesorController profesorController;
    private final AlumnoController alumnoController;
    private final AsignaturaController asignaturaController;
    private final CursaRepository cursaRepo = new CursaRepository();

    public ConsultasController(ProfesorController profesorController,
            AlumnoController alumnoController,
            AsignaturaController asignaturaController) {
        this.profesorController = profesorController;
        this.alumnoController = alumnoController;
        this.asignaturaController = asignaturaController;
    }

    // 4.1a Ficha profesor
    public void fichaProfesor(String dni) {
        Profesor p = profesorController.consultarProfesor(dni);
        System.out.println(p == null ? "No existe." : "Si existe: " + p);
    }

    // 4.1b Listado profesores
    public void listadoProfesores() {
        profesorController.listarProfesores().forEach(System.out::println);
    }

    // 4.1c Asignaturas de un profesor
    public void asignaturasDeProfesor(String dniProfesor) {
        List<String> lista = cursaRepo.asignaturasDeProfesor(dniProfesor);
        if (lista.isEmpty())
            System.out.println("⚠️ No hay asignaturas para ese profesor (o no existe).");
        else
            lista.forEach(System.out::println);
    }

    // 4.2a Ficha alumno
    public void fichaAlumno(String dni) {
        Alumno a = alumnoController.consultarAlumno(dni);
        System.out.println(a == null ? "❌ No existe." : "✅ " + a);
    }

    // 4.2b Asignaturas de un alumno con su profesor
    public void asignaturasDeAlumnoConProfesor(String dniAlumno) {
        List<String> lista = cursaRepo.asignaturasDeAlumnoConProfesor(dniAlumno);
        if (lista.isEmpty())
            System.out.println("⚠️ No tiene asignaturas matriculadas (o no existe).");
        else
            lista.forEach(System.out::println);
    }

    // 4.3a Ficha asignatura
    public void fichaAsignatura(int codigo) {
        Asignatura a = asignaturaController.consultarAsignatura(codigo);
        System.out.println(a == null ? "❌ No existe." : "✅ " + a);
    }

    // 4.3b Nº alumnos por asignatura (ordenado)
    public void numAlumnosPorAsignaturaOrdenado() {
        List<String> lista = cursaRepo.numAlumnosPorAsignaturaOrdenado();
        if (lista.isEmpty())
            System.out.println("⚠️ No hay datos.");
        else
            lista.forEach(System.out::println);
    }
}
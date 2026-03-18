package model.controller;

import model.Alumno;
import model.repository.AlumnoRepository;

import java.util.List;

public class AlumnoController {

    private final AlumnoRepository repo = new AlumnoRepository();

    public boolean altaAlumno(String dni, String nombre, String apellidos) {
        if (repo.existeAlumno(dni))
            return false;
        return repo.insertarAlumno(new Alumno(dni, nombre, apellidos));
    }

    public Alumno consultarAlumno(String dni) {
        return repo.obtenerAlumno(dni);
    }

    public boolean modificarAlumno(String dni, String nombre, String apellidos) {
        if (!repo.existeAlumno(dni))
            return false;
        return repo.modificarAlumno(new Alumno(dni, nombre, apellidos));
    }

    public boolean borrarAlumno(String dni) {
        if (!repo.existeAlumno(dni))
            return false;
        return repo.borrarAlumno(dni);
    }

    public List<Alumno> listarAlumnos() {
        return repo.dameTodosAlumnos();
    }

    public boolean existeAlumno(String dni) {
        return repo.existeAlumno(dni);
    }
}
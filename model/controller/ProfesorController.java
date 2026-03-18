package model.controller;

import model.Profesor;
import model.repository.ProfesorRepository;

import java.util.List;

public class ProfesorController {

    private final ProfesorRepository repo = new ProfesorRepository();

    public boolean altaProfesor(String dni, String nombre, String apellidos) {
        if (repo.existeProfesor(dni))
            return false;
        return repo.insertarProfesor(new Profesor(dni, nombre, apellidos));
    }

    public Profesor consultarProfesor(String dni) {
        return repo.obtenerProfesor(dni);
    }

    public boolean modificarProfesor(String dni, String nombre, String apellidos) {
        if (!repo.existeProfesor(dni))
            return false;
        return repo.modificarProfesor(new Profesor(dni, nombre, apellidos));
    }

    public boolean borrarProfesor(String dni) {
        if (!repo.existeProfesor(dni))
            return false;
        return repo.borrarProfesor(dni);
    }

    public List<Profesor> listarProfesores() {
        return repo.dameTodosProfesores();
    }

    public boolean existeProfesor(String dni) {
        return repo.existeProfesor(dni);
    }
}
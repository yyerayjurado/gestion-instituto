package model.controller;

import model.Asignatura;
import model.repository.AsignaturaRepository;

import java.util.List;

public class AsignaturaController {

    private final AsignaturaRepository repo = new AsignaturaRepository();
    private final ProfesorController profesorController;

    public AsignaturaController(ProfesorController profesorController) {
        this.profesorController = profesorController;
    }

    public boolean altaAsignatura(int codigo, String nombre, String dniProfesor) {
        if (repo.existeAsignatura(codigo))
            return false;
        if (!profesorController.existeProfesor(dniProfesor))
            return false;
        return repo.insertarAsignatura(new Asignatura(codigo, nombre, dniProfesor));
    }

    public Asignatura consultarAsignatura(int codigo) {
        return repo.obtenerAsignatura(codigo);
    }

    public boolean modificarAsignatura(int codigo, String nombre, String dniProfesor) {
        if (!repo.existeAsignatura(codigo))
            return false;
        if (!profesorController.existeProfesor(dniProfesor))
            return false;
        return repo.modificarAsignatura(new Asignatura(codigo, nombre, dniProfesor));
    }

    public boolean borrarAsignatura(int codigo) {
        if (!repo.existeAsignatura(codigo))
            return false;
        return repo.borrarAsignatura(codigo);
    }

    public List<Asignatura> listarAsignaturas() {
        return repo.dameTodasAsignaturas();
    }

    public boolean existeAsignatura(int codigo) {
        return repo.existeAsignatura(codigo);
    }
}
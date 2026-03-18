package model.controller;

import model.repository.CursaRepository;

public class MatriculaController {

    private final CursaRepository repo = new CursaRepository();
    private final AlumnoController alumnoController;
    private final AsignaturaController asignaturaController;

    public MatriculaController(AlumnoController alumnoController, AsignaturaController asignaturaController) {
        this.alumnoController = alumnoController;
        this.asignaturaController = asignaturaController;
    }

    public boolean matricular(String dniAlumno, int codAsignatura) {
        if (!alumnoController.existeAlumno(dniAlumno))
            return false;
        if (!asignaturaController.existeAsignatura(codAsignatura))
            return false;
        if (repo.existeMatricula(dniAlumno, codAsignatura))
            return false;
        return repo.matricular(dniAlumno, codAsignatura);
    }

    public boolean desmatricular(String dniAlumno, int codAsignatura) {
        if (!repo.existeMatricula(dniAlumno, codAsignatura))
            return false;
        return repo.desmatricular(dniAlumno, codAsignatura);
    }
}
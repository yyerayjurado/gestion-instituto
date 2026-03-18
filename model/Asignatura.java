package model;

public class Asignatura {
    private int codigoAsignatura;
    private String nombre;
    private String dniProfesor;

    public Asignatura(int codigoAsignatura, String nombre, String dniProfesor) {
        this.codigoAsignatura = codigoAsignatura;
        this.nombre = nombre;
        this.dniProfesor = dniProfesor;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    @Override
    public String toString() {
        return "Código: " + codigoAsignatura + " | Nombre: " + nombre + " | DNI Profesor: " + dniProfesor;
    }
}
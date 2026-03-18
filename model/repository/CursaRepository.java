package model.repository;

import model.repository.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursaRepository {

    public boolean existeMatricula(String dniAlumno, int codAsignatura) {
        String sql = "SELECT 1 FROM cursa WHERE dni_alumno = ? AND codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniAlumno);
            ps.setInt(2, codAsignatura);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean matricular(String dniAlumno, int codAsignatura) {
        String sql = "INSERT INTO cursa (dni_alumno, codigo_asignatura) VALUES (?, ?)";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniAlumno);
            ps.setInt(2, codAsignatura);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean desmatricular(String dniAlumno, int codAsignatura) {
        String sql = "DELETE FROM cursa WHERE dni_alumno = ? AND codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniAlumno);
            ps.setInt(2, codAsignatura);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // c) Listado de asignaturas de un profesor
    public List<String> asignaturasDeProfesor(String dniProfesor) {
        List<String> res = new ArrayList<>();
        String sql = """
                    SELECT codigo_asignatura, nombre
                    FROM asignatura
                    WHERE dni_profesor = ?
                """;
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniProfesor);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add("Código: " + rs.getInt("codigo_asignatura") + " | " + rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
        }
        return res;
    }

    // b) Listado de asignaturas de un alumno con su profesor
    public List<String> asignaturasDeAlumnoConProfesor(String dniAlumno) {
        List<String> res = new ArrayList<>();
        String sql = """
                    SELECT a.codigo_asignatura, a.nombre AS asignatura, p.nombre AS prof_nombre, p.apellidos AS prof_apellidos
                    FROM cursa c
                    JOIN asignatura a ON c.codigo_asignatura = a.codigo_asignatura
                    JOIN profesor p ON a.dni_profesor = p.dni
                    WHERE c.dni_alumno = ?
                """;
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dniAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add("[" + rs.getInt("codigo_asignatura") + "] " + rs.getString("asignatura")
                            + " | Profesor: " + rs.getString("prof_nombre") + " " + rs.getString("prof_apellidos"));
                }
            }
        } catch (SQLException e) {
        }
        return res;
    }

    // b) Número de alumnos matriculados por asignatura (ordenado)
    public List<String> numAlumnosPorAsignaturaOrdenado() {
        List<String> res = new ArrayList<>();
        String sql = """
                    SELECT a.codigo_asignatura, a.nombre, COUNT(c.dni_alumno) AS matriculados
                    FROM asignatura a
                    LEFT JOIN cursa c ON a.codigo_asignatura = c.codigo_asignatura
                    GROUP BY a.codigo_asignatura, a.nombre
                    ORDER BY matriculados DESC
                """;
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                res.add("[" + rs.getInt("codigo_asignatura") + "] " + rs.getString("nombre")
                        + " -> " + rs.getInt("matriculados") + " matriculados");
            }
        } catch (SQLException e) {
        }
        return res;
    }
}
package model.repository;

import model.Alumno;
import model.repository.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoRepository {

    public boolean existeAlumno(String dni) {
        String sql = "SELECT 1 FROM alumno WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean insertarAlumno(Alumno a) {
        String sql = "INSERT INTO alumno (dni, nombre, apellidos) VALUES (?, ?, ?)";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getDni());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getApellidos());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Alumno obtenerAlumno(String dni) {
        String sql = "SELECT * FROM alumno WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Alumno(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"));
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean modificarAlumno(Alumno a) {
        String sql = "UPDATE alumno SET nombre = ?, apellidos = ? WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getDni());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean borrarAlumno(String dni) {
        String sql = "DELETE FROM alumno WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Alumno> dameTodosAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Alumno(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos")));
            }
        } catch (SQLException e) {
        }
        return lista;
    }
}
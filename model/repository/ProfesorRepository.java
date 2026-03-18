package model.repository;

import model.Profesor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorRepository {

    public boolean existeProfesor(String dni) {
        String sql = "SELECT 1 FROM profesor WHERE dni = ?";
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

    public boolean insertarProfesor(Profesor p) {
        String sql = "INSERT INTO profesor (dni, nombre, apellidos) VALUES (?, ?, ?)";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDni());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getApellidos());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Profesor obtenerProfesor(String dni) {
        String sql = "SELECT * FROM profesor WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Profesor(
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"));
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean modificarProfesor(Profesor p) {
        String sql = "UPDATE profesor SET nombre = ?, apellidos = ? WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getApellidos());
            ps.setString(3, p.getDni());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean borrarProfesor(String dni) {
        String sql = "DELETE FROM profesor WHERE dni = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Profesor> dameTodosProfesores() {
        List<Profesor> lista = new ArrayList<>();
        String sql = "SELECT * FROM profesor";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Profesor(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos")));
            }
        } catch (SQLException e) {
        }
        return lista;
    }
}
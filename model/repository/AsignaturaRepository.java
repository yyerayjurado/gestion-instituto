package model.repository;

import model.Asignatura;
import model.repository.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsignaturaRepository {

    public boolean existeAsignatura(int codigo) {
        String sql = "SELECT 1 FROM asignatura WHERE codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean insertarAsignatura(Asignatura a) {
        String sql = "INSERT INTO asignatura (codigo_asignatura, nombre, dni_profesor) VALUES (?, ?, ?)";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, a.getCodigoAsignatura());
            ps.setString(2, a.getNombre());
            ps.setString(3, a.getDniProfesor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public Asignatura obtenerAsignatura(int codigo) {
        String sql = "SELECT * FROM asignatura WHERE codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Asignatura(
                            rs.getInt("codigo_asignatura"),
                            rs.getString("nombre"),
                            rs.getString("dni_profesor"));
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public boolean modificarAsignatura(Asignatura a) {
        String sql = "UPDATE asignatura SET nombre = ?, dni_profesor = ? WHERE codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, a.getNombre());
            ps.setString(2, a.getDniProfesor());
            ps.setInt(3, a.getCodigoAsignatura());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean borrarAsignatura(int codigo) {
        String sql = "DELETE FROM asignatura WHERE codigo_asignatura = ?";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<Asignatura> dameTodasAsignaturas() {
        List<Asignatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM asignatura";
        try (Connection con = dbConexion.conectar();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Asignatura(
                        rs.getInt("codigo_asignatura"),
                        rs.getString("nombre"),
                        rs.getString("dni_profesor")));
            }
        } catch (SQLException e) {
        }
        return lista;
    }
}
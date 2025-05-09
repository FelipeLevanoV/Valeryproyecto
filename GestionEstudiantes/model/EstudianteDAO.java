package model;

import database.DBConnection;
import java.sql.*;
import java.util.*;

public class EstudianteDAO {

    public void insertar(Estudiante est) throws SQLException {
        String sql = "INSERT INTO estudiantes(nombre, apellido, correo, estado) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getApellido());
            ps.setString(3, est.getCorreo());
            ps.setBoolean(4, est.isEstado());
            ps.executeUpdate();
        }
    }

    public void modificar(Estudiante est) throws SQLException {
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, correo=?, estado=? WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, est.getNombre());
            ps.setString(2, est.getApellido());
            ps.setString(3, est.getCorreo());
            ps.setBoolean(4, est.isEstado());
            ps.setInt(5, est.getId());
            ps.executeUpdate();
        }
    }

    public void eliminarLogico(int id) throws SQLException {
        String sql = "UPDATE estudiantes SET estado = 0 WHERE id=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Estudiante> listar(boolean activos) throws SQLException {
        List<Estudiante> lista = new ArrayList<>();
        String sql = "SELECT * FROM estudiantes WHERE estado=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setBoolean(1, activos);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Estudiante est = new Estudiante();
                est.setId(rs.getInt("id"));
                est.setNombre(rs.getString("nombre"));
                est.setApellido(rs.getString("apellido"));
                est.setCorreo(rs.getString("correo"));
                est.setEstado(rs.getBoolean("estado"));
                lista.add(est);
            }
        }
        return lista;
    }
}
package dao.implementacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Direccion;
import util.ConexionBD;

public class DireccionDAOImplementacion {

    private Connection conexion;

    public DireccionDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    public boolean agregarDireccion(Direccion direccion) {
        String sql = "INSERT INTO direccion (calle, numero, ciudad, codigo_postal, estado) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getNumero());
            ps.setString(3, direccion.getCiudad());
            ps.setString(4, direccion.getCodigoPostal());
            ps.setString(5, direccion.getEstado());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar dirección falló, ninguna fila afectada.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    direccion.setIdDireccion(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Agregar dirección falló, no se obtuvo el ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarDireccion(Direccion direccion) {
        String sql = "UPDATE direccion SET calle = ?, numero = ?, ciudad = ?, codigo_postal = ?, estado = ? WHERE id_direccion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, direccion.getCalle());
            ps.setString(2, direccion.getNumero());
            ps.setString(3, direccion.getCiudad());
            ps.setString(4, direccion.getCodigoPostal());
            ps.setString(5, direccion.getEstado());
            ps.setInt(6, direccion.getIdDireccion());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminarDireccion(int idDireccion) {
        String sql = "DELETE FROM direccion WHERE id_direccion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDireccion);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Direccion obtenerDireccionPorId(int idDireccion) {
        String sql = "SELECT * FROM direccion WHERE id_direccion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDireccion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Direccion(
                            rs.getInt("id_direccion"),
                            rs.getString("calle"),
                            rs.getString("numero"),
                            rs.getString("ciudad"),
                            rs.getString("codigo_postal"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Direccion> obtenerTodasLasDirecciones() {
        List<Direccion> direcciones = new ArrayList<>();
        String sql = "SELECT * FROM direccion";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Direccion direccion = new Direccion(
                        rs.getInt("id_direccion"),
                        rs.getString("calle"),
                        rs.getString("numero"),
                        rs.getString("ciudad"),
                        rs.getString("codigo_postal"),
                        rs.getString("estado")
                );
                direcciones.add(direccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return direcciones;
    }
}

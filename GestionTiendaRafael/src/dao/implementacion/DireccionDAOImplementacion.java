package dao.implementacion;

import dao.interfaces.DireccionDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Direccion;
import util.ConexionBD;

public class DireccionDAOImplementacion implements DireccionDAO {

    private final Connection conexion;

    public DireccionDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public void agregarDireccion(Direccion direccion) throws Exception {
        String sqlDireccion = "INSERT INTO direccion (calle, numero, ciudad, codigo_postal, estado) VALUES (?, ?, ?, ?, ?) RETURNING id_direccion";
        try (PreparedStatement stmtDireccion = conexion.prepareStatement(sqlDireccion)) {
            stmtDireccion.setString(1, direccion.getCalle());
            stmtDireccion.setString(2, direccion.getNumero());
            stmtDireccion.setString(3, direccion.getCiudad());
            stmtDireccion.setString(4, direccion.getCodigoPostal());
            stmtDireccion.setString(5, direccion.getEstado());

            ResultSet rs = stmtDireccion.executeQuery();
            if (rs.next()) {
                int direccionId = rs.getInt("id_direccion");
                direccion.setIdDireccion(direccionId); // Asignar el ID al objeto Dirección
            } else {
                throw new Exception("Error al obtener el ID de la dirección recién insertada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al agregar la dirección: " + e.getMessage());
        }
    }

    @Override
    public boolean actualizarDireccion(Direccion direccion) throws Exception {
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
            throw new Exception("Error al actualizar la dirección: " + e.getMessage());
        }
    }

    @Override
    public boolean eliminarDireccion(int idDireccion) throws Exception {
        String sql = "DELETE FROM direccion WHERE id_direccion = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idDireccion);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar la dirección: " + e.getMessage());
        }
    }

    @Override
    public Direccion obtenerDireccionPorId(int idDireccion) throws Exception {
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
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener la dirección por ID: " + e.getMessage());
        }
    }

    @Override
    public List<Direccion> obtenerTodasLasDirecciones() throws Exception {
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
            return direcciones;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Error al obtener todas las direcciones: " + e.getMessage());
        }
    }
}

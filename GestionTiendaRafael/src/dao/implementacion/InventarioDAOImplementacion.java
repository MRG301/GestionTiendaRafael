package dao.implementacion;

import dao.interfaces.InventarioDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Inventario;
import util.ConexionBD;

public class InventarioDAOImplementacion implements InventarioDAO {

    private Connection conexion;

    public InventarioDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public boolean agregarInventario(Inventario inventario) {
        String sql = "INSERT INTO inventario (id_producto, stock_actual) VALUES (?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, inventario.getIdProducto());
            ps.setInt(2, inventario.getStockActual());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar inventario falló, ninguna fila afectada.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inventario.setIdInventario(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Agregar inventario falló, no se obtuvo el ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarInventario(Inventario inventario) {
        String sql = "UPDATE inventario SET stock_actual = ? WHERE id_inventario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, inventario.getStockActual());
            ps.setInt(2, inventario.getIdInventario());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarInventario(int idInventario) {
        String sql = "DELETE FROM inventario WHERE id_inventario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idInventario);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Inventario obtenerInventarioPorId(int idInventario) {
        String sql = "SELECT * FROM inventario WHERE id_inventario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idInventario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Inventario(
                            rs.getInt("id_inventario"),
                            rs.getLong("id_producto"),
                            rs.getInt("stock_actual")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inventario> obtenerTodosLosInventarios() {
        List<Inventario> inventarios = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inventario inventario = new Inventario(
                        rs.getInt("id_inventario"),
                        rs.getLong("id_producto"),
                        rs.getInt("stock_actual")
                );
                inventarios.add(inventario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }

    @Override
    public Inventario obtenerInventarioPorProducto(long idProducto) {
        String sql = "SELECT * FROM inventario WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Inventario(
                            rs.getInt("id_inventario"),
                            rs.getLong("id_producto"),
                            rs.getInt("stock_actual")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

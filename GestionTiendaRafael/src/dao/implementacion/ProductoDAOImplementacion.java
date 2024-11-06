package dao.implementacion;

import dao.interfaces.ProductoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.entidades.Producto;
import modelo.enums.CategoriaTienda;
import util.ConexionBD;

public class ProductoDAOImplementacion implements ProductoDAO {

   private Connection conexion;

    public ProductoDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }
 
    @Override
    public boolean agregarProducto(Producto producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, categoria) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getCategoria().name()); // Almacena el enum como String
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar producto falló, ninguna fila afectada.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setIdProducto(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Agregar producto falló, no se obtuvo el ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?, categoria = ? WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecio());
            ps.setInt(4, producto.getStock());
            ps.setString(5, producto.getCategoria().name());
            ps.setLong(6, producto.getIdProducto());
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public boolean eliminarProducto(long idProducto) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idProducto);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return false;
    }

    @Override
    public Producto obtenerProductoPorId(long idProducto) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Producto(
                            rs.getLong("id_producto"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            CategoriaTienda.valueOf(rs.getString("categoria"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return null;
    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getLong("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock"),
                        CategoriaTienda.valueOf(rs.getString("categoria"))
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return productos;
    }

    @Override
    public List<Producto> obtenerProductosPorCategoria(CategoriaTienda categoria) {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE categoria = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, categoria.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getLong("id_producto"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio"),
                            rs.getInt("stock"),
                            CategoriaTienda.valueOf(rs.getString("categoria"))
                    );
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return productos;
    }
}

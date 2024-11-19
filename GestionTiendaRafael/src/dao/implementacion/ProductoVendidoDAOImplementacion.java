package dao.implementacion;

import dao.interfaces.ProductoDAO;
import dao.interfaces.ProductoVendidoDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Producto;
import modelo.entidades.ProductoVendido;
import util.ConexionBD;

public class ProductoVendidoDAOImplementacion implements ProductoVendidoDAO {

    private Connection conexion;
    private ProductoDAO productoDAO;

    public ProductoVendidoDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public boolean agregarProductoVendido(ProductoVendido productoVendido) {
        String sql = "INSERT INTO producto_vendido (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, productoVendido.getIdVenta());
            ps.setLong(2, productoVendido.getIdProducto());
            ps.setInt(3, productoVendido.getCantidad());
            ps.setDouble(4, productoVendido.getPrecioUnitario());
            ps.setDouble(5, productoVendido.getSubtotal());
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar producto vendido falló, ninguna fila afectada.");
            }
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    productoVendido.setIdProductoVendido(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Agregar producto vendido falló, no se obtuvo el ID.");
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarProductoVendido(long idProductoVendido) {
        String sql = "DELETE FROM producto_vendido WHERE id_producto_vendido = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idProductoVendido);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductoVendido obtenerProductoVendidoPorId(long idProductoVendido) {
        productoDAO = new ProductoDAOImplementacion();
        ProductoVendido pv = null;
        String sql = "SELECT * FROM producto_vendido WHERE id_producto_vendido = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idProductoVendido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long idVenta = rs.getLong("id_venta");
                    long idProducto = rs.getLong("id_producto");
                    int cantidad = rs.getInt("cantidad");
                    double precioUnitario = rs.getDouble("precio_unitario");
                    double subtotal = rs.getDouble("subtotal");

                    // Obtener el objeto Producto correspondiente
                    Producto producto = productoDAO.obtenerProductoPorId(idProducto);

                    // Usar el constructor completo
                    pv = new ProductoVendido(
                            rs.getLong("id_producto_vendido"),
                            idVenta,
                            idProducto,
                            cantidad,
                            precioUnitario,
                            subtotal,
                            producto
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return pv;
    }

    @Override
    public List<ProductoVendido> obtenerProductosVendidosPorVenta(long idVenta) {
        List<ProductoVendido> productosVendidos = new ArrayList<>();
        String sql = "SELECT * FROM producto_vendido WHERE id_venta = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setLong(1, idVenta);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    long idProductoVendido = rs.getLong("id_producto_vendido");
                    long idProducto = rs.getLong("id_producto");
                    int cantidad = rs.getInt("cantidad");
                    double precioUnitario = rs.getDouble("precio_unitario");
                    double subtotal = rs.getDouble("subtotal");

                    // Obtener el objeto Producto correspondiente
                    Producto producto = productoDAO.obtenerProductoPorId(idProducto);

                    // Verificar que el producto no sea nulo
                    if (producto != null) {
                        ProductoVendido pv = new ProductoVendido(
                                idProductoVendido,
                                idVenta,
                                idProducto,
                                cantidad,
                                precioUnitario,
                                subtotal,
                                producto
                        );
                        productosVendidos.add(pv);
                    } else {
                        // Manejar el caso en que el producto no existe
                        System.err.println("Producto con ID " + idProducto + " no encontrado.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging más robusto
        }
        return productosVendidos;
    }
}

package dao.implementacion;

import dao.interfaces.ProductoDAO;
import dao.interfaces.VentaDAO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.entidades.Producto;
import modelo.entidades.ProductoVendido;
import modelo.entidades.Venta;
import util.ConexionBD;

public class VentaDAOImplementacion implements VentaDAO {

    private final Connection conexion;
    private ProductoDAO productoDAO;

    public VentaDAOImplementacion() {
        this.conexion = ConexionBD.getInstance().getConexion();
    }

    @Override
    public boolean guardarVenta(Venta venta) throws Exception {
        String sqlVenta = "INSERT INTO venta (fecha, total, id_vendedor, id_cliente) VALUES (?, ?, ?, ?) RETURNING id_venta";
        String sqlProductoVendido = "INSERT INTO producto_vendido (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try {
            conexion.setAutoCommit(false);

            // Insertar la venta
            long idVenta;
            try (PreparedStatement psVenta = conexion.prepareStatement(sqlVenta)) {
                psVenta.setDate(1, Date.valueOf(venta.getFecha()));
                psVenta.setDouble(2, venta.getTotal());
                psVenta.setLong(3, venta.getIdVendedor());
                psVenta.setLong(4, venta.getIdCliente());

                ResultSet rs = psVenta.executeQuery();
                if (rs.next()) {
                    idVenta = rs.getLong(1);
                    venta.setIdVenta(idVenta);
                } else {
                    throw new SQLException("Agregar venta falló, no se obtuvo el ID.");
                }
            }

            // Insertar los productos vendidos
            try (PreparedStatement psProductoVendido = conexion.prepareStatement(sqlProductoVendido)) {
                for (ProductoVendido pv : venta.getProductosVendidos()) {
                    psProductoVendido.setLong(1, venta.getIdVenta());
                    psProductoVendido.setLong(2, pv.getIdProducto());
                    psProductoVendido.setInt(3, pv.getCantidad());
                    psProductoVendido.setDouble(4, pv.getPrecioUnitario());
                    psProductoVendido.setDouble(5, pv.getSubtotal());
                    psProductoVendido.addBatch();
                }
                psProductoVendido.executeBatch();
            }

            // Confirmar la transacción
            conexion.commit();
            return true;
        } catch (SQLException e) {
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                throw new Exception("Error al hacer rollback: " + ex.getMessage());
            }
            throw new Exception("Error al guardar la venta: " + e.getMessage());
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                throw new Exception("Error al restablecer autoCommit: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean agregarVenta(Venta venta) {
        String sqlVenta = "INSERT INTO venta (fecha, total, id_vendedor, id_cliente) VALUES (?, ?, ?, ?) RETURNING id_venta";
        String sqlProductoVendido = "INSERT INTO producto_vendido (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            conexion.setAutoCommit(false);

            // Insertar venta
            PreparedStatement psVenta = conexion.prepareStatement(sqlVenta, Statement.RETURN_GENERATED_KEYS);
            psVenta.setDate(1, Date.valueOf(venta.getFecha())); // Convertir LocalDate a java.sql.Date
            psVenta.setDouble(2, venta.getTotal());
            psVenta.setLong(3, venta.getIdVendedor());
            psVenta.setLong(4, venta.getIdCliente());
            int filasAfectadas = psVenta.executeUpdate();
            if (filasAfectadas == 0) {
                throw new SQLException("Agregar venta falló, ninguna fila afectada.");
            }

            // Obtener idVenta generado
            long idVenta = 0;
            try (ResultSet generatedKeys = psVenta.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idVenta = generatedKeys.getLong(1);
                    venta.setIdVenta(idVenta);
                } else {
                    throw new SQLException("Agregar venta falló, no se obtuvo el ID.");
                }
            }

            // Insertar productos vendidos
            for (ProductoVendido productoVendido : venta.getProductosVendidos()) {
                PreparedStatement psProductoVendido = conexion.prepareStatement(sqlProductoVendido);
                psProductoVendido.setLong(1, idVenta);
                psProductoVendido.setLong(2, productoVendido.getIdProducto());
                psProductoVendido.setInt(3, productoVendido.getCantidad());
                psProductoVendido.setDouble(4, productoVendido.getPrecioUnitario());
                psProductoVendido.setDouble(5, productoVendido.getSubtotal());
                psProductoVendido.executeUpdate();
            }

            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Considera usar un sistema de logging
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace(); // Considera usar un sistema de logging
            }
        }
        return false;
    }

    @Override
    public boolean actualizarVenta(Venta venta) {
        String sqlActualizarVenta = "UPDATE venta SET fecha = ?, total = ?, id_vendedor = ?, id_cliente = ? WHERE id_venta = ?";
        String sqlEliminarProductosVendidos = "DELETE FROM producto_vendido WHERE id_venta = ?";
        String sqlAgregarProductoVendido = "INSERT INTO producto_vendido (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try {
            conexion.setAutoCommit(false);

            // Actualizar venta
            PreparedStatement psActualizarVenta = conexion.prepareStatement(sqlActualizarVenta);
            psActualizarVenta.setDate(1, Date.valueOf(venta.getFecha()));
            psActualizarVenta.setDouble(2, venta.getTotal());
            psActualizarVenta.setLong(3, venta.getIdVendedor());
            psActualizarVenta.setLong(4, venta.getIdCliente());
            psActualizarVenta.setLong(5, venta.getIdVenta());
            psActualizarVenta.executeUpdate();

            // Eliminar productos vendidos existentes
            PreparedStatement psEliminarProductos = conexion.prepareStatement(sqlEliminarProductosVendidos);
            psEliminarProductos.setLong(1, venta.getIdVenta());
            psEliminarProductos.executeUpdate();

            // Insertar nuevos productos vendidos
            for (ProductoVendido productoVendido : venta.getProductosVendidos()) {
                PreparedStatement psAgregarPV = conexion.prepareStatement(sqlAgregarProductoVendido);
                psAgregarPV.setLong(1, venta.getIdVenta());
                psAgregarPV.setLong(2, productoVendido.getIdProducto());
                psAgregarPV.setInt(3, productoVendido.getCantidad());
                psAgregarPV.setDouble(4, productoVendido.getPrecioUnitario());
                psAgregarPV.setDouble(5, productoVendido.getSubtotal());
                psAgregarPV.executeUpdate();
            }

            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Considera usar un sistema de logging
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace(); // Considera usar un sistema de logging
            }
        }
        return false;
    }

    @Override
    public boolean eliminarVenta(long idVenta) {
        String sqlEliminarProductosVendidos = "DELETE FROM producto_vendido WHERE id_venta = ?";
        String sqlEliminarVenta = "DELETE FROM venta WHERE id_venta = ?";
        try {
            conexion.setAutoCommit(false);

            // Eliminar productos vendidos asociados
            PreparedStatement psEliminarPV = conexion.prepareStatement(sqlEliminarProductosVendidos);
            psEliminarPV.setLong(1, idVenta);
            psEliminarPV.executeUpdate();

            // Eliminar venta
            PreparedStatement psEliminarVenta = conexion.prepareStatement(sqlEliminarVenta);
            psEliminarVenta.setLong(1, idVenta);
            psEliminarVenta.executeUpdate();

            conexion.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
            try {
                conexion.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace(); // Considera usar un sistema de logging
            }
        } finally {
            try {
                conexion.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace(); // Considera usar un sistema de logging
            }
        }
        return false;
    }

    @Override
    public Venta obtenerVentaPorId(long idVenta) {
        String sqlVenta = "SELECT * FROM venta WHERE id_venta = ?";
        String sqlProductosVendidos = "SELECT * FROM producto_vendido WHERE id_venta = ?";
        try (PreparedStatement psVenta = conexion.prepareStatement(sqlVenta)) {
            psVenta.setLong(1, idVenta);
            try (ResultSet rsVenta = psVenta.executeQuery()) {
                if (rsVenta.next()) {
                    Venta venta = new Venta(
                            rsVenta.getLong("id_venta"),
                            rsVenta.getDate("fecha").toLocalDate(), // Convertir java.sql.Date a LocalDate
                            rsVenta.getDouble("total"),
                            rsVenta.getLong("id_vendedor"),
                            rsVenta.getLong("id_cliente"),
                            new ArrayList<>()
                    );

                    // Obtener productos vendidos
                    PreparedStatement psProductos = conexion.prepareStatement(sqlProductosVendidos);
                    psProductos.setLong(1, idVenta);
                    try (ResultSet rsProductos = psProductos.executeQuery()) {
                        while (rsProductos.next()) {
                            Producto producto = productoDAO.obtenerProductoPorId(rsProductos.getLong("id_producto"));
                            ProductoVendido pv = new ProductoVendido(
                                    rsProductos.getLong("id_producto_vendido"),
                                    rsProductos.getLong("id_venta"),
                                    rsProductos.getLong("id_producto"),
                                    rsProductos.getInt("cantidad"),
                                    rsProductos.getDouble("precio_unitario"),
                                    rsProductos.getDouble("subtotal"),
                                    producto
                            );
                        }
                    }
                    return venta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        }
        return null;
    }

    @Override
    public List<Venta> obtenerTodasLasVentas() {
        productoDAO = new ProductoDAOImplementacion();
        List<Venta> ventas = new ArrayList<>();
        String sqlVenta = "SELECT * FROM venta";
        try (Statement stmt = conexion.createStatement(); ResultSet rsVenta = stmt.executeQuery(sqlVenta)) {
            while (rsVenta.next()) {
                Venta venta = new Venta(
                        rsVenta.getLong("id_venta"),
                        rsVenta.getDate("fecha").toLocalDate(),
                        rsVenta.getDouble("total"),
                        rsVenta.getLong("id_vendedor"),
                        rsVenta.getLong("id_cliente"),
                        new ArrayList<>()
                );

                // Obtener productos vendidos para cada venta
                String sqlProductosVendidos = "SELECT * FROM producto_vendido WHERE id_venta = ?";
                try (PreparedStatement psProductos = conexion.prepareStatement(sqlProductosVendidos)) {
                    psProductos.setLong(1, venta.getIdVenta());
                    try (ResultSet rsProductos = psProductos.executeQuery()) {
                        while (rsProductos.next()) {
                            Producto producto = productoDAO.obtenerProductoPorId(rsProductos.getLong("id_producto"));
                            ProductoVendido pv = new ProductoVendido(
                                    rsProductos.getLong("id_producto_vendido"),
                                    rsProductos.getLong("id_venta"),
                                    rsProductos.getLong("id_producto"),
                                    rsProductos.getInt("cantidad"),
                                    rsProductos.getDouble("precio_unitario"),
                                    rsProductos.getDouble("subtotal"),
                                    producto
                            );
                            venta.getProductosVendidos().add(pv);
                        }
                    }
                }

                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        } catch (Exception e) {
            e.printStackTrace(); // Maneja otras excepciones potenciales
        }
        return ventas;
    }

    @Override
    public List<Venta> obtenerVentasPorFecha(LocalDate fecha) {
        List<Venta> ventas = new ArrayList<>();
        String sqlVenta = "SELECT * FROM venta WHERE fecha = ?";
        try (PreparedStatement psVenta = conexion.prepareStatement(sqlVenta)) {
            psVenta.setDate(1, Date.valueOf(fecha));
            try (ResultSet rsVenta = psVenta.executeQuery()) {
                while (rsVenta.next()) {
                    Venta venta = new Venta(
                            rsVenta.getLong("id_venta"),
                            rsVenta.getDate("fecha").toLocalDate(),
                            rsVenta.getDouble("total"),
                            rsVenta.getLong("id_vendedor"),
                            rsVenta.getLong("id_cliente"),
                            new ArrayList<>()
                    );

                    // Obtener productos vendidos
                    String sqlProductosVendidos = "SELECT * FROM producto_vendido WHERE id_venta = ?";
                    try (PreparedStatement psProductos = conexion.prepareStatement(sqlProductosVendidos)) {
                        psProductos.setLong(1, venta.getIdVenta());
                        try (ResultSet rsProductos = psProductos.executeQuery()) {
                            while (rsProductos.next()) {
                                Producto producto = productoDAO.obtenerProductoPorId(rsProductos.getLong("id_producto"));
                                ProductoVendido pv = new ProductoVendido(
                                        rsProductos.getLong("id_producto_vendido"),
                                        rsProductos.getLong("id_venta"),
                                        rsProductos.getLong("id_producto"),
                                        rsProductos.getInt("cantidad"),
                                        rsProductos.getDouble("precio_unitario"),
                                        rsProductos.getDouble("subtotal"),
                                        producto
                                );
                                venta.getProductosVendidos().add(pv);
                            }
                        }
                    }

                    ventas.add(venta);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Considera usar un sistema de logging
        } catch (Exception e) {
            e.printStackTrace(); // Maneja otras excepciones potenciales
        }
        return ventas;
    }
}

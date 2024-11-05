package modelo.entidades;

import java.time.LocalDate;
import java.util.List;

public class Venta {

    private int idVenta;
    private LocalDate fecha;
    private double total;
    private int idVendedor; // Cambiado de idUsuario a idVendedor
    private int idCliente;

    private List<ProductoVendido> productosVendidos;

    public Venta(long idVenta, LocalDate fecha, double total, long idVendedor, long idCliente, List<ProductoVendido> productosVendidos) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.productosVendidos = productosVendidos;
    }

    public Venta(Date fecha, double total, int idVendedor, int idCliente, List<ProductoVendido> productosVendidos) {
        this.fecha = fecha;
        this.total = total;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.productosVendidos = productosVendidos;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public List<ProductoVendido> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

}

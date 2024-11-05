package modelo.entidades;

import java.time.LocalDate;
import java.util.List;

public class Venta {

    private long idVenta;
    private LocalDate fecha;
    private double total;
    private long idVendedor;
    private long idCliente;

    private List<ProductoVendido> productosVendidos;

    public Venta(long idVenta, LocalDate fecha, double total, long idVendedor, long idCliente, List<ProductoVendido> productosVendidos) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.productosVendidos = productosVendidos;
    }

    public Venta(LocalDate fecha, double total, long idVendedor, long idCliente, List<ProductoVendido> productosVendidos) {
        this.fecha = fecha;
        this.total = total;
        this.idVendedor = idVendedor;
        this.idCliente = idCliente;
        this.productosVendidos = productosVendidos;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(long idVendedor) {
        this.idVendedor = idVendedor;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public List<ProductoVendido> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(List<ProductoVendido> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

}

package modelo.entidades;

public class ProductoVendido {

    private long idProductoVendido;
    private long idVenta;
    private long idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ProductoVendido(long idProductoVendido, long idVenta, long idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idProductoVendido = idProductoVendido;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // Constructor sin ID (para inserciones donde el ID se genera automáticamente)
    public ProductoVendido(long idVenta, long idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public long getIdProductoVendido() {
        return idProductoVendido;
    }

    public void setIdProductoVendido(long idProductoVendido) {
        this.idProductoVendido = idProductoVendido;
    }

    public long getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(long idVenta) {
        this.idVenta = idVenta;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}

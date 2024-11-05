package modelo.entidades;

public class ProductoVendido {

    private int idProductoVendido;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ProductoVendido(int idProductoVendido, int idVenta, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idProductoVendido = idProductoVendido;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    // Constructor sin ID (para inserciones donde el ID se genera automáticamente)
    public ProductoVendido(int idVenta, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdProductoVendido() {
        return idProductoVendido;
    }

    public void setIdProductoVendido(int idProductoVendido) {
        this.idProductoVendido = idProductoVendido;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
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

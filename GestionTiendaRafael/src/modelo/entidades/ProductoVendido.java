package modelo.entidades;

public class ProductoVendido {

    private long idProductoVendido;
    private long idVenta;
    private long idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private Producto producto;

    // Constructor completo
    public ProductoVendido(long idProductoVendido, long idVenta, long idProducto, int cantidad, double precioUnitario, double subtotal, Producto producto) {
        this.idProductoVendido = idProductoVendido;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    // Constructor simplificado
    public ProductoVendido(long idVenta, Producto producto, int cantidad) {
        this.idVenta = idVenta;
        this.idProducto = producto.getIdProducto();
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
        this.subtotal = cantidad * precioUnitario;
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}

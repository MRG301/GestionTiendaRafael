package modelo.entidades;

public class Inventario {

    private int idInventario;
    private long idProducto;
    private int stockActual;

    public Inventario(int idInventario, long idProducto, int stockActual) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.stockActual = stockActual;
    }

    public Inventario(long idProducto, int stockActual) {
        this.idProducto = idProducto;
        this.stockActual = stockActual;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

}

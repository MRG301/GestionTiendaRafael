package modelo.entidades;

import modelo.enums.CategoriaTienda;

public class Producto {

    private long idProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private CategoriaTienda categoria;

    public Producto(long idProducto, String nombre, String descripcion, double precio, int stock, CategoriaTienda categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public Producto(String nombre, String descripcion, double precio, int stock, CategoriaTienda categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public CategoriaTienda getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTienda categoria) {
        this.categoria = categoria;
    }

}

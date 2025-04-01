package model;

import java.time.LocalDate;

public class Pedido {
    private int id;
    private Inventario producto;
    private Proveedor proveedor;
    private int cantidad;
    private double precio;
    private LocalDate fechaPedido;

    
    public Pedido() {
    }

    
    public Pedido(int id, Inventario producto, Proveedor proveedor, int cantidad, double precio, LocalDate fechaPedido) {
        this.id = id;
        this.producto = producto;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaPedido = fechaPedido;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Inventario getProducto() {
        return producto;
    }

    public void setProducto(Inventario producto) {
        this.producto = producto;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    // Método para crear un nuevo pedido
    public static Pedido crearPedido(int id, Inventario producto, Proveedor proveedor, int cantidad, double precio) {
        return new Pedido(id, producto, proveedor, cantidad, precio, LocalDate.now());
    }

    
    @Override
    public String toString() {
        return "Pedido [id=" + id + ", producto=" + producto + ", proveedor=" + proveedor + ", cantidad=" + cantidad
                + ", precio=" + precio + ", fechaPedido=" + fechaPedido + "]";
    }
}

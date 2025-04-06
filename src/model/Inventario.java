package model;

public class Inventario {
    private int id;
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor vacío (opcional, pero recomendable)
    public Inventario() {
    }
    


    public Inventario(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }



    // Constructor con todos los parámetros
    public Inventario(int id, String nombre, int cantidad, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Inventario(int idProducto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    @Override
public String toString() {
	return "Inventario [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio + "]";
}
}

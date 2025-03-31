package model;

public class Servicio {
private int id;
private String nombre;
private String descripción;
private double precio;
public Servicio() {
	super();
}
public Servicio(int id, String nombre, String descripción, double precio) {
	super();
	this.id = id;
	this.nombre = nombre;
	this.descripción = descripción;
	this.precio = precio;
}
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
public String getDescripción() {
	return descripción;
}
public void setDescripción(String descripción) {
	this.descripción = descripción;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
@Override
public String toString() {
	return "Servicio [id=" + id + ", nombre=" + nombre + ", descripción=" + descripción + ", precio=" + precio + "]";
}

}

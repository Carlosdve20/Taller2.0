package model;

public class Empleado {
private int id;
private String nombre;
private String apellido;
private String puesto;
private double salario;
public Empleado() {
	
}
public Empleado(int id, String nombre, String apellido, String puesto, double salario) {
	
	this.id = id;
	this.nombre = nombre;
	this.apellido = apellido;
	this.puesto = puesto;
	this.salario = salario;
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
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getPuesto() {
	return puesto;
}
public void setPuesto(String puesto) {
	this.puesto = puesto;
}
public double getSalario() {
	return salario;
}
public void setSalario(double salario) {
	this.salario = salario;
}
@Override
public String toString() {
	return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", puesto=" + puesto + ", salario="
			+ salario + "]";
}

}

package model;

import java.time.LocalDate;
import java.util.List;

public class Reparacion {
private int id;
private Cliente cliente;
private Vehiculo vehiculo;
private Empleado empleado;
private LocalDate fechaEntrada;
private LocalDate fechaSalida;
private List<Servicio>servicios;
private double preciototal;
public Reparacion() {
	
}
public Reparacion(int id, Cliente cliente, Vehiculo vehiculo, Empleado empleado, LocalDate fechaEntrada,
		LocalDate fechaSalida, List<Servicio> servicios, double preciototal) {
	
	this.id = id;
	this.cliente = cliente;
	this.vehiculo = vehiculo;
	this.empleado = empleado;
	this.fechaEntrada = fechaEntrada;
	this.fechaSalida = fechaSalida;
	this.servicios = servicios;
	this.preciototal = preciototal;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Cliente getCliente() {
	return cliente;
}
public void setCliente(Cliente cliente) {
	this.cliente = cliente;
}
public Vehiculo getVehiculo() {
	return vehiculo;
}
public void setVehiculo(Vehiculo vehiculo) {
	this.vehiculo = vehiculo;
}
public Empleado getEmpleado() {
	return empleado;
}
public void setEmpleado(Empleado empleado) {
	this.empleado = empleado;
}
public LocalDate getFechaEntrada() {
	return fechaEntrada;
}
public void setFechaEntrada(LocalDate fechaEntrada) {
	this.fechaEntrada = fechaEntrada;
}
public LocalDate getFechaSalida() {
	return fechaSalida;
}
public void setFechaSalida(LocalDate fechaSalida) {
	this.fechaSalida = fechaSalida;
}
public List<Servicio> getServicios() {
	return servicios;
}
public void setServicios(List<Servicio> servicios) {
	this.servicios = servicios;
}
public double getPreciototal() {
	return preciototal;
}
public void setPreciototal(double preciototal) {
	this.preciototal = preciototal;
}
@Override
public String toString() {
	return "Reparacion [id=" + id + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", empleado=" + empleado
			+ ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", servicios=" + servicios
			+ ", preciototal=" + preciototal + "]";
}

}

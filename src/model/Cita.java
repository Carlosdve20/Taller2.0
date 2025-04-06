package model;

import java.time.LocalDateTime;

public class Cita {
private int id;
private Cliente cliente;
private Vehiculo vehiculo;
private LocalDateTime fecha;
public Cita() {
	
}

public Cita(Cliente cliente, Vehiculo vehiculo, LocalDateTime fecha) {
	this.cliente = cliente;
	this.vehiculo = vehiculo;
	this.fecha = fecha;
}

public Cita(int id, Cliente cliente, Vehiculo vehiculo, LocalDateTime fecha) {
	
	this.id = id;
	this.cliente = cliente;
	this.vehiculo = vehiculo;
	this.fecha = fecha;
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
public LocalDateTime getFecha() {
	return fecha;
}
public void setFecha(LocalDateTime fecha) {
	this.fecha = fecha;
}

@Override
public String toString() {
	return "Cita [id=" + id + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", fecha=" + fecha + "]";
}

}

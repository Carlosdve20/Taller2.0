package model;

import dao.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {
private int id;
private String nombre;
private String apellido;
private String dni;
private String telefono;
private String correo;
private String direccon;
public Cliente() {
	
}

public Cliente(String nombre, String apellido, String dni, String telefono, String correo, String direccon) {
	
	this.nombre = nombre;
	this.apellido = apellido;
	this.dni = dni;
	this.telefono = telefono;
	this.correo = correo;
	this.direccon = direccon;
}

public Cliente(int id, String nombre, String apellido, String dni, String telefono, String correo, String direccon) {
	
	this.id = id;
	this.nombre = nombre;
	this.apellido = apellido;
	this.dni = dni;
	this.telefono = telefono;
	this.correo = correo;
	this.direccon = direccon;
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
public String getDni() {
	return dni;
}
public void setDni(String dni) {
	this.dni = dni;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getCorreo() {
	return correo;
}
public void setCorreo(String correo) {
	this.correo = correo;
}
public String getDireccon() {
	return direccon;
}
public void setDireccon(String direccon) {
	this.direccon = direccon;
}
@Override
public String toString() {
	return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", telefono="
			+ telefono + ", correo=" + correo + ", direccon=" + direccon + "]";
}


 public static Cliente obtenerClientePorId(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Cliente WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Cliente(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("dni"),
                                rs.getString("telefono"),
                                rs.getString("correo"),
                                rs.getString("direccion")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener cliente: " + e.getMessage());
            }
        }
        return null;
    }
}
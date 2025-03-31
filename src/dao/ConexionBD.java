package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	// URL de conexión a la base de datos MySQL
	 private static final String URL = "jdbc:mysql://localhost:3306/Taller"; 
	 private static final String USUARIO = "usuario"; 
	 private static final String CONTRASENA = "contraseña"; // 
	
	 // Método para establecer la conexión con la base de datos
	 public static Connection conectar() {
		try {
		// Establecer la conexión con la base de datos
			return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (SQLException e) {
			System.out.println("Error al conectar a la base de datos: " + 
			e.getMessage());
			return null;
		}
	 }

}

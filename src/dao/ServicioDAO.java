package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Servicio;

public class ServicioDAO {

    // MÉTODO PARA INSERTAR SERVICIO
    public static void agregarServicio(Servicio servicio) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Servicio (nombre, descripción, precio) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, servicio.getNombre());
                stmt.setString(2, servicio.getDescripción());
                stmt.setDouble(3, servicio.getPrecio());

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        servicio.setId(generatedKeys.getInt(1)); // Obtener el ID autogenerado
                    }
                    System.out.println("Servicio agregado con éxito. ID asignado: " + servicio.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar servicio: " + e.getMessage());
            }
        }
    }

    // MÉTODO PARA OBTENER TODOS LOS SERVICIOS
    public static List<Servicio> obtenerServicios() {
        List<Servicio> listaServicios = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Servicio";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Servicio servicio = new Servicio(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("descripción"),
                            rs.getDouble("precio")
                    );
                    listaServicios.add(servicio);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener servicios: " + e.getMessage());
            }
        }
        return listaServicios;
    }

    // MÉTODO PARA ACTUALIZAR UN SERVICIO
    public static void actualizarServicio(Servicio servicio) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Servicio SET nombre = ?, descripción = ?, precio = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, servicio.getNombre());
                stmt.setString(2, servicio.getDescripción());
                stmt.setDouble(3, servicio.getPrecio());
                stmt.setInt(4, servicio.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Servicio actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar servicio: " + e.getMessage());
            }
        }
    }

    // MÉTODO PARA ELIMINAR UN SERVICIO
    public static void eliminarServicio(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Servicio WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Servicio eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar servicio: " + e.getMessage());
            }
        }
    }
     // Método para obtener un servicio por su ID
     public static Servicio obtenerServicioPorId(int id) {
        Servicio servicio = null;
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT id, nombre, descripcion, precio FROM Servicio WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        servicio = new Servicio(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("descripcion"),
                                rs.getDouble("precio")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener servicio por ID: " + e.getMessage());
            } 
        }
        return servicio;
    }
}

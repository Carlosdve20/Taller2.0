package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Inventario;

public class InventarioDAO {

    public static void agregarInventario(Inventario inventario) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            
            String query = "INSERT INTO inventario (nombre, cantidad, precio) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                
            
                stmt.setString(1, inventario.getNombre());  
                stmt.setInt(2, inventario.getCantidad());   
                stmt.setDouble(3, inventario.getPrecio());  

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                  
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        inventario.setId(generatedKeys.getInt(1));  
                    }
                    System.out.println("Objetos agregados con éxito al inventario. ID asignado: " + inventario.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar objetos al inventario: " + e.getMessage());
            }
        }
    }

    public static List<Inventario> obtenerInventario() {
        List<Inventario> listaInventario = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Inventario"; 
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    
                    Inventario inventario = new Inventario(
                        rs.getInt("id"),          
                        rs.getString("nombre"),    
                        rs.getInt("cantidad"),     
                        rs.getDouble("precio")     
                    );
                    listaInventario.add(inventario);  
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener inventario: " + e.getMessage());
            }
        }
        return listaInventario;  
    }

    public static void actualizarInventario(Inventario inventario) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            
            String query = "UPDATE inventario SET nombre = ?, cantidad = ?, precio = ? WHERE id = ?"; 
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                
                stmt.setString(1, inventario.getNombre());  
                stmt.setInt(2, inventario.getCantidad());   
                stmt.setDouble(3, inventario.getPrecio());  
                stmt.setInt(4, inventario.getId());         
    
                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Inventario actualizado con éxito.");
                } else {
                    System.out.println("No se encontró el inventario para actualizar.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar inventario: " + e.getMessage());
            }

            
        }
    }
    public static void eliminarInventario(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Inventario WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Inventario eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar Inventario: " + e.getMessage());
            }
            
        }
    }
}
    
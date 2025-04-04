package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDAO {
    
    // MÉTODO PARA INSERTAR CLIENTE
  
    public static void agregarCliente(Cliente cliente) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Cliente (nombre, apellido, dni, telefono, correo, direccion) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getApellido());
                stmt.setString(3, cliente.getDni());
                stmt.setString(4, cliente.getTelefono());
                stmt.setString(5, cliente.getCorreo());
                stmt.setString(6, cliente.getDireccion());
                
                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        cliente.setId(generatedKeys.getInt(1)); // Obtener el ID autogenerado
                    }
                    System.out.println("Cliente agregado con éxito. ID asignado: " + cliente.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar cliente: " + e.getMessage());
            }
        }
    }

    // MÉTODO PARA OBTENER TODOS LOS CLIENTES
    public static List<Cliente> obtenerClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Cliente";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Cliente cliente = new Cliente(
                        rs.getInt("id"), 
                        rs.getString("nombre"), 
                        rs.getString("apellido"), 
                        rs.getString("dni"), 
                        rs.getString("telefono"), 
                        rs.getString("correo"), 
                        rs.getString("direccion")
                    );
                    listaClientes.add(cliente);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener clientes: " + e.getMessage());
            }
        }
        return listaClientes;
    }

    // MÉTODO PARA ACTUALIZAR UN CLIENTE
    public static void actualizarCliente(Cliente cliente) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Cliente SET nombre = ?, apellido = ?, dni = ?, telefono = ?, correo = ?, direccion = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, cliente.getNombre());
                stmt.setString(2, cliente.getApellido());
                stmt.setString(3, cliente.getDni());
                stmt.setString(4, cliente.getTelefono());
                stmt.setString(5, cliente.getCorreo());
                stmt.setString(6, cliente.getDireccion());
                stmt.setInt(7, cliente.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Cliente actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar cliente: " + e.getMessage());
            }
        }
    }

    // MÉTODO PARA ELIMINAR UN CLIENTE
    public static void eliminarCliente(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cliente WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Cliente eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar cliente: " + e.getMessage());
            }
            
        }
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


package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Proveedor;

public class ProveedorDAO {

    // Método para insertar un proveedor
    public static void agregarProveedor(Proveedor proveedor) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Proveedor (nombre, telefono, direccion) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, proveedor.getNombre());
                stmt.setString(2, proveedor.getTelefono());
                stmt.setString(3, proveedor.getDireccion());

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        proveedor.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("Proveedor agregado con éxito. ID asignado: " + proveedor.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar proveedor: " + e.getMessage());
            }
        }
    }

    // Método para obtener todos los proveedores
    public static List<Proveedor> obtenerProveedores() {
        List<Proveedor> listaProveedores = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Proveedor";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Proveedor proveedor = new Proveedor(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("telefono"),
                            rs.getString("direccion")
                    );
                    listaProveedores.add(proveedor);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener proveedores: " + e.getMessage());
            }
        }
        return listaProveedores;
    }

    // Método para actualizar un proveedor
    public static void actualizarProveedor(Proveedor proveedor) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Proveedor SET nombre = ?, telefono = ?, direccion = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, proveedor.getNombre());
                stmt.setString(2, proveedor.getTelefono());
                stmt.setString(3, proveedor.getDireccion());
                stmt.setInt(4, proveedor.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Proveedor actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar proveedor: " + e.getMessage());
            }
        }
    }

    // Método para eliminar un proveedor
    public static void eliminarProveedor(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Proveedor WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Proveedor eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar proveedor: " + e.getMessage());
            }
        }
    }

    public static Proveedor obtenerProveedorPorId(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Proveedor WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Proveedor(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("telefono"),
                                rs.getString("direccion")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener proveedor: " + e.getMessage());
            }
        }
        return null;
    }
}


package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Empleado;

public class EmpleadoDAO {
    
     // Método para insertar un empleado
     public static void agregarEmpleado(Empleado empleado) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Empleado (nombre, apellido, puesto, salario) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getPuesto());
                stmt.setDouble(4, empleado.getSalario());

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        empleado.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("Empleado agregado con éxito. ID asignado: " + empleado.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar empleado: " + e.getMessage());
            }
        }
    }

    // Método para obtener todos los empleados
    public static List<Empleado> obtenerEmpleados() {
        List<Empleado> listaEmpleados = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Empleado";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Empleado empleado = new Empleado(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            
                            rs.getString("puesto"),
                            rs.getDouble("salario")
                    );
                    listaEmpleados.add(empleado);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener empleados: " + e.getMessage());
            }
        }
        return listaEmpleados;
    }

    // Método para actualizar un empleado
    public static void actualizarEmpleado(Empleado empleado) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Empleado SET nombre = ?, apellido = ?, puesto = ?, salario = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, empleado.getNombre());
                stmt.setString(2, empleado.getApellido());
                stmt.setString(3, empleado.getPuesto());
                stmt.setDouble(4, empleado.getSalario());
                stmt.setInt(5, empleado.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Empleado actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar empleado: " + e.getMessage());
            }
        }
    }

    // Método para eliminar un empleado
    public static void eliminarEmpleado(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Empleado WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Empleado eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar empleado: " + e.getMessage());
            }
        }
    }

    public static Empleado obtenerEmpleadoPorId(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Empleado WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Empleado(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("puesto"),
                                rs.getDouble("salario")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener empleado: " + e.getMessage());
            }
        }
        return null;
    }
}


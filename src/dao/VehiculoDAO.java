package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Vehiculo;

public class VehiculoDAO {

    // Método para insertar un vehículo
    public static void agregarVehiculo(Vehiculo vehiculo) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Vehiculo (matricula, marca, modelo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, vehiculo.getMatricula());
                stmt.setString(2, vehiculo.getMarca());
                stmt.setString(3, vehiculo.getModelo());

                stmt.executeUpdate();
                System.out.println("Vehículo agregado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error al agregar vehículo: " + e.getMessage());
            }
        }
    }

    // Método para obtener todos los vehículos
    public static List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Vehiculo vehiculo = new Vehiculo(
                            rs.getString("matricula"),
                            rs.getString("marca"),
                            rs.getString("modelo")
                    );
                    listaVehiculos.add(vehiculo);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener vehículos: " + e.getMessage());
            }
        }
        return listaVehiculos;
    }

    // Método para actualizar un vehículo
    public static void actualizarVehiculo(Vehiculo vehiculo) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Vehiculo SET marca = ?, modelo = ? WHERE matricula = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, vehiculo.getMarca());
                stmt.setString(2, vehiculo.getModelo());
                stmt.setString(3, vehiculo.getMatricula());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Vehículo actualizado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar vehículo: " + e.getMessage());
            }
        }
    }

    // Método para eliminar un vehículo
    public static void eliminarVehiculo(String matricula) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Vehiculo WHERE matricula = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setString(1, matricula);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Vehículo eliminado con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar vehículo: " + e.getMessage());
            }
        }
    }

    public static Vehiculo obtenerVehiculoPorMatricula(String matricula) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Vehiculo WHERE matricula = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query);) {
                stmt.setString(1, matricula);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Vehiculo(
                                rs.getString("matricula"),
                                rs.getString("marca"),
                                rs.getString("modelo")
                        );
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener vehiculo: " + e.getMessage());
            }
        }
        return null;
    }
}
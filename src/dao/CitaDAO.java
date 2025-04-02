package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cita;


public class CitaDAO {

    // Método para insertar una cita
    public static void agregarCita(Cita cita) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Cita (id_cliente, matricula, fecha) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, cita.getCliente().getId());
                stmt.setString(2, cita.getVehiculo().getMatricula());
                stmt.setTimestamp(3, Timestamp.valueOf(cita.getFecha()));

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        cita.setId(generatedKeys.getInt(1));
                    }
                    System.out.println("Cita agregada con éxito. ID asignado: " + cita.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar cita: " + e.getMessage());
            }
        }
    }

    // Método para obtener todas las citas
    public static List<Cita> obtenerCitas() {
        List<Cita> listaCitas = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Cita";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Cita cita = new Cita(
                            rs.getInt("id"),
                            ClienteDAO.obtenerClientePorId(rs.getInt("id_cliente")),
                            VehiculoDAO.obtenerVehiculoPorMatricula(rs.getString("matricula")),
                            rs.getTimestamp("fecha").toLocalDateTime()
                    );
                    listaCitas.add(cita);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener citas: " + e.getMessage());
            }
        }
        return listaCitas;
    }

    // Método para actualizar una cita
    public static void actualizarCita(Cita cita) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Cita SET id_cliente = ?, matricula = ?, fecha = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, cita.getCliente().getId());
                stmt.setString(2, cita.getVehiculo().getMatricula());
                stmt.setTimestamp(3, Timestamp.valueOf(cita.getFecha()));
                stmt.setInt(4, cita.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Cita actualizada con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar cita: " + e.getMessage());
            }
        }
    }

    // Método para eliminar una cita
    public static void eliminarCita(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Cita WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Cita eliminada con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar cita: " + e.getMessage());
            }
        }
    }
}
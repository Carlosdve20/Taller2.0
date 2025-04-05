package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Cliente;
import model.Empleado;
import model.Reparacion;
import model.Servicio;
import model.Vehiculo;

public class ReparacionDAO {

    // Método para insertar una nueva reparación
    public static void agregarReparacion(Reparacion reparacion) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Reparacion (id_cliente, matricula, id_empleado, fechaEntrada, fechaSalida, precioTotal) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, reparacion.getCliente().getId());
                stmt.setString(2, reparacion.getVehiculo().getMatricula());
                stmt.setInt(3, reparacion.getEmpleado().getId());
                stmt.setDate(4, Date.valueOf(reparacion.getFechaEntrada()));
                stmt.setDate(5, reparacion.getFechaSalida() != null ? Date.valueOf(reparacion.getFechaSalida()) : null);
                stmt.setDouble(6, reparacion.getPreciototal());

                int filasInsertadas = stmt.executeUpdate();
                if (filasInsertadas > 0) {
                    ResultSet generatedKeys = stmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        reparacion.setId(generatedKeys.getInt(1));
                        System.out.println("Reparación agregada con éxito. ID asignado: " + reparacion.getId());
                        // Aquí podrías llamar a un método para agregar los servicios asociados si los tienes inmediatamente
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar reparación: " + e.getMessage());
            } 
        }
    }

    // Método para obtener una reparación por su ID
    public static Reparacion obtenerReparacionPorId(int id) {
        Reparacion reparacion = null;
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT r.id, r.fechaEntrada, r.fechaSalida, r.precioTotal, " +
                           "c.id AS cliente_id, c.Nombre AS cliente_nombre, c.apellido AS cliente_apellido, c.dni AS cliente_dni, c.telefono AS cliente_telefono, c.correo AS cliente_correo, c.direccion AS cliente_direccion, " +
                           "v.matricula AS vehiculo_matricula, v.marca AS vehiculo_marca, v.modelo AS vehiculo_modelo, " +
                           "e.id AS empleado_id, e.Nombre AS empleado_nombre, e.apellido AS empleado_apellido, e.dni AS empleado_dni, e.puesto AS empleado_puesto, e.salario AS empleado_salario, e.disponibilidad AS empleado_disponibilidad " +
                           "FROM Reparacion r " +
                           "JOIN Cliente c ON r.id_cliente = c.id " +
                           "JOIN Vehiculo v ON r.matricula = v.matricula " +
                           "JOIN Empleado e ON r.id_empleado = e.id " +
                           "WHERE r.id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Cliente cliente = new Cliente(rs.getInt("cliente_id"), rs.getString("cliente_nombre"), rs.getString("cliente_apellido"), rs.getString("cliente_dni"), rs.getString("cliente_telefono"), rs.getString("cliente_correo"), rs.getString("cliente_direccion"));
                    Vehiculo vehiculo = new Vehiculo(rs.getString("vehiculo_matricula"), rs.getString("vehiculo_marca"), rs.getString("vehiculo_modelo"));
                    Empleado empleado = new Empleado(rs.getInt("empleado_id"), rs.getString("empleado_nombre"), rs.getString("empleado_apellido"), rs.getString("empleado_dni"), rs.getString("empleado_puesto"), rs.getDouble("empleado_salario"), rs.getBoolean("empleado_disponibilidad"));
                    reparacion = new Reparacion(rs.getInt("id"), cliente, vehiculo, empleado, rs.getDate("fechaEntrada").toLocalDate(), rs.getDate("fechaSalida") != null ? rs.getDate("fechaSalida").toLocalDate() : null, obtenerServiciosDeReparacion(id), rs.getDouble("precioTotal"));
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener reparación por ID: " + e.getMessage());
            } 
        }
        return reparacion;
    }

    // Método para obtener todas las reparaciones
    public static List<Reparacion> obtenerTodasReparaciones() {
        List<Reparacion> listaReparaciones = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT r.id, r.fechaEntrada, r.fechaSalida, r.precioTotal, " +
                           "c.id AS cliente_id, c.Nombre AS cliente_nombre, c.apellido AS cliente_apellido, c.dni AS cliente_dni, c.telefono AS cliente_telefono, c.correo AS cliente_correo, c.direccion AS cliente_direccion, " +
                           "v.matricula AS vehiculo_matricula, v.marca AS vehiculo_marca, v.modelo AS vehiculo_modelo, " +
                           "e.id AS empleado_id, e.Nombre AS empleado_nombre, e.apellido AS empleado_apellido, e.dni AS empleado_dni, e.puesto AS empleado_puesto, e.salario AS empleado_salario, e.disponibilidad AS empleado_disponibilidad " +
                           "FROM Reparacion r " +
                           "JOIN Cliente c ON r.id_cliente = c.id " +
                           "JOIN Vehiculo v ON r.matricula = v.matricula " +
                           "JOIN Empleado e ON r.id_empleado = e.id";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    int idReparacion = rs.getInt("id");
                    Cliente cliente = new Cliente(rs.getInt("cliente_id"), rs.getString("cliente_nombre"), rs.getString("cliente_apellido"), rs.getString("cliente_dni"), rs.getString("cliente_telefono"), rs.getString("cliente_correo"), rs.getString("cliente_direccion"));
                    Vehiculo vehiculo = new Vehiculo(rs.getString("vehiculo_matricula"), rs.getString("vehiculo_marca"), rs.getString("vehiculo_modelo"));
                    Empleado empleado = new Empleado(rs.getInt("empleado_id"), rs.getString("empleado_nombre"), rs.getString("empleado_apellido"), rs.getString("empleado_dni"), rs.getString("empleado_puesto"), rs.getDouble("empleado_salario"), rs.getBoolean("empleado_disponibilidad"));
                    Reparacion reparacion = new Reparacion(rs.getInt("id"), cliente, vehiculo, empleado, rs.getDate("fechaEntrada").toLocalDate(), rs.getDate("fechaSalida") != null ? rs.getDate("fechaSalida").toLocalDate() : null, obtenerServiciosDeReparacion(idReparacion), rs.getDouble("precioTotal"));
                    listaReparaciones.add(reparacion);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener todas las reparaciones: " + e.getMessage());
            } 
        }
        return listaReparaciones;
    }

    // Método para actualizar una reparación
    public static void actualizarReparacion(Reparacion reparacion) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Reparacion SET id_cliente = ?, matricula = ?, id_empleado = ?, fechaEntrada = ?, fechaSalida = ?, precioTotal = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, reparacion.getCliente().getId());
                stmt.setString(2, reparacion.getVehiculo().getMatricula());
                stmt.setInt(3, reparacion.getEmpleado().getId());
                stmt.setDate(4, Date.valueOf(reparacion.getFechaEntrada()));
                stmt.setDate(5, reparacion.getFechaSalida() != null ? Date.valueOf(reparacion.getFechaSalida()) : null);
                stmt.setDouble(6, reparacion.getPreciototal());
                stmt.setInt(7, reparacion.getId());

                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Reparación actualizada con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar reparación: " + e.getMessage());
            } 
        }
    }

    // Método para eliminar una reparación por su ID
    public static void eliminarReparacion(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Reparacion WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Reparación eliminada con éxito.");
                    eliminarServiciosDeReparacion(id); // Eliminar también los servicios asociados
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar reparación: " + e.getMessage());
            }
        }
    }

    // Método para actualizar el empleado asignado a una reparación
    public static void actualizarEmpleadoEnReparacion(int idReparacion, int idEmpleado) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Reparacion SET id_empleado = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idEmpleado);
                stmt.setInt(2, idReparacion);
                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Empleado asignado a la reparación con éxito.");
                }
            } catch (SQLException e) {
                System.out.println("Error al asignar empleado a la reparación: " + e.getMessage());
            } 
        }
    }

    // Método para marcar una reparación como finalizada
    public static void marcarComoFinalizada(int idReparacion) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Reparacion SET fechaSalida = CURRENT_DATE WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idReparacion);
                int filasActualizadas = stmt.executeUpdate();
                if (filasActualizadas > 0) {
                    System.out.println("Reparación marcada como finalizada.");
                }
            } catch (SQLException e) {
                System.out.println("Error al marcar reparación como finalizada: " + e.getMessage());
            }
        }
    }

    // Método para obtener los servicios asociados a una reparación
    public static List<Servicio> obtenerServiciosDeReparacion(int idReparacion) {
        List<Servicio> listaServicios = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT s.id, s.nombre, s.descripcion, s.precio " +
                           "FROM Servicio s " +
                           "JOIN Reparacion_Servicio rs ON s.id = rs.id_servicio " +
                           "WHERE rs.id_reparacion = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idReparacion);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Servicio servicio = new Servicio(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getDouble("precio"));
                    listaServicios.add(servicio);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener servicios de reparación: " + e.getMessage());
            } 
        }
        return listaServicios;
    }

    // Método para agregar un servicio a una reparación
    public static void agregarServicioAReparacion(int idReparacion, int idServicio) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Reparacion_Servicio (id_reparacion, id_servicio) VALUES (?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idReparacion);
                stmt.setInt(2, idServicio);
                stmt.executeUpdate();
                System.out.println("Servicio agregado a la reparación.");
            } catch (SQLException e) {
                System.out.println("Error al agregar servicio a la reparación: " + e.getMessage());
            } 
        }
    }

    // Método para eliminar un servicio específico de una reparación
    public static void eliminarServicioDeReparacion(int idReparacion, int idServicio) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Reparacion_Servicio WHERE id_reparacion = ? AND id_servicio = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idReparacion);
                stmt.setInt(2, idServicio);
                int filasEliminadas = stmt.executeUpdate();
                if (filasEliminadas > 0) {
                    System.out.println("Servicio eliminado de la reparación.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar servicio de la reparación: " + e.getMessage());
            } 
        }
    }

    // Método para eliminar todos los servicios asociados a una reparación (al eliminar la reparación)
    private static void eliminarServiciosDeReparacion(int idReparacion) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Reparacion_Servicio WHERE id_reparacion = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idReparacion);
                stmt.executeUpdate();
                System.out.println("Servicios asociados a la reparación eliminados.");
            } catch (SQLException e) {
                System.out.println("Error al eliminar servicios de la reparación: " + e.getMessage());
            } 
        }
    }

    // Método para verificar si un empleado está actualmente en una reparación activa
    public static boolean estaEmpleadoEnReparacionActiva(int idEmpleado) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT COUNT(*) FROM Reparacion WHERE id_empleado = ? AND fechaSalida IS NULL";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, idEmpleado);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            } catch (SQLException e) {
                System.out.println("Error al verificar si el empleado está en una reparación activa: " + e.getMessage());
            } 
        }
        return false;
    }
}
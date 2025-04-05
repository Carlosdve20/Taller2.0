package view;

import model.Cliente;
import model.Empleado;
import model.Reparacion;
import model.Servicio;
import model.Vehiculo;
import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.ReparacionDAO;
import dao.ServicioDAO;
import dao.VehiculoDAO;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class ServicioReparacion {

    public void crearNuevaReparacion(int clienteId, String matriculaVehiculo, int empleadoId, LocalDate fechaEntrada, LocalDate fechaSalida, List<Integer> serviciosIds) {
        // 1. Obtener los objetos relacionados desde la base de datos
        Cliente cliente = ClienteDAO.obtenerClientePorId(clienteId);
        Vehiculo vehiculo = VehiculoDAO.obtenerVehiculoPorMatricula(matriculaVehiculo);
        Empleado empleado = EmpleadoDAO.obtenerEmpleadoPorId(empleadoId);

        if (cliente == null) {
            System.out.println("Error: Cliente con ID " + clienteId + " no encontrado.");
            return;
        }
        if (vehiculo == null) {
            System.out.println("Error: Vehículo con matrícula " + matriculaVehiculo + " no encontrado.");
            return;
        }
        if (empleado == null) {
            System.out.println("Error: Empleado con ID " + empleadoId + " no encontrado.");
            return;
        }

        // 2. Crear el objeto Reparacion
        Reparacion nuevaReparacion = new Reparacion();
        nuevaReparacion.setCliente(cliente);
        nuevaReparacion.setVehiculo(vehiculo);
        nuevaReparacion.setEmpleado(empleado);
        nuevaReparacion.setFechaEntrada(fechaEntrada);
        nuevaReparacion.setFechaSalida(fechaSalida);
        nuevaReparacion.setServicios(new ArrayList<>()); // Inicializar la lista de servicios
        nuevaReparacion.setPreciototal(0.0); // Inicializar el precio total

        // 3. Utilizar el ReparacionDAO para agregar la reparación a la base de datos (para obtener el ID)
        ReparacionDAO.agregarReparacion(nuevaReparacion);

        if (nuevaReparacion.getId() != 0 && serviciosIds != null && !serviciosIds.isEmpty()) {
            double precioTotal = 0.0;
            List<Servicio> servicios = new ArrayList<>();
            for (int servicioId : serviciosIds) {
                Servicio servicio = ServicioDAO.obtenerServicioPorId(servicioId);
                if (servicio != null) {
                    ReparacionDAO.agregarServicioAReparacion(nuevaReparacion.getId(), servicio.getId());
                    precioTotal += servicio.getPrecio();
                    servicios.add(servicio);
                } else {
                    System.out.println("Advertencia: Servicio con ID " + servicioId + " no encontrado.");
                }
            }
            nuevaReparacion.setServicios(servicios);
            nuevaReparacion.setPreciototal(precioTotal);
            ReparacionDAO.actualizarReparacion(nuevaReparacion); // Actualizar el precio total
            System.out.println("Reparación con ID " + nuevaReparacion.getId() + " creada con servicios.");
        } else if (nuevaReparacion.getId() != 0) {
            System.out.println("Reparación con ID " + nuevaReparacion.getId() + " creada sin servicios.");
        } else {
            System.out.println("Error al crear la reparación.");
        }
    }

    // Sobrecarga del método para no requerir fecha de salida inicial
    public void crearNuevaReparacion(int clienteId, String matriculaVehiculo, int empleadoId, LocalDate fechaEntrada, List<Integer> serviciosIds) {
        crearNuevaReparacion(clienteId, matriculaVehiculo, empleadoId, fechaEntrada, null, serviciosIds);
    }

    // Sobrecarga sin servicios iniciales
    public void crearNuevaReparacion(int clienteId, String matriculaVehiculo, int empleadoId, LocalDate fechaEntrada, LocalDate fechaSalida) {
        crearNuevaReparacion(clienteId, matriculaVehiculo, empleadoId, fechaEntrada, fechaSalida, null);
    }

    public void crearNuevaReparacion(int clienteId, String matriculaVehiculo, int empleadoId, LocalDate fechaEntrada) {
        crearNuevaReparacion(clienteId, matriculaVehiculo, empleadoId, fechaEntrada, null, null);
    }

    // Método para asignar un empleado a una reparación
    public void asignarEmpleadoAReparacion(int idReparacion, int idEmpleado) {
        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        Empleado empleado = EmpleadoDAO.obtenerEmpleadoPorId(idEmpleado);

        if (reparacion == null) {
            System.out.println("Error: Reparación con ID " + idReparacion + " no encontrada.");
            return;
        }
        if (empleado == null) {
            System.out.println("Error: Empleado con ID " + idEmpleado + " no encontrado.");
            return;
        }

        reparacion.setEmpleado(empleado);
        ReparacionDAO.actualizarReparacion(reparacion);
        System.out.println("Empleado con ID " + idEmpleado + " asignado a la reparación con ID " + idReparacion + ".");
    }

    // Método para agregar un servicio a una reparación existente
    public void agregarServicioAReparacion(int idReparacion, int idServicio) {
        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        Servicio servicio = ServicioDAO.obtenerServicioPorId(idServicio);

        if (reparacion == null) {
            System.out.println("Error: Reparación con ID " + idReparacion + " no encontrada.");
            return;
        }
        if (servicio == null) {
            System.out.println("Error: Servicio con ID " + idServicio + " no encontrado.");
            return;
        }

        ReparacionDAO.agregarServicioAReparacion(idReparacion, idServicio);
        if (reparacion.getServicios() != null) {
            reparacion.getServicios().add(servicio); // Actualizar la lista en memoria
        }
        reparacion.setPreciototal(reparacion.getPreciototal() + servicio.getPrecio());
        ReparacionDAO.actualizarReparacion(reparacion); // Actualizar el precio total
        System.out.println("Servicio con ID " + idServicio + " agregado a la reparación con ID " + idReparacion + ".");
    }

    // Método para eliminar un servicio de una reparación
    public void eliminarServicioDeReparacion(int idReparacion, int idServicio) {
        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        Servicio servicio = ServicioDAO.obtenerServicioPorId(idServicio);

        if (reparacion == null) {
            System.out.println("Error: Reparación con ID " + idReparacion + " no encontrada.");
            return;
        }
        if (servicio == null) {
            System.out.println("Error: Servicio con ID " + idServicio + " no encontrado.");
            return;
        }

        ReparacionDAO.eliminarServicioDeReparacion(idReparacion, idServicio);
        if (reparacion.getServicios() != null) {
            reparacion.getServicios().removeIf(s -> s.getId() == idServicio); // Eliminar de la lista en memoria
        }
        reparacion.setPreciototal(reparacion.getPreciototal() - servicio.getPrecio());
        ReparacionDAO.actualizarReparacion(reparacion); // Actualizar el precio total
        System.out.println("Servicio con ID " + idServicio + " eliminado de la reparación con ID " + idReparacion + ".");
    }

    // Método para marcar una reparación como finalizada
    public void finalizarReparacion(int idReparacion) {
        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        if (reparacion == null) {
            System.out.println("Error: Reparación con ID " + idReparacion + " no encontrada.");
            return;
        }
        reparacion.setFechaSalida(LocalDate.now());
        ReparacionDAO.actualizarReparacion(reparacion);
        System.out.println("Reparación con ID " + idReparacion + " marcada como finalizada.");
    }

}

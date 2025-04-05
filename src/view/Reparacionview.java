package view;

import dao.ClienteDAO;
import dao.EmpleadoDAO;
import dao.ReparacionDAO;
import dao.ServicioDAO;
import dao.VehiculoDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Cliente;
import model.Empleado;
import model.Reparacion;
import model.Servicio;
import model.Vehiculo;

public class Reparacionview {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final ServicioReparacion servicioReparacion = new ServicioReparacion();

    public static void gestionarReparaciones(Scanner sc) {
        int opcion;
        do {
            System.out.println("\nGestión de Reparaciones:");
            System.out.println("1. Registrar nueva reparación");
            System.out.println("2. Modificar reparación (datos básicos)");
            System.out.println("3. Asignar empleado a reparación");
            System.out.println("4. Agregar servicio a reparación");
            System.out.println("5. Eliminar servicio de reparación");
            System.out.println("6. Marcar reparación como finalizada");
            System.out.println("7. Ver detalles de una reparación");
            System.out.println("8. Listar todas las reparaciones");
            System.out.println("9. Eliminar reparación");
            System.out.println("10. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1 -> registrarNuevaReparacion(sc);
                case 2 -> modificarReparacion(sc);
                case 3 -> asignarEmpleadoAReparacion(sc);
                case 4 -> agregarServicioAReparacion(sc);
                case 5 -> eliminarServicioDeReparacion(sc);
                case 6 -> marcarReparacionComoFinalizada(sc);
                case 7 -> verDetallesReparacion(sc);
                case 8 -> listarTodasReparaciones();
                case 9 -> eliminarReparacion(sc);
                case 10 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 10);
    }

    public static void registrarNuevaReparacion(Scanner sc) {
        System.out.println("\nRegistrar Nueva Reparación:");
           // Solicitar ID del cliente
        System.out.print("ID del cliente: ");
        int clienteId = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        Cliente cliente = ClienteDAO.obtenerClientePorId(clienteId);
        if (cliente == null) {
            System.out.println("Cliente con ID " + clienteId + " no encontrado.");
            return;
        }

        // Solicitar matrícula del vehículo
        System.out.print("Matrícula del vehículo: ");
        String matriculaVehiculo = sc.nextLine();
        Vehiculo vehiculo = VehiculoDAO.obtenerVehiculoPorMatricula(matriculaVehiculo);
        if (vehiculo == null) {
            System.out.println("Vehículo con matrícula " + matriculaVehiculo + " no encontrado.");
            return;
        }

        // Solicitar ID del empleado asignado
        System.out.print("ID del empleado asignado: ");
        int empleadoId = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        Empleado empleado = EmpleadoDAO.obtenerEmpleadoPorId(empleadoId);
        if (empleado == null) {
            System.out.println("Empleado con ID " + empleadoId + " no encontrado.");
            return;
        }

        // Solicitar fecha de entrada
        System.out.print("Fecha de entrada (yyyy-MM-dd): ");
        String fechaEntradaStr = sc.nextLine();
        LocalDate fechaEntrada;
        try {
            fechaEntrada = LocalDate.parse(fechaEntradaStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha incorrecto. Use yyyy-MM-dd.");
            return;
        }

        // Solicitar fecha de salida (opcional)
        System.out.print("Fecha de salida (yyyy-MM-dd, dejar vacío si no se conoce): ");
        String fechaSalidaStr = sc.nextLine();
        LocalDate fechaSalida = null;
        if (!fechaSalidaStr.isEmpty()) {
            try {
                fechaSalida = LocalDate.parse(fechaSalidaStr, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto para la salida. Use yyyy-MM-dd.");
                return;
            }
        }

        // Solicitar servicios
        List<Servicio> todosServicios = ServicioDAO.obtenerServicios();
        if (!todosServicios.isEmpty()) {
            System.out.println("\nServicios disponibles:");
            for (Servicio servicio : todosServicios) {
                System.out.println(servicio.getId() + ". " + servicio.getNombre() + " - " + servicio.getPrecio() + "€");
            }

            List<Integer> serviciosIds = new ArrayList<>();
            while (true) {
                System.out.print("Ingrese el ID del servicio a añadir (o 0 para finalizar): ");
                int servicioId = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea
                if (servicioId == 0) {
                    break;
                }
                Servicio servicio = ServicioDAO.obtenerServicioPorId(servicioId);
                if (servicio != null) {
                    serviciosIds.add(servicioId);
                } else {
                    System.out.println("Servicio con ID " + servicioId + " no encontrado.");
                }
            }

            // Llamar al servicio para crear la reparación con servicios
            servicioReparacion.crearNuevaReparacion(clienteId, matriculaVehiculo, empleadoId, fechaEntrada, fechaSalida, serviciosIds);

        } else {
            // Llamar al servicio para crear la reparación sin servicios
            servicioReparacion.crearNuevaReparacion(clienteId, matriculaVehiculo, empleadoId, fechaEntrada, fechaSalida);
            System.out.println("No hay servicios disponibles para añadir.");
        }

        System.out.println("Reparación registrada.");
    }

    

    public static void modificarReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación a modificar: ");
        int idReparacion = sc.nextInt();
        sc.nextLine();

        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        if (reparacion == null) {
            System.out.println("Reparación con ID " + idReparacion + " no encontrada.");
            return;
        }

        System.out.println("Introduzca los nuevos datos (dejar en blanco para no modificar):");

        System.out.print("Nueva fecha de entrada (yyyy-MM-dd, actual: " + DATE_FORMATTER.format(reparacion.getFechaEntrada()) + "): ");
        String fechaEntradaStr = sc.nextLine();
        if (!fechaEntradaStr.isEmpty()) {
            try {
                reparacion.setFechaEntrada(LocalDate.parse(fechaEntradaStr, DATE_FORMATTER));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto.");
            }
        }

        System.out.print("Nueva fecha de salida (yyyy-MM-dd, actual: " + (reparacion.getFechaSalida() != null ? DATE_FORMATTER.format(reparacion.getFechaSalida()) : "sin definir") + "): ");
        String fechaSalidaStr = sc.nextLine();
        if (!fechaSalidaStr.isEmpty()) {
            try {
                reparacion.setFechaSalida(LocalDate.parse(fechaSalidaStr, DATE_FORMATTER));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto.");
            }
        } else if (fechaSalidaStr.isEmpty()) {
            reparacion.setFechaSalida(null); // Permitir eliminar la fecha de salida
        }

        System.out.print("Nuevo precio total (actual: " + reparacion.getPreciototal() + "): ");
        String precioTotalStr = sc.nextLine();
        if (!precioTotalStr.isEmpty()) {
            try {
                reparacion.setPreciototal(Double.parseDouble(precioTotalStr));
            } catch (NumberFormatException e) {
                System.out.println("Formato de precio incorrecto.");
            }
        }

        ReparacionDAO.actualizarReparacion(reparacion);
        System.out.println("Reparación con ID " + idReparacion + " modificada.");
    }

    public static void asignarEmpleadoAReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación: ");
        int idReparacion = sc.nextInt();
        System.out.print("Ingrese el ID del empleado a asignar: ");
        int idEmpleado = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        servicioReparacion.asignarEmpleadoAReparacion(idReparacion, idEmpleado);
    }

    public static void agregarServicioAReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación: ");
        int idReparacion = sc.nextInt();
        System.out.print("Ingrese el ID del servicio a agregar: ");
        int idServicio = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        servicioReparacion.agregarServicioAReparacion(idReparacion, idServicio);
    }

    public static void eliminarServicioDeReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación: ");
        int idReparacion = sc.nextInt();
        System.out.print("Ingrese el ID del servicio a eliminar: ");
        int idServicio = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        servicioReparacion.eliminarServicioDeReparacion(idReparacion, idServicio);
    }

    public static void marcarReparacionComoFinalizada(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación a marcar como finalizada: ");
        int idReparacion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        servicioReparacion.finalizarReparacion(idReparacion);
    }

    public static void verDetallesReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación a ver: ");
        int idReparacion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        Reparacion reparacion = ReparacionDAO.obtenerReparacionPorId(idReparacion);
        if (reparacion != null) {
            System.out.println("\nDetalles de la Reparación:");
            System.out.println(reparacion); // Asume que toString() en Reparacion está bien implementado
            if (reparacion.getServicios() != null && !reparacion.getServicios().isEmpty()) {
                System.out.println("\nServicios Asociados:");
                reparacion.getServicios().forEach(System.out::println);
            } else {
                System.out.println("\nNo hay servicios asociados a esta reparación.");
            }
        } else {
            System.out.println("Reparación con ID " + idReparacion + " no encontrada.");
        }
    }

    public static void listarTodasReparaciones() {
        List<Reparacion> reparaciones = ReparacionDAO.obtenerTodasReparaciones();
        if (reparaciones.isEmpty()) {
            System.out.println("No hay reparaciones registradas.");
        } else {
            System.out.println("\nLista de Todas las Reparaciones:");
            for (Reparacion reparacion : reparaciones) {
                System.out.println(reparacion.getId() + ": Cliente=" + reparacion.getCliente().getNombre() + " " + reparacion.getCliente().getApellido() +
                                   ", Vehículo=" + reparacion.getVehiculo().getMatricula() + ", Empleado=" + reparacion.getEmpleado().getNombre() + " " + reparacion.getEmpleado().getApellido() +
                                   ", Entrada=" + DATE_FORMATTER.format(reparacion.getFechaEntrada()) +
                                   (reparacion.getFechaSalida() != null ? ", Salida=" + DATE_FORMATTER.format(reparacion.getFechaSalida()) : ", Salida=Pendiente") +
                                   ", Precio=" + reparacion.getPreciototal() + "€");
            }
        }
    }

    public static void eliminarReparacion(Scanner sc) {
        System.out.print("Ingrese el ID de la reparación a eliminar: ");
        int idReparacion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea
        ReparacionDAO.eliminarReparacion(idReparacion);
        System.out.println("Reparación con ID " + idReparacion + " eliminada.");
    }
}
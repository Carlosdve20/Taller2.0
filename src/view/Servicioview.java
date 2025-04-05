package view;

import dao.ServicioDAO;
import model.Servicio;
import java.util.List;
import java.util.Scanner;

public class Servicioview {

    public static void gestionarServicios(Scanner sc) {
        int opcion;
        do {
            System.out.println("\nGestión de Servicios:");
            System.out.println("1. Agregar nuevo servicio");
            System.out.println("2. Modificar servicio existente");
            System.out.println("3. Eliminar servicio");
            System.out.println("4. Listar todos los servicios");
            System.out.println("5. Ver detalles de un servicio");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1 -> agregarServicio(sc);
                case 2 -> modificarServicio(sc);
                case 3 -> eliminarServicio(sc);
                case 4 -> listarServicios();
                case 5 -> verDetallesServicio(sc);
                case 6 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 6);
    }

    public static void agregarServicio(Scanner sc) {
        System.out.println("\nAgregar Nuevo Servicio:");
        System.out.print("Nombre del servicio: ");
        String nombre = sc.nextLine();
        System.out.print("Descripción del servicio: ");
        String descripcion = sc.nextLine();
        System.out.print("Precio del servicio: ");
        double precio = sc.nextDouble();
        sc.nextLine(); // Consumir la nueva línea

        Servicio nuevoServicio = new Servicio(0, nombre, descripcion, precio); // ID se asigna en la BD
        ServicioDAO.agregarServicio(nuevoServicio);
        System.out.println("Servicio '" + nombre + "' agregado con éxito.");
    }

    public static void modificarServicio(Scanner sc) {
        System.out.println("\nModificar Servicio Existente:");
        System.out.print("Ingrese el ID del servicio a modificar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        Servicio servicio = ServicioDAO.obtenerServicioPorId(id);
        if (servicio == null) {
            System.out.println("Servicio con ID " + id + " no encontrado.");
            return;
        }

        System.out.println("Introduzca los nuevos datos (dejar en blanco para no modificar):");

        System.out.print("Nuevo nombre (" + servicio.getNombre() + "): ");
        String nuevoNombre = sc.nextLine();
        if (!nuevoNombre.isEmpty()) {
            servicio.setNombre(nuevoNombre);
        }

        System.out.print("Nueva descripción (" + servicio.getDescripción() + "): ");
        String nuevaDescripcion = sc.nextLine();
        if (!nuevaDescripcion.isEmpty()) {
            servicio.setDescripción(nuevaDescripcion);
        }

        System.out.print("Nuevo precio (" + servicio.getPrecio() + "): ");
        String nuevoPrecioStr = sc.nextLine();
        if (!nuevoPrecioStr.isEmpty()) {
            try {
                double nuevoPrecio = Double.parseDouble(nuevoPrecioStr);
                servicio.setPrecio(nuevoPrecio);
            } catch (NumberFormatException e) {
                System.out.println("Formato de precio incorrecto.");
            }
        }

        ServicioDAO.actualizarServicio(servicio);
        System.out.println("Servicio con ID " + id + " modificado con éxito.");
    }

    public static void eliminarServicio(Scanner sc) {
        System.out.println("\nEliminar Servicio:");
        System.out.print("Ingrese el ID del servicio a eliminar: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        Servicio servicio = ServicioDAO.obtenerServicioPorId(id);
        if (servicio == null) {
            System.out.println("Servicio con ID " + id + " no encontrado.");
            return;
        }

        System.out.print("¿Está seguro de que desea eliminar el servicio '" + servicio.getNombre() + "' (ID: " + id + ")? (s/n): ");
        String confirmacion = sc.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            ServicioDAO.eliminarServicio(id);
            System.out.println("Servicio con ID " + id + " eliminado con éxito.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public static void listarServicios() {
        System.out.println("\nLista de Todos los Servicios:");
        List<Servicio> servicios = ServicioDAO.obtenerServicios();
        if (servicios.isEmpty()) {
            System.out.println("No hay servicios registrados.");
        } else {
            for (Servicio servicio : servicios) {
                System.out.println(servicio.getId() + ". " + servicio.getNombre() + " - " + servicio.getPrecio() + "€");
            }
        }
    }

    public static void verDetallesServicio(Scanner sc) {
        System.out.println("\nVer Detalles de Servicio:");
        System.out.print("Ingrese el ID del servicio a ver: ");
        int id = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        Servicio servicio = ServicioDAO.obtenerServicioPorId(id);
        if (servicio != null) {
            System.out.println("\nDetalles del Servicio:");
            System.out.println("ID: " + servicio.getId());
            System.out.println("Nombre: " + servicio.getNombre());
            System.out.println("Descripción: " + servicio.getDescripción());
            System.out.println("Precio: " + servicio.getPrecio() + "€");
        } else {
            System.out.println("Servicio con ID " + id + " no encontrado.");
        }
    }
}

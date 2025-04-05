package view;

import dao.ProveedorDAO;
import java.util.List;
import java.util.Scanner;
import model.Proveedor;

public class Proveedorview {

    public static void gestionarProveedores(Scanner sc) {
        int opcion;
        do {
            System.out.println("Gestión de Proveedores");
            System.out.println("1. Agregar proveedor");
            System.out.println("2. Buscar proveedores por nombre");
            System.out.println("3. Actualizar proveedor");
            System.out.println("4. Eliminar proveedor");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarProveedor(sc);
                case 2 -> buscarProveedorPorNombre(sc);
                case 3 -> actualizarProveedor(sc);
                case 4 -> eliminarProveedor(sc);
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private static void agregarProveedor(Scanner sc) {
        System.out.println("Agregar Proveedor ");
        
        
        System.out.print("Introduce ID: ");
        String idString = sc.nextLine();  
        int id = Integer.parseInt(idString);
        System.out.print("Introduce nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce teléfono: ");
        String telefono = sc.nextLine();
        System.out.print("Introduce dirección: ");
        String direccion = sc.nextLine();

        
        Proveedor proveedor = new Proveedor(id, nombre, telefono, direccion);
        ProveedorDAO.agregarProveedor(proveedor);
        System.out.println("Proveedor agregado correctamente.");
    }

    private static void buscarProveedorPorNombre(Scanner sc) {
        System.out.println("Buscar Proveedor por Nombre ");
        System.out.print("Ingrese el nombre a buscar: ");
        String nombre = sc.nextLine();
        List<Proveedor> resultados = ProveedorDAO.buscarProveedorPorNombre(nombre);

        if (resultados.isEmpty()) {
            System.out.println("No se encontraron proveedores con ese nombre.");
        } else {
            for (Proveedor p : resultados) {
                System.out.println(p);
            }
        }
    }

    private static void actualizarProveedor(Scanner sc) {
        System.out.println("Actualizar Proveedor ");
        System.out.print("Ingrese el nombre del proveedor a actualizar: ");
        String nombre = sc.nextLine();

        List<Proveedor> resultados = ProveedorDAO.buscarProveedorPorNombre(nombre);
        if (resultados.isEmpty()) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        Proveedor proveedor = resultados.get(0); 
        System.out.println("Proveedor encontrado: " + proveedor);

        System.out.print("Nuevo teléfono: ");
        proveedor.setTelefono(sc.nextLine());
        System.out.print("Nueva dirección: ");
        proveedor.setDireccion(sc.nextLine());

        ProveedorDAO.actualizarProveedor(proveedor);
        System.out.println("Proveedor actualizado correctamente.");
    }

    private static void eliminarProveedor(Scanner sc) {
        System.out.println("Eliminar Proveedor");
        System.out.print("Ingrese el nombre del proveedor a eliminar: ");
        String nombre = sc.nextLine();

        List<Proveedor> resultados = ProveedorDAO.buscarProveedorPorNombre(nombre);
        if (resultados.isEmpty()) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        Proveedor proveedor = resultados.get(0); 
        ProveedorDAO.eliminarProveedor(proveedor.getId());
        System.out.println("Proveedor eliminado correctamente.");
    }
}

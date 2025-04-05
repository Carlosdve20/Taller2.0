package view;

import dao.InventarioDAO;
import java.util.List;
import java.util.Scanner;
import model.Inventario;

public class Inventarioview {
    
    	public static void gestionarInventario(Scanner sc) {
        System.out.println("Gestión de Inventario:");
        System.out.println("1. Registrar nuevo producto");
        System.out.println("2. Modificar producto");
        System.out.println("3. ver inventario");
        System.out.println("4. Eliminar producto");
        System.out.println("5. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1 -> {
                System.out.println("Introduce nombre:");
                String nombre = sc.nextLine();
                System.out.println("Introduce cantidad:");
                int cantidad = Integer.parseInt(sc.nextLine());
                System.out.println("Introduce precio:");
                double precio = Double.parseDouble(sc.nextLine());
                Inventario nuevoInventario = new Inventario(0, nombre, cantidad, precio);
                InventarioDAO.agregarInventario(nuevoInventario);
                System.out.println("Producto agregado correctamente.");
                }
            case 2 -> {
                System.out.println("Introduce ID del producto a modificar:");
                int idModificar = sc.nextInt();
                sc.nextLine();
                System.out.println("Introduce nuevo nombre:");
                String nuevoNombre = sc.nextLine();
                System.out.println("Introduce nueva cantidad:");
                int nuevaCantidad = Integer.parseInt(sc.nextLine());
                System.out.println("Introduce nuevo precio:");
                double nuevoPrecio = Double.parseDouble(sc.nextLine());
                Inventario inventarioModificado = new Inventario(idModificar, nuevoNombre, nuevaCantidad, nuevoPrecio);
                InventarioDAO.actualizarInventario(inventarioModificado);
                System.out.println("Producto modificado correctamente.");
                }
            case 3 -> {
                List<Inventario> inventario = InventarioDAO.obtenerInventario();
                inventario.forEach(System.out::println);
                }
                
            case 4 -> {
                System.out.println("Introduce ID del producto a eliminar:");
                int idEliminar = sc.nextInt();
                sc.nextLine();
                InventarioDAO.eliminarInventario(idEliminar);
                System.out.println("Producto eliminado correctamente.");
                }
                
            case 5 -> {System.out.println("Volviendo al menú principal...");
                
            }
            default -> System.out.println("Opción no válida.");
        }
    }
    
}


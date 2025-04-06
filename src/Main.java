import java.util.Scanner;
import view.Citaview;
import view.Clienteview;
import view.Empleadoview;
import view.Inventarioview;
import view.Pedidoview;
import view.Proveedorview;
import view.Reparacionview;
import view.Servicioview;
import view.Vehiculoview;
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Gestión de Taller, elige lo que necesitas: ");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Vehículos");
            System.out.println("3. Gestión de Reparaciones");
            System.out.println("4. Gestionar empleados");
            System.out.println("5. Gestionar Inventario");
            System.out.println("6. Gestionar proveedores");
            System.out.println("7. Gestionar Servicios");
            System.out.println("8. Gestionar citas");
            System.out.println("9. Gestión de Pedidos");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            // Menú con opciones
            switch (opcion) {
                case 1 -> Clienteview.gestionarClientes(sc);

                case 2 -> Vehiculoview.gestionarVehiculos(sc);

                case 3 -> Reparacionview.gestionarReparaciones(sc);

                case 4 -> Empleadoview.gestionarEmpleados(sc);

                case 5 -> Inventarioview.gestionarInventario(sc);

                case 6 -> Proveedorview.gestionarProveedores(sc);

                case 7 -> Servicioview.gestionarServicios(sc);
            
                case 8 -> Citaview.gestionarCitas(sc);

                case 9 -> Pedidoview.gestionarPedido(sc);

                case 10 -> {
                    System.out.println("Saliendo del sistema...");
                    System.exit(0); 
                }

                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 10); 
    }
}

       



      




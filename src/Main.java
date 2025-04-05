import java.util.Scanner;
import view.Citaview;
import view.Clienteview;
import view.Inventarioview;
import view.Vehiculoview;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Gestión de Taller, elige lo que necesitas: ");
            System.out.println("1. Gestión de Clientes");
            System.out.println("2. Gestión de Vehículos");
            System.out.println("3. Registrar una Reparación");
            System.out.println("4. Registrar empleado");
            System.out.println("5. Gestionar Inventario");
            System.out.println("6. Registrar proveedor");
            System.out.println("7. Gestionar citas");
            System.out.println("8. Crear Pedido");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {

                case 1 -> Clienteview.gestionarClientes(sc);

                case 2 -> Vehiculoview.gestionarVehiculos(sc);

                case 3 -> System.out.println("Funcionalidad en desarrollo: Registrar Reparación");

                case 4 -> System.out.println("Registrando empleado...");

                case 5 -> {
                    Inventarioview.gestionarInventario(sc);
                    System.out.println("Inventario gestionado.");
                }

                case 6 -> System.out.println("Funcionalidad en desarrollo: Registrar proveedor");

                case 7 -> {
                    Citaview.gestionarCitas(sc);
                    System.out.println("Citas gestionadas.");
                }

                case 8 -> System.out.println("Funcionalidad en desarrollo: Crear Pedido");

                case 9 -> {
                    System.out.println("Saliendo del sistema...");
                    System.exit(0); 
                }

                default -> System.out.println("Opción no válida.");
            }

        } while (opcion != 9); 
    }
}

            
                /* 
                    private static void registrarPedido(Scanner sc) {
                        
                        System.out.println("Introduce nombre producto");
                        String producto=sc.nextLine();
                        System.out.println("Introduce proveedor");
                        String proveedor=sc.nextLine();
                        System.out.println("Introduce cantidad");
                        int cantidad=sc.nextInt();
                        
                        System.out.println("Introduce la fecha"); 
                        String fecha_pedido=sc.nextLine(); 
                        Pedido nuevoPedido = new Pedido(producto, proveedor, cantidad, precio,fecha_pedido);
                                    
                                PedidoDAO.agregarPedido(nuevoPedido);*/

          
	
	
	/*private static void registrarReparacion(Scanner sc){
		System.out.println("");
	}*/
	



      



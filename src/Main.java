

    import dao.CitaDAO;
    import dao.ClienteDAO;
    import dao.InventarioDAO;
    import dao.VehiculoDAO;
    import java.time.LocalDateTime;
    import java.util.List;
    import java.util.Scanner;
    import model.Cita;
    import model.Cliente;
    import model.Inventario;
    import model.Vehiculo;
   import view.Citaview;
   import view.Clienteview;
    
    public class Main {

        public static void main(String[] args) {
            

            Scanner sc= new Scanner(System.in);
            int opcion;

            

                
            
                    do {
                        System.out.println("Gestión de Taller, elige lo que necesitas: ");
                        System.out.println("1. Registrar un cliente");
                        System.out.println("2. Registrar una Reparacion");
                        System.out.println("3. Registrar empleado");
                        System.out.println("4. Revisar inventario");
                        System.out.println("5. Registrar proveedor");
                        System.out.println("6. Gestionar citas");
                        System.out.println("7. Crear Pedido");
                        System.out.println("8. Modificar o borrar cliente");
                        System.out.println("9 .Modificar o borrar Reparacion");
                        System.out.println("10 .Modificar o borrar empleado");
                        System.out.println("11 .Modificar o borrar proveedor");
                        System.out.println("12 .Modificar o borrar servicio");
                        System.out.println("13 .Modificar o borrar pedido");
                        System.out.println("14 .Salir");
                        System.out.print("Seleccione una opción: ");
                        opcion = sc.nextInt();
                        sc.nextLine(); // Limpiar buffer
                        
                        switch (opcion) {
                            
                            case 1 -> Clienteview.gestionarClientes(sc);
                        // case 2 -> registrarReparacion(sc);
                            case 3 -> System.out.println("Registrando Pedido...");
                                
                            case 4 -> {
                                gestionarInventario(sc);
                                System.out.println("Revisando Inventario...");
                            }
                                
                            case 5 ->{}
                        
                            case 6 -> {
                                Citaview.gestionarCitas(sc);
                                System.out.println("Gestionar citas...");
                            }
                                
                            case 7 -> {
                            }
                            case 8 -> System.out.println("Saliendo del sistema...");
                            case 9 -> System.out.println("Saliendo del sistema...");
                            case 10 -> System.out.println("Saliendo del sistema...");
                            case 11 -> System.out.println("Saliendo del sistema...");
                            case 12 -> System.out.println("Saliendo del sistema...");
                            case 13 -> System.out.println("Saliendo del sistema...");
                            case 14 -> System.out.println("Saliendo del sistema...");
                            default -> System.out.println(" Opción no válida.");
                        }   } while (opcion != 14);
                   

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
	
	private static void gestionarInventario(Scanner sc) {
        System.out.println("Gestión de Inventario:");
        System.out.println("1. Registrar nuevo producto");
        System.out.println("2. Modificar producto");
        System.out.println("3. ver inventario");
        System.out.println("4. Eliminar producto");
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
            default -> System.out.println("Opción no válida.");
        }
    }


        
    }



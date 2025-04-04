

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
                            
                            case 1 -> gestionarClientes(sc);
                        // case 2 -> registrarReparacion(sc);
                            case 3 -> System.out.println("Registrando Pedido...");
                                
                            case 4 -> {
                                gestionarInventario(sc);
                                System.out.println("Revisando Inventario...");
                            }
                                
                            case 5 ->{}
                        
                            case 6 -> {
                                gestionarCitas(sc);
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
                    /*// Obtener y mostrar todos los clientes
                    System.out.println("Lista de clientes:");
                    for (Cliente cliente : ClienteDAO.obtenerClientes()) {
                    System.out.println(cliente);
                    }
                    // Actualizar cliente
                    nuevoCliente.setTelefono("600987654");
                    ClienteDAO.actualizarCliente(nuevoCliente);
                    // Eliminar cliente (descomentar para probar)
                    ClienteDAO.eliminarCliente(nuevoCliente.getId());*/

                }
            

            private static void gestionarClientes(Scanner sc) {
                System.out.println("Gestión de Clientes:");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Modificar cliente");
                System.out.println("3. Eliminar cliente");
                System.out.println("4. Revisar cliente");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();
        
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
            

            switch (opcion) {
                case 1 -> {
                                System.out.println("Introduce nombre:");
                                String nombre = sc.nextLine();
                                System.out.println("Introduce apellido:");
                                String apellido = sc.nextLine();
                                System.out.println("Introduce DNI:");
                                String dni = sc.nextLine();
                                System.out.println("Introduce telefono:");
                                String telefono = sc.nextLine();
                                System.out.println("Introduce correo:");
                                String correo = sc.nextLine();
                                System.out.println("Introduce direccion:");
                                String direccion = sc.nextLine();
                                Cliente nuevoCliente = new Cliente(nombre, apellido, dni, telefono, correo, direccion);
                                ClienteDAO.agregarCliente(nuevoCliente);
                                System.out.println("Cliente registrado correctamente.");
                }
                case 2 -> {
                                System.out.println("Introduce ID del cliente a modificar:");
                                int idModificar = sc.nextInt();
                                sc.nextLine();
                                
                                Cliente clienteModificado = ClienteDAO.obtenerClientePorId(idModificar);
                                
                                if (clienteModificado == null) {
                                    System.out.println("Cliente no encontrado.");
                                    break;
                                }
                                
                                System.out.println("Introduce nuevo nombre:");
                                clienteModificado.setNombre(sc.nextLine());
                                System.out.println("Introduce nuevo apellido:");
                                clienteModificado.setApellido(sc.nextLine());
                                System.out.println("Introduce nuevo telefono:");
                                clienteModificado.setTelefono(sc.nextLine());
                                System.out.println("Introduce nuevo correo:");
                                clienteModificado.setCorreo(sc.nextLine());
                                System.out.println("Introduce nueva direccion:");
                                clienteModificado.setDireccion(sc.nextLine());
                                
                                ClienteDAO.actualizarCliente(clienteModificado);
                                System.out.println("Cliente modificado correctamente.");
                }

				
			
			case 3 -> {
                    System.out.println("Introduce ID del cliente a eliminar:");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    ClienteDAO.eliminarCliente(idEliminar);
                    System.out.println("Cliente eliminado correctamente.");
                }
			case 4 -> {
                    System.out.println("Introduce ID del cliente a revisar:");
                    int idRevisar = sc.nextInt();
                    sc.nextLine();
                     Cliente clienteRevisado = ClienteDAO.obtenerClientePorId(idRevisar);
                     if (clienteRevisado != null) {
                         System.out.println("Cliente encontrado: " + clienteRevisado);
                     } else {
                         System.out.println("Cliente no encontrado.");
                     }
                }
				default -> System.out.println("Opción no válida.");
			}
		}
	
	
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


        private static void gestionarCitas(Scanner sc) {
            System.out.println("Gestión de Citas:");
            System.out.println("1. Agregar Cita");
            System.out.println("2. Ver Citas");
            System.out.println("3. Actualizar Cita");
            System.out.println("4. Eliminar Cita");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.println("Introduce ID del cliente:");
                    int idCliente = sc.nextInt();
                    sc.nextLine();
                    Cliente cliente = ClienteDAO.obtenerClientePorId(idCliente);
                    
                    System.out.println("Introduce matrícula del vehículo:");
                    String matricula = sc.nextLine();
                    Vehiculo vehiculo = VehiculoDAO.obtenerVehiculoPorMatricula(matricula);
                    
                    System.out.println("Introduce fecha y hora (YYYY-MM-DDTHH:MM):");
                    String fechaStr = sc.nextLine();
                    LocalDateTime fecha = LocalDateTime.parse(fechaStr);
                    
                    Cita nuevaCita = new Cita(0, cliente, vehiculo, fecha);
                    CitaDAO.agregarCita(nuevaCita);
                    System.out.println("Cita registrada correctamente.");
                    }
                case 2 -> {
                    List<Cita> citas = CitaDAO.obtenerCitas();
                    citas.forEach(System.out::println);
                    }
                case 3 -> {
                    System.out.println("Introduce ID de la cita a modificar:");
                    int idModificar = sc.nextInt();
                    sc.nextLine();
                    Cita citaModificada = CitaDAO.obtenerCitaPorId(idModificar);
                    if (citaModificada == null) {
                        System.out.println("Cita no encontrada.");
                        break;
                    }   System.out.println("Introduce nueva fecha y hora (YYYY-MM-DDTHH:MM):");
                        String fechaStr = sc.nextLine();
                        citaModificada.setFecha(LocalDateTime.parse(fechaStr));
                        CitaDAO.actualizarCita(citaModificada);
                        System.out.println("Cita actualizada correctamente.");
                    }
                case 4 -> {
                    System.out.println("Introduce ID de la cita a eliminar:");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    CitaDAO.eliminarCita(idEliminar);
                    System.out.println("Cita eliminada correctamente.");
                    }
                default -> System.out.println("Opción no válida.");
            }
        }
    }



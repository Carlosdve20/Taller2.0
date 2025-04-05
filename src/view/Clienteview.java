package view;

import dao.ClienteDAO;
import java.util.Scanner;
import model.Cliente;

public class Clienteview {
    
    public static void gestionarClientes(Scanner sc) {
                System.out.println("Gestión de Clientes:");
                System.out.println("1. Registrar cliente");
                System.out.println("2. Modificar cliente");
                System.out.println("3. Eliminar cliente");
                System.out.println("4. Revisar cliente");
                System.out.println("5. Volver al menú principal");
                System.out.print("Seleccione una opción: ");
                int opcion = sc.nextInt();
                sc.nextLine();
        
            

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
                System.out.println("Introduce Nº socio:(ID) del cliente a modificar:");
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
                System.out.println("Introduce Nº socio:(ID) del cliente a eliminar:");
                int idEliminar = sc.nextInt();
                sc.nextLine();
                ClienteDAO.eliminarCliente(idEliminar);
                System.out.println("Cliente eliminado correctamente.");
                }
		case 4 -> {
                System.out.println("Introduce Nº socio:(ID) del cliente a revisar:");
                int idRevisar = sc.nextInt();
                sc.nextLine();
                 Cliente clienteRevisado = ClienteDAO.obtenerClientePorId(idRevisar);
                 if (clienteRevisado != null) {
                     System.out.println("Cliente encontrado: " + clienteRevisado);
                 } else {
                     System.out.println("Cliente no encontrado.");
                 }
                
                }
                
                case 5 -> {System.out.println("Volviendo al menú principal...");
                
            }
				default -> System.out.println("Opción no válida.");
			}
		}
}

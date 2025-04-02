

import dao.ClienteDAO;
import java.util.Scanner;
import model.Cliente;

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
            System.out.println("6. Registrar Servicio");
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
                
                case 1:
					registraCliente(sc);
                    break;
                case 2:
					registrarReparacion(sc);
                    break;
                case 3:
                    System.out.println("Revisando Inventario...");
                    break;
            
                case 4:
                    System.out.println("Registrando Pedido...");
                    break;
                case 5:
                 
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 7:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 8:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 9:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 10:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 11:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 12:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 13:
                    System.out.println("Saliendo del sistema...");
                    break;
				case 14:
                    System.out.println("Saliendo del sistema...");
                    break;				
                default:
                    System.out.println(" Opción no válida.");
            }
        } while (opcion != 14);


        sc.close();
    


	       

	     

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

	private static void registraCliente(Scanner sc) {
		System.out.println("Introduce nombre");
		String nombre=sc.nextLine();
		System.out.println("Introduce apellido");
		String apellido=sc.nextLine();
		System.out.println("Introduce DNI");
		String dni=sc.nextLine();
		System.out.println("Introduce telefono");
		String telefono=sc.nextLine();
		System.out.println("Introduce correo");
		String correo=sc.nextLine();
		System.out.println("Introduce direccion");
		String direccion=sc.nextLine();
		   // Crear un cliente nuevo
		   Cliente nuevoCliente = new Cliente(nombre, apellido, dni, telefono,correo ,direccion);
			        // Insertar el cliente en la BD
		        ClienteDAO.agregarCliente(nuevoCliente);
	}
	
	private static void registrarReparacion(Scanner sc){
		System.out.println("");
	}
	}        



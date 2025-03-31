

import dao.ClienteDAO;
import model.Cliente;

public class Main {

	public static void main(String[] args) {


	        // Crear un cliente nuevo
	        Cliente nuevoCliente = new Cliente("Juan", "Pérez", "12345678X", "678123456", "juan@mail.com", "Calle Falsa 123");

	        // Insertar el cliente en la BD
	        ClienteDAO.agregarCliente(nuevoCliente);

	        // Obtener y mostrar todos los clientes
	        System.out.println("Lista de clientes:");
	        for (Cliente cliente : ClienteDAO.obtenerClientes()) {
	            System.out.println(cliente);
	        }

	        // Actualizar cliente
	        nuevoCliente.setTelefono("600987654");
	        ClienteDAO.actualizarCliente(nuevoCliente);

	        // Eliminar cliente (descomentar para probar)
	        // ClienteDAO.eliminarCliente(nuevoCliente.getId());
	    }
	}



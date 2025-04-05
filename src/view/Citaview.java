package view;


import dao.CitaDAO;
import dao.ClienteDAO;
import dao.VehiculoDAO;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import model.Cita;
import model.Cliente;
import model.Vehiculo;

public class Citaview {
    
      public static void gestionarCitas(Scanner sc) {


            System.out.println("Gestión de Citas:");
            System.out.println("1. Agregar Cita");
            System.out.println("2. Ver Citas");
            System.out.println("3. Actualizar Cita");
            System.out.println("4. Eliminar Cita");

            System.out.println("5. Volver al menú principal");

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

                    
                case 5 -> {System.out.println("Volviendo al menú principal...");
                }
                default -> System.out.println("Opción no válida.");
            }
            
        }
    }



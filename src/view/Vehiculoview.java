package view;

import dao.VehiculoDAO;
import java.util.List;
import java.util.Scanner;
import model.Vehiculo;

public class Vehiculoview {

    public static void gestionarVehiculos(Scanner sc) {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Vehículos ---");
            System.out.println("1. Agregar vehículo");
            System.out.println("2. Ver vehículos");
            System.out.println("3. Actualizar vehículo");
            System.out.println("4. Eliminar vehículo");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> agregarVehiculo(sc);
                case 2 -> verVehiculos();
                case 3 -> actualizarVehiculo(sc);
                case 4 -> eliminarVehiculo(sc);
                case 5 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void agregarVehiculo(Scanner sc) {
        System.out.println("\n--- Agregar Vehículo ---");
        System.out.print("Ingrese la matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Ingrese la marca: ");
        String marca = sc.nextLine();
        System.out.print("Ingrese el modelo: ");
        String modelo = sc.nextLine();

        Vehiculo nuevo = new Vehiculo(matricula, marca, modelo);
        VehiculoDAO.agregarVehiculo(nuevo);
        System.out.println("Vehículo agregado correctamente.");
    }

    private static void verVehiculos() {
        System.out.println("\n--- Lista de Vehículos ---");
        List<Vehiculo> vehiculos = VehiculoDAO.obtenerVehiculos();
        for (Vehiculo v : vehiculos) {
            System.out.println(v);
        }
    }

    private static void actualizarVehiculo(Scanner sc) {
        System.out.println("\n--- Actualizar Vehículo ---");
        System.out.print("Ingrese la matrícula del vehículo a actualizar: ");
        String matricula = sc.nextLine();

        Vehiculo existente = VehiculoDAO.obtenerVehiculoPorMatricula(matricula);
        if (existente != null) {
            System.out.print("Nueva marca: ");
            String marca = sc.nextLine();
            System.out.print("Nuevo modelo: ");
            String modelo = sc.nextLine();

            existente.setMarca(marca);
            existente.setModelo(modelo);
            VehiculoDAO.actualizarVehiculo(existente);
            System.out.println("Vehículo actualizado correctamente.");
        } else {
            System.out.println("Vehículo no encontrado.");
        }
    }

    private static void eliminarVehiculo(Scanner sc) {
        System.out.println("\n--- Eliminar Vehículo ---");
        System.out.print("Ingrese la matrícula del vehículo a eliminar: ");
        String matricula = sc.nextLine();

        VehiculoDAO.eliminarVehiculo(matricula);
        System.out.println("Vehículo eliminado si existía.");
    }
} 

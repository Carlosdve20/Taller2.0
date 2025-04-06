package view;

import dao.EmpleadoDAO;
import dao.ReparacionDAO;

import java.util.List;
import java.util.Scanner;
import model.Empleado;

public class Empleadoview {

    public static void gestionarEmpleados(Scanner sc) {
        System.out.println("Gestión de Empleados:");
        System.out.println("1. Registrar empleado");
        System.out.println("2. Modificar empleado");
        System.out.println("3. Eliminar empleado");
        System.out.println("4. Revisar empleado");
        System.out.println("5. Listar todos los empleados");
        System.out.println("6. Volver al menú principal");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); // Consumir la nueva línea

        switch (opcion) {
            case 1 -> {
                System.out.println("Introduce nombre:");
                String nombre = sc.nextLine();
                System.out.println("Introduce apellido:");
                String apellido = sc.nextLine();
                System.out.println("Introduce DNI:");
                String dni = sc.nextLine();
                System.out.println("Introduce puesto:");
                String puesto = sc.nextLine();
                System.out.println("Introduce salario:");
                double salario = sc.nextDouble();
                sc.nextLine(); // Consumir la nueva línea
                Empleado nuevoEmpleado = new Empleado(nombre, apellido, dni, puesto, salario, true);
                EmpleadoDAO.agregarEmpleado(nuevoEmpleado);
                System.out.println("Empleado registrado correctamente.");
            }
                  case 2 -> {
                System.out.println("Introduce ID del empleado a modificar:");
                int idModificar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea

                Empleado empleadoModificado = EmpleadoDAO.obtenerEmpleadoPorId(idModificar);

                if (empleadoModificado == null) {
                    System.out.println("Empleado no encontrado.");
                    break;
                }

                System.out.println("Introduce nuevo nombre (" + empleadoModificado.getNombre() + "):");
                String nuevoNombre = sc.nextLine();
                if (!nuevoNombre.isEmpty()) {
                    empleadoModificado.setNombre(nuevoNombre);
                }
                System.out.println("Introduce nuevo apellido (" + empleadoModificado.getApellido() + "):");
                String nuevoApellido = sc.nextLine();
                if (!nuevoApellido.isEmpty()) {
                    empleadoModificado.setApellido(nuevoApellido);
                }
                System.out.println("Introduce nuevo DNI (" + empleadoModificado.getDni() + "):");
                String nuevoDni = sc.nextLine();
                if (!nuevoDni.isEmpty()) {
                    empleadoModificado.setDni(nuevoDni);
                }
                System.out.println("Introduce nuevo puesto (" + empleadoModificado.getPuesto() + "):");
                String nuevoPuesto = sc.nextLine();
                if (!nuevoPuesto.isEmpty()) {
                    empleadoModificado.setPuesto(nuevoPuesto);
                }
                System.out.println("Introduce nuevo salario (" + empleadoModificado.getSalario() + "):");
                String nuevoSalarioStr = sc.nextLine();
                if (!nuevoSalarioStr.isEmpty()) {
                    try {
                        double nuevoSalario = Double.parseDouble(nuevoSalarioStr);
                        empleadoModificado.setSalario(nuevoSalario);
                    } catch (NumberFormatException e) {
                        System.out.println("Formato de salario no válido, manteniendo el valor anterior.");
                    }
                }
                System.out.println("¿Nueva disponibilidad? (" + empleadoModificado.isDisponibilidad() + ") (true/false, dejar vacío para no cambiar):");
                String nuevaDisponibilidadStr = sc.nextLine();
                if (!nuevaDisponibilidadStr.isEmpty()) {
                    boolean nuevaDisponibilidad = Boolean.parseBoolean(nuevaDisponibilidadStr);
                    // **Validación para no cambiar disponibilidad si está en reparación activa**
                    if (nuevaDisponibilidad && ReparacionDAO.estaEmpleadoEnReparacionActiva(empleadoModificado.getId())) {
                        System.out.println("Advertencia: Este empleado está actualmente asignado a una reparación activa. Cambiar su disponibilidad a true podría generar inconsistencias.");
                        System.out.print("¿Desea continuar de todos modos? (s/n): ");
                        String confirmacion = sc.nextLine().toLowerCase();
                        if (confirmacion.equals("s")) {
                            empleadoModificado.setDisponibilidad(nuevaDisponibilidad);
                        } else {
                            System.out.println("Cambio de disponibilidad cancelado.");
                        }
                    } else {
                        empleadoModificado.setDisponibilidad(nuevaDisponibilidad);
                    }
                }

                EmpleadoDAO.actualizarEmpleado(empleadoModificado);
                System.out.println("Empleado modificado correctamente.");
            }
            case 3 -> {
                System.out.println("Introduce ID del empleado a eliminar:");
                int idEliminar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea
                EmpleadoDAO.eliminarEmpleado(idEliminar);
                System.out.println("Empleado eliminado correctamente.");
            }
            case 4 -> {
                System.out.println("Introduce ID del empleado a revisar:");
                int idRevisar = sc.nextInt();
                sc.nextLine(); // Consumir la nueva línea
                Empleado empleadoRevisado = EmpleadoDAO.obtenerEmpleadoPorId(idRevisar);
                if (empleadoRevisado != null) {
                    System.out.println("Empleado encontrado: " + empleadoRevisado);
                } else {
                    System.out.println("Empleado no encontrado.");
                }
            }
            case 5 -> {
                List<Empleado> empleados = EmpleadoDAO.obtenerTodosEmpleados();
                if (empleados.isEmpty()) {
                    System.out.println("No hay empleados registrados.");
                } else {
                    System.out.println("Lista de todos los empleados:");
                    for (Empleado empleado : empleados) {
                        System.out.println(empleado);
                    }
                }
            }
            case 6 -> {
                System.out.println("Volviendo al menú principal...");
            }
            default -> System.out.println("Opción no válida.");
        }
    }
}

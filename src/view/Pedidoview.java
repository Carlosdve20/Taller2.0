package view;

import dao.PedidoDAO;
import model.Pedido;
import model.Inventario;
import model.Proveedor;

import java.time.LocalDate;
import java.util.Scanner;

public class Pedidoview {

    public static void gestionarPedido(Scanner sc) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("GESTIÓN DE PEDIDOS ");
            System.out.println("1. Agregar pedido");
            System.out.println("2. Buscar pedido por ID");
            System.out.println("3. Eliminar pedido");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1 -> {
                    try {
                        Pedido pedido = new Pedido();

                        System.out.print("ID producto: ");
                        int idProducto = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("ID proveedor: ");
                        int idProveedor = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Cantidad: ");
                        int cantidad = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Precio: ");
                        double precio = scanner.nextDouble();
                        scanner.nextLine();

                        pedido.setProducto(new Inventario(idProducto));
                        pedido.setProveedor(new Proveedor(idProveedor));
                        pedido.setCantidad(cantidad);
                        pedido.setPrecio(precio);
                        pedido.setFechaPedido(LocalDate.now());

                        PedidoDAO.agregarPedido(pedido);
                    } catch (Exception e) {
                        System.out.println("Error al agregar pedido... " + e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        System.out.print("Ingrese el ID del pedido: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        Pedido pedido = PedidoDAO.obtenerPedidoPorId(id);
                        if (pedido != null) {
                            System.out.println("Pedido encontrado:");
                            System.out.println("ID: " + pedido.getId());
                            System.out.println("Producto ID: " + pedido.getProducto().getId());
                            System.out.println("Proveedor ID: " + pedido.getProveedor().getId());
                            System.out.println("Cantidad: " + pedido.getCantidad());
                            System.out.println("Precio: " + pedido.getPrecio());
                            System.out.println("Fecha Pedido: " + pedido.getFechaPedido());
                        } else {
                            System.out.println("No se encontró el pedido.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al buscar pedido... " + e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        System.out.print("Ingrese el ID del pedido a eliminar: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();

                        PedidoDAO.eliminarPedido(id);
                    } catch (Exception e) {
                        System.out.println("Error al eliminar pedido... " + e.getMessage());
                    }
                }

                case 4 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción no válida");
            }

        } while (opcion != 4);
    }
}

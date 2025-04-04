/*package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;

public class PedidoDAO{

    

        public static void agregarPedido(Pedido pedido) {
    Connection conexion = ConexionBD.conectar();
    if (conexion != null) {
        String query = "INSERT INTO Pedido (producto, proveedor, cantidad, precio, fecha_pedido) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, pedido.getProducto().getId());
            stmt.setInt(2, pedido.getProveedor().getId());
            stmt.setInt(3, pedido.getCantidad()); // Si cantidad es int
            stmt.setDouble(4, pedido.getPrecio()); 
            stmt.setDate(5, java.sql.Date.valueOf(pedido.getFechaPedido()));

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    pedido.setId(generatedKeys.getInt(1)); // Obtener el ID autogenerado
                }
                System.out.println("Pedido agregado con éxito. ID asignado: " + pedido.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar pedido: " + e.getMessage());
        } finally {
            try {
                conexion.close(); // Cerrar la conexión después de la operación
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}


        public static List<Pedido> obtenerPedidos() {
            List<Pedido> listaPedidos=new ArrayList<>();
            Connection conexion = ConexionBD.conectar();
            if( conexion != null) {
                String query="SELECT * from Pedido";
                try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                    while(rs.next()){
                        Pedido pedido = new Pedido (

                            rs.getInt("id"), 
                            InventarioDAO.obtenerInventarioPorId(rs.getInt("id_producto")),
                            ProveedorDAO.obtenerProveedorPorId(rs.getInt("id_proveedor")), 
                            rs.getInt("cantidad"), 
                            rs.getDouble("precio"), 
                            rs.getDate("fechaPedido").toLocalDate()
                        );
                        listaPedidos.add(pedido);
                    }
                } catch (SQLException e) {
                    System.out.println("Error al obtener lista de pedidos: " + e.getMessage());
                } finally {
                    try {
                        conexion.close();
                    } catch (SQLException e) {
                        System.out.println("Error al cerrar la conexión: " + e.getMessage());
                    }
                }
            
            return listaPedidos;
        }
    }
     public static void modificarPedido(Pedido pedido) {
    Connection conexion = ConexionBD.conectar();
    if (conexion != null) {
        String query = "UPDATE Pedido SET producto = ?, proveedor = ?, cantidad = ?, precio = ?, fecha_pedido = ? WHERE id = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, pedido.getProducto().getId());
            stmt.setInt(2, pedido.getProveedor().getId());
            stmt.setInt(3, pedido.getCantidad()); // Si cantidad es int
            stmt.setDouble(4, pedido.getPrecio()); 
            stmt.setDate(5, java.sql.Date.valueOf(pedido.getFechaPedido()));

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El pedido ha sido modificado.");
            } else {
                System.out.println("No se encontró el pedido con ID: " + pedido.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error al modificar pedido: " + e.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

        public static void eliminarPedido(int id){
            Connection conexion = ConexionBD.conectar();
            if (conexion != null) {
                String query = "DELETE FROM Pedido WHERE id = ?";
                try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                    stmt.setInt(1, id);
                    int filasEliminadas = stmt.executeUpdate();
                    if (filasEliminadas > 0) {
                        System.out.println("Pedido eliminado con éxito.");
                    }
                } catch (SQLException e) {
                    System.out.println("Error al eliminar el pedido... " + e.getMessage());
                }
                
            }
        }
    } */

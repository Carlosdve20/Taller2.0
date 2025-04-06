package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;
import model.Inventario;
import model.Proveedor;
public class PedidoDAO {

    // Agregar un nuevo pedido
    public static void agregarPedido(Pedido pedido) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "INSERT INTO Pedido (producto_id, proveedor_id, cantidad, precio, fecha_pedido) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conexion.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, pedido.getProducto().getId());
                stmt.setInt(2, pedido.getProveedor().getId());
                stmt.setInt(3, pedido.getCantidad());
                stmt.setDouble(4, pedido.getPrecio());
                stmt.setDate(5, Date.valueOf(pedido.getFechaPedido()));

                int filas = stmt.executeUpdate();
                if (filas > 0) {
                    ResultSet keys = stmt.getGeneratedKeys();
                    if (keys.next()) {
                        pedido.setId(keys.getInt(1));
                    }
                    System.out.println("Pedido agregado correctamente. ID: " + pedido.getId());
                }
            } catch (SQLException e) {
                System.out.println("Error al agregar pedido: " + e.getMessage());
            }
        }
    }

    // Obtener todos los pedidos
    public static List<Pedido> obtenerPedidos() {
        List<Pedido> lista = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Pedido";
            try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    Pedido pedido = new Pedido();
                    pedido.setId(rs.getInt("id"));
                    pedido.setCantidad(rs.getInt("cantidad"));
                    pedido.setPrecio(rs.getDouble("precio"));
                    pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());

                    // Puedes cargar producto y proveedor usando sus DAOs aquí si los necesitas completos
                    Inventario producto = new Inventario();
                    producto.setId(rs.getInt("producto_id"));
                    pedido.setProducto(producto);

                    Proveedor proveedor = new Proveedor();
                    proveedor.setId(rs.getInt("proveedor_id"));
                    pedido.setProveedor(proveedor);

                    lista.add(pedido);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener pedidos: " + e.getMessage());
            }
        }
        return lista;
    }

    // Actualizar un pedido existente
    public static void actualizarPedido(Pedido pedido) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "UPDATE Pedido SET producto_id = ?, proveedor_id = ?, cantidad = ?, precio = ?, fecha_pedido = ? WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, pedido.getProducto().getId());
                stmt.setInt(2, pedido.getProveedor().getId());
                stmt.setInt(3, pedido.getCantidad());
                stmt.setDouble(4, pedido.getPrecio());
                stmt.setDate(5, Date.valueOf(pedido.getFechaPedido()));
                stmt.setInt(6, pedido.getId());

                int filas = stmt.executeUpdate();
                if (filas > 0) {
                    System.out.println("Pedido actualizado correctamente.");
                }
            } catch (SQLException e) {
                System.out.println("Error al actualizar pedido: " + e.getMessage());
            }
        }
    }

    // Eliminar un pedido por ID
    public static void eliminarPedido(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "DELETE FROM Pedido WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                int filas = stmt.executeUpdate();
                if (filas > 0) {
                    System.out.println("Pedido eliminado correctamente.");
                }
            } catch (SQLException e) {
                System.out.println("Error al eliminar pedido: " + e.getMessage());
            }
        }
    }

    // Obtener pedido por ID
    public static Pedido obtenerPedidoPorId(int id) {
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Pedido WHERE id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        Pedido pedido = new Pedido();
                        pedido.setId(rs.getInt("id"));
                        pedido.setCantidad(rs.getInt("cantidad"));
                        pedido.setPrecio(rs.getDouble("precio"));
                        pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());

                        Inventario producto = new Inventario();
                        producto.setId(rs.getInt("producto_id"));
                        pedido.setProducto(producto);

                        Proveedor proveedor = new Proveedor();
                        proveedor.setId(rs.getInt("proveedor_id"));
                        pedido.setProveedor(proveedor);

                        return pedido;
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener pedido: " + e.getMessage());
            }
        }
        return null;
    }

    // Buscar pedidos por proveedor ID
    public static List<Pedido> buscarPedidosPorProveedor(int proveedorId) {
        List<Pedido> lista = new ArrayList<>();
        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            String query = "SELECT * FROM Pedido WHERE proveedor_id = ?";
            try (PreparedStatement stmt = conexion.prepareStatement(query)) {
                stmt.setInt(1, proveedorId);
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Pedido pedido = new Pedido();
                        pedido.setId(rs.getInt("id"));
                        pedido.setCantidad(rs.getInt("cantidad"));
                        pedido.setPrecio(rs.getDouble("precio"));
                        pedido.setFechaPedido(rs.getDate("fecha_pedido").toLocalDate());

                        Inventario producto = new Inventario();
                        producto.setId(rs.getInt("producto_id"));
                        pedido.setProducto(producto);

                        Proveedor proveedor = new Proveedor();
                        proveedor.setId(rs.getInt("proveedor_id"));
                        pedido.setProveedor(proveedor);

                        lista.add(pedido);
                    }
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar pedidos por proveedor: " + e.getMessage());
            }
        }
        return lista;
    }
}

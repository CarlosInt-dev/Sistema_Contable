package module_3.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    
    // Variables para la conexión a MariaDB
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    // 1. Método para LISTAR todo el inventario y mostrarlo en tu tabla
    public List<Inventario> listar() {
        List<Inventario> lista = new ArrayList<>();
        String sql = "SELECT * FROM inventario";
        try {
            // ¡Aquí está el cambio! Llamamos directamente a la clase Conection
            con = Conection.conect(); 
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Inventario inv = new Inventario();
                inv.setId_inventario(rs.getInt("id_inventario"));
                inv.setId_producto(rs.getInt("id_producto"));
                inv.setCantidad_actual(rs.getDouble("cantidad_actual"));
                inv.setCosto_promedio(rs.getDouble("costo_promedio"));
                inv.setFecha_actualizacion(rs.getString("fecha_actualizacion"));
                lista.add(inv);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar inventario: " + e.toString());
        }
        return lista;
    }
    
    // 2. Método para CREAR un nuevo registro de inventario
    public boolean insertar(Inventario inv) {
        String sql = "INSERT INTO inventario (id_producto, cantidad_actual, costo_promedio) VALUES (?, ?, ?)";
        try {
            con = Conection.conect();
            ps = con.prepareStatement(sql);
            ps.setInt(1, inv.getId_producto());
            ps.setDouble(2, inv.getCantidad_actual());
            ps.setDouble(3, inv.getCosto_promedio());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al insertar inventario: " + e.toString());
            return false;
        }
    }
    
    // 3. Método para ACTUALIZAR el stock
    public boolean actualizarStock(Inventario inv) {
        String sql = "UPDATE inventario SET cantidad_actual = ?, costo_promedio = ? WHERE id_inventario = ?";
        try {
            con = Conection.conect();
            ps = con.prepareStatement(sql);
            ps.setDouble(1, inv.getCantidad_actual());
            ps.setDouble(2, inv.getCosto_promedio());
            ps.setInt(3, inv.getId_inventario());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock: " + e.toString());
            return false;
        }
    }
}
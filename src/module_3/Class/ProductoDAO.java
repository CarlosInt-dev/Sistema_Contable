package module_3.Class;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Método para listar los productos en la tabla
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT id_producto, codigo, nombre, descripcion, precio_compra FROM producto";
        
        try {
            con = Conection.conect();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                Producto p = new Producto();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setPrecioCompra(rs.getDouble("precio_compra"));
                lista.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.toString());
        }
        return lista;
    }
}

package module_3.Class;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroComprasIVADAO {

    // =========================
    // INSERTAR
    // =========================
    public boolean insertar(LibroComprasIVA obj) {
        String sql = "INSERT INTO libro_compras_iva "
                + "(id_empresa, id_orden, id_proveedor, numero_documento, fecha, monto_gravado, iva, total) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, obj.getIdEmpresa());

            if (obj.getIdOrden() != null) {
                ps.setInt(2, obj.getIdOrden());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setInt(3, obj.getIdProveedor());
            ps.setString(4, obj.getNumeroDocumento());
            ps.setDate(5, obj.getFecha());
            ps.setBigDecimal(6, obj.getMontoGravado());
            ps.setBigDecimal(7, obj.getIva());
            ps.setBigDecimal(8, obj.getTotal());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error insertar libro IVA: " + e.getMessage());
            return false;
        }
    }

    // =========================
    // LISTAR
    // =========================
    public List<LibroComprasIVA> listar() {
        List<LibroComprasIVA> lista = new ArrayList<>();
        String sql = "SELECT * FROM libro_compras_iva ORDER BY fecha DESC";

        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LibroComprasIVA obj = new LibroComprasIVA();
                obj.setIdLibro(rs.getInt("id_libro"));
                obj.setIdEmpresa(rs.getInt("id_empresa"));

                int idOrdenValor = rs.getInt("id_orden");
                obj.setIdOrden(rs.wasNull() ? null : idOrdenValor);

                obj.setIdProveedor(rs.getInt("id_proveedor"));
                obj.setNumeroDocumento(rs.getString("numero_documento"));
                obj.setFecha(rs.getDate("fecha"));
                obj.setMontoGravado(rs.getBigDecimal("monto_gravado"));
                obj.setIva(rs.getBigDecimal("iva"));
                obj.setTotal(rs.getBigDecimal("total"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            System.out.println("Error listar libro IVA: " + e.getMessage());
        }
        return lista;
    }

    // =========================
    // ELIMINAR
    // =========================
    public boolean eliminar(int idLibro) {
        String sql = "DELETE FROM libro_compras_iva WHERE id_libro = ?";

        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminar libro IVA: " + e.getMessage());
            return false;
        }
    }

    // =========================
    // VALIDAR FACTURA DUPLICADA
    // =========================
    public boolean existeFactura(int idEmpresa, int idProveedor, String numeroDoc) {
        String sql = "SELECT COUNT(*) FROM libro_compras_iva "
                + "WHERE id_empresa = ? AND id_proveedor = ? AND numero_documento = ?";

        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ps.setInt(2, idProveedor);
            ps.setString(3, numeroDoc);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error validar factura: " + e.getMessage());
        }
        return false;
    }

    // =========================
    // ÓRDENES ELEGIBLES PARA REGISTRAR EN EL LIBRO
    // (recibidas, de la empresa, que aún no tienen registro en libro_compras_iva)
    // =========================
    public List<OrdenCompra> listarOrdenesElegibles(int idEmpresa) {
        List<OrdenCompra> lista = new ArrayList<>();
        String sql = "SELECT oc.* FROM orden_compra oc "
                + "WHERE oc.id_empresa = ? AND oc.estado = 'RECIBIDA' "
                + "AND oc.id_orden NOT IN ("
                + "    SELECT id_orden FROM libro_compras_iva WHERE id_orden IS NOT NULL"
                + ") ORDER BY oc.fecha DESC";

        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenCompra o = new OrdenCompra(
                            rs.getInt("id_orden"),
                            rs.getInt("id_empresa"),
                            rs.getString("numero_orden"),
                            rs.getInt("id_proveedor"),
                            rs.getObject("id_requisicion") != null ? rs.getInt("id_requisicion") : null,
                            rs.getDate("fecha"),
                            rs.getString("estado"),
                            rs.getBigDecimal("total")
                    );
                    lista.add(o);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error listar ordenes elegibles: " + e.getMessage());
        }
        return lista;
    }
}

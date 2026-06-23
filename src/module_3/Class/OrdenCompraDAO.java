package module_3.Class;
 
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
 
public class OrdenCompraDAO {
 
    private Connection conn;
 
    public OrdenCompraDAO() {
        this.conn = Conection.conect();
    }
 
    // ─────────────────────────────────────────
    // INSERTAR ORDEN + DETALLES (transacción)
    // ─────────────────────────────────────────
    public boolean insertarOrdenCompleta(OrdenCompra orden) {
        String sqlOrden = "INSERT INTO orden_compra (id_empresa, numero_orden, id_proveedor, " +
                          "id_requisicion, fecha, estado, total) VALUES (?,?,?,?,?,?,?)";
        String sqlDetalle = "INSERT INTO detalle_orden_compra (id_orden, id_producto, cantidad, " +
                            "precio_unitario, descuento, subtotal) VALUES (?,?,?,?,?,?)";
        try {
            conn.setAutoCommit(false);
 
            PreparedStatement psOrden = conn.prepareStatement(sqlOrden, Statement.RETURN_GENERATED_KEYS);
            psOrden.setInt(1, orden.getIdEmpresa());
            psOrden.setString(2, orden.getNumeroOrden());
            psOrden.setInt(3, orden.getIdProveedor());
            if (orden.getIdRequisicion() != null)
                psOrden.setInt(4, orden.getIdRequisicion());
            else
                psOrden.setNull(4, Types.INTEGER);
            psOrden.setDate(5, orden.getFecha());
            psOrden.setString(6, orden.getEstado());
            psOrden.setBigDecimal(7, orden.getTotal());
            psOrden.executeUpdate();
 
            // Obtener el id generado
            ResultSet rs = psOrden.getGeneratedKeys();
            int idOrdenGenerado = 0;
            if (rs.next()) idOrdenGenerado = rs.getInt(1);
 
            // Insertar cada detalle
            for (DetalleOrdenCompra d : orden.getDetalles()) {
                PreparedStatement psDet = conn.prepareStatement(sqlDetalle);
                psDet.setInt(1, idOrdenGenerado);
                psDet.setInt(2, d.getIdProducto());
                psDet.setBigDecimal(3, d.getCantidad());
                psDet.setBigDecimal(4, d.getPrecioUnitario());
                psDet.setBigDecimal(5, d.getDescuento() != null ? d.getDescuento() : BigDecimal.ZERO);
                psDet.setBigDecimal(6, d.getSubtotal());
                psDet.executeUpdate();
            }
 
            conn.commit();
            conn.setAutoCommit(true);
            return true;
 
        } catch (SQLException e) {
            try { conn.rollback(); conn.setAutoCommit(true); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
            return false;
        }
    }
 
    // ─────────────────────────────────────────
    // LISTAR ORDENES POR EMPRESA
    // ─────────────────────────────────────────
    public List<OrdenCompra> listarPorEmpresa(int idEmpresa) {
        List<OrdenCompra> lista = new ArrayList<>();
        String sql = "SELECT oc.*, p.nombre AS nombre_proveedor " +
                     "FROM orden_compra oc " +
                     "JOIN proveedor p ON oc.id_proveedor = p.id_proveedor " +
                     "WHERE oc.id_empresa = ? ORDER BY oc.fecha DESC";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
 
    // ─────────────────────────────────────────
    // LISTAR DETALLES DE UNA ORDEN
    // ─────────────────────────────────────────
    public List<DetalleOrdenCompra> listarDetalles(int idOrden) {
        List<DetalleOrdenCompra> lista = new ArrayList<>();
        String sql = "SELECT d.*, p.nombre AS nombre_producto " +
                     "FROM detalle_orden_compra d " +
                     "JOIN producto p ON d.id_producto = p.id_producto " +
                     "WHERE d.id_orden = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idOrden);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DetalleOrdenCompra d = new DetalleOrdenCompra();
                d.setIdDetalle(rs.getInt("id_detalle"));
                d.setIdOrden(rs.getInt("id_orden"));
                d.setIdProducto(rs.getInt("id_producto"));
                d.setNombreProducto(rs.getString("nombre_producto"));
                d.setCantidad(rs.getBigDecimal("cantidad"));
                d.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
                d.setDescuento(rs.getBigDecimal("descuento"));
                d.setSubtotal(rs.getBigDecimal("subtotal"));
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
 
    // ─────────────────────────────────────────
    // ACTUALIZAR ESTADO DE ORDEN
    // ─────────────────────────────────────────
    public boolean actualizarEstado(int idOrden, String nuevoEstado) {
        String sql = "UPDATE orden_compra SET estado = ? WHERE id_orden = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idOrden);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    // ─────────────────────────────────────────
    // ELIMINAR ORDEN (y sus detalles por CASCADE)
    // ─────────────────────────────────────────
    public boolean eliminarOrden(int idOrden) {
        String sql = "DELETE FROM orden_compra WHERE id_orden = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idOrden);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
 
    // ─────────────────────────────────────────
    // GENERAR NÚMERO DE ORDEN AUTOMÁTICO
    // Formato: OC-2026-0001
    // ─────────────────────────────────────────
    public String generarNumeroOrden(int idEmpresa) {
        String sql = "SELECT COUNT(*) FROM orden_compra WHERE id_empresa = ? AND YEAR(fecha) = YEAR(CURDATE())";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1) + 1;
                int anio = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                return String.format("OC-%d-%04d", anio, count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "OC-0001";
    }
}
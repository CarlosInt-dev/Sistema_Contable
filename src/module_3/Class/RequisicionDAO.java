package module_3.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RequisicionDAO {

    private Connection conn;

    public RequisicionDAO() {
        this.conn = Conection.conect();
    }

    // ─────────────────────────────────────────
    // INSERTAR REQUISICION + DETALLES (transacción)
    // ─────────────────────────────────────────
    public boolean insertarRequisicionCompleta(Requisicion req, List<DetalleRequisicion> detalles) {
        String sqlReq = "INSERT INTO requisicion (id_empresa, fecha, estado, observacion) VALUES (?,?,?,?)";
        String sqlDetalle = "INSERT INTO detalle_requisicion (id_requisicion, id_producto, cantidad, observacion) VALUES (?,?,?,?)";

        PreparedStatement psReq = null;
        PreparedStatement psDet = null;
        ResultSet rs = null;

        try {
            conn.setAutoCommit(false);

            psReq = conn.prepareStatement(sqlReq, Statement.RETURN_GENERATED_KEYS);
            psReq.setInt(1, req.getIdEmpresa());
            psReq.setDate(2, req.getFecha());
            psReq.setString(3, req.getEstado());
            psReq.setString(4, req.getObservacion());
            psReq.executeUpdate();

            rs = psReq.getGeneratedKeys();
            int idRequisicionGenerado = 0;
            if (rs.next()) {
                idRequisicionGenerado = rs.getInt(1);
            }

            psDet = conn.prepareStatement(sqlDetalle);
            for (DetalleRequisicion d : detalles) {
                psDet.setInt(1, idRequisicionGenerado);
                psDet.setInt(2, d.getIdProducto());
                psDet.setBigDecimal(3, d.getCantidad());
                psDet.setString(4, d.getObservacion());
                psDet.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (psReq != null) {
                    psReq.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (psDet != null) {
                    psDet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ─────────────────────────────────────────
    // LISTAR REQUISICIONES POR EMPRESA
    // ─────────────────────────────────────────
    public List<Requisicion> listarPorEmpresa(int idEmpresa) {
        List<Requisicion> lista = new ArrayList<>();
        String sql = "SELECT * FROM requisicion WHERE id_empresa = ? ORDER BY fecha DESC";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpresa);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Requisicion r = new Requisicion(
                            rs.getInt("id_requisicion"),
                            rs.getInt("id_empresa"),
                            rs.getDate("fecha"),
                            rs.getString("estado"),
                            rs.getString("observacion")
                    );
                    lista.add(r);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // LISTAR DETALLES DE UNA REQUISICION
    // ─────────────────────────────────────────
    public List<DetalleRequisicion> listarDetalles(int idRequisicion) {
        List<DetalleRequisicion> lista = new ArrayList<>();
        String sql = "SELECT d.*, p.nombre AS nombre_producto "
                + "FROM detalle_requisicion d "
                + "JOIN producto p ON d.id_producto = p.id_producto "
                + "WHERE d.id_requisicion = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idRequisicion);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleRequisicion d = new DetalleRequisicion(
                            rs.getInt("id_detalle"),
                            rs.getInt("id_requisicion"),
                            rs.getInt("id_producto"),
                            rs.getBigDecimal("cantidad"),
                            rs.getString("observacion")
                    );
                    lista.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ─────────────────────────────────────────
    // ACTUALIZAR ESTADO DE REQUISICION
    // ─────────────────────────────────────────
    public boolean actualizarEstado(int idRequisicion, String nuevoEstado) {
        String sql = "UPDATE requisicion SET estado = ? WHERE id_requisicion = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nuevoEstado);
            ps.setInt(2, idRequisicion);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
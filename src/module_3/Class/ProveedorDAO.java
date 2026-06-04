package module_3.Class;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {

    // ── Insertar ───────────────────────────────────────────────────────────────
    public boolean insertar(Proveedor p) {
        String sql = "INSERT INTO proveedor (id_empresa, nombre, nit, telefono, correo, direccion, activo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getIdEmpresa());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getNit());
            ps.setString(4, p.getTelefono());
            ps.setString(5, p.getCorreo());
            ps.setString(6, p.getDireccion());
            ps.setBoolean(7, p.isActivo());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ── Actualizar ─────────────────────────────────────────────────────────────
    public boolean actualizar(Proveedor p) {
        String sql = "UPDATE proveedor SET nombre=?, nit=?, telefono=?, correo=?, direccion=?, activo=? "
                + "WHERE id_proveedor=? AND id_empresa=?";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getNit());
            ps.setString(3, p.getTelefono());
            ps.setString(4, p.getCorreo());
            ps.setString(5, p.getDireccion());
            ps.setBoolean(6, p.isActivo());
            ps.setInt(7, p.getIdProveedor());
            ps.setInt(8, p.getIdEmpresa());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ── Eliminar (baja lógica) ─────────────────────────────────────────────────
    public boolean eliminar(int idProveedor, int idEmpresa) {
        String sql = "UPDATE proveedor SET activo = 0 WHERE id_proveedor=? AND id_empresa=?";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            ps.setInt(2, idEmpresa);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ── Listar todos (activos) de una empresa ──────────────────────────────────
    public List<Proveedor> listar(int idEmpresa) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE id_empresa=? AND activo=1 ORDER BY nombre";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ── Buscar por nombre (para filtro en tabla) ───────────────────────────────
    public List<Proveedor> buscarPorNombre(int idEmpresa, String texto) {
        List<Proveedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM proveedor WHERE id_empresa=? AND activo=1 "
                + "AND nombre LIKE ? ORDER BY nombre";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idEmpresa);
            ps.setString(2, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // ── Obtener por ID ─────────────────────────────────────────────────────────
    public Proveedor obtenerPorId(int idProveedor, int idEmpresa) {
        String sql = "SELECT * FROM proveedor WHERE id_proveedor=? AND id_empresa=?";
        try (Connection con = Conection.conect(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProveedor);
            ps.setInt(2, idEmpresa);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ── Mapper ResultSet → Proveedor ───────────────────────────────────────────
    private Proveedor mapear(ResultSet rs) throws SQLException {
        return new Proveedor(
                rs.getInt("id_proveedor"),
                rs.getInt("id_empresa"),
                rs.getString("nombre"),
                rs.getString("nit"),
                rs.getString("telefono"),
                rs.getString("correo"),
                rs.getString("direccion"),
                rs.getBoolean("activo")
        );
    }
}

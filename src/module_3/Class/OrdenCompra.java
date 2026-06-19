package module_3.Class;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class OrdenCompra {

    private int idOrden;
    private int idEmpresa;
    private String numeroOrden;
    private int idProveedor;
    private Integer idRequisicion; // nullable
    private Date fecha;
    private String estado; // PENDIENTE, APROBADA, RECIBIDA, CANCELADA
    private BigDecimal total;

    // Detalles de la orden
    private List<DetalleOrdenCompra> detalles = new ArrayList<>();

    // Constructor vacío
    public OrdenCompra() {
    }

    // Constructor completo
    public OrdenCompra(int idOrden, int idEmpresa, String numeroOrden,
            int idProveedor, Integer idRequisicion, Date fecha,
            String estado, BigDecimal total) {
        this.idOrden = idOrden;
        this.idEmpresa = idEmpresa;
        this.numeroOrden = numeroOrden;
        this.idProveedor = idProveedor;
        this.idRequisicion = idRequisicion;
        this.fecha = fecha;
        this.estado = estado;
        this.total = total;
    }

    // Getters y Setters
    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Integer getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(Integer idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetalleOrdenCompra> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenCompra> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return numeroOrden + " - $" + total;
    }

}

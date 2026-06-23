package module_3.Class;

import java.math.BigDecimal;
import java.sql.Date;

public class LibroComprasIVA {

    private int idLibro;
    private int idEmpresa;
    private Integer idOrden; // Integer, no int -> permite NULL
    private int idProveedor;
    private String numeroDocumento;
    private Date fecha;
    private BigDecimal montoGravado;
    private BigDecimal iva;
    private BigDecimal total;

    public LibroComprasIVA() {
    }

    // Constructor para leer de la BD (ya tiene id)
    public LibroComprasIVA(int idLibro, int idEmpresa, Integer idOrden, int idProveedor,
            String numeroDocumento, Date fecha,
            BigDecimal montoGravado, BigDecimal iva, BigDecimal total) {
        this.idLibro = idLibro;
        this.idEmpresa = idEmpresa;
        this.idOrden = idOrden;
        this.idProveedor = idProveedor;
        this.numeroDocumento = numeroDocumento;
        this.fecha = fecha;
        this.montoGravado = montoGravado;
        this.iva = iva;
        this.total = total;
    }

    // Constructor para crear nuevo (todavía sin id)
    public LibroComprasIVA(int idEmpresa, Integer idOrden, int idProveedor,
            String numeroDocumento, Date fecha,
            BigDecimal montoGravado, BigDecimal iva, BigDecimal total) {
        this.idEmpresa = idEmpresa;
        this.idOrden = idOrden;
        this.idProveedor = idProveedor;
        this.numeroDocumento = numeroDocumento;
        this.fecha = fecha;
        this.montoGravado = montoGravado;
        this.iva = iva;
        this.total = total;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Integer getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(Integer idOrden) {
        this.idOrden = idOrden;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getMontoGravado() {
        return montoGravado;
    }

    public void setMontoGravado(BigDecimal montoGravado) {
        this.montoGravado = montoGravado;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}

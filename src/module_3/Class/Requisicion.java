package module_3.Class;

import java.sql.Date;

public class Requisicion {

    private int idRequisicion;
    private int idEmpresa;
    private Date fecha;
    private String estado; // "PENDIENTE", "APROBADA", "CANCELADA"
    private String observacion;

    // Constructor para leer de la BD (ya tiene id)
    public Requisicion(int idRequisicion, int idEmpresa, Date fecha, String estado, String observacion) {
        this.idRequisicion = idRequisicion;
        this.idEmpresa = idEmpresa;
        this.fecha = fecha;
        this.estado = estado;
        this.observacion = observacion;
    }

    // Constructor para crear nueva (todavía sin id)
    public Requisicion(int idEmpresa, Date fecha, String estado, String observacion) {
        this.idEmpresa = idEmpresa;
        this.fecha = fecha;
        this.estado = estado;
        this.observacion = observacion;
    }

    public int getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(int idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}

package module_3.Class;

import java.math.BigDecimal;

public class DetalleRequisicion {

    private int idDetalle;
    private int idRequisicion;
    private int idProducto;
    private BigDecimal cantidad;
    private String observacion;

    public DetalleRequisicion(int idDetalle, int idRequisicion, int idProducto, BigDecimal cantidad, String observacion) {
        this.idDetalle = idDetalle;
        this.idRequisicion = idRequisicion;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.observacion = observacion;
    }

    public DetalleRequisicion(int idRequisicion, int idProducto, BigDecimal cantidad, String observacion) {
        this.idRequisicion = idRequisicion;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.observacion = observacion;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public int getIdRequisicion() {
        return idRequisicion;
    }

    public void setIdRequisicion(int idRequisicion) {
        this.idRequisicion = idRequisicion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    
}
package module_3.Class;
 
import java.math.BigDecimal;
public class DetalleOrdenCompra {
 
    private int idDetalle;
    private int idOrden;
    private int idProducto;
    private BigDecimal cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal descuento;
    private BigDecimal subtotal;
 
    // Campos auxiliares para mostrar en tabla (no van a BD)
    private String nombreProducto;
 
    public DetalleOrdenCompra() {}
 
    public DetalleOrdenCompra(int idOrden, int idProducto, BigDecimal cantidad,
                               BigDecimal precioUnitario, BigDecimal descuento) {
        this.idOrden = idOrden;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.descuento = descuento;
        calcularSubtotal();
    }
 
    // Calcula subtotal automáticamente
    public void calcularSubtotal() {
        // subtotal = (cantidad * precioUnitario) - descuento
        BigDecimal bruto = cantidad.multiply(precioUnitario);
        this.subtotal = bruto.subtract(descuento != null ? descuento : BigDecimal.ZERO);
    }
 
    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }
 
    public int getIdOrden() { return idOrden; }
    public void setIdOrden(int idOrden) { this.idOrden = idOrden; }
 
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
 
    public BigDecimal getCantidad() { return cantidad; }
    public void setCantidad(BigDecimal cantidad) { this.cantidad = cantidad; }
 
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
 
    public BigDecimal getDescuento() { return descuento; }
    public void setDescuento(BigDecimal descuento) { this.descuento = descuento; }
 
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
 
    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }
}

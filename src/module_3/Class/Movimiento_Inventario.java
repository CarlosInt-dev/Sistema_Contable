package module_3.Class;

public class Movimiento_Inventario {
    
    // 1. Declaración de variables
    private int id_movimiento;
    private int id_inventario;
    private String tipo_movimiento; // Será 'ENTRADA' o 'SALIDA'
    private double cantidad;
    private String fecha_movimiento;
    private String descripcion;

    // 2. Constructor Vacío
    public Movimiento_Inventario() {
    }

    // 3. Constructor con parámetros
    public Movimiento_Inventario(int id_movimiento, int id_inventario, String tipo_movimiento, double cantidad, String fecha_movimiento, String descripcion) {
        this.id_movimiento = id_movimiento;
        this.id_inventario = id_inventario;
        this.tipo_movimiento = tipo_movimiento;
        this.cantidad = cantidad;
        this.fecha_movimiento = fecha_movimiento;
        this.descripcion = descripcion;
    }

    // 4. Getters y Setters
    public int getId_movimiento() {
        return id_movimiento;
    }

    public void setId_movimiento(int id_movimiento) {
        this.id_movimiento = id_movimiento;
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public String getTipo_movimiento() {
        return tipo_movimiento;
    }

    public void setTipo_movimiento(String tipo_movimiento) {
        this.tipo_movimiento = tipo_movimiento;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String fecha_movimiento) {
        this.fecha_movimiento = fecha_movimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

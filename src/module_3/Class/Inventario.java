package module_3.Class;

public class Inventario {
    
    // 1. Declaración de las variables (Idénticas a la base de datos)
    private int id_inventario;
    private int id_producto;
    private double cantidad_actual;
    private double costo_promedio;
    private String fecha_actualizacion;

    // 2. Constructor Vacío (Obligatorio para que NetBeans no dé errores)
    public Inventario() {
    }

    // 3. Constructor con todos los parámetros
    public Inventario(int id_inventario, int id_producto, double cantidad_actual, double costo_promedio, String fecha_actualizacion) {
        this.id_inventario = id_inventario;
        this.id_producto = id_producto;
        this.cantidad_actual = cantidad_actual;
        this.costo_promedio = costo_promedio;
        this.fecha_actualizacion = fecha_actualizacion;
    }

    // 4. Getters y Setters (Para leer y escribir los datos)
    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public double getCantidad_actual() {
        return cantidad_actual;
    }

    public void setCantidad_actual(double cantidad_actual) {
        this.cantidad_actual = cantidad_actual;
    }

    public double getCosto_promedio() {
        return costo_promedio;
    }

    public void setCosto_promedio(double costo_promedio) {
        this.costo_promedio = costo_promedio;
    }

    public String getFecha_actualizacion() {
        return fecha_actualizacion;
    }

    public void setFecha_actualizacion(String fecha_actualizacion) {
        this.fecha_actualizacion = fecha_actualizacion;
    }
}
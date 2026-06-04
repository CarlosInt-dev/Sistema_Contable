package module_3.Class;

public class Proveedor {

    private int idProveedor;
    private int idEmpresa;
    private String nombre;
    private String nit;
    private String telefono;
    private String correo;
    private String direccion;
    private boolean activo;

    public Proveedor() {
    }

    public Proveedor(int idProveedor, int idEmpresa, String nombre, String nit,
            String telefono, String correo, String direccion, boolean activo) {
        this.idProveedor = idProveedor;
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nit = nit;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.activo = activo;
    }

    // ── Getters & Setters ──────────────────────────────────────────────────────
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int v) {
        this.idProveedor = v;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int v) {
        this.idEmpresa = v;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String v) {
        this.nombre = v;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String v) {
        this.nit = v;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String v) {
        this.telefono = v;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String v) {
        this.correo = v;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String v) {
        this.direccion = v;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean v) {
        this.activo = v;
    }

    @Override
    public String toString() {
        return nombre; // para que se muestre bien en JComboBox
    }
}

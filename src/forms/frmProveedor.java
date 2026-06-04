package forms;

import module_3.Class.Proveedor;
import module_3.Class.ProveedorDAO;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class frmProveedor extends javax.swing.JFrame {
    // ── DAO y estado ───────────────────────────────────────────────────────────

    private final ProveedorDAO dao = new ProveedorDAO();
    private DefaultTableModel modeloTabla;
    private int idProveedorSeleccionado = -1;
    private int idEmpresa = 1; // cámbialo por Sesion.getIdEmpresa() cuando tengas la clase S
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmProveedor.class.getName());

    public frmProveedor() {
        initComponents();
        setSize(743, 518); // ancho, alto en píxeles
        setLocationRelativeTo(null);
        configurarEstilos();
        configurarTabla();
        configurarEventos();
        cargarTabla("");
    }
    // ── Estilos oscuros (igual que FrmOrdenCompra) ─────────────────────────────

    private void configurarEstilos() {
        Color fondo = new Color(30, 30, 40);
        Color texto = new Color(220, 220, 220);
        Color textoLabel = new Color(180, 180, 200);
        Color campoFondo = new Color(55, 55, 70);
        Color campoBorde = new Color(80, 80, 100);

        getContentPane().setBackground(fondo);

        // Títulos
        lblDatosDelProveedor.setForeground(Color.WHITE);
        lblDatosDelProveedor.setFont(new Font("Segoe UI", Font.BOLD, 13));

        lblListaDeProveedores.setForeground(Color.WHITE);
        lblListaDeProveedores.setFont(new Font("Segoe UI", Font.BOLD, 13));

        lblAnuncio.setForeground(new Color(120, 160, 200));
        lblAnuncio.setFont(new Font("Segoe UI", Font.ITALIC, 11));

        // Labels de campos
        for (JLabel lbl : new JLabel[]{lblNombre, lblNit, lblTelefono, lblCorreo, lblDireccion, lblBuscar}) {
            lbl.setForeground(textoLabel);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }

        // TextFields
        for (JTextField tf : new JTextField[]{txtNombre, txtNit, txtTelefono, txtCorreo, txtDireccion, txtBuscar}) {
            tf.setBackground(campoFondo);
            tf.setForeground(texto);
            tf.setCaretColor(Color.WHITE);
            tf.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(campoBorde, 1),
                    BorderFactory.createEmptyBorder(3, 6, 3, 6)
            ));
            tf.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        }

        // CheckBox
        chkActivo.setForeground(textoLabel);
        chkActivo.setBackground(fondo);
        chkActivo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // Separador
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setForeground(new Color(80, 80, 110));
        jSeparator1.setBackground(new Color(80, 80, 110));

        // Botones
        estilizarBoton(btnNuevo, new Color(70, 130, 180));
        estilizarBoton(btnGuardar, new Color(32, 150, 90));
        estilizarBoton(btnEliminar, new Color(200, 50, 50));

        // ScrollPane
        scrlProveedor.setBorder(BorderFactory.createLineBorder(campoBorde, 1));
        scrlProveedor.getViewport().setBackground(new Color(35, 35, 48));
    }

    private void estilizarBoton(JButton btn, Color color) {
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setOpaque(true);
    }

    // ── Configurar tabla ───────────────────────────────────────────────────────
    private void configurarTabla() {
        modeloTabla = new DefaultTableModel(
                new String[]{"ID", "Nombre", "NIT", "Teléfono", "Correo", "Dirección", "Activo"}, 0
        ) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblProveedores.setModel(modeloTabla);

        tblProveedores.setBackground(new Color(35, 35, 48));
        tblProveedores.setForeground(new Color(220, 220, 220));
        tblProveedores.setGridColor(new Color(60, 60, 80));
        tblProveedores.setRowHeight(24);
        tblProveedores.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblProveedores.setSelectionBackground(new Color(70, 130, 180));
        tblProveedores.setSelectionForeground(Color.WHITE);
        tblProveedores.setShowHorizontalLines(true);
        tblProveedores.setShowVerticalLines(false);

        tblProveedores.getTableHeader().setBackground(new Color(50, 50, 70));
        tblProveedores.getTableHeader().setForeground(new Color(180, 200, 230));
        tblProveedores.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblProveedores.getTableHeader().setReorderingAllowed(false);

        tblProveedores.getColumnModel().getColumn(0).setMaxWidth(40);
        tblProveedores.getColumnModel().getColumn(6).setMaxWidth(55);
    }

    // ── Eventos ────────────────────────────────────────────────────────────────
    private void configurarEventos() {
        btnNuevo.addActionListener(e -> limpiarFormulario());
        btnGuardar.addActionListener(e -> guardar());
        btnEliminar.addActionListener(e -> eliminar());

        tblProveedores.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarEnFormulario();
            }
        });

        txtBuscar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                cargarTabla(txtBuscar.getText().trim());
            }
        });
    }

    // ── Lógica CRUD ───────────────────────────────────────────────────────────
    private void cargarTabla(String filtro) {
        modeloTabla.setRowCount(0);
        List<Proveedor> lista = filtro.isEmpty()
                ? dao.listar(idEmpresa)
                : dao.buscarPorNombre(idEmpresa, filtro);

        for (Proveedor p : lista) {
            modeloTabla.addRow(new Object[]{
                p.getIdProveedor(),
                p.getNombre(),
                nvl(p.getNit()),
                nvl(p.getTelefono()),
                nvl(p.getCorreo()),
                nvl(p.getDireccion()),
                p.isActivo() ? "Sí" : "No"
            });
        }
    }

    private void cargarEnFormulario() {
        int fila = tblProveedores.getSelectedRow();
        if (fila < 0) {
            return;
        }

        idProveedorSeleccionado = (int) modeloTabla.getValueAt(fila, 0);
        txtNombre.setText((String) modeloTabla.getValueAt(fila, 1));
        txtNit.setText((String) modeloTabla.getValueAt(fila, 2));
        txtTelefono.setText((String) modeloTabla.getValueAt(fila, 3));
        txtCorreo.setText((String) modeloTabla.getValueAt(fila, 4));
        txtDireccion.setText((String) modeloTabla.getValueAt(fila, 5));
        chkActivo.setSelected("Sí".equals(modeloTabla.getValueAt(fila, 6)));
    }

    private void limpiarFormulario() {
        idProveedorSeleccionado = -1;
        txtNombre.setText("");
        txtNit.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");
        txtDireccion.setText("");
        chkActivo.setSelected(true);
        tblProveedores.clearSelection();
        txtNombre.requestFocus();
    }

    private void guardar() {
        if (txtNombre.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "El nombre del proveedor es obligatorio.",
                    "Validación", JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }

        Proveedor p = new Proveedor();
        p.setIdEmpresa(idEmpresa);
        p.setNombre(txtNombre.getText().trim());
        p.setNit(txtNit.getText().trim());
        p.setTelefono(txtTelefono.getText().trim());
        p.setCorreo(txtCorreo.getText().trim());
        p.setDireccion(txtDireccion.getText().trim());
        p.setActivo(chkActivo.isSelected());

        boolean ok;
        if (idProveedorSeleccionado == -1) {
            ok = dao.insertar(p);
        } else {
            p.setIdProveedor(idProveedorSeleccionado);
            ok = dao.actualizar(p);
        }

        if (ok) {
            JOptionPane.showMessageDialog(this,
                    idProveedorSeleccionado == -1
                            ? "Proveedor registrado correctamente."
                            : "Proveedor actualizado correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            limpiarFormulario();
            cargarTabla("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar. Revisá los datos.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminar() {
        if (idProveedorSeleccionado == -1) {
            JOptionPane.showMessageDialog(this,
                    "Seleccioná un proveedor de la tabla primero.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Desactivar el proveedor seleccionado?\n(Se marca como inactivo, no se elimina)",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            if (dao.eliminar(idProveedorSeleccionado, idEmpresa)) {
                JOptionPane.showMessageDialog(this,
                        "Proveedor desactivado correctamente.",
                        "Éxito", JOptionPane.INFORMATION_MESSAGE);
                limpiarFormulario();
                cargarTabla("");
            } else {
                JOptionPane.showMessageDialog(this,
                        "Error al desactivar el proveedor.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String nvl(String s) {
        return s != null ? s : "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDatosDelProveedor = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblNit = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        txtNit = new javax.swing.JTextField();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        chkActivo = new javax.swing.JCheckBox();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblListaDeProveedores = new javax.swing.JLabel();
        scrlProveedor = new javax.swing.JScrollPane();
        tblProveedores = new javax.swing.JTable();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        lblAnuncio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDatosDelProveedor.setText("Datos Del proveedor");
        getContentPane().add(lblDatosDelProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 120, -1));

        lblNombre.setText("Nombre *");
        getContentPane().add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 60, -1));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 220, -1));

        lblNit.setText("NIT *");
        getContentPane().add(lblNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 50, -1));

        lblTelefono.setText("Telefono *");
        getContentPane().add(lblTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 70, -1));
        getContentPane().add(txtNit, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 220, -1));

        lblCorreo.setText("Correo *");
        getContentPane().add(lblCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));
        getContentPane().add(txtCorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 220, -1));

        lblDireccion.setText("Dirrecion *");
        getContentPane().add(lblDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 70, -1));
        getContentPane().add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 230, -1));
        getContentPane().add(txtTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 130, -1));

        chkActivo.setText("Activo");
        getContentPane().add(chkActivo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 400, -1, -1));

        btnNuevo.setText("+ Nuevo");
        getContentPane().add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, -1, -1));

        btnGuardar.setText("Guardar");
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, -1, -1));

        btnEliminar.setText("Eliminar");
        getContentPane().add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 440, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 10, 520));

        lblListaDeProveedores.setText("Lista De Proveedores");
        getContentPane().add(lblListaDeProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 120, -1));

        tblProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrlProveedor.setViewportView(tblProveedores);

        getContentPane().add(scrlProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 410, 220));

        lblBuscar.setText("Buscar ");
        getContentPane().add(lblBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 50, 60, 20));
        getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 260, -1));

        lblAnuncio.setText("* Click en una fila carga los datos en los campos para editar ");
        getContentPane().add(lblAnuncio, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 350, 400, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> new frmProveedor().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JCheckBox chkActivo;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnuncio;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDatosDelProveedor;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblListaDeProveedores;
    private javax.swing.JLabel lblNit;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JScrollPane scrlProveedor;
    private javax.swing.JTable tblProveedores;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}

package forms;

import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import module_3.Class.Conection;
import module_3.Class.DetalleOrdenCompra;
import module_3.Class.OrdenCompra;
import module_3.Class.OrdenCompraDAO;

public class FrmOrdenCompra extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FrmOrdenCompra.class.getName());
    private OrdenCompraDAO dao = new OrdenCompraDAO();
    private List<DetalleOrdenCompra> listaDetalles = new ArrayList<>();
    private DefaultTableModel modeloDetalle;
    private DefaultTableModel modeloOrdenes;
    private int idEmpresaActual = 1; // cambiar por el id de la sesión del login

    public FrmOrdenCompra() {
        try {
            com.formdev.flatlaf.themes.FlatMacDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
        initComponents();
        initLogica();
        aplicarEstilos();
        
    }

    private void initLogica() {
        // Configurar tabla detalle de productos
        modeloDetalle = new javax.swing.table.DefaultTableModel(
                new String[]{"Producto", "Cantidad", "Precio Unit.", "Descuento", "Subtotal"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblDetalle.setModel(modeloDetalle);

        // Configurar tabla de órdenes
        modeloOrdenes = new javax.swing.table.DefaultTableModel(
                new String[]{"ID", "N° Orden", "Proveedor", "Fecha", "Estado", "Total"}, 0) {
            @Override
            public boolean isCellEditable(int r, int c) {
                return false;
            }
        };
        tblOrdenes.setModel(modeloOrdenes);

        // Cargar combos y datos iniciales
        cargarProveedores();
        cargarProductos();
        cargarEstados();
        generarNumeroOrden();
        txtFecha.setText(new java.sql.Date(System.currentTimeMillis()).toString());
        txtDescuento.setText("0.00");
        txtTotal.setText("0.00");
        txtNumeroOrden.setEditable(false);
        txtTotal.setEditable(false);

        cargarOrdenes();

        // Al seleccionar una orden en la tabla inferior, mostrar sus detalles
        tblOrdenes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cargarDetallesOrdenSeleccionada();
            }
        });
        soloNumerosDecimales(txtCantidad);
        soloNumerosDecimales(txtPrecioUnit);
        soloNumerosDecimales(txtDescuento);
    }

    private void cargarEstados() {
        cmbEstado.removeAllItems();
        cmbEstado.addItem("PENDIENTE");
        cmbEstado.addItem("APROBADA");
        cmbEstado.addItem("RECIBIDA");
        cmbEstado.addItem("CANCELADA");
    }

    private void cargarProveedores() {
        cmbProveedor.removeAllItems();
        String sql = "SELECT id_proveedor, nombre FROM proveedor WHERE id_empresa = ?";
        try (java.sql.PreparedStatement ps = Conection.conect().prepareStatement(sql)) {
            ps.setInt(1, idEmpresaActual);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbProveedor.addItem(rs.getInt("id_proveedor") + "|" + rs.getString("nombre"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void cargarProductos() {
        cmbProducto.removeAllItems();
        String sql = "SELECT id_producto, nombre FROM producto WHERE id_empresa = ?";
        try (java.sql.PreparedStatement ps = Conection.conect().prepareStatement(sql)) {
            ps.setInt(1, idEmpresaActual);
            java.sql.ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbProducto.addItem(rs.getInt("id_producto") + "|" + rs.getString("nombre"));
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    private void generarNumeroOrden() {
        txtNumeroOrden.setText(dao.generarNumeroOrden(idEmpresaActual));
    }

    private void agregarDetalle() {
        try {
            if (cmbProducto.getSelectedItem() == null) {
                javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un producto.");
                return;
            }
            String[] partes = cmbProducto.getSelectedItem().toString().split("\\|");
            int idProducto = Integer.parseInt(partes[0]);
            String nombreProducto = partes[1];

            BigDecimal cantidad = new BigDecimal(txtCantidad.getText().trim());
            BigDecimal precio = new BigDecimal(txtPrecioUnit.getText().trim());
            BigDecimal descuento = new BigDecimal(
                    txtDescuento.getText().trim().isEmpty() ? "0" : txtDescuento.getText().trim()
            );

            if (cantidad.compareTo(BigDecimal.ZERO) <= 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a 0.");
                return;
            }

            DetalleOrdenCompra detalle = new DetalleOrdenCompra(0, idProducto, cantidad, precio, descuento);
            detalle.setNombreProducto(nombreProducto);
            detalle.calcularSubtotal();
            listaDetalles.add(detalle);

            modeloDetalle.addRow(new Object[]{
                nombreProducto,
                cantidad,
                precio,
                descuento,
                detalle.getSubtotal()
            });

            actualizarTotal();
            limpiarCamposDetalle();

        } catch (NumberFormatException ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.");
        }
    }

    private void actualizarTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for (DetalleOrdenCompra d : listaDetalles) {
            total = total.add(d.getSubtotal());
        }
        txtTotal.setText(String.format("%.2f", total));
    }

    private void guardarOrden() {
        if (listaDetalles.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Agregue al menos un producto.");
            return;
        }
        if (cmbProveedor.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione un proveedor.");
            return;
        }
        try {
            String[] partesProveedor = cmbProveedor.getSelectedItem().toString().split("\\|");
            int idProveedor = Integer.parseInt(partesProveedor[0]);

            OrdenCompra orden = new OrdenCompra();
            orden.setIdEmpresa(idEmpresaActual);
            orden.setNumeroOrden(txtNumeroOrden.getText());
            orden.setIdProveedor(idProveedor);
            orden.setIdRequisicion(null);
            orden.setFecha(java.sql.Date.valueOf(txtFecha.getText().trim()));
            orden.setEstado(cmbEstado.getSelectedItem().toString());
            orden.setTotal(new BigDecimal(txtTotal.getText()));
            orden.setDetalles(listaDetalles);

            boolean ok = dao.insertarOrdenCompleta(orden);
            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(this, "✅ Orden guardada correctamente.");
                limpiarFormulario();
                cargarOrdenes();
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "❌ Error al guardar la orden.");
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void aplicarEstilos() {

        // ── Fuente general ──────────────────────────────
        java.awt.Font fuenteNormal = new java.awt.Font("Impact", java.awt.Font.ITALIC, 10);
        java.awt.Font fuenteNormalHover = new java.awt.Font("Impact", java.awt.Font.ITALIC, 14);
        java.awt.Font fuenteBold = new java.awt.Font("Impact", java.awt.Font.ITALIC, 10);
        java.awt.Font fuenteBoldHover = new java.awt.Font("Impact", java.awt.Font.ITALIC, 14);
        java.awt.Font fuenteTitulo = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 15);

        // ── Colores ─────────────────────────────────────
        java.awt.Color colorAcento = new java.awt.Color(3, 52, 110);   // azul Mac
        java.awt.Color colorFondoCard = new java.awt.Color(34, 40, 49);    // storm carbon
        java.awt.Color colorTexto = new java.awt.Color(194, 179, 158); // dune stone
        java.awt.Color colorExito = new java.awt.Color(68, 161, 148);   // verde
        java.awt.Color colorPeligro = new java.awt.Color(237, 53, 0);   // rojo

        // ── Tamaño del formulario ───────────────────────
        this.setSize(670, 640);
        this.setLocationRelativeTo(null); // centrar en pantalla
        this.setTitle("📦 Gestión de Órdenes de Compra");
        // ── Content Pane ─────────────────────────────
        getContentPane().setBackground(colorFondoCard);

        // ── Campos de texto ─────────────────────────────
        java.awt.Component[] campos = {
            txtNumeroOrden, txtFecha, txtCantidad, txtPrecioUnit, txtDescuento, txtTotal
        };
        for (java.awt.Component c : campos) {
            c.setFont(fuenteNormal);
            c.setPreferredSize(new java.awt.Dimension(c.getPreferredSize().width, 32));
            c.setForeground(colorTexto);
        }

        // N° Orden en bold (es solo lectura)
        txtNumeroOrden.setFont(fuenteBold);
        txtNumeroOrden.setForeground(colorTexto);
        txtTotal.setFont(fuenteBold);
        txtTotal.setForeground(colorTexto);

        // ── ComboBoxes ──────────────────────────────────
        cmbProveedor.setFont(fuenteNormal);
        cmbProducto.setFont(fuenteNormal);
        cmbEstado.setFont(fuenteNormal);

        // ── Botones ─────────────────────────────────────
        // Botón Agregar

        btnAgregarDetalle.setFont(fuenteBold);
        btnAgregarDetalle.setBackground(colorAcento);
        btnAgregarDetalle.setForeground(colorTexto);
        btnAgregarDetalle.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAgregarDetalle.setBorderPainted(false);
        btnAgregarDetalle.setFocusPainted(false);
        btnAgregarDetalle.setText("Agregar");

        // Botón Guardar
        btnGuardar.setFont(fuenteBold);
        btnGuardar.setBackground(colorExito);
        btnGuardar.setForeground(java.awt.Color.WHITE);
        btnGuardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnGuardar.setBorderPainted(false);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setText(" Guardar");

        // Botón Nuevo
        btnNuevo.setFont(fuenteBold);
        btnNuevo.setBackground(colorAcento);
        btnNuevo.setForeground(colorTexto);
        btnNuevo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnNuevo.setBorderPainted(false);
        btnNuevo.setFocusPainted(false);
        btnNuevo.setText("Nueva");

        // Botón Eliminar
         ImageIcon imgDelete = new ImageIcon("src/images/eliminar.png");
        btnEliminar.setFont(fuenteBold);
        btnEliminar.setBackground(colorPeligro);
        btnEliminar.setForeground(new java.awt.Color(255, 234, 216));
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setText("Eliminar");
        

        // ── Tablas ──────────────────────────────────────
        // Tabla detalle
        tblDetalle.setFont(fuenteNormal);
        tblDetalle.setRowHeight(28);
        tblDetalle.setShowHorizontalLines(true);
        tblDetalle.setGridColor(new java.awt.Color(60, 60, 60));
        tblDetalle.getTableHeader().setFont(fuenteBold);
        tblDetalle.getTableHeader().setBackground(colorFondoCard);
        tblDetalle.getTableHeader().setForeground(colorTexto);
        tblDetalle.setSelectionBackground(colorAcento);
        tblDetalle.setSelectionForeground(new java.awt.Color(226, 226, 182));

        // Tabla órdenes
        tblOrdenes.setFont(fuenteNormal);
        tblOrdenes.setRowHeight(28);
        tblOrdenes.setShowHorizontalLines(true);
        tblOrdenes.setGridColor(new java.awt.Color(60, 60, 60));
        tblOrdenes.getTableHeader().setFont(fuenteBold);
        tblOrdenes.getTableHeader().setBackground(colorFondoCard);
        tblOrdenes.getTableHeader().setForeground(colorTexto);
        tblOrdenes.setSelectionBackground(colorAcento);
        tblOrdenes.setSelectionForeground( new java.awt.Color(244, 240, 228) );

        // ── Labels ──────────────────────────────────────
        // Recorremos todos los labels del formulario
        for (java.awt.Component comp : getContentPane().getComponents()) {
            if (comp instanceof javax.swing.JLabel) {
                comp.setFont(fuenteNormal);
            }
            // Si hay paneles anidados
            if (comp instanceof java.awt.Container) {
                for (java.awt.Component inner : ((java.awt.Container) comp).getComponents()) {
                    if (inner instanceof javax.swing.JLabel) {
                        inner.setFont(fuenteNormal);
                    }
                }
            }
        }
        // Forzar color de texto en todos los labels
        for (java.awt.Component comp : getContentPane().getComponents()) {
            if (comp instanceof javax.swing.JLabel) {
                ((javax.swing.JLabel) comp).setForeground(colorTexto);
                comp.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            }
            if (comp instanceof java.awt.Container) {
                for (java.awt.Component inner : ((java.awt.Container) comp).getComponents()) {
                    if (inner instanceof javax.swing.JLabel) {
                        ((javax.swing.JLabel) inner).setForeground(colorTexto);
                        inner.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
                    }
                }
            }
        }
        //Animación para cambiar tamaño de botones
        for (java.awt.Component comparation : getContentPane().getComponents()) {
            int anchoOriginalBtnAgregar = comparation.getWidth(),
                    altoOriginalBtnAgregar= comparation.getHeight();
            if (comparation instanceof javax.swing.JButton) {
                comparation.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Aumentar tamaño
                        comparation.setSize(anchoOriginalBtnAgregar + 25, altoOriginalBtnAgregar + 10);
                        comparation.setFont(fuenteBoldHover);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        // Regresar tamaño original
                        comparation.setSize(anchoOriginalBtnAgregar, altoOriginalBtnAgregar);
                        comparation.setFont(fuenteBold);
                    }
                });
            }
            // Si hay paneles anidados
            if (comparation instanceof java.awt.Container) {
                for (java.awt.Component inner : ((java.awt.Container) comparation).getComponents()) {
                    int anchoOriginalInner = inner.getWidth(),
                            altoOriginalInner= inner.getHeight();
                    if (inner instanceof javax.swing.JButton) {
                        inner.addMouseListener(new MouseAdapter() {



                            @Override
                            public void mouseEntered(MouseEvent e) {
                                // Aumentar tamaño
                                inner.setSize(anchoOriginalInner + 40, altoOriginalInner + 15);
                                inner.setFont(fuenteBoldHover);
                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                                // Regresar tamaño original
                                inner.setSize(anchoOriginalInner, altoOriginalInner);
                                inner.setFont(fuenteBold);
                            }
                        });
                    }
                }
            }
        }

        // ── Refrescar UI ────────────────────────────────
        this.revalidate();
        this.repaint();
    }

    private void soloNumerosDecimales(javax.swing.JTextField campo) {
        campo.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                String texto = campo.getText();
                // Solo permitir dígitos y un solo punto decimal
                if (!Character.isDigit(c) && c != '.') {
                    e.consume();
                    return;
                }
                if (c == '.' && texto.contains(".")) {
                    e.consume();
                }
            }
        });
    }

    private void cargarOrdenes() {
        modeloOrdenes.setRowCount(0);
        List<OrdenCompra> lista = dao.listarPorEmpresa(idEmpresaActual);
        for (OrdenCompra o : lista) {
            modeloOrdenes.addRow(new Object[]{
                o.getIdOrden(),
                o.getNumeroOrden(),
                o.getIdProveedor(),
                o.getFecha(),
                o.getEstado(),
                String.format("$%.2f", o.getTotal())
            });
        }
    }

    private void cargarDetallesOrdenSeleccionada() {
        int fila = tblOrdenes.getSelectedRow();
        if (fila < 0) {
            return;
        }
        int idOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        modeloDetalle.setRowCount(0);
        List<DetalleOrdenCompra> detalles = dao.listarDetalles(idOrden);
        for (DetalleOrdenCompra d : detalles) {
            modeloDetalle.addRow(new Object[]{
                d.getNombreProducto(),
                d.getCantidad(),
                d.getPrecioUnitario(),
                d.getDescuento(),
                d.getSubtotal()
            });
        }
    }

    private void eliminarOrden() {
        int fila = tblOrdenes.getSelectedRow();
        if (fila < 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Seleccione una orden para eliminar.");
            return;
        }
        int idOrden = (int) modeloOrdenes.getValueAt(fila, 0);
        String numOrden = modeloOrdenes.getValueAt(fila, 1).toString();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Eliminar la orden " + numOrden + "?", "Confirmar",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            if (dao.eliminarOrden(idOrden)) {
                javax.swing.JOptionPane.showMessageDialog(this, "Orden eliminada.");
                cargarOrdenes();
                modeloDetalle.setRowCount(0);
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "No se pudo eliminar.");
            }
        }
    }

    private void limpiarFormulario() {
        listaDetalles = new ArrayList<>();
        modeloDetalle.setRowCount(0);
        txtTotal.setText("0.00");
        limpiarCamposDetalle();
        generarNumeroOrden();
        txtFecha.setText(new java.sql.Date(System.currentTimeMillis()).toString());
        cmbEstado.setSelectedIndex(0);
    }

    private void limpiarCamposDetalle() {
        txtCantidad.setText("");
        txtPrecioUnit.setText("");
        txtDescuento.setText("0.00");
        if (cmbProducto.getItemCount() > 0) {
            cmbProducto.setSelectedIndex(0);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNumeroOrden = new javax.swing.JTextField();
        lblNumeroOrden = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblProveedor = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        cmbProveedor = new javax.swing.JComboBox<>();
        cmbEstado = new javax.swing.JComboBox<>();
        lblAgregarProducto = new javax.swing.JLabel();
        lblProducto = new javax.swing.JLabel();
        cmbProducto = new javax.swing.JComboBox<>();
        lblCantidad = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtPrecioUnit = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        btnAgregarDetalle = new javax.swing.JButton();
        scrlDetalle = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        scrlOrden = new javax.swing.JScrollPane();
        tblOrdenes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblNumeroOrden.setText("N* Orden");

        lblFecha.setText("Fecha");

        jLabel3.setText("jLabel3");

        lblProveedor.setText("Proveedor");

        lblEstado.setText("Estado");

        cmbProveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAgregarProducto.setText("Agregar Producto ");

        lblProducto.setText("Producto");

        cmbProducto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblCantidad.setText("Cantidad");

        jLabel1.setText("Precio Unit.");

        jLabel2.setText("Descuento ");

        btnAgregarDetalle.setText("Agregar");
        btnAgregarDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDetalleActionPerformed(evt);
            }
        });

        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
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
        scrlDetalle.setViewportView(tblDetalle);

        lblTotal.setText("Total:");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        tblOrdenes.setModel(new javax.swing.table.DefaultTableModel(
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
        scrlOrden.setViewportView(tblOrdenes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAgregarProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtPrecioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCantidad)
                                .addGap(18, 18, 18)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAgregarDetalle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrlDetalle))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(scrlOrden)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumeroOrden)
                    .addComponent(lblFecha)
                    .addComponent(lblProveedor)
                    .addComponent(lblEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAgregarProducto)
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblProducto)
                    .addComponent(cmbProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCantidad)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPrecioUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarDetalle)
                .addGap(18, 18, 18)
                .addComponent(scrlDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTotal)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnGuardar)
                        .addComponent(btnNuevo)
                        .addComponent(btnEliminar)))
                .addGap(18, 18, 18)
                .addComponent(scrlOrden, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDetalleActionPerformed
        agregarDetalle();
    }//GEN-LAST:event_btnAgregarDetalleActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarOrden();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarOrden();
    }//GEN-LAST:event_btnEliminarActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FrmOrdenCompra().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarDetalle;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbProducto;
    private javax.swing.JComboBox<String> cmbProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblAgregarProducto;
    private javax.swing.JLabel lblCantidad;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblNumeroOrden;
    private javax.swing.JLabel lblProducto;
    private javax.swing.JLabel lblProveedor;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JScrollPane scrlDetalle;
    private javax.swing.JScrollPane scrlOrden;
    private javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblOrdenes;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtNumeroOrden;
    private javax.swing.JTextField txtPrecioUnit;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}

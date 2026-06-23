package forms;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class frmLobby extends javax.swing.JFrame {

    Login padre;
    FlatSVGIcon lightMode = new FlatSVGIcon("icons/lightSun.svg", 24, 24);
    FlatSVGIcon darkMode = new FlatSVGIcon("icons/moon.svg", 24, 24);
    boolean isDark = false;

    public frmLobby(Login padre) {
        iniciar();
        initComponents();
        loadStyles();
        this.padre = padre;
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                padre.setVisible(true);
            }
        });

    }

    public java.awt.Color color(int r, int g, int b) {
        java.awt.Color newColor = new java.awt.Color(r, g, b);
        return newColor;
    }

    private void switchMode() {
        if (isDark == false) {
            getContentPane().setBackground(color(30, 30, 30));
            btnSwitch.setIcon(lightMode);
            btnSwitch.setBackground(color(30, 30, 30));
            isDark = true;
        } else {
            getContentPane().setBackground(color(242, 246, 252));
            btnSwitch.setBackground(color(242, 246, 252));
            btnSwitch.setIcon(darkMode);
            isDark = false;
        }
    }

    private void loadStyles() {
        //Background
        getContentPane().setBackground(color(242, 246, 252));
        //Panes

        //Icons
        FlatSVGIcon order = new FlatSVGIcon("icons/orden.svg", 28, 28);
        FlatSVGIcon product = new FlatSVGIcon("icons/product_catalog.svg", 28, 28);
        FlatSVGIcon inventory = new FlatSVGIcon("icons/inventory.svg", 28, 28);
        FlatSVGIcon book = new FlatSVGIcon("icons/book.svg", 28, 28);
        FlatSVGIcon provider = new FlatSVGIcon("icons/provider.svg", 28, 28);
        FlatSVGIcon money = new FlatSVGIcon("icons/orden.svg", 28, 28);
        //Buttons
        btnSwitch.setIcon(darkMode);
        btnSwitch.setBackground(color(242, 246, 252));
        btnSwitch.putClientProperty("FlatLaf.style", "arc:25");
        btnOrdenCompra.setIcon(order);
        btnOrdenCompra.putClientProperty("FlatLaf.style", "arc:25");
        btnOrdenCompra.setBackground(color(35,61,77));
        
        btnCatalogoProducto.setIcon(product);
        btnCatalogoProducto.putClientProperty("FlatLaf.style", "arc:25");
        btnCatalogoProducto.setBackground(color(35,61,77));
        
        btnInventario.setIcon(inventory);
        btnInventario.putClientProperty("FlatLaf.style", "arc:25");
        btnInventario.setBackground(color(35,61,77));
        
        btnLibroComprasIVA.setIcon(book);
        btnLibroComprasIVA.putClientProperty("FlatLaf.style", "arc:25");
        btnLibroComprasIVA.setBackground(color(35,61,77));
        btnProveedor.setIcon(provider);
        btnProveedor.putClientProperty("FlatLaf.style", "arc:25");
        btnProveedor.setBackground(color(35,61,77));
        
        btnRequisicion.setIcon(money);
        btnRequisicion.putClientProperty("FlatLaf.style", "arc:25");
        btnRequisicion.setBackground(color(35,61,77));
        
        //Animations
        for (java.awt.Component comparation : getContentPane().getComponents()) {
            int anchoOriginalBtnAgregar = comparation.getWidth(),
                    altoOriginalBtnAgregar = comparation.getHeight();
            if (comparation instanceof javax.swing.JButton && comparation != btnSwitch) {
                comparation.addMouseListener(new MouseAdapter() {

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        // Aumentar tamaño
                        comparation.setSize(anchoOriginalBtnAgregar + 10, altoOriginalBtnAgregar + 5);

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                        // Regresar tamaño original
                        comparation.setSize(anchoOriginalBtnAgregar, altoOriginalBtnAgregar);

                    }
                });
            }
            // Si hay paneles anidados
            if (comparation instanceof java.awt.Container) {
                for (java.awt.Component inner : ((java.awt.Container) comparation).getComponents()) {
                    int anchoOriginalInner = inner.getWidth(),
                            altoOriginalInner = inner.getHeight();
                    if (inner instanceof javax.swing.JButton && inner != btnSwitch) {
                        inner.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseEntered(MouseEvent e) {
                                // Aumentar tamaño
                                inner.setSize(anchoOriginalInner + 10, altoOriginalInner + 5);

                            }

                            @Override
                            public void mouseExited(MouseEvent e) {

                                // Regresar tamaño original
                                inner.setSize(anchoOriginalInner, altoOriginalInner);

                            }
                        });
                    }
                }
            }
        }
    }

    public void iniciar() {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSwitch = new javax.swing.JButton();
        btnOrdenCompra = new javax.swing.JButton();
        btnCatalogoProducto = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnLibroComprasIVA = new javax.swing.JButton();
        btnProveedor = new javax.swing.JButton();
        btnRequisicion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnSwitch.setFocusable(false);
        btnSwitch.addActionListener(this::btnSwitchActionPerformed);

        btnOrdenCompra.setText("Orden Compra");
        btnOrdenCompra.addActionListener(this::btnOrdenCompraActionPerformed);

        btnCatalogoProducto.setText("Catalogo Producto");
        btnCatalogoProducto.addActionListener(this::btnCatalogoProductoActionPerformed);

        btnInventario.setText("Inventario");
        btnInventario.addActionListener(this::btnInventarioActionPerformed);

        btnLibroComprasIVA.setText("Libro Compras IVA");
        btnLibroComprasIVA.addActionListener(this::btnLibroComprasIVAActionPerformed);

        btnProveedor.setText("Proveedor");
        btnProveedor.addActionListener(this::btnProveedorActionPerformed);

        btnRequisicion.setText("Requisicion");
        btnRequisicion.addActionListener(this::btnRequisicionActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnLibroComprasIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(165, 165, 165)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCatalogoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addGap(178, 178, 178)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRequisicion, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                    .addComponent(btnInventario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(181, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCatalogoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(164, 164, 164)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLibroComprasIVA, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRequisicion, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitchActionPerformed
        this.switchMode();
    }//GEN-LAST:event_btnSwitchActionPerformed

    private void btnOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenCompraActionPerformed

        FrmOrdenCompra frm = new FrmOrdenCompra(this);
        frm.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnOrdenCompraActionPerformed

    private void btnCatalogoProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatalogoProductoActionPerformed

        frmCatalogoProducto frm = new frmCatalogoProducto(this);
        frm.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnCatalogoProductoActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed

        frmInventario frm = new frmInventario(this);
        frm.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnLibroComprasIVAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLibroComprasIVAActionPerformed
        frmLibroComprasIVA frm = new frmLibroComprasIVA(this);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnLibroComprasIVAActionPerformed

    private void btnProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedorActionPerformed
        frmProveedor frm = new frmProveedor(this);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnProveedorActionPerformed

    private void btnRequisicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRequisicionActionPerformed
          frmRequisicion frm = new frmRequisicion(this);
        frm.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnRequisicionActionPerformed

    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCatalogoProducto;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnLibroComprasIVA;
    private javax.swing.JButton btnOrdenCompra;
    private javax.swing.JButton btnProveedor;
    private javax.swing.JButton btnRequisicion;
    private javax.swing.JButton btnSwitch;
    // End of variables declaration//GEN-END:variables
}

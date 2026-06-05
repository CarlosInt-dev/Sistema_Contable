/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author carlo
 */
public class Login extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());

    ImageIcon img = new ImageIcon("src/icons/security.png");
    boolean hidePassword = true;
    FlatSVGIcon iconViewPassword = new FlatSVGIcon("icons/view_password.svg", 24, 24);
    FlatSVGIcon iconHidePassword = new FlatSVGIcon("icons/hide_password.svg", 24, 24);

    public Login() {
        iniciar();
        initComponents();
        loadStyles();
        this.setIconImage(img.getImage());
    }

    public java.awt.Color color(int r, int g, int b) {
        java.awt.Color newColor = new java.awt.Color(r, g, b);
        return newColor;
    }

    private void loadStyles() {
        //SVG's
        FlatSVGIcon iconUser = new FlatSVGIcon("icons/user.svg", 24, 24);
        FlatSVGIcon iconPass = new FlatSVGIcon("icons/password.svg", 24, 24);
        FlatSVGIcon iconLogin = new FlatSVGIcon("icons/login.svg", 24, 24);
        FlatSVGIcon iconSignin = new FlatSVGIcon("icons/signin.svg", 24, 24);
        FlatSVGIcon iconHomePage = new FlatSVGIcon("icons/homepage.svg",lblHomePage.getWidth(),lblHomePage.getHeight());
        
        //Fondo
        getContentPane().setBackground(color(23, 27, 42));

        //Paneles
        panelLogin.setBackground(color(245, 251, 230));
        panelLogin.putClientProperty("FlatLaf.style", "arc:20");
        panelLogin.requestFocus();

        //TextFields
        txtUsuario.putClientProperty("JTextField.leadingIcon", iconUser);
        txtUsuario.putClientProperty("FlatLaf.style", "arc:25");
        txtUsuario.setBackground(color(245, 251, 230));
        txtUsuario.setForeground(color(23, 27, 42));
        txtUsuario.setText("Ingresa tu usuario");
        pswdPassword.putClientProperty("JTextField.leadingIcon", iconPass);
        pswdPassword.putClientProperty("FlatLaf.style", "arc:25");
        pswdPassword.setBackground(color(245, 251, 230));
        pswdPassword.setForeground(color(23, 27, 42));
        pswdPassword.setText("");
        pswdPassword.setEchoChar('•');

        //Buttons
        btnLogin.setBackground(color(47, 164, 215));
        btnLogin.setIcon(iconLogin);
        btnLogin.setForeground(color(245, 251, 230));
        btnLogin.putClientProperty("FlatLaf.style", "arc:25");
        btnSignin.setBackground(color(245, 251, 230));
        btnSignin.setIcon(iconSignin);
        btnSignin.setForeground(color(23, 27, 42));
        btnSignin.putClientProperty("FlatLaf.style", "arc:25");
        btnView.setBackground(color(245, 251, 230));
        btnView.setIcon(iconViewPassword);
        btnView.putClientProperty("FlatLaf.style", "arc:25");

        //Labels
        lblInicio.setForeground(color(23, 27, 42));
        lblUno.setForeground(color(23, 27, 42));
        lblUsuario.setForeground(color(23, 27, 42));
        lblPassword.setForeground(color(23, 27, 42));
        lblO.setForeground(color(23, 27, 42));
        lblHomePage.setIcon(iconHomePage);
        

        //Animaciones
        for (java.awt.Component comparation : getContentPane().getComponents()) {
            int anchoOriginalBtnAgregar = comparation.getWidth(),
                    altoOriginalBtnAgregar = comparation.getHeight();
            if (comparation instanceof javax.swing.JButton && comparation!=btnView) {
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
                    if (inner instanceof javax.swing.JButton && inner!=btnView) {
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

    private void iniciar() {
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLogin = new javax.swing.JPanel();
        txtUsuario = new javax.swing.JTextField();
        lblInicio = new javax.swing.JLabel();
        lblUno = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        pswdPassword = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnSignin = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        lblO = new javax.swing.JLabel();
        lblHomePage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión");
        setResizable(false);
        setType(java.awt.Window.Type.POPUP);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        panelLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                panelLoginFocusLost(evt);
            }
        });
        panelLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLoginMouseClicked(evt);
            }
        });

        txtUsuario.setText("Ingresa tu usuario");
        txtUsuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUsuarioFocusLost(evt);
            }
        });
        txtUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUsuarioMouseClicked(evt);
            }
        });

        lblInicio.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        lblInicio.setText("Iniciar Sesión");

        lblUno.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        lblUno.setText("Ingresa tus credenciales para acceder");

        lblUsuario.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        lblUsuario.setText("Usuario");

        lblPassword.setFont(new java.awt.Font("Bookman Old Style", 1, 12)); // NOI18N
        lblPassword.setText("Contraseña");

        pswdPassword.setText("jPasswordField1");
        pswdPassword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pswdPasswordFocusLost(evt);
            }
        });
        pswdPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pswdPasswordMouseClicked(evt);
            }
        });

        btnLogin.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnLogin.setText("Iniciar Sesión");

        btnSignin.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnSignin.setText("Registrarse");
        btnSignin.addActionListener(this::btnSigninActionPerformed);

        btnView.addActionListener(this::btnViewActionPerformed);

        lblO.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        lblO.setText("--Ó--");

        javax.swing.GroupLayout panelLoginLayout = new javax.swing.GroupLayout(panelLogin);
        panelLogin.setLayout(panelLoginLayout);
        panelLoginLayout.setHorizontalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblUno)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLoginLayout.createSequentialGroup()
                            .addGap(14, 14, 14)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pswdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                .addComponent(lblInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(88, 88, 88))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLoginLayout.createSequentialGroup()
                        .addComponent(lblO)
                        .addGap(168, 168, 168))))
        );
        panelLoginLayout.setVerticalGroup(
            panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLoginLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUno)
                .addGap(15, 15, 15)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(lblPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pswdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(lblO, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblHomePage, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHomePage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUsuarioMouseClicked
        if (txtUsuario.getText().equals("Ingresa tu usuario")) {
            txtUsuario.setText("");
            txtUsuario.requestFocus();
        } else {
            txtUsuario.requestFocus();
        }
    }//GEN-LAST:event_txtUsuarioMouseClicked

    private void panelLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_panelLoginFocusLost
        //No tocar Error de dedo
    }//GEN-LAST:event_panelLoginFocusLost

    private void txtUsuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUsuarioFocusLost
        if (txtUsuario.getText().trim().equals("")) {
            txtUsuario.setText("Ingresa tu usuario");
        } else {
            return;
        }

// TODO add your handling code here:
    }//GEN-LAST:event_txtUsuarioFocusLost

    private void pswdPasswordFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pswdPasswordFocusLost
        //No tocar Error de dedo
    }//GEN-LAST:event_pswdPasswordFocusLost

    private void pswdPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pswdPasswordMouseClicked

        pswdPassword.requestFocus();
        // TODO add your handling code here:
    }//GEN-LAST:event_pswdPasswordMouseClicked

    private void panelLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLoginMouseClicked
        panelLogin.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_panelLoginMouseClicked

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        panelLogin.requestFocus();    // TODO add your handling code here:
    }//GEN-LAST:event_formMouseClicked

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if(hidePassword==true){
            btnView.setIcon(iconViewPassword);
            pswdPassword.setEchoChar((char)0);
            hidePassword=false;
            return;
        }
        if(hidePassword==false){
            btnView.setIcon(iconHidePassword);
            pswdPassword.setEchoChar('•');
            hidePassword=true;
            return;
        }
        
    }//GEN-LAST:event_btnViewActionPerformed

    private void btnSigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigninActionPerformed
        frmSignin frm = new frmSignin(this);
        frm.setVisible(true);
        this.setVisible(false);
        
        
    }//GEN-LAST:event_btnSigninActionPerformed

    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignin;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel lblHomePage;
    private javax.swing.JLabel lblInicio;
    private javax.swing.JLabel lblO;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUno;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelLogin;
    private javax.swing.JPasswordField pswdPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}

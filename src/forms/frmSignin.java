/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forms;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import module_3.Class.*;
import javax.swing.ImageIcon;
public class frmSignin extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(frmSignin.class.getName());

    private Login padre;
    boolean hidePassword = true;
    FlatSVGIcon iconViewPassword = new FlatSVGIcon("icons/view_password.svg", 24, 24);
    FlatSVGIcon iconHidePassword = new FlatSVGIcon("icons/hide_password.svg", 24, 24);
    public frmSignin(Login padre) {
        iniciar();
        initComponents();
        loadStyles();
        this.padre=padre;
        addWindowListener(new java.awt.event.WindowAdapter(){
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                padre.setVisible(true);
            }
        });
    }

    
    public void iniciar(){
        try {
            FlatDarkLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void loadStyles(){
        //SVG's
        FlatSVGIcon iconUser = new FlatSVGIcon("icons/user.svg", 24, 24);
        FlatSVGIcon iconPass = new FlatSVGIcon("icons/password.svg", 24, 24);
        FlatSVGIcon iconSignin = new FlatSVGIcon("icons/Login.svg", 24, 24);
        FlatSVGIcon iconBussines = new FlatSVGIcon("icons/building.svg",24,24);
        //backGround
        getContentPane().setBackground(color(23, 27, 42));
        //Paneles
        pnlSignIn.setBackground(color(242,246,252));
        pnlSignIn.putClientProperty("FlatLaf.style", "arc:20");
        pnlBuy.setBackground(color(242,246,252));
        pnlBuy.putClientProperty("FlatLaf.style", "arc:20");
        pnlSignIn.requestFocus();
        //TextFields
        txtUserName.putClientProperty("JTextField.leadingIcon", iconUser);
        txtUserName.putClientProperty("FlatLaf.style", "arc:25");
        txtUserName.setBackground(color(242, 246, 252));
        txtUserName.setForeground(color(23, 27, 42));
        txtUserName.setText("Ingresa tu usuario");
        txtName.putClientProperty("JTextField.leadingIcon", iconUser);
        txtName.putClientProperty("FlatLaf.style", "arc:25");
        txtName.setBackground(color(242, 246, 252));
        txtName.setForeground(color(23, 27, 42));
        txtName.setText("Ingresa tu nombre");
        txtLastName.putClientProperty("JTextField.leadingIcon", iconUser);
        txtLastName.putClientProperty("FlatLaf.style", "arc:25");
        txtLastName.setBackground(color(242, 246, 252));
        txtLastName.setForeground(color(23, 27, 42));
        txtLastName.setText("Ingresa tu apellido");
        txtNit.putClientProperty("JTextField.leadingIcon", iconBussines);
        txtNit.putClientProperty("FlatLaf.style", "arc:25");
        txtNit.setBackground(color(242, 246, 252));
        txtNit.setForeground(color(23, 27, 42));
        txtNit.setText("NIT");
        pswdPassword.putClientProperty("JTextField.leadingIcon", iconPass);
        pswdPassword.putClientProperty("FlatLaf.style", "arc:25");
        pswdPassword.setBackground(color(242, 246, 252));
        pswdPassword.setForeground(color(23, 27, 42));
        pswdPassword.setText("");
        pswdPassword.setEchoChar('•');
        pswdPasswordConfirmation.putClientProperty("JTextField.leadingIcon", iconPass);
        pswdPasswordConfirmation.putClientProperty("FlatLaf.style", "arc:25");
        pswdPasswordConfirmation.setBackground(color(242, 246, 252));
        pswdPasswordConfirmation.setForeground(color(23, 27, 42));
        pswdPasswordConfirmation.setText("");
        pswdPasswordConfirmation.setEchoChar('•');
        //Buttons
        btnView.setBackground(color(242, 246, 252));
        btnView.setIcon(iconViewPassword);
        btnView.putClientProperty("FlatLaf.style", "arc:25");
        btnSignin.setBackground(color(47, 164, 215));
        btnSignin.setIcon(iconSignin);
        btnSignin.setForeground(color(242, 246, 252));
        btnSignin.putClientProperty("FlatLaf.style", "arc:25");
        
        //Labels
        lblOne.setForeground(color(23, 27, 42));
        lblPass.setForeground(color(23, 27, 42));
        lblConfirmPass.setForeground(color(23, 27, 42));
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
    public java.awt.Color color(int r, int g, int b) {
        java.awt.Color newColor = new java.awt.Color(r, g, b);
        return newColor;
    }
    public boolean validarCampos(Container container) {
        for (Component c : container.getComponents()) {

            if (c instanceof JTextField txt) {
                if (txt.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Todos los campos son obligatorios.");
                    txt.requestFocus();
                    return false;
                }
            }

            if (c instanceof JPasswordField pass) {
                if (String.valueOf(pass.getPassword()).trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Todos los campos son obligatorios.");
                    pass.requestFocus();
                    return false;
                }
            }

            if (c instanceof Container cont) {
                if (!validarCampos(cont)) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean validarPasswords(JPasswordField pass1, JPasswordField pass2) {

        String password1 = String.valueOf(pass1.getPassword());
        String password2 = String.valueOf(pass2.getPassword());

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(null,
                    "Las contraseñas no coinciden.");
            return false;
        }

        if (password1.length() < 8) {
            JOptionPane.showMessageDialog(null,
                    "La contraseña debe tener al menos 8 caracteres.");
            return false;
        }

        return true;
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSignIn = new javax.swing.JPanel();
        lblOne = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtUserName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        pswdPassword = new javax.swing.JPasswordField();
        txtNit = new javax.swing.JTextField();
        pswdPasswordConfirmation = new javax.swing.JPasswordField();
        btnSignin = new javax.swing.JButton();
        btnView = new javax.swing.JButton();
        lblPass = new javax.swing.JLabel();
        lblConfirmPass = new javax.swing.JLabel();
        pnlBuy = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlSignIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlSignInMouseClicked(evt);
            }
        });

        lblOne.setFont(new java.awt.Font("Bookman Old Style", 1, 24)); // NOI18N
        lblOne.setText("CREAR CUENTA");

        txtName.setText("Ingresa tu nombre");
        txtName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNameFocusLost(evt);
            }
        });
        txtName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNameMouseClicked(evt);
            }
        });
        txtName.addActionListener(this::txtNameActionPerformed);

        txtUserName.setText("Ingresa tu usuario");
        txtUserName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtUserNameFocusLost(evt);
            }
        });
        txtUserName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtUserNameMouseClicked(evt);
            }
        });
        txtUserName.addActionListener(this::txtUserNameActionPerformed);

        txtLastName.setText("Ingresa tu apellido");
        txtLastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtLastNameFocusLost(evt);
            }
        });
        txtLastName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLastNameMouseClicked(evt);
            }
        });
        txtLastName.addActionListener(this::txtLastNameActionPerformed);

        pswdPassword.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pswdPasswordMouseClicked(evt);
            }
        });
        pswdPassword.addActionListener(this::pswdPasswordActionPerformed);

        txtNit.setText("NIT");
        txtNit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtNitFocusLost(evt);
            }
        });
        txtNit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNitMouseClicked(evt);
            }
        });

        pswdPasswordConfirmation.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pswdPasswordConfirmationMouseClicked(evt);
            }
        });
        pswdPasswordConfirmation.addActionListener(this::pswdPasswordConfirmationActionPerformed);

        btnSignin.setFont(new java.awt.Font("Impact", 2, 14)); // NOI18N
        btnSignin.setText("Ingresar");
        btnSignin.addActionListener(this::btnSigninActionPerformed);

        btnView.addActionListener(this::btnViewActionPerformed);

        lblPass.setText("Ingrese su contraseña");

        lblConfirmPass.setText("Confirme la contraseña");

        javax.swing.GroupLayout pnlSignInLayout = new javax.swing.GroupLayout(pnlSignIn);
        pnlSignIn.setLayout(pnlSignInLayout);
        pnlSignInLayout.setHorizontalGroup(
            pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSignInLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblOne)
                .addGap(114, 114, 114))
            .addGroup(pnlSignInLayout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pnlSignInLayout.createSequentialGroup()
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(txtUserName)
                        .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlSignInLayout.createSequentialGroup()
                            .addGroup(pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(pswdPasswordConfirmation, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(pswdPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblConfirmPass, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                            .addGap(30, 30, 30)
                            .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(btnSignin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lblPass, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        pnlSignInLayout.setVerticalGroup(
            pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSignInLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(lblOne)
                .addGap(54, 54, 54)
                .addGroup(pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(txtNit, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlSignInLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSignInLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblPass)
                        .addGap(4, 4, 4)
                        .addComponent(pswdPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(lblConfirmPass)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pswdPasswordConfirmation, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSignInLayout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(40, 40, 40)
                .addComponent(btnSignin, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlBuyLayout = new javax.swing.GroupLayout(pnlBuy);
        pnlBuy.setLayout(pnlBuyLayout);
        pnlBuyLayout.setHorizontalGroup(
            pnlBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 408, Short.MAX_VALUE)
        );
        pnlBuyLayout.setVerticalGroup(
            pnlBuyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(pnlSignIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlSignIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        if (txtName.getText().equals("Ingresa tu nombre")) {
            txtName.setText("");
            txtName.requestFocus();
        } else {
            txtName.requestFocus();
        }
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLastNameActionPerformed
        if (txtLastName.getText().equals("Ingresa tu apellido")) {
            txtLastName.setText("");
            txtLastName.requestFocus();
        } else {
            txtLastName.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameActionPerformed

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        if (txtUserName.getText().equals("Ingresa tu usuario")) {
            txtUserName.setText("");
            txtUserName.requestFocus();
        } else {
            txtUserName.requestFocus();
        }
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void pswdPasswordConfirmationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswdPasswordConfirmationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswdPasswordConfirmationActionPerformed

    private void pswdPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pswdPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pswdPasswordActionPerformed

    private void pswdPasswordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pswdPasswordMouseClicked
        pswdPassword.requestFocus();
    }//GEN-LAST:event_pswdPasswordMouseClicked

    private void pswdPasswordConfirmationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pswdPasswordConfirmationMouseClicked
        pswdPasswordConfirmation.requestFocus();
    }//GEN-LAST:event_pswdPasswordConfirmationMouseClicked

    private void txtNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNameFocusLost
        if (txtName.getText().trim().equals("")) {
            txtName.setText("Ingresa tu nombre");
        } else {
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameFocusLost

    private void txtLastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtLastNameFocusLost
        if (txtLastName.getText().trim().equals("")) {
            txtLastName.setText("Ingresa tu apellido");
        } else {
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameFocusLost

    private void txtUserNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtUserNameFocusLost
        if (txtUserName.getText().trim().equals("")) {
            txtUserName.setText("Ingresa tu usuario");
        } else {
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameFocusLost

    private void txtNitFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNitFocusLost
        if (txtNit.getText().trim().equals("")) {
            txtNit.setText("NIT");
        } else {
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitFocusLost

    private void txtNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNameMouseClicked
        if (txtName.getText().equals("Ingresa tu nombre")) {
            txtName.setText("");
            txtName.requestFocus();
        } else {
            txtName.requestFocus();
        }
    }//GEN-LAST:event_txtNameMouseClicked

    private void txtLastNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLastNameMouseClicked
        if (txtLastName.getText().equals("Ingresa tu apellido")) {
            txtLastName.setText("");
            txtLastName.requestFocus();
        } else {
            txtLastName.requestFocus();
        }           // TODO add your handling code here:
    }//GEN-LAST:event_txtLastNameMouseClicked

    private void txtUserNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtUserNameMouseClicked
        if (txtUserName.getText().equals("Ingresa tu usuario")) {
            txtUserName.setText("");
            txtUserName.requestFocus();
        } else {
            txtUserName.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameMouseClicked

    private void txtNitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNitMouseClicked
        if (txtNit.getText().equals("NIT")) {
            txtNit.setText("");
            txtNit.requestFocus();
        } else {
            txtNit.requestFocus();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtNitMouseClicked

    private void btnSigninActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSigninActionPerformed
        if(!validarCampos(pnlSignIn)){
            return;
        }
        if(!validarPasswords(pswdPassword,pswdPasswordConfirmation)){
            return;
        }
        UserDAO uDao = new UserDAO();
        EmpresaDAO dao = new EmpresaDAO();
        Empresa empresa= dao.findEmpresa(txtNit.getText());
        User user = new User();
        user.setEmpresa(empresa);
        user.setName(txtName.getText() + " " + txtLastName.getText());
        user.setUserName(txtUserName.getText());
        String pass = String.valueOf(pswdPassword.getPassword());
        user.setPassword(pass);
        uDao.insertUser(user, "empleado");
        padre.setVisible(true);
        this.dispose();
        
        
        
// TODO add your handling code here:
    }//GEN-LAST:event_btnSigninActionPerformed

    private void btnViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewActionPerformed
        if(hidePassword==true){
            btnView.setIcon(iconHidePassword);
            pswdPassword.setEchoChar((char)0);
            pswdPasswordConfirmation.setEchoChar((char)0);
            hidePassword=false;
            return;
        }
        if(hidePassword==false){
            btnView.setIcon(iconViewPassword);
            pswdPassword.setEchoChar('•');
            pswdPasswordConfirmation.setEchoChar('•');
            hidePassword=true;
            return;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnViewActionPerformed

    private void pnlSignInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlSignInMouseClicked
        pnlSignIn.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_pnlSignInMouseClicked

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
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSignin;
    private javax.swing.JButton btnView;
    private javax.swing.JLabel lblConfirmPass;
    private javax.swing.JLabel lblOne;
    private javax.swing.JLabel lblPass;
    private javax.swing.JPanel pnlBuy;
    private javax.swing.JPanel pnlSignIn;
    private javax.swing.JPasswordField pswdPassword;
    private javax.swing.JPasswordField pswdPasswordConfirmation;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNit;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}

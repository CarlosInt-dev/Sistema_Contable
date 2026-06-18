
package forms;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;



public class frmLobby extends javax.swing.JFrame {
    
    Login padre;
    FlatSVGIcon lightMode = new FlatSVGIcon("icons/lightSun.svg",24,24);
    FlatSVGIcon darkMode = new FlatSVGIcon("icons/moon.svg",24,24);
    boolean isDark = false;
    

    public frmLobby(Login padre ) {
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
    
    public java.awt.Color color(int r, int g, int b) {
        java.awt.Color newColor = new java.awt.Color(r, g, b);
        return newColor;
    }
    
    private void switchMode(){
        if(isDark==false){
            getContentPane().setBackground(color(30,30,30));
            btnSwitch.setIcon(lightMode);
            btnSwitch.setBackground(color(30,30,30));
            isDark=true;
        }else{
            getContentPane().setBackground(color(242,246,252));
            btnSwitch.setBackground(color(242,246,252));
            btnSwitch.setIcon(darkMode);
            isDark=false;
        }
    }
    private void loadStyles(){
        //Background
        getContentPane().setBackground(color(242, 246, 252));
        //Panes

        //Icons
        //Buttons
        btnSwitch.setIcon(darkMode);
        btnSwitch.setBackground(color(242, 246, 252));
        btnSwitch.putClientProperty("FlatLaf.style", "arc:25");
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        btnSwitch.setFocusable(false);
        btnSwitch.addActionListener(this::btnSwitchActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(1142, Short.MAX_VALUE)
                .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnSwitch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(587, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSwitchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSwitchActionPerformed
        this.switchMode();
    }//GEN-LAST:event_btnSwitchActionPerformed

    public static void main(String args[]) {
       
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSwitch;
    // End of variables declaration//GEN-END:variables
}

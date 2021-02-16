package work.universitycourse.comp1549.Interfaces;

import javax.swing.ImageIcon;
import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */
public class Licenses extends javax.swing.JFrame {

    /**
     * Creates new form StartUpInterface
     */
    public Licenses() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon img = new ImageIcon(getClass().getResource("/icon.png"));
        licensesPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle
                .getBundle("work/universitycourse/comp1549/Interfaces/Bundle"); // NOI18N
        setTitle(bundle.getString("Licenses.title")); // NOI18N
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new java.awt.Dimension(854, 519));
        setName("DiscussionNet"); // NOI18N
        setResizable(false);

        licensesPanel.setBackground(new java.awt.Color(255, 255, 255));
        licensesPanel.setMaximumSize(new java.awt.Dimension(847, 519));
        licensesPanel.setMinimumSize(new java.awt.Dimension(847, 519));
        licensesPanel.setName("licensesPanel"); // NOI18N
        licensesPanel.setPreferredSize(new java.awt.Dimension(847, 519));
        InterfaceManager.detectExitRequest(licensesPanel);

        javax.swing.GroupLayout licensesPanelLayout = new javax.swing.GroupLayout(licensesPanel);
        licensesPanel.setLayout(licensesPanelLayout);
        licensesPanelLayout.setHorizontalGroup(licensesPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 847, Short.MAX_VALUE));
        licensesPanelLayout.setVerticalGroup(licensesPanelLayout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 519, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
                javax.swing.GroupLayout.Alignment.TRAILING,
                layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(licensesPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(licensesPanel,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        getAccessibleContext().setAccessibleName(bundle.getString("Licenses.AccessibleContext.accessibleName")); // NOI18N

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Licenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Licenses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel licensesPanel;
    // End of variables declaration//GEN-END:variables
}

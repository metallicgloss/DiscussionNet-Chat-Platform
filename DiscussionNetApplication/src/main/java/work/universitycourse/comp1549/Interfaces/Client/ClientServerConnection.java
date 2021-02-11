package work.universitycourse.comp1549.Interfaces.Client;

import work.universitycourse.comp1549.Components.RoundJTextField;
import work.universitycourse.comp1549.Modules.InterfaceManager;
import work.universitycourse.comp1549.Interfaces.Licenses;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */
public class ClientServerConnection extends javax.swing.JFrame {

    /**
     * Creates new form serverConnection
     */
    public ClientServerConnection() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        mainImage = new javax.swing.JLabel();
        discussionNetServerConnectionLabel1 = new javax.swing.JLabel();
        discussionNetServerConnectionLabel2 = new javax.swing.JLabel();
        serverIPAddressLabel = new javax.swing.JLabel();
        connectToServerButton = new javax.swing.JButton();
        serverPortLabel = new javax.swing.JLabel();
        serverIPAddressTextbox = new RoundJTextField();
        serverPortTextbox = new RoundJTextField();
        footerLicensesTextLabel = new javax.swing.JLabel();
        footerTextLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(854, 519));
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setMaximumSize(new java.awt.Dimension(847, 519));
        mainPanel.setMinimumSize(new java.awt.Dimension(847, 519));
        mainPanel.setPreferredSize(new java.awt.Dimension(847, 519));

        mainImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/connection.png"))); // NOI18N

        discussionNetServerConnectionLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 24)); // NOI18N
        discussionNetServerConnectionLabel1.setForeground(new java.awt.Color(0, 36, 109));
        discussionNetServerConnectionLabel1.setText("DiscussionNet");

        discussionNetServerConnectionLabel2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        discussionNetServerConnectionLabel2.setText("Server Connection");

        serverIPAddressLabel.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverIPAddressLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wireless.png"))); // NOI18N
        serverIPAddressLabel.setText("Server IP Address");

        connectToServerButton.setBackground(new java.awt.Color(255, 255, 255));
        connectToServerButton.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        connectToServerButton.setForeground(new java.awt.Color(255, 255, 255));
        connectToServerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/medium.png"))); // NOI18N
        connectToServerButton.setText("Connect to Server");
        connectToServerButton.setBorder(null);
        connectToServerButton.setBorderPainted(false);
        connectToServerButton.setContentAreaFilled(false);
        connectToServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectToServerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectToServerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                connectToServerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                connectToServerButtonMouseExited(evt);
            }
        });
        connectToServerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectToServerButtonActionPerformed(evt);
            }
        });

        serverPortLabel.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverPortLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/port_icon.png"))); // NOI18N
        serverPortLabel.setText("Server Port");

        serverIPAddressTextbox.setForeground(new java.awt.Color(0, 63, 143));
        serverIPAddressTextbox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        serverPortTextbox.setForeground(new java.awt.Color(0, 63, 143));
        serverPortTextbox.setMargin(new java.awt.Insets(0, 0, 0, 0));

        footerLicensesTextLabel.setFont(new java.awt.Font("Montserrat", 2, 9)); // NOI18N
        footerLicensesTextLabel.setForeground(new java.awt.Color(47, 46, 65));
        footerLicensesTextLabel.setText("Software Licenses");
        footerLicensesTextLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        footerLicensesTextLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });

        footerTextLabel.setFont(new java.awt.Font("Montserrat", 0, 9)); // NOI18N
        footerTextLabel.setForeground(new java.awt.Color(47, 46, 65));
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(mainImage)
                .addGap(79, 79, 79)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(mainPanelLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serverPortLabel)
                                    .addComponent(serverIPAddressLabel)
                                    .addGroup(mainPanelLayout.createSequentialGroup()
                                        .addComponent(discussionNetServerConnectionLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(discussionNetServerConnectionLabel2))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(serverIPAddressTextbox, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(serverPortTextbox))
                                .addGap(66, 66, 66)))
                        .addGap(79, 79, 79))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(connectToServerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(285, 285, 285)
                .addComponent(footerTextLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(footerLicensesTextLabel)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(mainImage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 139, Short.MAX_VALUE))
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(discussionNetServerConnectionLabel1)
                            .addComponent(discussionNetServerConnectionLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(serverIPAddressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverIPAddressTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(serverPortLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serverPortTextbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(connectToServerButton)
                        .addGap(117, 117, 117)))
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(footerLicensesTextLabel)
                    .addComponent(footerTextLabel)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void footerLicensesTextLabelMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.changeWindow(this, new Licenses());
    }// GEN-LAST:event_footerLicensesTextLabelMouseClicked

    private void connectToServerButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_connectToServerButtonActionPerformed
        // Handle Connection To Server Here
        InterfaceManager.changeWindow(this, new ClientIdentitySetup());
    }// GEN-LAST:event_connectToServerButtonActionPerformed

    private void connectToServerButtonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectToServerButtonMouseEntered
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }// GEN-LAST:event_connectToServerButtonMouseEntered

    private void connectToServerButtonMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_connectToServerButtonMouseExited
        InterfaceManager.buttonHover(connectToServerButton, false, "medium");
    }// GEN-LAST:event_connectToServerButtonMouseExited

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientServerConnection().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectToServerButton;
    private javax.swing.JLabel discussionNetServerConnectionLabel1;
    private javax.swing.JLabel discussionNetServerConnectionLabel2;
    private javax.swing.JLabel footerLicensesTextLabel;
    private javax.swing.JLabel footerTextLabel;
    private javax.swing.JLabel mainImage;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel serverIPAddressLabel;
    private javax.swing.JTextField serverIPAddressTextbox;
    private javax.swing.JLabel serverPortLabel;
    private javax.swing.JTextField serverPortTextbox;
    // End of variables declaration//GEN-END:variables
}
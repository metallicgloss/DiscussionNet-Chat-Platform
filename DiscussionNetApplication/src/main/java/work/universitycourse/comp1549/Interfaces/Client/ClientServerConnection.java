package work.universitycourse.comp1549.Interfaces.Client;

import work.universitycourse.comp1549.Components.RoundJTextField;
import work.universitycourse.comp1549.Modules.InterfaceManager;
import work.universitycourse.comp1549.Modules.ClientManager;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        clientServerConnectionPanel = new javax.swing.JPanel();
        mainImage = new javax.swing.JLabel();
        discussionNetServerConnectionLabel1 = new javax.swing.JLabel();
        discussionNetServerConnectionLabel2 = new javax.swing.JLabel();
        serverIPAddressLabel = new javax.swing.JLabel();
        userMessagesIconLabel = new javax.swing.JLabel();
        connectToServerButton = new javax.swing.JButton();
        serverPortLabel = new javax.swing.JLabel();
        serverIPAddressTextField = new RoundJTextField();
        serverPortTextField = new RoundJTextField();
        footerLicensesTextLabel = new javax.swing.JLabel();
        footerTextLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("work/universitycourse/comp1549/Interfaces/Client/Bundle"); // NOI18N
        setTitle(bundle.getString("ClientServerConnection.title")); // NOI18N
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new java.awt.Dimension(854, 519));
        setName("DiscussionNet"); // NOI18N
        setResizable(false);

        clientServerConnectionPanel.setBackground(new java.awt.Color(255, 255, 255));
        clientServerConnectionPanel.setMaximumSize(new java.awt.Dimension(847, 519));
        clientServerConnectionPanel.setMinimumSize(new java.awt.Dimension(847, 519));
        clientServerConnectionPanel.setName("clientServerConnectionPanel"); // NOI18N
        clientServerConnectionPanel.setPreferredSize(new java.awt.Dimension(847, 519));
        InterfaceManager.detectExitRequest(clientServerConnectionPanel);
        clientServerConnectionPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clientServerConnectionPanelMouseClicked(evt);
            }
        });
        clientServerConnectionPanel.setLayout(null);

        mainImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/graphics/connection.png"))); // NOI18N
        mainImage.setName("mainImage"); // NOI18N
        clientServerConnectionPanel.add(mainImage);
        mainImage.setBounds(78, 187, 205, 181);

        discussionNetServerConnectionLabel1.setFont(new java.awt.Font("Montserrat SemiBold", 0, 24)); // NOI18N
        discussionNetServerConnectionLabel1.setForeground(new java.awt.Color(0, 36, 109));
        discussionNetServerConnectionLabel1.setText(bundle.getString("ClientServerConnection.discussionNetServerConnectionLabel1.text_1")); // NOI18N
        discussionNetServerConnectionLabel1.setName("discussionNetServerConnectionLabel1"); // NOI18N
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel1);
        discussionNetServerConnectionLabel1.setBounds(363, 148, 175, 30);

        discussionNetServerConnectionLabel2.setFont(new java.awt.Font("Montserrat", 0, 24)); // NOI18N
        discussionNetServerConnectionLabel2.setText(bundle.getString("ClientServerConnection.discussionNetServerConnectionLabel2.text_1")); // NOI18N
        discussionNetServerConnectionLabel2.setName("discussionNetServerConnectionLabel2"); // NOI18N
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel2);
        discussionNetServerConnectionLabel2.setBounds(544, 148, 224, 30);

        serverIPAddressLabel.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverIPAddressLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/wireless.png"))); // NOI18N
        serverIPAddressLabel.setText(bundle.getString("ClientServerConnection.serverIPAddressLabel.text_1")); // NOI18N
        serverIPAddressLabel.setName("serverIPAddressLabel"); // NOI18N
        clientServerConnectionPanel.add(serverIPAddressLabel);
        serverIPAddressLabel.setBounds(363, 196, 129, 17);

        userMessagesIconLabel.setBackground(new java.awt.Color(255, 255, 255));
        userMessagesIconLabel.setForeground(new java.awt.Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/arrow_icon.png"))); // NOI18N
        userMessagesIconLabel.setText(bundle.getString("ClientServerConnection.userMessagesIconLabel.text")); // NOI18N
        userMessagesIconLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userMessagesIconLabel.setFocusable(false);
        userMessagesIconLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        userMessagesIconLabel.setIconTextGap(0);
        userMessagesIconLabel.setInheritsPopupMenu(false);
        userMessagesIconLabel.setName("userMessagesIconLabel"); // NOI18N
        userMessagesIconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userMessagesIconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userMessagesIconLabelMouseEntered(evt);
            }
        });
        clientServerConnectionPanel.add(userMessagesIconLabel);
        userMessagesIconLabel.setBounds(590, 340, 30, 30);

        connectToServerButton.setBackground(new java.awt.Color(255, 255, 255));
        connectToServerButton.setFont(new java.awt.Font("Montserrat", 0, 15)); // NOI18N
        connectToServerButton.setForeground(new java.awt.Color(255, 255, 255));
        connectToServerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/buttons/medium.png"))); // NOI18N
        connectToServerButton.setText(bundle.getString("ClientServerConnection.connectToServerButton.text_1")); // NOI18N
        connectToServerButton.setBorder(null);
        connectToServerButton.setBorderPainted(false);
        connectToServerButton.setContentAreaFilled(false);
        connectToServerButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        connectToServerButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectToServerButton.setName("connectToServerButton"); // NOI18N
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
        clientServerConnectionPanel.add(connectToServerButton);
        connectToServerButton.setBounds(360, 320, 340, 69);

        serverPortLabel.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverPortLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/port_icon.png"))); // NOI18N
        serverPortLabel.setText(bundle.getString("ClientServerConnection.serverPortLabel.text_1")); // NOI18N
        serverPortLabel.setName("serverPortLabel"); // NOI18N
        clientServerConnectionPanel.add(serverPortLabel);
        serverPortLabel.setBounds(363, 268, 88, 17);

        serverIPAddressTextField.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverIPAddressTextField.setForeground(new java.awt.Color(152, 150, 162));
        serverIPAddressTextField.setCaretColor(new java.awt.Color(152, 150, 162));
        serverIPAddressTextField.setDisabledTextColor(new java.awt.Color(152, 150, 162));
        serverIPAddressTextField.setMargin(new java.awt.Insets(0, 0, 0, 0));
        serverIPAddressTextField.setName("serverIPAddressTextField"); // NOI18N
        serverIPAddressTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                serverIPAddressTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                serverIPAddressTextFieldFocusLost(evt);
            }
        });
        clientServerConnectionPanel.add(serverIPAddressTextField);
        serverIPAddressTextField.setBounds(362, 219, 340, 19);

        serverPortTextField.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        serverPortTextField.setForeground(new java.awt.Color(152, 150, 162));
        serverPortTextField.setCaretColor(new java.awt.Color(152, 150, 162));
        serverPortTextField.setDisabledTextColor(new java.awt.Color(152, 150, 162));
        serverPortTextField.setMargin(new java.awt.Insets(0, 0, 0, 0));
        serverPortTextField.setName("serverPortTextField"); // NOI18N
        serverPortTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                serverPortTextFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                serverPortTextFieldFocusLost(evt);
            }
        });
        clientServerConnectionPanel.add(serverPortTextField);
        serverPortTextField.setBounds(362, 291, 340, 19);

        footerLicensesTextLabel.setFont(new java.awt.Font("Montserrat", 2, 9)); // NOI18N
        footerLicensesTextLabel.setForeground(new java.awt.Color(47, 46, 65));
        footerLicensesTextLabel.setText(bundle.getString("ClientServerConnection.footerLicensesTextLabel.text")); // NOI18N
        footerLicensesTextLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        footerLicensesTextLabel.setName("footerLicensesTextLabel"); // NOI18N
        footerLicensesTextLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });
        clientServerConnectionPanel.add(footerLicensesTextLabel);
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);

        footerTextLabel.setFont(new java.awt.Font("Montserrat", 0, 9)); // NOI18N
        footerTextLabel.setForeground(new java.awt.Color(47, 46, 65));
        footerTextLabel.setText(bundle.getString("ClientServerConnection.footerTextLabel.text_1")); // NOI18N
        footerTextLabel.setName("footerTextLabel"); // NOI18N
        clientServerConnectionPanel.add(footerTextLabel);
        footerTextLabel.setBounds(285, 507, 189, 12);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientServerConnectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clientServerConnectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName(bundle.getString("ClientServerConnection.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectToServerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectToServerButtonActionPerformed
        connectToServer();
    }//GEN-LAST:event_connectToServerButtonActionPerformed

    private void serverIPAddressTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverIPAddressTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, true);
    }//GEN-LAST:event_serverIPAddressTextFieldFocusGained

    private void serverIPAddressTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverIPAddressTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
    }//GEN-LAST:event_serverIPAddressTextFieldFocusLost

    private void serverPortTextFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverPortTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, true);
    }//GEN-LAST:event_serverPortTextFieldFocusGained

    private void serverPortTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_serverPortTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }//GEN-LAST:event_serverPortTextFieldFocusLost

    private void clientServerConnectionPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientServerConnectionPanelMouseClicked
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }//GEN-LAST:event_clientServerConnectionPanelMouseClicked

    private void userMessagesIconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseClicked
        connectToServer();
    }//GEN-LAST:event_userMessagesIconLabelMouseClicked

    private void userMessagesIconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseEntered
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }//GEN-LAST:event_userMessagesIconLabelMouseEntered

    private void footerLicensesTextLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.changeWindow(this, new Licenses());
    }//GEN-LAST:event_footerLicensesTextLabelMouseClicked

    private void connectToServerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectToServerButtonMouseEntered
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }//GEN-LAST:event_connectToServerButtonMouseEntered

    private void connectToServerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_connectToServerButtonMouseExited
        InterfaceManager.buttonHover(connectToServerButton, false, "medium");
    }//GEN-LAST:event_connectToServerButtonMouseExited

    private void connectToServer() {
        // TODO: Validation / Sanitisation of Input - potentially live updates for colour if invalid.
        InterfaceManager.changeWindow(this, new ClientIdentitySetup(serverIPAddressTextField.getText(), serverPortTextField.getText()));
    }

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
    private javax.swing.JPanel clientServerConnectionPanel;
    private javax.swing.JButton connectToServerButton;
    private javax.swing.JLabel discussionNetServerConnectionLabel1;
    private javax.swing.JLabel discussionNetServerConnectionLabel2;
    private javax.swing.JLabel footerLicensesTextLabel;
    private javax.swing.JLabel footerTextLabel;
    private javax.swing.JLabel mainImage;
    private javax.swing.JLabel serverIPAddressLabel;
    private javax.swing.JTextField serverIPAddressTextField;
    private javax.swing.JLabel serverPortLabel;
    private javax.swing.JTextField serverPortTextField;
    private javax.swing.JLabel userMessagesIconLabel;
    // End of variables declaration//GEN-END:variables
}

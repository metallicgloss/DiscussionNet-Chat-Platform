package work.universitycourse.comp1549.Interfaces.Client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */

@SuppressWarnings("serial")
public class ClientServerConnection extends JFrame {

    /**
     * Creates new form serverConnection
     */
    public ClientServerConnection() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        clientServerConnectionPanel = new JPanel();
        mainImage = new JLabel();
        discussionNetServerConnectionLabel1 = new JLabel();
        discussionNetServerConnectionLabel2 = new JLabel();
        serverIPAddressLabel = new JLabel();
        userMessagesIconLabel = new JLabel();
        connectToServerButton = new JButton();
        serverPortLabel = new JLabel();
        serverIPAddressTextField = new work.universitycourse.comp1549.Components.JRoundedTextField();
        serverPortTextField = new work.universitycourse.comp1549.Components.JRoundedTextField();
        footerLicensesTextLabel = new JLabel();
        footerTextLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        clientServerConnectionPanel.setBackground(new Color(255, 255, 255));
        clientServerConnectionPanel.setMaximumSize(new Dimension(847, 519));
        clientServerConnectionPanel.setMinimumSize(new Dimension(847, 519));
        clientServerConnectionPanel.setName("clientServerConnectionPanel");
        clientServerConnectionPanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(clientServerConnectionPanel);
        clientServerConnectionPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                clientServerConnectionPanelMouseClicked(evt);
            }
        });
        clientServerConnectionPanel.setLayout(null);

        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/connection.png")));
        mainImage.setName("mainImage");
        clientServerConnectionPanel.add(mainImage);
        mainImage.setBounds(78, 187, 205, 181);

        discussionNetServerConnectionLabel1.setFont(new Font("Montserrat SemiBold", 0, 24));
        discussionNetServerConnectionLabel1.setForeground(new Color(0, 36, 109));
        discussionNetServerConnectionLabel1.setText("DiscussionNet");
        discussionNetServerConnectionLabel1.setName("discussionNetServerConnectionLabel1");
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel1);
        discussionNetServerConnectionLabel1.setBounds(363, 148, 175, 30);

        discussionNetServerConnectionLabel2.setFont(new Font("Montserrat", 0, 24));
        discussionNetServerConnectionLabel2.setText("Server Connection");
        discussionNetServerConnectionLabel2.setName("discussionNetServerConnectionLabel2");
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel2);
        discussionNetServerConnectionLabel2.setBounds(544, 148, 224, 30);

        serverIPAddressLabel.setFont(new Font("Montserrat", 0, 13));
        serverIPAddressLabel.setIcon(new ImageIcon(getClass().getResource("/icons/wireless.png")));
        serverIPAddressLabel.setText("Server IP Address");
        serverIPAddressLabel.setName("serverIPAddressLabel");
        clientServerConnectionPanel.add(serverIPAddressLabel);
        serverIPAddressLabel.setBounds(363, 196, 129, 17);

        userMessagesIconLabel.setBackground(new Color(255, 255, 255));
        userMessagesIconLabel.setForeground(new Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new ImageIcon(getClass().getResource("/icons/arrow_icon.png")));
        userMessagesIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userMessagesIconLabel.setFocusable(false);
        userMessagesIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userMessagesIconLabel.setIconTextGap(0);
        userMessagesIconLabel.setInheritsPopupMenu(false);
        userMessagesIconLabel.setName("userMessagesIconLabel");
        userMessagesIconLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                userMessagesIconLabelMouseClicked(evt);
            }

            public void mouseEntered(MouseEvent evt) {
                userMessagesIconLabelMouseEntered(evt);
            }
        });
        clientServerConnectionPanel.add(userMessagesIconLabel);
        userMessagesIconLabel.setBounds(590, 340, 30, 30);

        connectToServerButton.setBackground(new Color(255, 255, 255));
        connectToServerButton.setFont(new Font("Montserrat", 0, 15));
        connectToServerButton.setForeground(new Color(255, 255, 255));
        connectToServerButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        connectToServerButton.setText("Connect to Server      ");
        connectToServerButton.setBorder(null);
        connectToServerButton.setBorderPainted(false);
        connectToServerButton.setContentAreaFilled(false);
        connectToServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        connectToServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        connectToServerButton.setName("connectToServerButton");
        connectToServerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                connectToServerButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                connectToServerButtonMouseExited(evt);
            }
        });
        connectToServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                connectToServerButtonActionPerformed(evt);
            }
        });
        clientServerConnectionPanel.add(connectToServerButton);
        connectToServerButton.setBounds(360, 320, 340, 69);

        serverPortLabel.setFont(new Font("Montserrat", 0, 13));
        serverPortLabel.setIcon(new ImageIcon(getClass().getResource("/icons/port_icon.png")));
        serverPortLabel.setText("Server Port");
        serverPortLabel.setName("serverPortLabel");
        clientServerConnectionPanel.add(serverPortLabel);
        serverPortLabel.setBounds(363, 268, 88, 17);

        serverIPAddressTextField.setFont(new Font("Montserrat", 0, 13));
        serverIPAddressTextField.setForeground(new Color(152, 150, 162));
        serverIPAddressTextField.setCaretColor(new Color(152, 150, 162));
        serverIPAddressTextField.setDisabledTextColor(new Color(152, 150, 162));
        serverIPAddressTextField.setMargin(new Insets(0, 5, 0, 5));
        serverIPAddressTextField.setName("serverIPAddressTextField");
        serverIPAddressTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                serverIPAddressTextFieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                serverIPAddressTextFieldFocusLost(evt);
            }
        });
        clientServerConnectionPanel.add(serverIPAddressTextField);
        serverIPAddressTextField.setBounds(362, 219, 340, 30);

        serverPortTextField.setFont(new Font("Montserrat", 0, 13));
        serverPortTextField.setForeground(new Color(152, 150, 162));
        serverPortTextField.setCaretColor(new Color(152, 150, 162));
        serverPortTextField.setDisabledTextColor(new Color(152, 150, 162));
        serverPortTextField.setMargin(new Insets(0, 5, 0, 5));
        serverPortTextField.setName("serverPortTextField");
        serverPortTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                serverPortTextFieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                serverPortTextFieldFocusLost(evt);
            }
        });
        clientServerConnectionPanel.add(serverPortTextField);
        serverPortTextField.setBounds(362, 291, 340, 30);

        footerLicensesTextLabel.setFont(new Font("Montserrat", 2, 9));
        footerLicensesTextLabel.setForeground(new Color(47, 46, 65));
        footerLicensesTextLabel.setText("Software Licenses");
        footerLicensesTextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerLicensesTextLabel.setName("footerLicensesTextLabel");
        footerLicensesTextLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });
        clientServerConnectionPanel.add(footerLicensesTextLabel);
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);

        footerTextLabel.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel.setName("footerTextLabel");
        clientServerConnectionPanel.add(footerTextLabel);
        footerTextLabel.setBounds(285, 507, 189, 12);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientServerConnectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientServerConnectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void connectToServerButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_connectToServerButtonActionPerformed
        connectToServer();
    }//GEN-LAST:event_connectToServerButtonActionPerformed

    private void serverIPAddressTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_serverIPAddressTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, true);
    }//GEN-LAST:event_serverIPAddressTextFieldFocusGained

    private void serverIPAddressTextFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_serverIPAddressTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
    }//GEN-LAST:event_serverIPAddressTextFieldFocusLost

    private void serverPortTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_serverPortTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, true);
    }//GEN-LAST:event_serverPortTextFieldFocusGained

    private void serverPortTextFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_serverPortTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }//GEN-LAST:event_serverPortTextFieldFocusLost

    private void clientServerConnectionPanelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_clientServerConnectionPanelMouseClicked
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }//GEN-LAST:event_clientServerConnectionPanelMouseClicked

    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseClicked
        connectToServer();
    }//GEN-LAST:event_userMessagesIconLabelMouseClicked

    private void userMessagesIconLabelMouseEntered(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseEntered
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }//GEN-LAST:event_userMessagesIconLabelMouseEntered

    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.displayLicenses();
    }//GEN-LAST:event_footerLicensesTextLabelMouseClicked

    private void connectToServerButtonMouseEntered(MouseEvent evt) {//GEN-FIRST:event_connectToServerButtonMouseEntered
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }//GEN-LAST:event_connectToServerButtonMouseEntered

    private void connectToServerButtonMouseExited(MouseEvent evt) {//GEN-FIRST:event_connectToServerButtonMouseExited
        InterfaceManager.buttonHover(connectToServerButton, false, "medium");
    }//GEN-LAST:event_connectToServerButtonMouseExited

    private void connectToServer() {
        if (InterfaceManager.validateIPAddress(serverIPAddressTextField.getText())
                && InterfaceManager.validatePort(serverPortTextField.getText())) {
            InterfaceManager.changeWindow(this,
                    new ClientIdentitySetup(serverIPAddressTextField.getText(), serverPortTextField.getText()));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
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
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientServerConnection().setVisible(true);
            }
        });
    }

    private JPanel clientServerConnectionPanel;
    private JButton connectToServerButton;
    private JLabel discussionNetServerConnectionLabel1;
    private JLabel discussionNetServerConnectionLabel2;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel;
    private JLabel mainImage;
    private JLabel serverIPAddressLabel;
    private JTextField serverIPAddressTextField;
    private JLabel serverPortLabel;
    private JTextField serverPortTextField;
    private JLabel userMessagesIconLabel;
}

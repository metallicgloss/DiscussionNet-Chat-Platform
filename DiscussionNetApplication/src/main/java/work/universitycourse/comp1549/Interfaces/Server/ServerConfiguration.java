package work.universitycourse.comp1549.Interfaces.Server;

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
public class ServerConfiguration extends JFrame {

    /**
     * Creates new form serverManagementConfiguration
     */
    public ServerConfiguration() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        serverConfigurationPanel = new JPanel();
        serverManagementConfigurationLabel1 = new JLabel();
        serverManagementConfigurationLabel2 = new JLabel();
        configuredIPAddresLabel = new JLabel();
        assignedNetworkPortLabel = new JLabel();
        assignedNetworkPortTextfield = new work.universitycourse.comp1549.Components.JRoundedTextField();
        userMessagesIconLabel = new JLabel();
        provisionServerButton = new JButton();
        mainImage = new JLabel();
        configuredIPAddressTextfield = new work.universitycourse.comp1549.Components.JRoundedTextField();
        footerTextLabel1 = new JLabel();
        footerLicensesTextLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setBackground(new Color(255, 255, 255));
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        serverConfigurationPanel.setBackground(new Color(255, 255, 255));
        serverConfigurationPanel.setMaximumSize(new Dimension(847, 519));
        serverConfigurationPanel.setMinimumSize(new Dimension(847, 519));
        serverConfigurationPanel.setName("serverConfigurationPanel");
        serverConfigurationPanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(serverConfigurationPanel);
        serverConfigurationPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                serverConfigurationPanelMouseClicked(evt);
            }
        });
        serverConfigurationPanel.setLayout(null);

        serverManagementConfigurationLabel1.setFont(new Font("Montserrat SemiBold", 0, 24));
        serverManagementConfigurationLabel1.setForeground(new Color(0, 36, 109));
        serverManagementConfigurationLabel1.setText("Server");
        serverManagementConfigurationLabel1.setName("serverManagementConfigurationLabel1");
        serverConfigurationPanel.add(serverManagementConfigurationLabel1);
        serverManagementConfigurationLabel1.setBounds(233, 238, 79, 30);

        serverManagementConfigurationLabel2.setFont(new Font("Montserrat", 0, 24));
        serverManagementConfigurationLabel2.setText("Management Configuration");
        serverManagementConfigurationLabel2.setName("serverManagementConfigurationLabel2");
        serverConfigurationPanel.add(serverManagementConfigurationLabel2);
        serverManagementConfigurationLabel2.setBounds(318, 238, 335, 30);

        configuredIPAddresLabel.setFont(new Font("Montserrat", 0, 13));
        configuredIPAddresLabel.setIcon(new ImageIcon(getClass().getResource("/icons/wireless.png")));
        configuredIPAddresLabel.setText("Configured IP Address");
        configuredIPAddresLabel.setName("configuredIPAddresLabel");
        serverConfigurationPanel.add(configuredIPAddresLabel);
        configuredIPAddresLabel.setBounds(140, 310, 162, 17);

        assignedNetworkPortLabel.setFont(new Font("Montserrat", 0, 13));
        assignedNetworkPortLabel.setIcon(new ImageIcon(getClass().getResource("/icons/port_icon.png")));
        assignedNetworkPortLabel.setText("Assigned Network Port");
        assignedNetworkPortLabel.setName("assignedNetworkPortLabel");
        serverConfigurationPanel.add(assignedNetworkPortLabel);
        assignedNetworkPortLabel.setBounds(549, 310, 166, 17);

        assignedNetworkPortTextfield.setFont(new Font("Montserrat", 0, 13));
        assignedNetworkPortTextfield.setForeground(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setHorizontalAlignment(JTextField.CENTER);
        assignedNetworkPortTextfield.setCaretColor(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setDisabledTextColor(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setMargin(new Insets(0, 5, 0, 5));
        assignedNetworkPortTextfield.setName("assignedNetworkPortTextfield");
        assignedNetworkPortTextfield.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                assignedNetworkPortTextfieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                assignedNetworkPortTextfieldFocusLost(evt);
            }
        });
        serverConfigurationPanel.add(assignedNetworkPortTextfield);
        assignedNetworkPortTextfield.setBounds(456, 333, 334, 30);

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

            public void mouseExited(MouseEvent evt) {
                userMessagesIconLabelMouseExited(evt);
            }
        });
        serverConfigurationPanel.add(userMessagesIconLabel);
        userMessagesIconLabel.setBounds(480, 390, 30, 30);

        provisionServerButton.setBackground(new Color(255, 255, 255));
        provisionServerButton.setFont(new Font("Montserrat", 0, 15));
        provisionServerButton.setForeground(new Color(255, 255, 255));
        provisionServerButton.setIcon(new ImageIcon(getClass().getResource("/buttons/large.png")));
        provisionServerButton.setText("Provision Server       ");
        provisionServerButton.setBorder(null);
        provisionServerButton.setBorderPainted(false);
        provisionServerButton.setContentAreaFilled(false);
        provisionServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        provisionServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        provisionServerButton.setIconTextGap(3);
        provisionServerButton.setName("provisionServerButton");
        provisionServerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                provisionServerButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                provisionServerButtonMouseExited(evt);
            }
        });
        provisionServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                provisionServerButtonActionPerformed(evt);
            }
        });
        serverConfigurationPanel.add(provisionServerButton);
        provisionServerButton.setBounds(50, 370, 761, 69);

        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/settings.png")));
        mainImage.setName("mainImage");
        serverConfigurationPanel.add(mainImage);
        mainImage.setBounds(272, 40, 315, 192);

        configuredIPAddressTextfield.setFont(new Font("Montserrat", 0, 13));
        configuredIPAddressTextfield.setForeground(new Color(152, 150, 162));
        configuredIPAddressTextfield.setHorizontalAlignment(JTextField.CENTER);
        configuredIPAddressTextfield.setCaretColor(new Color(152, 150, 162));
        configuredIPAddressTextfield.setDisabledTextColor(new Color(152, 150, 162));
        configuredIPAddressTextfield.setMargin(new Insets(0, 5, 0, 5));
        configuredIPAddressTextfield.setName("configuredIPAddressTextfield");
        configuredIPAddressTextfield.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                configuredIPAddressTextfieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                configuredIPAddressTextfieldFocusLost(evt);
            }
        });
        serverConfigurationPanel.add(configuredIPAddressTextfield);
        configuredIPAddressTextfield.setBounds(68, 333, 334, 30);

        footerTextLabel1.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel1.setForeground(new Color(47, 46, 65));
        footerTextLabel1.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel1.setName("footerTextLabel1");
        serverConfigurationPanel.add(footerTextLabel1);
        footerTextLabel1.setBounds(292, 507, 189, 12);

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
        serverConfigurationPanel.add(footerLicensesTextLabel);
        footerLicensesTextLabel.setBounds(487, 507, 80, 12);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                serverConfigurationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                serverConfigurationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }

    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.displayLicenses();
    }//GEN-LAST:event_footerLicensesTextLabelMouseClicked

    private void configuredIPAddressTextfieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_configuredIPAddressTextfieldFocusGained
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, true);
    }//GEN-LAST:event_configuredIPAddressTextfieldFocusGained

    private void configuredIPAddressTextfieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_configuredIPAddressTextfieldFocusLost
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, false);
    }//GEN-LAST:event_configuredIPAddressTextfieldFocusLost

    private void assignedNetworkPortTextfieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_assignedNetworkPortTextfieldFocusGained
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, true);
    }//GEN-LAST:event_assignedNetworkPortTextfieldFocusGained

    private void assignedNetworkPortTextfieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_assignedNetworkPortTextfieldFocusLost
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, false);
    }//GEN-LAST:event_assignedNetworkPortTextfieldFocusLost

    private void serverConfigurationPanelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_serverConfigurationPanelMouseClicked
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, false);
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, false);
    }//GEN-LAST:event_serverConfigurationPanelMouseClicked

    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseClicked
        provisionServer();
    }//GEN-LAST:event_userMessagesIconLabelMouseClicked

    private void userMessagesIconLabelMouseEntered(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseEntered
        InterfaceManager.buttonHover(provisionServerButton, true, "large");
    }//GEN-LAST:event_userMessagesIconLabelMouseEntered

    private void provisionServerButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_provisionServerButtonActionPerformed
        provisionServer();
    }//GEN-LAST:event_provisionServerButtonActionPerformed

    private void provisionServerButtonMouseEntered(MouseEvent evt) {//GEN-FIRST:event_provisionServerButtonMouseEntered
        InterfaceManager.buttonHover(provisionServerButton, true, "large");
    }//GEN-LAST:event_provisionServerButtonMouseEntered

    private void provisionServerButtonMouseExited(MouseEvent evt) {//GEN-FIRST:event_provisionServerButtonMouseExited
        InterfaceManager.buttonHover(provisionServerButton, false, "large");
    }//GEN-LAST:event_provisionServerButtonMouseExited

    private void userMessagesIconLabelMouseExited(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseExited
        InterfaceManager.buttonHover(provisionServerButton, false, "large");
    }//GEN-LAST:event_userMessagesIconLabelMouseExited

    private void provisionServer() {
        if (InterfaceManager.validateIPAddress(configuredIPAddressTextfield.getText())
                && InterfaceManager.validatePort(assignedNetworkPortTextfield.getText())) {
            InterfaceManager.changeWindow(this,
                    new ServerOverview(configuredIPAddressTextfield.getText(), assignedNetworkPortTextfield.getText()));
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
            java.util.logging.Logger.getLogger(ServerConfiguration.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerConfiguration.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerConfiguration.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerConfiguration.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerConfiguration().setVisible(true);
            }
        });
    }

    private JLabel assignedNetworkPortLabel;
    private JTextField assignedNetworkPortTextfield;
    private JLabel configuredIPAddresLabel;
    private JTextField configuredIPAddressTextfield;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel1;
    private JLabel mainImage;
    private JButton provisionServerButton;
    private JPanel serverConfigurationPanel;
    private JLabel serverManagementConfigurationLabel1;
    private JLabel serverManagementConfigurationLabel2;
    private JLabel userMessagesIconLabel;
}

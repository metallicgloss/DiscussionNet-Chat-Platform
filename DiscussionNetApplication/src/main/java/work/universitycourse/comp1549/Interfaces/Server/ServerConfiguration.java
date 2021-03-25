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

import work.universitycourse.comp1549.Components.JRoundedTextField;
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

    // #-----------------------------------------------------------------------#
    // #                  Create New ServerConfiguration Form                  #
    // #-----------------------------------------------------------------------#
    public ServerConfiguration() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        // Initialise interface objects.
        assignedNetworkPortLabel = new JLabel();
        assignedNetworkPortTextfield = new JRoundedTextField();
        configuredIPAddressLabel = new JLabel();
        configuredIPAddressTextfield = new JRoundedTextField();
        footerLicensesTextLabel = new JLabel();
        footerTextLabel1 = new JLabel();
        mainImage = new JLabel();
        provisionServerButton = new JButton();
        serverConfigurationPanel = new JPanel();
        serverManagementConfigurationLabel1 = new JLabel();
        serverManagementConfigurationLabel2 = new JLabel();
        userMessagesIconLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setBackground(new Color(255, 255, 255));
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        // Apply settings to the configuration panel.
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

        // Apply settings to the management title label.
        serverManagementConfigurationLabel1.setBounds(233, 238, 79, 30);
        serverManagementConfigurationLabel1.setFont(InterfaceManager.montserratSemiBold.deriveFont(23.0f));
        serverManagementConfigurationLabel1.setForeground(new Color(0, 36, 109));
        serverManagementConfigurationLabel1.setName("serverManagementConfigurationLabel1");
        serverManagementConfigurationLabel1.setText("Server");
        serverConfigurationPanel.add(serverManagementConfigurationLabel1);

        // Apply settings to the management title label (section 2)
        serverManagementConfigurationLabel2.setBounds(318, 238, 335, 30);
        serverManagementConfigurationLabel2.setFont(InterfaceManager.montserratRegular.deriveFont(23.0f));
        serverManagementConfigurationLabel2.setName("serverManagementConfigurationLabel2");
        serverManagementConfigurationLabel2.setText("Management Configuration");
        serverConfigurationPanel.add(serverManagementConfigurationLabel2);

        // Apply settings to the configured IP label.
        configuredIPAddressLabel.setBounds(140, 310, 162, 17);
        configuredIPAddressLabel.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        configuredIPAddressLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/wireless.png")));
        configuredIPAddressLabel.setName("configuredIPAddressLabel");
        configuredIPAddressLabel.setText("IP Address");
        serverConfigurationPanel.add(configuredIPAddressLabel);

        // Apply settings and effects to the IP text field.
        configuredIPAddressTextfield.setBounds(68, 333, 334, 30);
        configuredIPAddressTextfield.setCaretColor(new Color(152, 150, 162));
        configuredIPAddressTextfield.setDisabledTextColor(new Color(152, 150, 162));
        configuredIPAddressTextfield.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        configuredIPAddressTextfield.setForeground(new Color(152, 150, 162));
        configuredIPAddressTextfield.setHorizontalAlignment(JTextField.CENTER);
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

        // Apply settings to the allocated port label.
        assignedNetworkPortLabel.setBounds(549, 310, 166, 17);
        assignedNetworkPortLabel.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        assignedNetworkPortLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/port_icon.png")));
        assignedNetworkPortLabel.setName("assignedNetworkPortLabel");
        assignedNetworkPortLabel.setText("Port");
        serverConfigurationPanel.add(assignedNetworkPortLabel);

        // Apply settings and effects to the port text field.
        assignedNetworkPortTextfield.setBounds(456, 333, 334, 30);
        assignedNetworkPortTextfield.setCaretColor(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setDisabledTextColor(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        assignedNetworkPortTextfield.setForeground(new Color(152, 150, 162));
        assignedNetworkPortTextfield.setHorizontalAlignment(JTextField.CENTER);
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

        // Configure icon label to act as button, apply settings.
        userMessagesIconLabel.setBackground(new Color(255, 255, 255));
        userMessagesIconLabel.setBounds(480, 390, 30, 30);
        userMessagesIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userMessagesIconLabel.setFocusable(false);
        userMessagesIconLabel.setForeground(new Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userMessagesIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/arrow_icon.png")));
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

        // Configure button with events, apply settings.
        provisionServerButton.setBackground(new Color(255, 255, 255));
        provisionServerButton.setBorder(null);
        provisionServerButton.setBorderPainted(false);
        provisionServerButton.setBounds(50, 370, 761, 69);
        provisionServerButton.setContentAreaFilled(false);
        provisionServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        provisionServerButton.setFont(InterfaceManager.montserratRegular.deriveFont(15.0f));
        provisionServerButton.setForeground(new Color(255, 255, 255));
        provisionServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        provisionServerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/buttons/large.png")));
        provisionServerButton.setIconTextGap(3);
        provisionServerButton.setName("provisionServerButton");
        provisionServerButton.setText("Provision Server       ");
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

        // Set graphic settings. 
        mainImage.setBounds(272, 40, 315, 192);
        mainImage.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/settings.png")));
        mainImage.setName("mainImage");
        serverConfigurationPanel.add(mainImage);

        // Apply settings to the first footer label.
        footerTextLabel1.setBounds(292, 507, 189, 12);
        footerTextLabel1.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerTextLabel1.setForeground(new Color(47, 46, 65));
        footerTextLabel1.setName("footerTextLabel1");
        footerTextLabel1.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        serverConfigurationPanel.add(footerTextLabel1);

        // Apply settings and action to the footer display label.
        footerLicensesTextLabel.setBounds(487, 507, 80, 12);
        footerLicensesTextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerLicensesTextLabel.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerLicensesTextLabel.setForeground(new Color(47, 46, 65));
        footerLicensesTextLabel.setName("footerLicensesTextLabel");
        footerLicensesTextLabel.setText("Software Licenses");
        footerLicensesTextLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });
        serverConfigurationPanel.add(footerLicensesTextLabel);

        // Apply group layout configuration.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                serverConfigurationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                serverConfigurationPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Display licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
    }

    // Disable all field focus effects.
    private void serverConfigurationPanelMouseClicked(MouseEvent evt) {
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, false);
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, false);
    }

    // Execute server provisioning.
    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {
        provisionServer();
    }

    // Execute server provisioning.
    private void provisionServerButtonActionPerformed(ActionEvent evt) {
        provisionServer();
    }

    // Validate user input, if passed, change window.
    private void provisionServer() {
        if (InterfaceManager.validateIPAddress(configuredIPAddressTextfield.getText())
                && InterfaceManager.validatePort(assignedNetworkPortTextfield.getText())) {
            InterfaceManager.changeWindow(this,
                    new ServerOverview(configuredIPAddressTextfield.getText(), assignedNetworkPortTextfield.getText()));
        }
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Effects                         #
    // #-----------------------------------------------------------------------#

    // Configure Focus Gained Effect On
    private void configuredIPAddressTextfieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, true);
    }

    // Configure Focus Gained Effect Off
    private void configuredIPAddressTextfieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(configuredIPAddressTextfield, false);
    }

    // Configure Focus Gained Effect On
    private void assignedNetworkPortTextfieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, true);
    }

    // Configure Focus Gained Effect Off
    private void assignedNetworkPortTextfieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(assignedNetworkPortTextfield, false);
    }

    // Configure Button Hover Effect On
    private void userMessagesIconLabelMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(provisionServerButton, true, "large");
    }

    // Configure Button Hover Effect Off
    private void userMessagesIconLabelMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(provisionServerButton, false, "large");
    }

    // Configure Button Hover Effect On
    private void provisionServerButtonMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(provisionServerButton, true, "large");
    }

    // Configure Button Hover Effect Off
    private void provisionServerButtonMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(provisionServerButton, false, "large");
    }

    // #-----------------------------------------------------------------------#
    // #                           Interface Runnable                          #
    // #-----------------------------------------------------------------------#

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    // If Windows style available, set the default look and feel of the generated elements.
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerConfiguration.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerConfiguration().setVisible(true);
            }
        });
    }

    private JButton provisionServerButton;
    private JLabel assignedNetworkPortLabel;
    private JLabel configuredIPAddressLabel;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel1;
    private JLabel mainImage;
    private JLabel serverManagementConfigurationLabel1;
    private JLabel serverManagementConfigurationLabel2;
    private JLabel userMessagesIconLabel;
    private JPanel serverConfigurationPanel;
    private JTextField assignedNetworkPortTextfield;
    private JTextField configuredIPAddressTextfield;
}

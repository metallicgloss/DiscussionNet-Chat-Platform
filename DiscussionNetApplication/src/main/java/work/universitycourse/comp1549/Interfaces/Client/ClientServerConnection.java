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
public class ClientServerConnection extends JFrame {

    // #-----------------------------------------------------------------------#
    // #                Create New ClientServerConnection Form                 #
    // #-----------------------------------------------------------------------#
    public ClientServerConnection() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        // Initialise interface objects.
        clientServerConnectionPanel = new JPanel();
        connectToServerButton = new JButton();
        discussionNetServerConnectionLabel1 = new JLabel();
        discussionNetServerConnectionLabel2 = new JLabel();
        footerLicensesTextLabel = new JLabel();
        footerTextLabel = new JLabel();
        mainImage = new JLabel();
        serverIPAddressLabel = new JLabel();
        serverIPAddressTextField = new JRoundedTextField();
        serverPortLabel = new JLabel();
        serverPortTextField = new JRoundedTextField();
        userMessagesIconLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        InterfaceManager.detectExitRequest(clientServerConnectionPanel);

        // Apply settings to the main panel.
        clientServerConnectionPanel.setBackground(new Color(255, 255, 255));
        clientServerConnectionPanel.setMaximumSize(new Dimension(847, 519));
        clientServerConnectionPanel.setMinimumSize(new Dimension(847, 519));
        clientServerConnectionPanel.setName("clientServerConnectionPanel");
        clientServerConnectionPanel.setPreferredSize(new Dimension(847, 519));
        clientServerConnectionPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                clientServerConnectionPanelMouseClicked(evt);
            }
        });
        clientServerConnectionPanel.setLayout(null);

        // Apply settings to the page graphic.
        mainImage.setBounds(78, 187, 205, 181);
        mainImage.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/connection.png")));
        mainImage.setName("mainImage");
        clientServerConnectionPanel.add(mainImage);

        // Apply settings to the title label (section 1)
        discussionNetServerConnectionLabel1.setBounds(363, 148, 175, 30);
        discussionNetServerConnectionLabel1.setFont(InterfaceManager.montserratSemiBold.deriveFont(23.0f));
        discussionNetServerConnectionLabel1.setForeground(new Color(0, 36, 109));
        discussionNetServerConnectionLabel1.setName("discussionNetServerConnectionLabel1");
        discussionNetServerConnectionLabel1.setText("DiscussionNet");
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel1);

        // Apply settings to the title label (section 2)
        discussionNetServerConnectionLabel2.setBounds(544, 148, 224, 30);
        discussionNetServerConnectionLabel2.setFont(InterfaceManager.montserratRegular.deriveFont(23.0f));
        discussionNetServerConnectionLabel2.setName("discussionNetServerConnectionLabel2");
        discussionNetServerConnectionLabel2.setText("Server Connection");
        clientServerConnectionPanel.add(discussionNetServerConnectionLabel2);

        // Apply settings to the IP address label.
        serverIPAddressLabel.setBounds(363, 196, 129, 17);
        serverIPAddressLabel.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        serverIPAddressLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/wireless.png")));
        serverIPAddressLabel.setName("serverIPAddressLabel");
        serverIPAddressLabel.setText("Server IP Address");
        clientServerConnectionPanel.add(serverIPAddressLabel);

        // Apply settings and effects to the IP text field.
        serverIPAddressTextField.setBounds(362, 219, 340, 30);
        serverIPAddressTextField.setCaretColor(new Color(152, 150, 162));
        serverIPAddressTextField.setDisabledTextColor(new Color(152, 150, 162));
        serverIPAddressTextField.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        serverIPAddressTextField.setForeground(new Color(152, 150, 162));
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

        // Apply settings to the port label.
        serverPortLabel.setBounds(363, 268, 88, 17);
        serverPortLabel.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        serverPortLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/port_icon.png")));
        serverPortLabel.setName("serverPortLabel");
        serverPortLabel.setText("Server Port");
        clientServerConnectionPanel.add(serverPortLabel);

        // Apply settings and effects to the server port text field.
        serverPortTextField.setBounds(362, 291, 340, 30);
        serverPortTextField.setCaretColor(new Color(152, 150, 162));
        serverPortTextField.setDisabledTextColor(new Color(152, 150, 162));
        serverPortTextField.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        serverPortTextField.setForeground(new Color(152, 150, 162));
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

        // Apply settings, effects and actions to the message send icon.
        userMessagesIconLabel.setBounds(590, 340, 30, 30);
        userMessagesIconLabel.setBackground(new Color(255, 255, 255));
        userMessagesIconLabel.setForeground(new Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/arrow_icon.png")));
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
                connectToServerButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                connectToServerButtonMouseExited(evt);
            }
        });
        clientServerConnectionPanel.add(userMessagesIconLabel);

        // Apply settings, effects and actions to the connection button.
        connectToServerButton.setBackground(new Color(255, 255, 255));
        connectToServerButton.setBorder(null);
        connectToServerButton.setBorderPainted(false);
        connectToServerButton.setBounds(360, 320, 340, 69);
        connectToServerButton.setContentAreaFilled(false);
        connectToServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        connectToServerButton.setFont(InterfaceManager.montserratRegular.deriveFont(15.0f));
        connectToServerButton.setForeground(new Color(255, 255, 255));
        connectToServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        connectToServerButton.setIcon(new ImageIcon(getClass().getResource("/imgs/buttons/medium.png")));
        connectToServerButton.setName("connectToServerButton");
        connectToServerButton.setText("Connect to Server      ");
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

        // Apply settings to the first footer label.
        footerTextLabel.setBounds(285, 507, 189, 12);
        footerTextLabel.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setName("footerTextLabel");
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        clientServerConnectionPanel.add(footerTextLabel);

        // Apply settings and action to the footer display label.
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);
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
        clientServerConnectionPanel.add(footerLicensesTextLabel);

        // Apply group layout configuration.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientServerConnectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientServerConnectionPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Execute connect to server function
    private void connectToServerButtonActionPerformed(ActionEvent evt) {
        connectToServer();
    }

    // Execute connect to server function
    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {
        connectToServer();
    }

    // Display licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
    }

    // Clear focus effects.
    private void clientServerConnectionPanelMouseClicked(MouseEvent evt) {
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }

    // If input valid, change window and pass values.
    private void connectToServer() {
        if (InterfaceManager.validateIPAddress(serverIPAddressTextField.getText())
                && InterfaceManager.validatePort(serverPortTextField.getText())) {
            InterfaceManager.changeWindow(this,
                    new ClientIdentitySetup(serverIPAddressTextField.getText(), serverPortTextField.getText()));
        }
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Effects                         #
    // #-----------------------------------------------------------------------#

    // Configure Focus Gained Effect On
    private void serverIPAddressTextFieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, true);
    }

    // Configure Focus Gained Effect Off
    private void serverIPAddressTextFieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(serverIPAddressTextField, false);
    }

    // Configure Focus Gained Effect On
    private void serverPortTextFieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, true);
    }

    // Configure Focus Gained Effect Off
    private void serverPortTextFieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(serverPortTextField, false);
    }
    
    // Configure Button Hover Effect On
    private void connectToServerButtonMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(connectToServerButton, true, "medium");
    }

    // Configure Button Hover Effect Off
    private void connectToServerButtonMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(connectToServerButton, false, "medium");
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientServerConnection.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Execute startup of interface.
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

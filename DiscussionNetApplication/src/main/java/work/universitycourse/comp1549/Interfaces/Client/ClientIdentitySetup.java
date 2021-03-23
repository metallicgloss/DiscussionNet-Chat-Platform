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
public class ClientIdentitySetup extends JFrame {

    private String serverIPAddress;
    private String serverPort;

    // #-----------------------------------------------------------------------#
    // #                  Create New ClientIdentitySetup Form                  #
    // #-----------------------------------------------------------------------#
    public ClientIdentitySetup(String serverIPAddress, String serverPort) {
        super();
        this.serverIPAddress = serverIPAddress;
        this.serverPort = serverPort;
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        // Initialise interface objects.
        assignedIdentificationTextfield = new JRoundedTextField();
        assignedIDNumberLabel = new JLabel();
        authenticateButton = new JButton();
        clientIdentitySetupPanel = new JPanel();
        clientIPAddressLabel = new JLabel();
        clientIPAddressTextField = new JRoundedTextField();
        clientPortLabel = new JLabel();
        clientPortTextField = new JRoundedTextField();
        discussionNetIdentitySetupLabel1 = new JLabel();
        discussionNetIdentitySetupLabel2 = new JLabel();
        footerLicensesTextLabel = new JLabel();
        footerTextLabel = new JLabel();
        mainImage = new JLabel();
        userMessagesIconLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);
        setTitle("DiscussionNet");

        // Apply settings to the primary identity panel.
        clientIdentitySetupPanel.setBackground(new Color(255, 255, 255));
        clientIdentitySetupPanel.setMaximumSize(new Dimension(847, 519));
        clientIdentitySetupPanel.setMinimumSize(new Dimension(847, 519));
        clientIdentitySetupPanel.setName("");
        clientIdentitySetupPanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(clientIdentitySetupPanel);
        clientIdentitySetupPanel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                clientIdentitySetupPanelMouseClicked(evt);
            }
        });
        clientIdentitySetupPanel.setLayout(null);

        // Apply settings to the page graphic.
        mainImage.setBounds(70, 159, 239, 207);
        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/identity.png")));
        mainImage.setName("mainImage");
        clientIdentitySetupPanel.add(mainImage);

        // Apply settings to the identity title label (section 1)
        discussionNetIdentitySetupLabel1.setBounds(426, 83, 175, 30);
        discussionNetIdentitySetupLabel1.setFont(new Font("Montserrat SemiBold", 0, 24));
        discussionNetIdentitySetupLabel1.setForeground(new Color(0, 36, 109));
        discussionNetIdentitySetupLabel1.setName("discussionNetIdentitySetupLabel1");
        discussionNetIdentitySetupLabel1.setText("DiscussionNet");
        clientIdentitySetupPanel.add(discussionNetIdentitySetupLabel1);

        // Apply settings to the identity title label (section 2)
        discussionNetIdentitySetupLabel2.setBounds(607, 83, 171, 30);
        discussionNetIdentitySetupLabel2.setFont(new Font("Montserrat", 0, 24));
        discussionNetIdentitySetupLabel2.setName("discussionNetIdentitySetupLabel2");
        discussionNetIdentitySetupLabel2.setText("Identity Setup");
        clientIdentitySetupPanel.add(discussionNetIdentitySetupLabel2);

        // Apply settings to the assign ID label.
        assignedIDNumberLabel.setBounds(426, 131, 153, 17);
        assignedIDNumberLabel.setFont(new Font("Montserrat", 0, 13));
        assignedIDNumberLabel.setForeground(new Color(47, 46, 65));
        assignedIDNumberLabel.setIcon(new ImageIcon(getClass().getResource("/icons/user_id.png")));
        assignedIDNumberLabel.setName("assignedIDNumberLabel");
        assignedIDNumberLabel.setText("Assigned ID Number");
        clientIdentitySetupPanel.add(assignedIDNumberLabel);

        // Apply settings and effects to the assign ID text field.
        assignedIdentificationTextfield.setAlignmentX(0.0F);
        assignedIdentificationTextfield.setAlignmentY(0.0F);
        assignedIdentificationTextfield.setBounds(426, 159, 343, 30);
        assignedIdentificationTextfield.setCaretColor(new Color(152, 150, 162));
        assignedIdentificationTextfield.setDisabledTextColor(new Color(152, 150, 162));
        assignedIdentificationTextfield.setFont(new Font("Montserrat", 0, 13));
        assignedIdentificationTextfield.setForeground(new Color(152, 150, 162));
        assignedIdentificationTextfield.setMargin(new Insets(0, 5, 0, 5));
        assignedIdentificationTextfield.setName("assignedIdentificationTextfield");
        assignedIdentificationTextfield.setSelectionColor(new Color(0, 63, 143));
        assignedIdentificationTextfield.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                assignedIdentificationTextfieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                assignedIdentificationTextfieldFocusLost(evt);
            }
        });
        clientIdentitySetupPanel.add(assignedIdentificationTextfield);

        // Apply settings to the client IP label.
        clientIPAddressLabel.setBounds(426, 209, 127, 17);
        clientIPAddressLabel.setFont(new Font("Montserrat", 0, 13));
        clientIPAddressLabel.setForeground(new Color(47, 46, 65));
        clientIPAddressLabel.setIcon(new ImageIcon(getClass().getResource("/icons/wireless.png")));
        clientIPAddressLabel.setName("clientIPAddressLabel");
        clientIPAddressLabel.setText("Client IP Address");
        clientIdentitySetupPanel.add(clientIPAddressLabel);

        // Apply settings and effects to the IP text field.
        clientIPAddressTextField.setBounds(426, 237, 343, 30);
        clientIPAddressTextField.setCaretColor(new Color(152, 150, 162));
        clientIPAddressTextField.setDisabledTextColor(new Color(152, 150, 162));
        clientIPAddressTextField.setFont(new Font("Montserrat", 0, 13));
        clientIPAddressTextField.setForeground(new Color(152, 150, 162));
        clientIPAddressTextField.setMargin(new Insets(0, 5, 0, 5));
        clientIPAddressTextField.setName("clientIPAddressTextField");
        clientIPAddressTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                clientIPAddressTextFieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                clientIPAddressTextFieldFocusLost(evt);
            }
        });
        clientIdentitySetupPanel.add(clientIPAddressTextField);

        // Apply settings to the port label.
        clientPortLabel.setBounds(426, 293, 86, 17);
        clientPortLabel.setFont(new Font("Montserrat", 0, 13));
        clientPortLabel.setForeground(new Color(47, 46, 65));
        clientPortLabel.setIcon(new ImageIcon(getClass().getResource("/icons/port_icon.png")));
        clientPortLabel.setName("clientPortLabel");
        clientPortLabel.setText("Client Port");
        clientIdentitySetupPanel.add(clientPortLabel);

        // Apply settings and effects to the port text field.
        clientPortTextField.setBounds(426, 321, 343, 30);
        clientPortTextField.setCaretColor(new Color(152, 150, 162));
        clientPortTextField.setDisabledTextColor(new Color(152, 150, 162));
        clientPortTextField.setFont(new Font("Montserrat", 0, 13));
        clientPortTextField.setForeground(new Color(152, 150, 162));
        clientPortTextField.setMargin(new Insets(0, 5, 0, 5));
        clientPortTextField.setName("clientPortTextField");
        clientPortTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent evt) {
                clientPortTextFieldFocusGained(evt);
            }

            public void focusLost(FocusEvent evt) {
                clientPortTextFieldFocusLost(evt);
            }
        });
        clientIdentitySetupPanel.add(clientPortTextField);

        // Apply settings, effects and action to the authenticate icon.
        userMessagesIconLabel.setBackground(new Color(255, 255, 255));
        userMessagesIconLabel.setBounds(640, 370, 30, 30);
        userMessagesIconLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userMessagesIconLabel.setFocusable(false);
        userMessagesIconLabel.setForeground(new Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userMessagesIconLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new ImageIcon(getClass().getResource("/icons/arrow_icon.png")));
        userMessagesIconLabel.setIconTextGap(0);
        userMessagesIconLabel.setInheritsPopupMenu(false);
        userMessagesIconLabel.setName("userMessagesIconLabel");
        userMessagesIconLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                userMessagesIconLabelMouseClicked(evt);
            }

            public void mouseEntered(MouseEvent evt) {
                authenticateButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                authenticateButtonMouseExited(evt);
            }
        });
        clientIdentitySetupPanel.add(userMessagesIconLabel);

        // Apply settings, effects and action to the authenticate button.
        authenticateButton.setBackground(new Color(255, 255, 255));
        authenticateButton.setBorder(null);
        authenticateButton.setBorderPainted(false);
        authenticateButton.setBounds(430, 350, 343, 69);
        authenticateButton.setContentAreaFilled(false);
        authenticateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        authenticateButton.setFont(new Font("Montserrat", 0, 15));
        authenticateButton.setForeground(new Color(255, 255, 255));
        authenticateButton.setHorizontalTextPosition(SwingConstants.CENTER);
        authenticateButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        authenticateButton.setName("authenticateButton");
        authenticateButton.setText("Authenticate     ");
        authenticateButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                authenticateButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                authenticateButtonMouseExited(evt);
            }
        });
        authenticateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                authenticateButtonActionPerformed(evt);
            }
        });
        clientIdentitySetupPanel.add(authenticateButton);

        // Apply settings to the first footer label.
        footerTextLabel.setBounds(285, 507, 189, 12);
        footerTextLabel.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setName("footerTextLabel");
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        clientIdentitySetupPanel.add(footerTextLabel);

        // Apply settings and action to the footer display label.
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);
        footerLicensesTextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerLicensesTextLabel.setFont(new Font("Montserrat", 2, 9));
        footerLicensesTextLabel.setForeground(new Color(47, 46, 65));
        footerLicensesTextLabel.setName("footerLicensesTextLabel");
        footerLicensesTextLabel.setText("Software Licenses");
        footerLicensesTextLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });
        clientIdentitySetupPanel.add(footerLicensesTextLabel);

        // Apply group layout configuration.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientIdentitySetupPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientIdentitySetupPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Execute confirm identity action.
    private void authenticateButtonActionPerformed(ActionEvent evt) {
        confirmIdentity();
    }

    // Execute confirm identity action.
    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {
        confirmIdentity();
    }

    // Clear focus effects.
    private void clientIdentitySetupPanelMouseClicked(MouseEvent evt) {
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, false);
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, false);
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, false);
    }

    // Display licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
    }

    // Confirm identity.
    private void confirmIdentity()  {
        // If validation passes.
        if (InterfaceManager.validateIPAddress(clientIPAddressTextField.getText())
                && InterfaceManager.validatePort(clientPortTextField.getText())
                && InterfaceManager.validateIdentifier(assignedIdentificationTextfield.getText())) {

            if (this.serverIPAddress.equals(clientIPAddressTextField.getText())) {
                // If IP address of the server and client matches, make sure ports aren't the same to prevent loopback.
                if (this.serverPort.equals(clientPortTextField.getText())) {
                    InterfaceManager.displayWarning(
                            "Loopback Detected - Fields cannot be the same as the server you're connecting to.");
                    return;
                }
            }

            // Change window and pass values.
            InterfaceManager.changeWindow(this,
                    new ClientMessaging(this.serverIPAddress, this.serverPort,
                            assignedIdentificationTextfield.getText(), clientIPAddressTextField.getText(),
                            clientPortTextField.getText()));
        }
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Effects                         #
    // #-----------------------------------------------------------------------#

    // Configure Button Hover Effect On
    private void authenticateButtonMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(authenticateButton, true, "medium");
    }

    // Configure Button Hover Effect Off
    private void authenticateButtonMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(authenticateButton, false, "medium");
    }

    // Configure Focus Gained Effect On
    private void assignedIdentificationTextfieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, true);
    }

    // Configure Focus Gained Effect Off
    private void assignedIdentificationTextfieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, false);
    }

    // Configure Focus Gained Effect On
    private void clientIPAddressTextFieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, true);
    }

    // Configure Focus Gained Effect Off
    private void clientIPAddressTextFieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, false);
    }

    // Configure Focus Gained Effect On
    private void clientPortTextFieldFocusGained(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, true);
    }

    // Configure Focus Gained Effect Off
    private void clientPortTextFieldFocusLost(FocusEvent evt) {
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, false);
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
            java.util.logging.Logger.getLogger(ClientIdentitySetup.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientIdentitySetup(args[0], args[1]).setVisible(true);
            }
        });
    }

    private JLabel assignedIDNumberLabel;
    private JTextField assignedIdentificationTextfield;
    private JButton authenticateButton;
    private JLabel clientIPAddressLabel;
    private JTextField clientIPAddressTextField;
    private JPanel clientIdentitySetupPanel;
    private JLabel clientPortLabel;
    private JTextField clientPortTextField;
    private JLabel discussionNetIdentitySetupLabel1;
    private JLabel discussionNetIdentitySetupLabel2;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel;
    private JLabel mainImage;
    private JLabel userMessagesIconLabel;
}

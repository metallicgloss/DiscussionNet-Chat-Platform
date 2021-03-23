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

        clientIdentitySetupPanel = new JPanel();
        mainImage = new JLabel();
        discussionNetIdentitySetupLabel1 = new JLabel();
        discussionNetIdentitySetupLabel2 = new JLabel();
        assignedIDNumberLabel = new JLabel();
        assignedIdentificationTextfield = new work.universitycourse.comp1549.Components.JRoundedTextField();
        clientIPAddressLabel = new JLabel();
        clientIPAddressTextField = new work.universitycourse.comp1549.Components.JRoundedTextField();
        userMessagesIconLabel = new JLabel();
        clientPortLabel = new JLabel();
        clientPortTextField = new work.universitycourse.comp1549.Components.JRoundedTextField();
        authenticateButton = new JButton();
        footerTextLabel = new JLabel();
        footerLicensesTextLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

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

        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/identity.png")));
        mainImage.setName("mainImage");
        clientIdentitySetupPanel.add(mainImage);
        mainImage.setBounds(70, 159, 239, 207);

        discussionNetIdentitySetupLabel1.setFont(new Font("Montserrat SemiBold", 0, 24));
        discussionNetIdentitySetupLabel1.setForeground(new Color(0, 36, 109));
        discussionNetIdentitySetupLabel1.setText("DiscussionNet");
        discussionNetIdentitySetupLabel1.setName("discussionNetIdentitySetupLabel1");
        clientIdentitySetupPanel.add(discussionNetIdentitySetupLabel1);
        discussionNetIdentitySetupLabel1.setBounds(426, 83, 175, 30);

        discussionNetIdentitySetupLabel2.setFont(new Font("Montserrat", 0, 24));
        discussionNetIdentitySetupLabel2.setText("Identity Setup");
        discussionNetIdentitySetupLabel2.setName("discussionNetIdentitySetupLabel2");
        clientIdentitySetupPanel.add(discussionNetIdentitySetupLabel2);
        discussionNetIdentitySetupLabel2.setBounds(607, 83, 171, 30);

        assignedIDNumberLabel.setFont(new Font("Montserrat", 0, 13));
        assignedIDNumberLabel.setForeground(new Color(47, 46, 65));
        assignedIDNumberLabel.setIcon(new ImageIcon(getClass().getResource("/icons/user_id.png")));
        assignedIDNumberLabel.setText("Assigned ID Number");
        assignedIDNumberLabel.setName("assignedIDNumberLabel");
        clientIdentitySetupPanel.add(assignedIDNumberLabel);
        assignedIDNumberLabel.setBounds(426, 131, 153, 17);

        assignedIdentificationTextfield.setFont(new Font("Montserrat", 0, 13));
        assignedIdentificationTextfield.setForeground(new Color(152, 150, 162));
        assignedIdentificationTextfield.setAlignmentX(0.0F);
        assignedIdentificationTextfield.setAlignmentY(0.0F);
        assignedIdentificationTextfield.setCaretColor(new Color(152, 150, 162));
        assignedIdentificationTextfield.setDisabledTextColor(new Color(152, 150, 162));
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
        assignedIdentificationTextfield.setBounds(426, 159, 343, 30);

        clientIPAddressLabel.setFont(new Font("Montserrat", 0, 13));
        clientIPAddressLabel.setForeground(new Color(47, 46, 65));
        clientIPAddressLabel.setIcon(new ImageIcon(getClass().getResource("/icons/wireless.png")));
        clientIPAddressLabel.setText("Client IP Address");
        clientIPAddressLabel.setName("clientIPAddressLabel");
        clientIdentitySetupPanel.add(clientIPAddressLabel);
        clientIPAddressLabel.setBounds(426, 209, 127, 17);

        clientIPAddressTextField.setFont(new Font("Montserrat", 0, 13));
        clientIPAddressTextField.setForeground(new Color(152, 150, 162));
        clientIPAddressTextField.setCaretColor(new Color(152, 150, 162));
        clientIPAddressTextField.setDisabledTextColor(new Color(152, 150, 162));
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
        clientIPAddressTextField.setBounds(426, 237, 343, 30);

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
        clientIdentitySetupPanel.add(userMessagesIconLabel);
        userMessagesIconLabel.setBounds(640, 370, 30, 30);

        clientPortLabel.setFont(new Font("Montserrat", 0, 13));
        clientPortLabel.setForeground(new Color(47, 46, 65));
        clientPortLabel.setIcon(new ImageIcon(getClass().getResource("/icons/port_icon.png")));
        clientPortLabel.setText("Client Port");
        clientPortLabel.setName("clientPortLabel");
        clientIdentitySetupPanel.add(clientPortLabel);
        clientPortLabel.setBounds(426, 293, 86, 17);

        clientPortTextField.setFont(new Font("Montserrat", 0, 13));
        clientPortTextField.setForeground(new Color(152, 150, 162));
        clientPortTextField.setCaretColor(new Color(152, 150, 162));
        clientPortTextField.setDisabledTextColor(new Color(152, 150, 162));
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
        clientPortTextField.setBounds(426, 321, 343, 30);

        authenticateButton.setBackground(new Color(255, 255, 255));
        authenticateButton.setFont(new Font("Montserrat", 0, 15));
        authenticateButton.setForeground(new Color(255, 255, 255));
        authenticateButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        authenticateButton.setText("Authenticate     ");
        authenticateButton.setBorder(null);
        authenticateButton.setBorderPainted(false);
        authenticateButton.setContentAreaFilled(false);
        authenticateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        authenticateButton.setHorizontalTextPosition(SwingConstants.CENTER);
        authenticateButton.setName("authenticateButton");
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
        authenticateButton.setBounds(430, 350, 343, 69);

        footerTextLabel.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel.setName("footerTextLabel");
        clientIdentitySetupPanel.add(footerTextLabel);
        footerTextLabel.setBounds(285, 507, 189, 12);

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
        clientIdentitySetupPanel.add(footerLicensesTextLabel);
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientIdentitySetupPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                clientIdentitySetupPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }

    private void authenticateButtonActionPerformed(ActionEvent evt) {//GEN-FIRST:event_authenticateButtonActionPerformed
        confirmIdentity();
    }//GEN-LAST:event_authenticateButtonActionPerformed

    private void authenticateButtonMouseEntered(MouseEvent evt) {//GEN-FIRST:event_authenticateButtonMouseEntered
        InterfaceManager.buttonHover(authenticateButton, true, "medium");
    }//GEN-LAST:event_authenticateButtonMouseEntered

    private void authenticateButtonMouseExited(MouseEvent evt) {//GEN-FIRST:event_authenticateButtonMouseExited
        InterfaceManager.buttonHover(authenticateButton, false, "medium");
    }//GEN-LAST:event_authenticateButtonMouseExited

    private void assignedIdentificationTextfieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_assignedIdentificationTextfieldFocusGained
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, true);
    }//GEN-LAST:event_assignedIdentificationTextfieldFocusGained

    private void assignedIdentificationTextfieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_assignedIdentificationTextfieldFocusLost
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, false);
    }//GEN-LAST:event_assignedIdentificationTextfieldFocusLost

    private void clientIPAddressTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_clientIPAddressTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, true);
    }//GEN-LAST:event_clientIPAddressTextFieldFocusGained

    private void clientIPAddressTextFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_clientIPAddressTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, false);
    }//GEN-LAST:event_clientIPAddressTextFieldFocusLost

    private void clientPortTextFieldFocusGained(FocusEvent evt) {//GEN-FIRST:event_clientPortTextFieldFocusGained
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, true);
    }//GEN-LAST:event_clientPortTextFieldFocusGained

    private void clientPortTextFieldFocusLost(FocusEvent evt) {//GEN-FIRST:event_clientPortTextFieldFocusLost
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, false);
    }//GEN-LAST:event_clientPortTextFieldFocusLost

    private void clientIdentitySetupPanelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_clientIdentitySetupPanelMouseClicked
        InterfaceManager.toggleTextFieldFocus(assignedIdentificationTextfield, false);
        InterfaceManager.toggleTextFieldFocus(clientIPAddressTextField, false);
        InterfaceManager.toggleTextFieldFocus(clientPortTextField, false);
    }//GEN-LAST:event_clientIdentitySetupPanelMouseClicked

    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseClicked
        confirmIdentity();
    }//GEN-LAST:event_userMessagesIconLabelMouseClicked

    private void userMessagesIconLabelMouseEntered(MouseEvent evt) {//GEN-FIRST:event_userMessagesIconLabelMouseEntered
        InterfaceManager.buttonHover(authenticateButton, true, "medium");
    }//GEN-LAST:event_userMessagesIconLabelMouseEntered

    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.displayLicenses();
    }//GEN-LAST:event_footerLicensesTextLabelMouseClicked

    private void confirmIdentity() {
        if (InterfaceManager.validateIPAddress(clientIPAddressTextField.getText())
                && InterfaceManager.validatePort(clientPortTextField.getText())
                && InterfaceManager.validateIdentifier(assignedIdentificationTextfield.getText())) {
            // If IP address of the server and client matches, make sure ports aren't the same to prevent loopback.

            if (this.serverIPAddress.equals(clientIPAddressTextField.getText())) {
                if (this.serverPort.equals(clientPortTextField.getText())) {
                    InterfaceManager.displayWarning(
                            "Loopback Detected - Fields cannot be the same as the server you're connecting to.");
                    return;
                }
            }

            InterfaceManager.changeWindow(this,
                    new ClientMessaging(this.serverIPAddress, this.serverPort,
                            assignedIdentificationTextfield.getText(), clientIPAddressTextField.getText(),
                            clientPortTextField.getText()));
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientIdentitySetup.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        /* Create and display the form */
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

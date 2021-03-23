package work.universitycourse.comp1549.Interfaces;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import work.universitycourse.comp1549.Interfaces.Client.ClientServerConnection;
import work.universitycourse.comp1549.Interfaces.Server.ServerConfiguration;
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
public class StartUpInterface extends JFrame {

    // #-----------------------------------------------------------------------#
    // #                   Create New StartUpInterface Form                    #
    // #-----------------------------------------------------------------------#
    public StartUpInterface() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        // Initialise interface objects.
        configureClientButton = new JButton();
        configureServerButton = new JButton();
        createNetworkLabel = new JLabel();
        existingNetworkLabel = new JLabel();
        footerLicensesTextLabel = new JLabel();
        footerTextLabel = new JLabel();
        mainImage = new JLabel();
        startUpInterfacePanel = new JPanel();
        titleLabel1 = new JLabel();
        titleLabel2 = new JLabel();
        titleLabel3 = new JLabel();

        // Define application window settings.
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);
        setTitle("DiscussionNet V1.0 ");

        // Window panel.
        InterfaceManager.detectExitRequest(startUpInterfacePanel);
        startUpInterfacePanel.setBackground(new Color(255, 255, 255));
        startUpInterfacePanel.setLayout(null);
        startUpInterfacePanel.setMaximumSize(new Dimension(847, 519));
        startUpInterfacePanel.setMinimumSize(new Dimension(847, 519));
        startUpInterfacePanel.setName("startUpInterfacePanel");
        startUpInterfacePanel.setPreferredSize(new Dimension(847, 519));

        // Apply settings to the startup interface graphic.
        mainImage.setBounds(298, 51, 260, 200);
        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/welcome_image.png")));
        mainImage.setName("mainImage");
        startUpInterfacePanel.add(mainImage);

        // Apply settings to first title label (standard text) label.
        titleLabel1.setBounds(190, 269, 152, 30);
        titleLabel1.setFont(new Font("Montserrat", 0, 24));
        titleLabel1.setForeground(new Color(47, 46, 65));
        titleLabel1.setName("titleLabel1");
        titleLabel1.setText("Welcome To");
        startUpInterfacePanel.add(titleLabel1);

        // Apply settings to second title label (blue bold) label.
        titleLabel2.setBounds(348, 269, 175, 30);
        titleLabel2.setFont(new Font("Montserrat SemiBold", 0, 24));
        titleLabel2.setForeground(new Color(0, 36, 109));
        titleLabel2.setName("titleLabel2");
        titleLabel2.setText("DiscussionNet");
        startUpInterfacePanel.add(titleLabel2);

        // Apply settings to third title label (standard text) label.
        titleLabel3.setBounds(529, 269, 143, 30);
        titleLabel3.setFont(new Font("Montserrat", 0, 24));
        titleLabel3.setForeground(new Color(47, 46, 65));
        titleLabel3.setName("titleLabel3");
        titleLabel3.setText("Initial Setup");
        startUpInterfacePanel.add(titleLabel3);

        // Apply settings to the "Existing Network" label.
        existingNetworkLabel.setBounds(102, 350, 269, 17);
        existingNetworkLabel.setFont(new Font("Montserrat", 0, 13));
        existingNetworkLabel.setForeground(new Color(47, 46, 65));
        existingNetworkLabel.setName("existingNetworkLabel");
        existingNetworkLabel.setText("I want to connect to an existing network...");
        startUpInterfacePanel.add(existingNetworkLabel);

        // Apply settings to the "Create Network" label.
        createNetworkLabel.setBounds(506, 350, 208, 17);
        createNetworkLabel.setFont(new Font("Montserrat", 0, 13));
        createNetworkLabel.setForeground(new Color(47, 46, 65));
        createNetworkLabel.setName("createNetworkLabel");
        createNetworkLabel.setText("I want to create a new network...");
        startUpInterfacePanel.add(createNetworkLabel);

        // Apply settings and actions to the Configure Server button.
        configureServerButton.setBackground(new Color(255, 255, 255));
        configureServerButton.setBorder(null);
        configureServerButton.setBorderPainted(false);
        configureServerButton.setBounds(428, 373, 367, 69);
        configureServerButton.setContentAreaFilled(false);
        configureServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configureServerButton.setFont(new Font("Montserrat", 0, 15));
        configureServerButton.setForeground(new Color(255, 255, 255));
        configureServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        configureServerButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        configureServerButton.setName("configureServerButton");
        configureServerButton.setText("Configure Server");

        configureServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                configureServerButtonActionPerformed(evt);
            }
        });

        configureServerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                configureServerButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                configureServerButtonMouseExited(evt);
            }
        });

        startUpInterfacePanel.add(configureServerButton);

        // Apply settings and actions to the Configure Client button.
        configureClientButton.setBackground(new Color(255, 255, 255));
        configureClientButton.setBorder(null);
        configureClientButton.setBorderPainted(false);
        configureClientButton.setBounds(43, 373, 367, 69);
        configureClientButton.setContentAreaFilled(false);
        configureClientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configureClientButton.setFont(new Font("Montserrat", 0, 15));
        configureClientButton.setForeground(new Color(255, 255, 255));
        configureClientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        configureClientButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        configureClientButton.setName("configureClientButton");
        configureClientButton.setText("Configure Client");

        configureClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                configureClientButtonActionPerformed(evt);
            }
        });

        configureClientButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                configureClientButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                configureClientButtonMouseExited(evt);
            }
        });

        startUpInterfacePanel.add(configureClientButton);

        // Apply settings to the footer label text.
        footerTextLabel.setBounds(285, 507, 189, 12);
        footerTextLabel.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setName("footerTextLabel");
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        startUpInterfacePanel.add(footerTextLabel);

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

        startUpInterfacePanel.add(footerLicensesTextLabel);

        // Apply group layout configuration.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addGap(0, 0, 0).addComponent(startUpInterfacePanel,
                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
                startUpInterfacePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        getAccessibleContext().setAccessibleName("DiscussionNet");

        pack();
        setLocationRelativeTo(null);
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Configure Client Selected.
    private void configureClientButtonActionPerformed(ActionEvent evt) {
        // Change window to Configure Client Connection.
        InterfaceManager.changeWindow(this, new ClientServerConnection());
    }

    // Configure Server Selected.
    private void configureServerButtonActionPerformed(ActionEvent evt) {
        // Change window to Configure Server.
        InterfaceManager.changeWindow(this, new ServerConfiguration());
    }

    // Show licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Effects                         #
    // #-----------------------------------------------------------------------#

    // Configure Client Hover Effect On
    private void configureClientButtonMouseEntered(MouseEvent evt) {
        // Set button to hover effect.
        InterfaceManager.buttonHover(configureClientButton, true, "medium");
    }

    // Configure Client Hover Effect Off
    private void configureClientButtonMouseExited(MouseEvent evt) {
        // Disable hover effect.
        InterfaceManager.buttonHover(configureClientButton, false, "medium");
    }

    // Configure Server Hover Effect On
    private void configureServerButtonMouseEntered(MouseEvent evt) {
        // Set button to hover effect.
        InterfaceManager.buttonHover(configureServerButton, true, "medium");
    }

    // Configure Server Hover Effect Off
    private void configureServerButtonMouseExited(MouseEvent evt) {
        // Disable hover effect.
        InterfaceManager.buttonHover(configureServerButton, false, "medium");
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
            java.util.logging.Logger.getLogger(StartUpInterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartUpInterface().setVisible(true);

            }
        });
    }

    private JButton configureClientButton;
    private JButton configureServerButton;
    private JLabel createNetworkLabel;
    private JLabel existingNetworkLabel;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel;
    private JLabel mainImage;
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JLabel titleLabel3;
    private JPanel startUpInterfacePanel;
}

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
        startUpInterfacePanel = new JPanel();
        existingNetworkLabel = new JLabel();
        createNetworkLabel = new JLabel();
        titleLabel2 = new JLabel();
        titleLabel3 = new JLabel();
        titleLabel1 = new JLabel();
        mainImage = new JLabel();
        configureServerButton = new JButton();
        configureClientButton = new JButton();
        footerTextLabel = new JLabel();
        footerLicensesTextLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet V1.0 ");
        setBackground(new Color(255, 255, 255));
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        startUpInterfacePanel.setBackground(new Color(255, 255, 255));
        startUpInterfacePanel.setMaximumSize(new Dimension(847, 519));
        startUpInterfacePanel.setMinimumSize(new Dimension(847, 519));
        startUpInterfacePanel.setName("startUpInterfacePanel");
        startUpInterfacePanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(startUpInterfacePanel);
        startUpInterfacePanel.setLayout(null);

        existingNetworkLabel.setFont(new Font("Montserrat", 0, 13));
        existingNetworkLabel.setForeground(new Color(47, 46, 65));
        existingNetworkLabel.setText("I want to connect to an existing network...");
        existingNetworkLabel.setName("existingNetworkLabel");
        startUpInterfacePanel.add(existingNetworkLabel);
        existingNetworkLabel.setBounds(102, 350, 269, 17);

        createNetworkLabel.setFont(new Font("Montserrat", 0, 13));
        createNetworkLabel.setForeground(new Color(47, 46, 65));
        createNetworkLabel.setText("I want to create a new network...");
        createNetworkLabel.setName("createNetworkLabel");
        startUpInterfacePanel.add(createNetworkLabel);
        createNetworkLabel.setBounds(506, 350, 208, 17);

        titleLabel2.setFont(new Font("Montserrat SemiBold", 0, 24));
        titleLabel2.setForeground(new Color(0, 36, 109));
        titleLabel2.setText("DiscussionNet");
        titleLabel2.setName("titleLabel2");
        startUpInterfacePanel.add(titleLabel2);
        titleLabel2.setBounds(348, 269, 175, 30);

        titleLabel3.setFont(new Font("Montserrat", 0, 24));
        titleLabel3.setForeground(new Color(47, 46, 65));
        titleLabel3.setText("Initial Setup");
        titleLabel3.setName("titleLabel3");
        startUpInterfacePanel.add(titleLabel3);
        titleLabel3.setBounds(529, 269, 143, 30);

        titleLabel1.setFont(new Font("Montserrat", 0, 24));
        titleLabel1.setForeground(new Color(47, 46, 65));
        titleLabel1.setText("Welcome To");
        titleLabel1.setName("titleLabel1");
        startUpInterfacePanel.add(titleLabel1);
        titleLabel1.setBounds(190, 269, 152, 30);

        mainImage.setIcon(new ImageIcon(getClass().getResource("/graphics/welcome_image.png")));
        mainImage.setName("mainImage");
        startUpInterfacePanel.add(mainImage);
        mainImage.setBounds(298, 51, 260, 200);

        configureServerButton.setBackground(new Color(255, 255, 255));
        configureServerButton.setFont(new Font("Montserrat", 0, 15));
        configureServerButton.setForeground(new Color(255, 255, 255));
        configureServerButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        configureServerButton.setText("Configure Server");
        configureServerButton.setBorder(null);
        configureServerButton.setBorderPainted(false);
        configureServerButton.setContentAreaFilled(false);
        configureServerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configureServerButton.setHorizontalTextPosition(SwingConstants.CENTER);
        configureServerButton.setName("configureServerButton");
        configureServerButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                configureServerButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                configureServerButtonMouseExited(evt);
            }
        });
        configureServerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                configureServerButtonActionPerformed(evt);
            }
        });
        startUpInterfacePanel.add(configureServerButton);
        configureServerButton.setBounds(428, 373, 367, 69);

        configureClientButton.setBackground(new Color(255, 255, 255));
        configureClientButton.setFont(new Font("Montserrat", 0, 15));
        configureClientButton.setForeground(new Color(255, 255, 255));
        configureClientButton.setIcon(new ImageIcon(getClass().getResource("/buttons/medium.png")));
        configureClientButton.setText("Configure Client");
        configureClientButton.setBorder(null);
        configureClientButton.setBorderPainted(false);
        configureClientButton.setContentAreaFilled(false);
        configureClientButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        configureClientButton.setHorizontalTextPosition(SwingConstants.CENTER);
        configureClientButton.setName("configureClientButton");
        configureClientButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                configureClientButtonMouseEntered(evt);
            }

            public void mouseExited(MouseEvent evt) {
                configureClientButtonMouseExited(evt);
            }
        });
        configureClientButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                configureClientButtonActionPerformed(evt);
            }
        });
        startUpInterfacePanel.add(configureClientButton);
        configureClientButton.setBounds(43, 373, 367, 69);

        footerTextLabel.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel.setName("footerTextLabel");
        startUpInterfacePanel.add(footerTextLabel);
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
        startUpInterfacePanel.add(footerLicensesTextLabel);
        footerLicensesTextLabel.setBounds(480, 507, 80, 12);

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

    private void configureClientButtonActionPerformed(ActionEvent evt) {
        InterfaceManager.changeWindow(this, new ClientServerConnection());
    }

    private void configureServerButtonActionPerformed(ActionEvent evt) {
        InterfaceManager.changeWindow(this, new ServerConfiguration());
    }

    private void configureClientButtonMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(configureClientButton, true, "medium");
    }

    private void configureClientButtonMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(configureClientButton, false, "medium");
    }

    private void configureServerButtonMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(configureServerButton, true, "medium");
    }

    private void configureServerButtonMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(configureServerButton, false, "medium");
    }

    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
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
            java.util.logging.Logger.getLogger(StartUpInterface.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        /* Create and display the form */
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
    private JPanel startUpInterfacePanel;
    private JLabel titleLabel1;
    private JLabel titleLabel2;
    private JLabel titleLabel3;
}

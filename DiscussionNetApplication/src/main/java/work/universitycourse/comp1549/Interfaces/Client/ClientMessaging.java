package work.universitycourse.comp1549.Interfaces.Client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.util.Calendar;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import static javax.swing.JOptionPane.showMessageDialog;

import work.universitycourse.comp1549.Components.JRoundedTextField;
import work.universitycourse.comp1549.Modules.ClientManager;
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
public class ClientMessaging extends JFrame {

    public String serverIPAddress;
    public String serverPort;
    public String clientIdentifier;
    public String clientIPAddress;
    public String clientPort;

    public Boolean clientStatus = true;

    private ClientManager client;

    // #-----------------------------------------------------------------------#
    // #                    Create New ClientMessaging Form                    #
    // #-----------------------------------------------------------------------#

    public ClientMessaging(String serverIPAddress, String serverPort, String clientIdentifier, String clientIPAddress,
            String clientPort) {
        super();
        this.serverIPAddress = serverIPAddress;
        this.serverPort = serverPort;
        this.clientIdentifier = clientIdentifier;
        this.clientIPAddress = clientIPAddress;
        this.clientPort = clientPort;
        initComponents();

        this.client = new ClientManager(this.primaryMessagePane, this, this.serverDetailsCoordinatorValueLabel,
                this.serverIPAddress, Integer.parseInt(this.serverPort), this.clientIdentifier, this.clientIPAddress,
                Integer.parseInt(this.clientPort));

        if (!this.client.getClientStatus()) {
            // Error occurred on startup, don't continue launch.
            this.clientStatus = false;
        } else {
            InterfaceManager.createClient(this.primaryMessagePane, "Group Chat");
        }

        clientDetailsIPAddressValueLabel.setText(this.clientIPAddress);
        clientDetailsConnectionPortValueLabel.setText(this.clientPort);
        clientDetailsAssignedIDNumberValueLabel.setText(this.clientIdentifier);
        serverDetailsIPAddressValueLabel.setText(this.serverIPAddress);
        serverDetailsConnectionPortValueLabel.setText(this.serverPort);
        serverDetailsConnectionStatusValueLabel.setText("Connected");

    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#

    private void initComponents() {

        // Initialise interface objects.
        clientControlPanelLabel1 = new JLabel();
        clientControlPanelLabel2 = new JLabel();
        clientDetailsAssignedIDNumberLabel = new JLabel();
        clientDetailsAssignedIDNumberValueLabel = new JLabel();
        clientDetailsConnectionPortLabel = new JLabel();
        clientDetailsConnectionPortValueLabel = new JLabel();
        clientDetailsIcon = new JLabel();
        clientDetailsIPAddressLabel = new JLabel();
        clientDetailsIPAddressValueLabel = new JLabel();
        clientDetailsLabel1 = new JLabel();
        clientDetailsLabel2 = new JLabel();
        clientDetailsPanel1 = new JPanel();
        clientDetailsPanel2 = new JPanel();
        clientFeaturesLabel = new JLabel();
        discussionNetLabel = new JLabel();
        exitApplicationLabel = new JLabel();
        footerLicensesTextLabel = new JLabel();
        footerPanel = new JPanel();
        footerTextLabel = new JLabel();
        headerNamePanel = new JPanel();
        headerTitlePanel = new JPanel();
        primaryMessagePane = new JTabbedPane();
        sendArrowIconLabel = new JLabel();
        serverDetailsConnectionPortLabel = new JLabel();
        serverDetailsConnectionPortValueLabel = new JLabel();
        serverDetailsConnectionStatusLabel = new JLabel();
        serverDetailsConnectionStatusValueLabel = new JLabel();
        serverDetailsCoordinatorLabel = new JLabel();
        serverDetailsCoordinatorValueLabel = new JLabel();
        serverDetailsIcon = new JLabel();
        serverDetailsIPAddressLabel = new JLabel();
        serverDetailsIPAddressValueLabel = new JLabel();
        serverDetailsLabel1 = new JLabel();
        serverDetailsLabel2 = new JLabel();
        serverDetailsPanel1 = new JPanel();
        serverDetailsPanel2 = new JPanel();
        sidePanel = new JPanel();
        userListLabel = new JLabel();
        userMessagesButton = new JButton();
        userMessagesIconLabel = new JLabel();
        userMessagesTextfield = new JRoundedTextField();
        userMessagingIconLabel = new JLabel();
        userMessagingLabel = new JLabel();
        userMessagingLabel1 = new JLabel();
        userMessagingLabel2 = new JLabel();
        userMessagingMainPanel = new JPanel();
        userMessagingTopPanel = new JPanel();

        sendArrowIconLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/black_arrow.png")));
        sendArrowIconLabel.setName("sendArrowIconLabel");

        // Define application window settings.
        setBackground(new Color(242, 244, 250));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(1090, 708));
        setName("DiscussionNet");
        setResizable(false);
        setTitle("DiscussionNet");

        InterfaceManager.detectExitRequest(headerNamePanel);

        // Apply settings to the
        discussionNetLabel.setFont(InterfaceManager.montserratSemiBold.deriveFont(16.0f));
        discussionNetLabel.setForeground(new Color(0, 36, 109));
        discussionNetLabel.setText("DiscussionNet");
        discussionNetLabel.setName("discussionNetLabel");

        // #-------------------------------------------------------------------#
        // #                     Header Container / Panel                      #
        // #-------------------------------------------------------------------#  

        headerNamePanel.setBackground(new Color(255, 255, 255));
        headerNamePanel.setName("headerNamePanel");
        headerNamePanel.setPreferredSize(new Dimension(153, 43));

        // Apply settings to the header panel.
        headerTitlePanel.setBackground(new Color(255, 255, 255));
        headerTitlePanel.setName("headerTitlePanel");

        // Apply settings to the title label.
        clientControlPanelLabel2.setFont(InterfaceManager.montserratRegular.deriveFont(14.0f));
        clientControlPanelLabel2.setForeground(new Color(0, 36, 109));
        clientControlPanelLabel2.setText("Control Panel");
        clientControlPanelLabel2.setName("clientControlPanelLabel2");

        // Apply settings to the title label (section 2)
        clientControlPanelLabel1.setFont(InterfaceManager.montserratSemiBold.deriveFont(14.0f));
        clientControlPanelLabel1.setForeground(new Color(0, 36, 109));
        clientControlPanelLabel1.setText("Client");
        clientControlPanelLabel1.setName("clientControlPanelLabel1");

        // Apply settings to the
        GroupLayout headerTitlePanelLayout = new GroupLayout(headerTitlePanel);
        headerTitlePanel.setLayout(headerTitlePanelLayout);
        headerTitlePanelLayout.setHorizontalGroup(headerTitlePanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(headerTitlePanelLayout.createSequentialGroup().addContainerGap()
                        .addComponent(clientControlPanelLabel1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clientControlPanelLabel2)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        headerTitlePanelLayout.setVerticalGroup(headerTitlePanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
                        headerTitlePanelLayout.createSequentialGroup().addGap(15, 15, 15)
                                .addGroup(headerTitlePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clientControlPanelLabel2).addComponent(clientControlPanelLabel1))
                                .addContainerGap()));

        // Apply group layout configuration for the header
        GroupLayout headerNamePanelLayout = new GroupLayout(headerNamePanel);
        headerNamePanel.setLayout(headerNamePanelLayout);
        headerNamePanelLayout
                .setHorizontalGroup(headerNamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(headerNamePanelLayout.createSequentialGroup().addGap(18, 18, 18)
                                .addComponent(discussionNetLabel).addContainerGap(18, Short.MAX_VALUE)));
        headerNamePanelLayout.setVerticalGroup(headerNamePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        headerNamePanelLayout.createSequentialGroup().addGap(14, 14, 14)
                                .addComponent(discussionNetLabel)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        // #-------------------------------------------------------------------#
        // #                              Side Menu                            #
        // #-------------------------------------------------------------------#  

        // Apply settings to the side panel.
        sidePanel.setBackground(new Color(0, 36, 109));
        sidePanel.setForeground(new Color(0, 36, 109));
        sidePanel.setToolTipText("");
        sidePanel.setName("sidePanel");
        sidePanel.setPreferredSize(new Dimension(154, 60));

        // Apply settings to the client features title.
        clientFeaturesLabel.setBackground(new Color(255, 255, 255));
        clientFeaturesLabel.setFont(InterfaceManager.montserratRegular.deriveFont(12.0f));
        clientFeaturesLabel.setForeground(new Color(255, 255, 255));
        clientFeaturesLabel.setText("CLIENT FEATURES");
        clientFeaturesLabel.setName("clientFeaturesLabel");

        // Apply settings to the message menu option label.
        userMessagingLabel.setFont(InterfaceManager.montserratRegular.deriveFont(11.0f));
        userMessagingLabel.setForeground(new Color(255, 255, 255));
        userMessagingLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/mail.png")));
        userMessagingLabel.setText("User Messaging");
        userMessagingLabel.setName("userMessagingLabel");

        // Apply settings and actions to the user list menu label.
        userListLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        userListLabel.setFont(InterfaceManager.montserratRegular.deriveFont(11.0f));
        userListLabel.setForeground(new Color(255, 255, 255));
        userListLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/users.png")));
        userListLabel.setName("userListLabel");
        userListLabel.setText("User List");
        userListLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                userListLabelMouseClicked(evt);
            }
        });

        // Apply settings to the menu exit option.
        exitApplicationLabel.setBackground(new Color(255, 255, 255));
        exitApplicationLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        exitApplicationLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        exitApplicationLabel.setForeground(new Color(255, 255, 255));
        exitApplicationLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/exit.png")));
        exitApplicationLabel.setName("exitApplicationLabel");
        exitApplicationLabel.setText("Exit Application");
        exitApplicationLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                exitApplicationLabelMouseClicked(evt);
            }
        });

        // Apply group layout configuration for the side panel.
        GroupLayout sidePanelLayout = new GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(sidePanelLayout.createSequentialGroup().addGap(19, 19, 19)
                        .addGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(userListLabel).addComponent(userMessagingLabel)
                                .addComponent(clientFeaturesLabel).addComponent(exitApplicationLabel))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        sidePanelLayout.setVerticalGroup(sidePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(sidePanelLayout
                        .createSequentialGroup().addGap(27, 27, 27).addComponent(clientFeaturesLabel).addGap(18, 18, 18)
                        .addComponent(userMessagingLabel).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(userListLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exitApplicationLabel).addGap(28, 28, 28)));

        // #-------------------------------------------------------------------#
        // #                        Client Details Box                         #
        // #-------------------------------------------------------------------#

        // Apply settings to the client details panel.
        clientDetailsPanel1.setBackground(new Color(255, 255, 255));
        clientDetailsPanel1.setName("clientDetailsPanel1");
        clientDetailsPanel1.setPreferredSize(new Dimension(322, 100));

        // Apply settings to the secondary details panel.
        clientDetailsPanel2.setBackground(new Color(255, 255, 255));
        clientDetailsPanel2.setMinimumSize(new Dimension(322, 92));
        clientDetailsPanel2.setName("clientDetailsPanel2");

        // Apply settings to the client details icon.
        clientDetailsIcon.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/client_details.png")));
        clientDetailsIcon.setName("clientDetailsIcon");

        // Apply settings to the client details title label.
        clientDetailsLabel1.setFont(InterfaceManager.montserratRegular.deriveFont(18.0f));
        clientDetailsLabel1.setText("Client");
        clientDetailsLabel1.setName("clientDetailsLabel1");

        // Apply settings to the secondary client details title.
        clientDetailsLabel2.setFont(InterfaceManager.montserratSemiBold.deriveFont(18.0f));
        clientDetailsLabel2.setForeground(new Color(0, 36, 109));
        clientDetailsLabel2.setText("Details");
        clientDetailsLabel2.setName("clientDetailsLabel2");

        // Apply settings to the client details IP label.
        clientDetailsIPAddressLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        clientDetailsIPAddressLabel.setText("IP Address");
        clientDetailsIPAddressLabel.setName("clientDetailsIPAddressLabel");

        // Apply settings to the client IP value label.
        clientDetailsIPAddressValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        clientDetailsIPAddressValueLabel.setName("clientDetailsIPAddressValueLabel");

        // Apply settings to the client details port label.
        clientDetailsConnectionPortLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        clientDetailsConnectionPortLabel.setText("Connection Port");
        clientDetailsConnectionPortLabel.setName("clientDetailsConnectionPortLabel");

        // Apply settings to the client port value panel.
        clientDetailsConnectionPortValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        clientDetailsConnectionPortValueLabel.setName("clientDetailsConnectionPortValueLabel");

        // Apply settings to the client details ID label.
        clientDetailsAssignedIDNumberLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        clientDetailsAssignedIDNumberLabel.setText("Assigned ID Number");
        clientDetailsAssignedIDNumberLabel.setName("clientDetailsAssignedIDNumberLabel");

        // Apply settings to the client ID value label.
        clientDetailsAssignedIDNumberValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        clientDetailsAssignedIDNumberValueLabel.setForeground(new Color(112, 161, 255));
        clientDetailsAssignedIDNumberValueLabel.setName("clientDetailsAssignedIDNumberValueLabel");

        // Apply group layout configuration for the primary client details.
        GroupLayout clientDetailsPanel1Layout = new GroupLayout(clientDetailsPanel1);
        clientDetailsPanel1.setLayout(clientDetailsPanel1Layout);
        clientDetailsPanel1Layout
                .setHorizontalGroup(clientDetailsPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientDetailsPanel1Layout.createSequentialGroup().addGap(45, 45, 45)
                                .addComponent(clientDetailsIcon).addGap(37, 37, 37).addComponent(clientDetailsLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(clientDetailsLabel2).addContainerGap(72, Short.MAX_VALUE)));
        clientDetailsPanel1Layout
                .setVerticalGroup(clientDetailsPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientDetailsPanel1Layout.createSequentialGroup().addContainerGap(12, Short.MAX_VALUE)
                                .addComponent(clientDetailsIcon).addGap(22, 22, 22))
                        .addGroup(clientDetailsPanel1Layout.createSequentialGroup().addGap(35, 35, 35)
                                .addGroup(clientDetailsPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clientDetailsLabel1).addComponent(clientDetailsLabel2))
                                .addGap(0, 0, Short.MAX_VALUE)));

        // Apply settings to the client details panel.
        GroupLayout clientDetailsPanel2Layout = new GroupLayout(clientDetailsPanel2);
        clientDetailsPanel2.setLayout(clientDetailsPanel2Layout);
        clientDetailsPanel2Layout
                .setHorizontalGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientDetailsPanel2Layout.createSequentialGroup().addGap(41, 41, 41)
                                .addGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clientDetailsIPAddressLabel)
                                        .addComponent(clientDetailsIPAddressValueLabel))
                                .addGap(52, 52, 52)
                                .addGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clientDetailsConnectionPortLabel)
                                        .addComponent(clientDetailsConnectionPortValueLabel))
                                .addGap(45, 45, 45)
                                .addGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(clientDetailsAssignedIDNumberValueLabel)
                                        .addComponent(clientDetailsAssignedIDNumberLabel))
                                .addContainerGap(165, Short.MAX_VALUE)));
        clientDetailsPanel2Layout
                .setVerticalGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(clientDetailsPanel2Layout.createSequentialGroup().addGap(25, 25, 25)
                                .addGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clientDetailsIPAddressLabel)
                                        .addComponent(clientDetailsConnectionPortLabel)
                                        .addComponent(clientDetailsAssignedIDNumberLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(clientDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(clientDetailsIPAddressValueLabel)
                                        .addComponent(clientDetailsConnectionPortValueLabel)
                                        .addComponent(clientDetailsAssignedIDNumberValueLabel))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        // #-------------------------------------------------------------------#
        // #                        Server Details Box                         #
        // #-------------------------------------------------------------------#

        // Apply settings to the server details panel.
        serverDetailsPanel1.setBackground(new Color(255, 255, 255));
        serverDetailsPanel1.setName("serverDetailsPanel1");
        serverDetailsPanel1.setPreferredSize(new Dimension(322, 100));

        // Apply settings to the server icon.
        serverDetailsIcon.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/server_details.png")));
        serverDetailsIcon.setName("serverDetailsIcon");

        // Apply settings to the server details title (section 1)
        serverDetailsLabel1.setFont(InterfaceManager.montserratRegular.deriveFont(18.0f));
        serverDetailsLabel1.setText("Server");
        serverDetailsLabel1.setName("serverDetailsLabel1");

        // Apply settings to the server details title (section 2)
        serverDetailsLabel2.setFont(InterfaceManager.montserratSemiBold.deriveFont(18.0f));
        serverDetailsLabel2.setForeground(new Color(0, 36, 109));
        serverDetailsLabel2.setText("Details");
        serverDetailsLabel2.setName("serverDetailsLabel2");

        // Apply settings to the server details panel.
        serverDetailsPanel2.setBackground(new Color(255, 255, 255));
        serverDetailsPanel2.setName("serverDetailsPanel2");
        serverDetailsPanel2.setPreferredSize(new Dimension(322, 92));

        // Apply settings to the server IP label.
        serverDetailsIPAddressLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        serverDetailsIPAddressLabel.setText("IP Address");
        serverDetailsIPAddressLabel.setName("serverDetailsIPAddressLabel");

        // Apply settings to the server IP value label.
        serverDetailsIPAddressValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        serverDetailsIPAddressValueLabel.setName("serverDetailsIPAddressValueLabel");

        // Apply settings to the server port label.
        serverDetailsConnectionPortLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        serverDetailsConnectionPortLabel.setText("Connection Port");
        serverDetailsConnectionPortLabel.setName("serverDetailsConnectionPortLabel");

        // Apply settings to the server port value label.
        serverDetailsConnectionPortValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        serverDetailsConnectionPortValueLabel.setName("serverDetailsConnectionPortValueLabel");

        // Apply settings to the server status label.
        serverDetailsConnectionStatusLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        serverDetailsConnectionStatusLabel.setText("Connection Status");
        serverDetailsConnectionStatusLabel.setName("serverDetailsConnectionStatusLabel");

        // Apply settings to the server status value label.
        serverDetailsConnectionStatusValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        serverDetailsConnectionStatusValueLabel.setForeground(new Color(46, 213, 116));
        serverDetailsConnectionStatusValueLabel.setName("serverDetailsConnectionStatusValueLabel");

        serverDetailsCoordinatorLabel.setFont(InterfaceManager.montserratMedium.deriveFont(11.0f));
        serverDetailsCoordinatorLabel.setText("Server Coordinator");
        serverDetailsCoordinatorLabel.setName("serverDetailsCoordinatorLabel");

        serverDetailsCoordinatorValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(11.0f));
        serverDetailsCoordinatorValueLabel.setName("serverDetailsCoordinatorValueLabel");

        // Apply group layout configuration for the server details.
        GroupLayout serverDetailsPanel1Layout = new GroupLayout(serverDetailsPanel1);
        serverDetailsPanel1.setLayout(serverDetailsPanel1Layout);
        serverDetailsPanel1Layout
                .setHorizontalGroup(serverDetailsPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(serverDetailsPanel1Layout.createSequentialGroup().addGap(36, 36, 36)
                                .addComponent(serverDetailsIcon).addGap(39, 39, 39).addComponent(serverDetailsLabel1)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serverDetailsLabel2).addContainerGap(63, Short.MAX_VALUE)));
        serverDetailsPanel1Layout.setVerticalGroup(serverDetailsPanel1Layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(serverDetailsPanel1Layout.createSequentialGroup().addGroup(serverDetailsPanel1Layout
                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(serverDetailsPanel1Layout.createSequentialGroup().addContainerGap()
                                .addComponent(serverDetailsIcon))
                        .addGroup(serverDetailsPanel1Layout.createSequentialGroup().addGap(34, 34, 34)
                                .addGroup(serverDetailsPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(serverDetailsLabel1).addComponent(serverDetailsLabel2))))
                        .addContainerGap()));

        // Apply settings to the server details.
        GroupLayout serverDetailsPanel2Layout = new GroupLayout(serverDetailsPanel2);
        serverDetailsPanel2.setLayout(serverDetailsPanel2Layout);
        serverDetailsPanel2Layout
                .setHorizontalGroup(serverDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(serverDetailsPanel2Layout.createSequentialGroup().addGap(39, 39, 39)
                                .addGroup(serverDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(serverDetailsIPAddressLabel)
                                        .addComponent(serverDetailsIPAddressValueLabel))
                                .addGap(50, 50, 50)
                                .addGroup(serverDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(serverDetailsConnectionPortLabel)
                                        .addComponent(serverDetailsConnectionPortValueLabel))
                                .addGap(48, 48, 48)
                                .addGroup(serverDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(serverDetailsConnectionStatusLabel)
                                        .addComponent(serverDetailsConnectionStatusValueLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE)
                                .addGroup(serverDetailsPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(serverDetailsCoordinatorLabel)
                                        .addComponent(serverDetailsCoordinatorValueLabel))
                                .addGap(37, 37, 37)));
        serverDetailsPanel2Layout.setVerticalGroup(
                serverDetailsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(serverDetailsPanel2Layout.createSequentialGroup().addGap(25, 25, 25)
                                .addGroup(serverDetailsPanel2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(serverDetailsIPAddressLabel)
                                        .addComponent(serverDetailsConnectionPortLabel)
                                        .addComponent(serverDetailsConnectionStatusLabel)
                                        .addComponent(serverDetailsCoordinatorLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(serverDetailsPanel2Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(serverDetailsPanel2Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(serverDetailsIPAddressValueLabel)
                                                .addComponent(serverDetailsConnectionPortValueLabel)
                                                .addComponent(serverDetailsConnectionStatusValueLabel))
                                        .addComponent(serverDetailsCoordinatorValueLabel))
                                .addContainerGap(47, Short.MAX_VALUE)));

        // #-------------------------------------------------------------------#
        // #                           Messaging Box                           #
        // #-------------------------------------------------------------------#          

        // Apply settings to the messaging panel.
        userMessagingTopPanel.setBackground(new Color(255, 255, 255));
        userMessagingTopPanel.setMinimumSize(new Dimension(93, 100));
        userMessagingTopPanel.setName("userMessagingTopPanel");
        userMessagingTopPanel.setPreferredSize(new Dimension(894, 93));

        // Apply settings to the messaging icon.
        userMessagingIconLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/user_messaging.png")));
        userMessagingIconLabel.setName("userMessagingIconLabel");

        // Apply settings to the messaging label title (section 1)
        userMessagingLabel1.setFont(InterfaceManager.montserratRegular.deriveFont(28.0f));
        userMessagingLabel1.setText("User");
        userMessagingLabel1.setName("userMessagingLabel1");

        // Apply settings to themessaging label title (section 2)
        userMessagingLabel2.setFont(InterfaceManager.montserratSemiBold.deriveFont(28.0f));
        userMessagingLabel2.setForeground(new Color(0, 36, 109));
        userMessagingLabel2.setText("Messaging");
        userMessagingLabel2.setName("userMessagingLabel2");

        // Apply settings to the user panel
        GroupLayout userMessagingTopPanelLayout = new GroupLayout(userMessagingTopPanel);
        userMessagingTopPanel.setLayout(userMessagingTopPanelLayout);
        userMessagingTopPanelLayout.setHorizontalGroup(userMessagingTopPanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(userMessagingTopPanelLayout.createSequentialGroup().addGap(295, 295, 295)
                        .addComponent(userMessagingIconLabel).addGap(18, 18, 18).addComponent(userMessagingLabel1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(userMessagingLabel2)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        userMessagingTopPanelLayout.setVerticalGroup(userMessagingTopPanelLayout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(userMessagingTopPanelLayout.createSequentialGroup()
                        .addGroup(userMessagingTopPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(userMessagingTopPanelLayout.createSequentialGroup().addGap(20, 20, 20)
                                        .addGroup(userMessagingTopPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(userMessagingLabel2).addComponent(userMessagingLabel1)))
                                .addGroup(userMessagingTopPanelLayout.createSequentialGroup().addContainerGap()
                                        .addComponent(userMessagingIconLabel)))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        // Apply settings to the primary message content panel.
        userMessagingMainPanel.setBackground(new Color(255, 255, 255));
        userMessagingMainPanel.setName("userMessagingMainPanel");
        userMessagingMainPanel.setPreferredSize(new Dimension(737, 298));
        userMessagingMainPanel.setLayout(null);

        // Apply settings and listener to the message send text field.
        userMessagesTextfield.setBounds(10, 10, 760, 44);
        userMessagesTextfield.setCaretColor(new Color(152, 150, 162));
        userMessagesTextfield.setDisabledTextColor(new Color(152, 150, 162));
        userMessagesTextfield.setFont(InterfaceManager.montserratRegular.deriveFont(13.0f));
        userMessagesTextfield.setForeground(new Color(152, 150, 162));
        userMessagesTextfield.setName("userMessagesTextfield");
        userMessagesTextfield.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                userMessagesTextfieldKeyPressed(evt);
            }
        });
        userMessagingMainPanel.add(userMessagesTextfield);

        // Apply settings, effects and actions to the send icon
        userMessagesIconLabel.setBackground(new Color(255, 255, 255));
        userMessagesIconLabel.setForeground(new Color(255, 255, 255));
        userMessagesIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userMessagesIconLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/icons/arrow_circle_icon.png")));
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
        userMessagingMainPanel.add(userMessagesIconLabel);
        userMessagesIconLabel.setBounds(780, 10, 120, 40);

        // Apply settings to the send button - no actions needed as label is overlayed on top fully
        userMessagesButton.setBorder(null);
        userMessagesButton.setBounds(770, 0, 140, 60);
        userMessagesButton.setContentAreaFilled(false);
        userMessagesButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        userMessagesButton.setHorizontalTextPosition(SwingConstants.CENTER);
        userMessagesButton.setIcon(new ImageIcon(getClass().getResource("/imgs/buttons/small.png")));
        userMessagesButton.setName("userMessagesButton");
        userMessagingMainPanel.add(userMessagesButton);

        primaryMessagePane.setName("messageTabbedPane");

        // #-------------------------------------------------------------------#
        // #                            Footer Box                             #
        // #-------------------------------------------------------------------#

        // Apply settings to the footer panel.
        footerPanel.setBackground(new Color(255, 255, 255));
        footerPanel.setName("footerPanel");
        footerPanel.setPreferredSize(new Dimension(936, 20));

        // Apply settings to the footer label (first section)
        footerTextLabel.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerTextLabel.setForeground(new Color(47, 46, 65));
        footerTextLabel.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel.setName("footerTextLabel");

        // Apply settings and actions to the footer licenses label
        footerLicensesTextLabel.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerLicensesTextLabel.setForeground(new Color(47, 46, 65));
        footerLicensesTextLabel.setText("Software Licenses");
        footerLicensesTextLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        footerLicensesTextLabel.setName("footerLicensesTextLabel");
        footerLicensesTextLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                footerLicensesTextLabelMouseClicked(evt);
            }
        });

        // Apply group layout configuration for the footer
        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout
                .setHorizontalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(footerTextLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(footerLicensesTextLabel).addGap(328, 328, 328)));
        footerPanelLayout.setVerticalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        footerPanelLayout.createSequentialGroup().addGap(5, 5, 5)
                                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(footerLicensesTextLabel).addComponent(footerTextLabel))
                                .addContainerGap()));

        // Apply settings to the screen.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(headerNamePanel, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                        .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(footerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup().addGap(2, 2, 2).addComponent(headerTitlePanel,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup().addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(serverDetailsPanel1, GroupLayout.PREFERRED_SIZE, 334,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2).addComponent(serverDetailsPanel2,
                                                        GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(clientDetailsPanel1, GroupLayout.PREFERRED_SIZE, 334,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2).addComponent(clientDetailsPanel2,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                                        Short.MAX_VALUE))
                                        .addComponent(userMessagingTopPanel, GroupLayout.DEFAULT_SIZE, 912,
                                                Short.MAX_VALUE))
                                .addGap(12, 12, 12))
                        .addGroup(
                                layout.createSequentialGroup().addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(userMessagingMainPanel, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(primaryMessagePane))
                                        .addContainerGap()))));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(headerNamePanel, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE).addComponent(
                                headerTitlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout
                        .createSequentialGroup().addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(clientDetailsPanel1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                                .addComponent(clientDetailsPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                                        Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(serverDetailsPanel1, GroupLayout.PREFERRED_SIZE, 92,
                                        GroupLayout.PREFERRED_SIZE)
                                .addComponent(serverDetailsPanel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(userMessagingTopPanel, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(primaryMessagePane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(userMessagingMainPanel, GroupLayout.PREFERRED_SIZE, 67,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(footerPanel,
                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(sidePanel, GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE))));

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Display user list.
    private void userListLabelMouseClicked(MouseEvent evt) {
        showMessageDialog(null, this.client.getAllClientsInfoFromLocalListAsFormattedString(), "User List",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Display licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
    }

    // Exit the application.
    private void exitApplicationLabelMouseClicked(MouseEvent evt) {
            showMessageDialog(null, "<html><h3>Thanks for using DiscussionNet!</h3><p>Should you need any future assistance, please visit https://comp1549.universitycourse.work for more details. Goodbye!</p></html>", "Program Closing",
                            JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Execute send message function.
    private void userMessagesIconLabelMouseClicked(MouseEvent evt) {
        this.sendMessage();
    }

    // If enter key pressed by user while in text field, send message.
    private void userMessagesTextfieldKeyPressed(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.sendMessage();
        }
    }

    // Send message
    private void sendMessage() {
        // Default message type to private.
        Boolean messageType = false;

        // Get selected channel.
        String messageRecipient = this.primaryMessagePane.getTitleAt(this.primaryMessagePane.getSelectedIndex());

        if (messageRecipient == "Group Chat") {
            // If group chat message, alter message type.
            messageType = true;
        }

        // Execute send of message.
        this.client.sendMessage(messageRecipient, userMessagesTextfield.getText(), messageType);

        // Display the outbound message on the user interface.
        InterfaceManager.displayMessage(this.primaryMessagePane, Calendar.getInstance().getTime(), "Sent",
                this.clientIdentifier, userMessagesTextfield.getText(), messageType);

        // Clear text box after message sending.
        userMessagesTextfield.setText("");
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Effects                         #
    // #-----------------------------------------------------------------------#

    // Configure Button Hover Effect On
    private void userMessagesIconLabelMouseEntered(MouseEvent evt) {
        InterfaceManager.buttonHover(userMessagesButton, true, "small");
    }

    // Configure Button Hover Effect Off
    private void userMessagesIconLabelMouseExited(MouseEvent evt) {
        InterfaceManager.buttonHover(userMessagesButton, false, "small");
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
            java.util.logging.Logger.getLogger(ClientMessaging.class.getName()).log(java.util.logging.Level.SEVERE,
                    null, ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMessaging(args[0], args[1], args[2], args[3], args[4]).setVisible(true);
            }
        });
    }

    private JButton userMessagesButton;
    private JLabel clientControlPanelLabel1;
    private JLabel clientControlPanelLabel2;
    private JLabel clientDetailsAssignedIDNumberLabel;
    private JLabel clientDetailsAssignedIDNumberValueLabel;
    private JLabel clientDetailsConnectionPortLabel;
    private JLabel clientDetailsConnectionPortValueLabel;
    private JLabel clientDetailsIcon;
    private JLabel clientDetailsIPAddressLabel;
    private JLabel clientDetailsIPAddressValueLabel;
    private JLabel clientDetailsLabel1;
    private JLabel clientDetailsLabel2;
    private JLabel clientFeaturesLabel;
    private JLabel discussionNetLabel;
    private JLabel exitApplicationLabel;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel;
    private JLabel sendArrowIconLabel;
    private JLabel serverDetailsConnectionPortLabel;
    private JLabel serverDetailsConnectionPortValueLabel;
    private JLabel serverDetailsConnectionStatusLabel;
    private JLabel serverDetailsConnectionStatusValueLabel;
    private JLabel serverDetailsCoordinatorLabel;
    private JLabel serverDetailsCoordinatorValueLabel;
    private JLabel serverDetailsIcon;
    private JLabel serverDetailsIPAddressLabel;
    private JLabel serverDetailsIPAddressValueLabel;
    private JLabel serverDetailsLabel1;
    private JLabel serverDetailsLabel2;
    private JLabel userListLabel;
    private JLabel userMessagesIconLabel;
    private JLabel userMessagingIconLabel;
    private JLabel userMessagingLabel;
    private JLabel userMessagingLabel1;
    private JLabel userMessagingLabel2;
    private JPanel clientDetailsPanel1;
    private JPanel clientDetailsPanel2;
    private JPanel footerPanel;
    private JPanel headerNamePanel;
    private JPanel headerTitlePanel;
    private JPanel serverDetailsPanel1;
    private JPanel serverDetailsPanel2;
    private JPanel sidePanel;
    private JPanel userMessagingMainPanel;
    private JPanel userMessagingTopPanel;
    private JTabbedPane primaryMessagePane;
    private JTextField userMessagesTextfield;
}

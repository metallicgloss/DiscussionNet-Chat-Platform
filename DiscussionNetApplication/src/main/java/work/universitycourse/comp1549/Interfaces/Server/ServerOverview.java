package work.universitycourse.comp1549.Interfaces.Server;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import javax.swing.border.LineBorder;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import work.universitycourse.comp1549.Modules.InterfaceManager;
import work.universitycourse.comp1549.Modules.ServerManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */

@SuppressWarnings({ "serial", "rawtypes" })
public class ServerOverview extends JFrame {
    private String serverIPAddress;
    private String serverPort;

    // #-----------------------------------------------------------------------#
    // #                     Create New ServerOverview Form                    #
    // #-----------------------------------------------------------------------#
    public ServerOverview(String serverIPAddress, String serverPort) {
        super();
        this.serverIPAddress = serverIPAddress;
        this.serverPort = serverPort;

        initComponents();
        table();

        // Execute thread to run instance of the server in the background - detached from the interface (to avoid lockup)
        new Thread(new Runnable() {
            private JTable serverLog;
            private String serverIPAddress;
            private int serverPort;

            public Runnable init(JTable serverLog, String serverIPAddress, int serverPort) {
                this.serverLog = serverLog;
                this.serverIPAddress = serverIPAddress;
                this.serverPort = serverPort;
                return this;
            }

            @Override
            public void run() {
                ServerManager serverInstance = new ServerManager(this.serverLog, this.serverIPAddress, this.serverPort);
                serverInstance.startServer();
            }
        }.init(this.mainTable, this.serverIPAddress, Integer.parseInt(this.serverPort))).start();

        // Update fields.
        statusValueLabel.setText("Active");
        ipAddressValueLabel.setText(this.serverIPAddress);
        portValueLabel.setText(this.serverPort);

    }

    // Table settings and styling overrides.
    private void table() {
        mainTable.setOpaque(false);
        mainTable.setFillsViewportHeight(true);
        mainTable.setBackground(Color.white);

        mainTable.getTableHeader().setFont(InterfaceManager.montserratMedium.deriveFont(13.0f));

        DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer) mainTable.getDefaultRenderer(String.class);
        stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) mainTable.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        JTableHeader header = mainTable.getTableHeader();
        header.setBorder(new LineBorder(new Color(229, 229, 229), 1));
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#

    @SuppressWarnings("unchecked")
    private void initComponents() {

        // Initialise interface objects.
        communicationLogsLabel1 = new JLabel();
        communicationLogsLabel2 = new JLabel();
        footerLicensesTextLabel = new JLabel();
        footerPanel = new JPanel();
        footerTextLabel2 = new JLabel();
        ipAddressLabel = new JLabel();
        ipAddressValueLabel = new JLabel();
        mainTable = new JTable();
        middlePanel = new JPanel();
        portLabel = new JLabel();
        portValueLabel = new JLabel();
        scrollingPane = new JScrollPane();
        serverStatusImageLabel = new JLabel();
        serverStatusTextLabel = new JLabel();
        statusLabel = new JLabel();
        statusValueLabel = new JLabel();
        topPanel = new JPanel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        // Apply settings to the top panel box.
        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setMaximumSize(new Dimension(847, 519));
        topPanel.setName("topPanel");
        topPanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(topPanel);

        // Apply settings to the communication logs label (section 1)
        communicationLogsLabel1.setFont(InterfaceManager.montserratRegular.deriveFont(23.0f));
        communicationLogsLabel1.setName("communicationLogsLabel1");
        communicationLogsLabel1.setText("DiscussionNet Server");

        // Apply settings to the communication logs label (section 2)
        communicationLogsLabel2.setFont(InterfaceManager.montserratSemiBold.deriveFont(23.0f));
        communicationLogsLabel2.setForeground(new Color(0, 36, 109));
        communicationLogsLabel2.setName("communicationLogsLabel2");
        communicationLogsLabel2.setText("Logs");

        scrollingPane.setName("scrollingPane");

        // Apply table settings, schema.
        mainTable.setFont(InterfaceManager.montserratRegular.deriveFont(11.0f));
        mainTable.setModel(new DefaultTableModel(new Object[][] {

        }, new String[] { "Source", "Destination", "Type", "Payload" }) {
            Class[] types = new Class[] { java.lang.String.class, java.lang.String.class, java.lang.String.class,
                    java.lang.String.class };
            boolean[] canEdit = new boolean[] { false, false, false, false };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        mainTable.getTableHeader().setBackground(Color.white);
        mainTable.getTableHeader().setForeground(Color.black);
        mainTable.getTableHeader().setOpaque(false);
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.getTableHeader().setResizingAllowed(false);
        mainTable.setAutoscrolls(false);
        mainTable.setFocusable(false);
        mainTable.setGridColor(new Color(255, 255, 255));
        mainTable.setIntercellSpacing(new Dimension(2, 0));
        mainTable.setName("mainTable");
        mainTable.setOpaque(false);
        mainTable.setRowHeight(30);
        mainTable.setRowSelectionAllowed(false);
        mainTable.setShowGrid(false);
        scrollingPane.setViewportView(mainTable);
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setResizable(false);
            mainTable.getColumnModel().getColumn(0).setHeaderValue("Source");
            mainTable.getColumnModel().getColumn(1).setResizable(false);
            mainTable.getColumnModel().getColumn(1).setHeaderValue("Destination");
            mainTable.getColumnModel().getColumn(2).setResizable(false);
            mainTable.getColumnModel().getColumn(2).setHeaderValue("Type");
            mainTable.getColumnModel().getColumn(3).setResizable(false);
            mainTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            mainTable.getColumnModel().getColumn(3).setHeaderValue("Payload");
        }

        // Apply group layout configuration for the top panel.
        GroupLayout topPanelLayout = new GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        topPanelLayout.createSequentialGroup()
                                                .addComponent(scrollingPane, GroupLayout.PREFERRED_SIZE, 784,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addGap(33, 33, 33))
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        topPanelLayout.createSequentialGroup().addComponent(communicationLogsLabel1)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(communicationLogsLabel2).addGap(266, 266, 266)))));
        topPanelLayout.setVerticalGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(topPanelLayout.createSequentialGroup().addGap(44, 44, 44)
                        .addGroup(topPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(communicationLogsLabel1).addComponent(communicationLogsLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(scrollingPane, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(38, Short.MAX_VALUE)));

        middlePanel.setBackground(new Color(255, 255, 255));
        middlePanel.setName("middlePanel");

        // Apply settings to the status text label.
        serverStatusTextLabel.setFont(InterfaceManager.montserratRegular.deriveFont(23.0f));
        serverStatusTextLabel.setText("Server Status");
        serverStatusTextLabel.setName("serverStatusTextLabel");

        // Apply settings to the status title label.
        statusLabel.setFont(InterfaceManager.montserratMedium.deriveFont(13.0f));
        statusLabel.setText("Status");
        statusLabel.setName("statusLabel");

        // Apply settings to the IP title label.
        ipAddressLabel.setFont(InterfaceManager.montserratMedium.deriveFont(13.0f));
        ipAddressLabel.setText("IP Address");
        ipAddressLabel.setName("ipAddressLabel");

        // Apply settings to the port title label.
        portLabel.setFont(InterfaceManager.montserratMedium.deriveFont(13.0f));
        portLabel.setText("Port");
        portLabel.setName("portLabel");

        // Apply settings to the status value label.
        statusValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(13.0f));
        statusValueLabel.setForeground(new Color(46, 213, 116));
        statusValueLabel.setName("statusValueLabel");

        // Apply settings to the IP Value label.
        ipAddressValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(13.0f));
        ipAddressValueLabel.setName("ipAddressValueLabel");

        // Apply settings to the port value label.
        portValueLabel.setFont(InterfaceManager.montserratLight.deriveFont(13.0f));
        portValueLabel.setName("portValueLabel");

        // Apply settings status graphic.
        serverStatusImageLabel.setIcon(new ImageIcon(getClass().getResource("/imgs/graphics/broadcast_active.png")));
        serverStatusImageLabel.setName("serverStatusImageLabel");

        // Apply group layout configuration for the middle panel.
        GroupLayout middlePanelLayout = new GroupLayout(middlePanel);
        middlePanel.setLayout(middlePanelLayout);
        middlePanelLayout.setHorizontalGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, middlePanelLayout.createSequentialGroup()
                        .addContainerGap(168, Short.MAX_VALUE).addComponent(serverStatusImageLabel).addGap(71, 71, 71)
                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(middlePanelLayout.createSequentialGroup()
                                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(statusValueLabel).addComponent(statusLabel))
                                        .addGap(89, 89, 89)
                                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(ipAddressLabel).addComponent(ipAddressValueLabel))
                                        .addGap(60, 60, 60)
                                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(portValueLabel).addComponent(portLabel)))
                                .addComponent(serverStatusTextLabel))
                        .addGap(185, 185, 185)));
        middlePanelLayout.setVerticalGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(middlePanelLayout.createSequentialGroup().addContainerGap(51, Short.MAX_VALUE)
                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(GroupLayout.Alignment.TRAILING,
                                        middlePanelLayout.createSequentialGroup().addComponent(serverStatusImageLabel)
                                                .addGap(40, 40, 40))
                                .addGroup(GroupLayout.Alignment.TRAILING, middlePanelLayout.createSequentialGroup()
                                        .addComponent(serverStatusTextLabel)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(portLabel).addComponent(ipAddressLabel)
                                                .addComponent(statusLabel))
                                        .addGap(1, 1, 1)
                                        .addGroup(middlePanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(statusValueLabel).addComponent(ipAddressValueLabel)
                                                .addComponent(portValueLabel))
                                        .addGap(57, 57, 57)))));

        footerPanel.setBackground(new Color(255, 255, 255));
        footerPanel.setName("footerPanel");

        // Apply settings to the first footer label.
        footerTextLabel2.setFont(InterfaceManager.montserratRegular.deriveFont(9.0f));
        footerTextLabel2.setForeground(new Color(47, 46, 65));
        footerTextLabel2.setName("footerTextLabel2");
        footerTextLabel2.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");

        // Apply settings and action to the footer display label.
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

        // Apply group layout configuration for footer panel.
        GroupLayout footerPanelLayout = new GroupLayout(footerPanel);
        footerPanel.setLayout(footerPanelLayout);
        footerPanelLayout.setHorizontalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(footerPanelLayout.createSequentialGroup().addGap(287, 287, 287).addComponent(footerTextLabel2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(footerLicensesTextLabel)
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        footerPanelLayout.setVerticalGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING,
                        footerPanelLayout.createSequentialGroup().addGap(0, 2, Short.MAX_VALUE)
                                .addGroup(footerPanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                                        .addComponent(footerLicensesTextLabel).addComponent(footerTextLabel2))));

        // Apply group layout configuration.
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 864, Short.MAX_VALUE)
                .addComponent(footerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(middlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(topPanel, GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE).addGap(2, 2, 2)
                        .addComponent(middlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2).addComponent(footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                GroupLayout.PREFERRED_SIZE)));

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                        User Interface Actions                         #
    // #-----------------------------------------------------------------------#

    // Display licenses window.
    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {
        InterfaceManager.displayLicenses();
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
            java.util.logging.Logger.getLogger(ServerOverview.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerOverview(args[0], args[1]).setVisible(true);
            }
        });
    }

    private JLabel communicationLogsLabel1;
    private JLabel communicationLogsLabel2;
    private JLabel footerLicensesTextLabel;
    private JLabel footerTextLabel2;
    private JLabel ipAddressLabel;
    private JLabel ipAddressValueLabel;
    private JLabel portLabel;
    private JLabel portValueLabel;
    private JLabel serverStatusImageLabel;
    private JLabel serverStatusTextLabel;
    private JLabel statusLabel;
    private JLabel statusValueLabel;
    private JPanel footerPanel;
    private JPanel middlePanel;
    private JPanel topPanel;
    private JScrollPane scrollingPane;
    private JTable mainTable;
}

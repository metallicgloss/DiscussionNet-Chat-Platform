package work.universitycourse.comp1549.Interfaces.Server;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.EventQueue;
import java.awt.Font;
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

@SuppressWarnings("serial")
public class ServerOverview extends JFrame {
    private static String serverIPAddress;
    private static String serverPort;

    /**
     * Creates new form communicationLogs
     */
    public ServerOverview(String serverIPAddress, String serverPort) {
        super();
        this.serverIPAddress = serverIPAddress;
        this.serverPort = serverPort;

        initComponents();
        table();

        new Thread(new Runnable() {
            private JTable serverLog;
            private String serverIPAddress;
            private int serverPort;
            private int maxConnections;

            public Runnable init(JTable serverLog, String serverIPAddress, int serverPort, int maxConnections) {
                this.serverLog = serverLog;
                this.serverIPAddress = serverIPAddress;
                this.serverPort = serverPort;
                this.maxConnections = maxConnections;
                return this;
            }

            @Override
            public void run() {
                ServerManager serverInstance = new ServerManager(this.serverLog, this.serverIPAddress, this.serverPort,
                        1024);
                serverInstance.startServer();
            }
        }.init(this.mainTable, this.serverIPAddress, Integer.parseInt(this.serverPort), 1024)).start();

        statusValueLabel.setText("Active");
        ipAddressValueLabel.setText(this.serverIPAddress);
        portValueLabel.setText(this.serverPort);

    }

    private void table() {
        mainTable.setOpaque(false);
        mainTable.setFillsViewportHeight(true);
        mainTable.setBackground(Color.white);

        mainTable.getTableHeader().setFont(new Font("Montserrat Medium", 0, 13));

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

        topPanel = new JPanel();
        communicationLogsLabel1 = new JLabel();
        communicationLogsLabel2 = new JLabel();
        scrollingPane = new JScrollPane();
        mainTable = new JTable();
        middlePanel = new JPanel();
        serverStatusTextLabel = new JLabel();
        statusLabel = new JLabel();
        ipAddressLabel = new JLabel();
        portLabel = new JLabel();
        statusValueLabel = new JLabel();
        ipAddressValueLabel = new JLabel();
        portValueLabel = new JLabel();
        serverStatusImageLabel = new JLabel();
        footerPanel = new JPanel();
        footerTextLabel2 = new JLabel();
        footerLicensesTextLabel = new JLabel();

        // Define application window settings.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("DiscussionNet");
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);

        topPanel.setBackground(new Color(255, 255, 255));
        topPanel.setMaximumSize(new Dimension(847, 519));
        topPanel.setName("topPanel");
        topPanel.setPreferredSize(new Dimension(847, 519));
        InterfaceManager.detectExitRequest(topPanel);

        communicationLogsLabel1.setFont(new Font("Montserrat", 0, 24));
        communicationLogsLabel1.setText("DiscussionNet Server");
        communicationLogsLabel1.setName("communicationLogsLabel1");

        communicationLogsLabel2.setFont(new Font("Montserrat SemiBold", 0, 24));
        communicationLogsLabel2.setForeground(new Color(0, 36, 109));
        communicationLogsLabel2.setText("Logs");
        communicationLogsLabel2.setName("communicationLogsLabel2");

        scrollingPane.setName("scrollingPane");

        mainTable.setFont(new Font("Montserrat", 0, 11));
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
        mainTable.setAutoscrolls(false);
        mainTable.setFocusable(false);
        mainTable.setGridColor(new Color(255, 255, 255));
        mainTable.setIntercellSpacing(new Dimension(2, 0));
        mainTable.setName("mainTable");
        mainTable.setOpaque(false);
        mainTable.setRowHeight(30);
        mainTable.setRowSelectionAllowed(false);
        mainTable.setShowGrid(false);
        mainTable.getTableHeader().setResizingAllowed(false);
        mainTable.getTableHeader().setReorderingAllowed(false);
        mainTable.getTableHeader().setOpaque(false);
        mainTable.getTableHeader().setBackground(Color.white);
        mainTable.getTableHeader().setForeground(Color.black);
        scrollingPane.setViewportView(mainTable);
        java.util.ResourceBundle bundle = java.util.ResourceBundle
                .getBundle("work/universitycourse/comp1549/Interfaces/Server/Bundle");
        if (mainTable.getColumnModel().getColumnCount() > 0) {
            mainTable.getColumnModel().getColumn(0).setResizable(false);
            mainTable.getColumnModel().getColumn(0).setHeaderValue("Source");
            mainTable.getColumnModel().getColumn(1).setResizable(false);
            mainTable.getColumnModel().getColumn(1)
                    .setHeaderValue(bundle.getString("ServerOverview.mainTable.columnModel.title3"));
            mainTable.getColumnModel().getColumn(2).setResizable(false);
            mainTable.getColumnModel().getColumn(2)
                    .setHeaderValue(bundle.getString("ServerOverview.mainTable.columnModel.title6"));
            mainTable.getColumnModel().getColumn(3).setResizable(false);
            mainTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            mainTable.getColumnModel().getColumn(3)
                    .setHeaderValue(bundle.getString("ServerOverview.mainTable.columnModel.title7"));
        }

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

        serverStatusTextLabel.setFont(new Font("Montserrat", 0, 24));
        serverStatusTextLabel.setText("Server Status");
        serverStatusTextLabel.setName("serverStatusTextLabel");

        statusLabel.setFont(new Font("Montserrat Medium", 0, 13));
        statusLabel.setText("Status");
        statusLabel.setName("statusLabel");

        ipAddressLabel.setFont(new Font("Montserrat Medium", 0, 13));
        ipAddressLabel.setText("IP Address");
        ipAddressLabel.setName("ipAddressLabel");

        portLabel.setFont(new Font("Montserrat Medium", 0, 13));
        portLabel.setText("Port");
        portLabel.setName("portLabel");

        statusValueLabel.setFont(new Font("Montserrat Light", 0, 13));
        statusValueLabel.setForeground(new Color(46, 213, 116));
        statusValueLabel.setName("statusValueLabel");

        ipAddressValueLabel.setFont(new Font("Montserrat Light", 0, 13));
        ipAddressValueLabel.setName("ipAddressValueLabel");

        portValueLabel.setFont(new Font("Montserrat Light", 0, 13));
        portValueLabel.setName("portValueLabel");

        serverStatusImageLabel.setIcon(new ImageIcon(getClass().getResource("/graphics/broadcast_active.png")));
        serverStatusImageLabel.setName("serverStatusImageLabel");

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

        footerTextLabel2.setFont(new Font("Montserrat", 0, 9));
        footerTextLabel2.setForeground(new Color(47, 46, 65));
        footerTextLabel2.setText("DiscussionNet V1.0   -   © Code Squad 2021   -");
        footerTextLabel2.setName("footerTextLabel2");

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

    private void footerLicensesTextLabelMouseClicked(MouseEvent evt) {//GEN-FIRST:event_footerLicensesTextLabelMouseClicked
        InterfaceManager.displayLicenses();
    }//GEN-LAST:event_footerLicensesTextLabelMouseClicked

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
            java.util.logging.Logger.getLogger(ServerOverview.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerOverview.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerOverview.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerOverview.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerOverview(args[0], args[1]).setVisible(true);
            }
        });
    }

    private JLabel communicationLogsLabel1;
    private JLabel communicationLogsLabel2;
    private JLabel footerLicensesTextLabel;
    private JPanel footerPanel;
    private JLabel footerTextLabel2;
    private JLabel ipAddressLabel;
    private JLabel ipAddressValueLabel;
    private JTable mainTable;
    private JPanel middlePanel;
    private JLabel portLabel;
    private JLabel portValueLabel;
    private JScrollPane scrollingPane;
    private JLabel serverStatusImageLabel;
    private JLabel serverStatusTextLabel;
    private JLabel statusLabel;
    private JLabel statusValueLabel;
    private JPanel topPanel;
}

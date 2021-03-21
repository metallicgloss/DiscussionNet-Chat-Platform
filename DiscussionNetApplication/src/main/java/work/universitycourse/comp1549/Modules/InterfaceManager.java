package work.universitycourse.comp1549.Modules;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import work.universitycourse.comp1549.Components.JRoundedTextField;
import work.universitycourse.comp1549.Interfaces.Licenses;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class InterfaceManager {
    // Define program icon.
    public static ImageIcon programIcon = new ImageIcon(InterfaceManager.class.getResource("/icon.png"));

    public static Action exitProgram = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Exit application.
            System.exit(0);
        }
    };
     
    public static void detectExitRequest(JPanel currentWindow) {
        // Target input map of "C" and "CTRL" pressed at the same time.
        currentWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK), "exitApplicationRequest");
        
        // Map action of "exitProgram" to input map defined above of "exitApplicationRequest".
        currentWindow.getActionMap().put("exitApplicationRequest", exitProgram);   
    }
     
    public static void displayLicenses() {
        // Show licenses page in new window.
        JFrame licenseWindow = new Licenses();
        licenseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        licenseWindow.setVisible(true);     
    }
     
    public static void changeWindow(JFrame currentWindow, JFrame targetWindow) {
        // Close current JFrame.
        currentWindow.dispose();
        
        // Set the location of the new window to align to previous (eg, center or where user left it)
        targetWindow.setLocationRelativeTo(currentWindow);
        
        // Show new window.
        targetWindow.setVisible(true);     
    }
    
    public static void buttonHover(JButton targetButton, Boolean buttonState, String buttonSize) {
        // If true, mouse is hovering over button.
        if(buttonState) {
            targetButton.setIcon(new javax.swing.ImageIcon(InterfaceManager.class.getResource("/buttons/" + buttonSize + "-hover.png")));
        } else {
            targetButton.setIcon(new javax.swing.ImageIcon(InterfaceManager.class.getResource("/buttons/" + buttonSize + ".png")));
        }
    }
    
    public static void displayError(Exception errorString, String errorMessage) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        errorString.printStackTrace(pw);
        String stackTrace = sw.toString();
        showMessageDialog(null, stackTrace + " " + errorMessage, "System Execution Error", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void displayWarning(String warningMessage) {
        showMessageDialog(null, warningMessage, "Validation Error", JOptionPane.WARNING_MESSAGE);
    }
    
    public static void toggleTextFieldFocus(JTextField TextField, Boolean toggleStatus) {
        if(toggleStatus) {
            TextField.setForeground(Color.WHITE);
            TextField.setBackground(new Color(164,189,255));
            TextField.setCaretColor(Color.WHITE);
        } else {
            TextField.setForeground(new Color(152,150,162));
            TextField.setBackground(Color.WHITE);
            TextField.setCaretColor(new Color(152,150,162));
        }
    }
    
    public static void registerServerLog(JTable serverLog, String sourceClient, String destinationClient, String requestType, String requestPayload) {
        DefaultTableModel model = (DefaultTableModel) serverLog.getModel();
        model.addRow(new Object[]{sourceClient, destinationClient, requestType, requestPayload});
    }
    
    public static void createClient(JTabbedPane messagePane, String userID) {        
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(0,2));
        messagePanel.setBackground(new java.awt.Color(255, 255, 255));
                
        JLabel userIDLabel = new JLabel();
        userIDLabel.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        userIDLabel.setText("Channel: " + userID);
        userIDLabel.setBorder(new CompoundBorder(userIDLabel.getBorder(), new EmptyBorder(10,10,10,10)));
        messagePanel.add(userIDLabel);
        messagePanel.add(new JLabel());
        
        JScrollPane messageParentPanel = new JScrollPane(messagePanel);
        messageParentPanel.setBorder(BorderFactory.createEmptyBorder());
        
        messagePane.addTab(userID, messageParentPanel);
    }
    
    public static void removeClient(JTabbedPane messagePane, String userID) {
        messagePane.remove(identifyClientTabIndex(messagePane, userID));
    }
    
    public static void displayMessage(JTabbedPane messageListPane, Date messageTime, String messageType, String userID, String messageContent, Boolean groupMessage) {
        JScrollPane messageList = new JScrollPane();
        
        if (messageType.equals("Received")) {
            // Inbound message, may not be selected for user. Get content from the tab of that user.
            if(groupMessage) {
                messageList = (JScrollPane)messageListPane.getComponentAt(0);
            } else {
                messageList = (JScrollPane)messageListPane.getComponentAt(identifyClientTabIndex(messageListPane, userID));
            }
        } else {
            messageList = (JScrollPane)messageListPane.getSelectedComponent();
        }

        JViewport messageView = messageList.getViewport();
        JPanel targetPanel = (JPanel)messageView.getComponents()[0];
        
        if(messageType.equals("Sent")) {
            targetPanel.add(new JLabel());
        }
        
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        targetPanel.add(createMessage(userID, dateFormatter.format(messageTime), messageType, messageContent));
        
        if(messageType.equals("Received")) {
            targetPanel.add(new JLabel());
        }
        
        // Trigger refresh of component tree to render new content in panel; required to show messages quickly without mouse hover over tab.
        SwingUtilities.updateComponentTreeUI(targetPanel);
        
        JScrollBar messageListScroll = messageList.getVerticalScrollBar();
        messageListScroll.setValue(messageListScroll.getMaximum());
    }
    
    public static JLabel createMessage(String userID, String messageTime, String messageType, String messageContent) {
        JLabel newMessage = new JLabel();
        String messageAlignment;
        
        if(messageType.equals("Sent")) {
            newMessage.setHorizontalAlignment(SwingConstants.RIGHT);
            messageAlignment = "right";
        } else {
            newMessage.setHorizontalAlignment(SwingConstants.LEFT);
            messageAlignment = "left";
        }
        
        newMessage.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        newMessage.setText("<html><body style='width: 300px'><div style='text-align: " + messageAlignment + ";'><b>" + userID + "</b> - " + messageTime + "<br/>" + messageContent + "</div></body></html>");
        newMessage.setBorder(new CompoundBorder(newMessage.getBorder(), new EmptyBorder(2,10,2,10)));
        
        return newMessage;
    }
    
    public static int identifyClientTabIndex(JTabbedPane messagePane, String searchString) {
        int tabCount = messagePane.getTabCount();
        for (int i=0; i < tabCount; i++) 
        {
          String tabTitle = messagePane.getTitleAt(i);
          if (tabTitle.equals(searchString)) return i;
        }
        return -1;
    }
    
    public static boolean validateIPAddress(String internetProtocolAddress) {
        InetAddressValidator ipValidator = InetAddressValidator.getInstance();
 
        // Validate an IPv4 address - validation for null handled.
        if (! ipValidator.isValidInet4Address(internetProtocolAddress)) {
            displayWarning("Please ensure the input entered is a valid IPv4 address.");
            return false;
        }
            
        return true;
    }
    
    public static boolean validatePort(String networkPort) {
        RegexValidator typeValidator = new RegexValidator("^[0-9]*$");
        IntegerValidator rangeValidator = IntegerValidator.getInstance();
 
        // Validate the port entered isn't empty and is numeric.
        if (!typeValidator.isValid(networkPort) || networkPort.isEmpty()) {
            displayWarning("Please ensure the port specified is numeric.");
            return false;
        }
        
        // Validate that port provided is within range.
        if (!rangeValidator.isInRange(Integer.parseInt(networkPort), 1024, 49151)) {
            displayWarning("Please ensure the port specified is within the usable range (1024 - 49151).");
            return false;
        }
        
        // If code execution reached, completed validation.
        return true;
    }
    
    public static boolean validateIdentifier(String networkPort) {
        RegexValidator typeValidator = new RegexValidator("^[a-zA-Z0-9_]*$");
 
        // Validate no non-alphanumeric characters.
        if (!typeValidator.isValid(networkPort) || networkPort.isEmpty()) {
            displayWarning("Please ensure the ID entered correctly (and doesn't contain special characters or spaces except underscores).");
            return false;
        }
        
        // If code execution reached, completed validation.
        return true;
    }
}

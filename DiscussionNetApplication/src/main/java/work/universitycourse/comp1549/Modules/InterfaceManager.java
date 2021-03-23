package work.universitycourse.comp1549.Modules;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.commons.validator.routines.IntegerValidator;
import org.apache.commons.validator.routines.RegexValidator;
import static javax.swing.JOptionPane.showMessageDialog;
import work.universitycourse.comp1549.Interfaces.Licenses;
import work.universitycourse.comp1549.Interfaces.StartUpInterface;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */

// #---------------------------------------------------------------------------#
// #                                 Contents                                  #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                             InterfaceManager                              #
// #   Manages all client-sided UI alterations, actions, messages and errors.  #
// #                                                                           #
// #                   1 - Exit Request                                        #
// #                   2 - Window Change Requests                              #
// #                   3 - Warning Handlers                                    #
// #                   4 - UI Effect Handlers                                  #
// #                   5 - Client Handlers                                     #
// #                   6 - Message Handlers                                    #
// #                   7 - Input Validation Handlers                           #
// #                   8 - Server Log Handler                                  #
// #                                                                           #
// #---------------------------------------------------------------------------#

@SuppressWarnings("serial")
public class InterfaceManager {
    // Define program icon.
    public static ImageIcon programIcon = new ImageIcon(InterfaceManager.class.getResource("/icon.png"));

    // #-----------------------------------------------------------------------#
    // #                            1 - Exit Request                           #
    // #-----------------------------------------------------------------------#

    public static Action exitProgram = new AbstractAction() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Exit application.
            System.exit(0);
        }
    };

    // Allow CTRL+C to exit GUI appliocation.
    public static void detectExitRequest(JPanel currentWindow) {
        // Target input map of "C" and "CTRL" pressed at the same time.
        currentWindow.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK),
                "exitApplicationRequest");

        // Map action of "exitProgram" to input map defined above of "exitApplicationRequest".
        currentWindow.getActionMap().put("exitApplicationRequest", exitProgram);
    }

    // #-----------------------------------------------------------------------#
    // #                      2 - Window Change Requests                       #
    // #-----------------------------------------------------------------------#

    // Open licenses page in new window on top of program.
    public static void displayLicenses() {
        JFrame licenseWindow = new Licenses();
        licenseWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        licenseWindow.setVisible(true);
    }

    // Switch visibile window - close existing, open new.
    public static void changeWindow(JFrame currentWindow, JFrame targetWindow) {
        // Close current JFrame.
        currentWindow.dispose();

        // Set the location of the new window to align to previous (eg, center or where user left it)
        targetWindow.setLocationRelativeTo(currentWindow);

        // Show new window.
        targetWindow.setVisible(true);
    }

    // #-----------------------------------------------------------------------#
    // #                         3 - Warning Handlers                          #
    // #-----------------------------------------------------------------------#

    // Display a software error and stack trace to user - not expected fault.
    public static void displayError(Exception errorString, String errorMessage) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        errorString.printStackTrace(pw);
        String stackTrace = sw.toString();
        showMessageDialog(null, stackTrace + " " + errorMessage, "System Execution Error", JOptionPane.WARNING_MESSAGE);
    }

    // Display a warning or software notication message to the user.
    public static void displayWarning(String warningMessage) {
        showMessageDialog(null, warningMessage, "Application Notification", JOptionPane.WARNING_MESSAGE);
    }

    // Connection could not be initialised on startup, inform user to get Sysadmin for network review.
    public static void executeConnectionFault() {
        // Display formatted message informing them of the network configuration error.
        showMessageDialog(null,
                "<html><h2><b>Sorry!</b></h2><h3>A network related error occured. The connection could not be maintained.</h3>If in doubt, please review the network configuration for your device with a systems administrator before re-attempting the setup of this application.",
                "Connection Intialisation Error", JOptionPane.WARNING_MESSAGE);

        // Due to the complexity of the error, instead of re-routing user back to startup, close program to allow for sysadmin intervention if required to identify available ip/port.
        // High chance that sysadmin intervention will be required.
        java.awt.Window win[] = java.awt.Window.getWindows();
        for (int i = 0; i < win.length; i++) {
            win[i].dispose();
        }
        JFrame startUpInterface = new StartUpInterface();
        startUpInterface.setVisible(true);
    }

    // #-----------------------------------------------------------------------#
    // #                         4 - UI Effect Handlers                        #
    // #-----------------------------------------------------------------------#

    // Handle user interface hover events to alter the button - colour effect.
    public static void buttonHover(JButton targetButton, Boolean buttonState, String buttonSize) {
        // If true, mouse is hovering over button.
        if (buttonState) {
            targetButton.setIcon(new ImageIcon(
                    InterfaceManager.class.getResource("/buttons/" + buttonSize + "-hover.png")));
        } else {
            targetButton.setIcon(
                    new ImageIcon(InterfaceManager.class.getResource("/buttons/" + buttonSize + ".png")));
        }
    }

    // Handle user text field focus event to apply styles.
    public static void toggleTextFieldFocus(JTextField TextField, Boolean toggleStatus) {
        if (toggleStatus) {
            // Field focused, set blue background and white text.
            TextField.setForeground(Color.WHITE);
            TextField.setBackground(new Color(164, 189, 255));
            TextField.setCaretColor(Color.WHITE);
        } else {
            // Focus lost, set white background and greyblue text.
            TextField.setForeground(new Color(152, 150, 162));
            TextField.setBackground(Color.WHITE);
            TextField.setCaretColor(new Color(152, 150, 162));
        }
    }

    // #-----------------------------------------------------------------------#
    // #                          5 - Client Handlers                          #
    // #-----------------------------------------------------------------------#

    // New client connected - create client on the interface as new tab.
    public static void createClient(JTabbedPane messagePane, String userID) {
        // Build new user panel.
        JPanel messagePanel = new JPanel();
        messagePanel.setLayout(new GridLayout(0, 2));
        messagePanel.setBackground(new Color(255, 255, 255));

        // Create label to identify the channel.
        JLabel userIDLabel = new JLabel();
        userIDLabel.setFont(new Font("Montserrat", 0, 18));
        userIDLabel.setText("Channel: " + userID);
        userIDLabel.setBorder(new CompoundBorder(userIDLabel.getBorder(), new EmptyBorder(10, 10, 10, 10)));
        messagePanel.add(userIDLabel);
        messagePanel.add(new JLabel());

        // Create scroll pane and add panel to it - enables messages to be scrollable.
        JScrollPane messageParentPanel = new JScrollPane(messagePanel);
        messageParentPanel.setBorder(BorderFactory.createEmptyBorder());

        // Create tab including the scroll pane as content.
        messagePane.addTab(userID, messageParentPanel);
    }

    // Client disconnected - remove tab.
    public static void removeClient(JTabbedPane messagePane, String userID) {
        messagePane.remove(identifyClientTabIndex(messagePane, userID));
    }

    // Identify the tab index of a client.
    public static int identifyClientTabIndex(JTabbedPane messagePane, String searchString) {
        // Get the total tab count.
        int tabCount = messagePane.getTabCount();

        // For each tab, loop through until the title matches.
        for (int i = 0; i < tabCount; i++) {
            String tabTitle = messagePane.getTitleAt(i);
            if (tabTitle.equals(searchString)) {
                // Title matches user, return index.
                return i;
            }
        }

        // Index not found - invalid client.
        return -1;
    }

    // Update coordinator on interface after instruction.
    public static void updateCoordinator(JLabel coordinatorLabel, String coordinatorID) {
        coordinatorLabel.setText(coordinatorID);
    }

    // #-----------------------------------------------------------------------#
    // #                          6 - Message Handlers                         #
    // #-----------------------------------------------------------------------#

    // Display a message on the screen.
    public static void displayMessage(JTabbedPane messageListPane, Date messageTime, String messageType, String userID,
            String messageContent, Boolean groupMessage) {
        JScrollPane messageList = new JScrollPane();

        // If the message is being received, user may not have focused on tab.
        if (messageType.equals("Received")) {
            if (groupMessage) {
                // Group message - add to group chat channel.
                messageList = (JScrollPane) messageListPane.getComponentAt(0);
            } else {
                // Private message - add to the private message tab of user.
                messageList = (JScrollPane) messageListPane
                        .getComponentAt(identifyClientTabIndex(messageListPane, userID));
            }
        } else {
            // Outgoing message, add channel currently being looked at.
            messageList = (JScrollPane) messageListPane.getSelectedComponent();
        }

        // Target panel in tab to append content to.
        JViewport messageView = messageList.getViewport();
        JPanel targetPanel = (JPanel) messageView.getComponents()[0];

        // If message being send, add label before.
        if (messageType.equals("Sent")) {
            targetPanel.add(new JLabel());
        }

        // Initialise date formatter.
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // Create message to add to panel.
        targetPanel.add(createMessage(userID, dateFormatter.format(messageTime), messageType, messageContent));

        if (messageType.equals("Received")) {
            targetPanel.add(new JLabel());
        }

        // Trigger refresh of component tree to render new content in panel; required to show messages quickly without mouse hover over tab.
        SwingUtilities.updateComponentTreeUI(targetPanel);

        // Set scroll to the bottom to allow message to be seen.
        JScrollBar messageListScroll = messageList.getVerticalScrollBar();
        messageListScroll.setValue(messageListScroll.getMaximum());
    }

    // Create message value for use when displaying message to the screen.
    public static JLabel createMessage(String userID, String messageTime, String messageType, String messageContent) {
        JLabel newMessage = new JLabel();
        String messageAlignment;

        // If message sent, align to the right, else, left.
        if (messageType.equals("Sent")) {
            newMessage.setHorizontalAlignment(SwingConstants.RIGHT);
            messageAlignment = "right";
        } else {
            newMessage.setHorizontalAlignment(SwingConstants.LEFT);
            messageAlignment = "left";
        }

        // Define message label - content can be HTML.
        newMessage.setFont(new Font("Montserrat", 0, 12));
        newMessage.setText("<html><body style='width: 300px'><div style='text-align: " + messageAlignment + ";'><b>"
                + userID + "</b> - " + messageTime + "<br/>" + messageContent + "</div></body></html>");
        newMessage.setBorder(new CompoundBorder(newMessage.getBorder(), new EmptyBorder(2, 10, 2, 10)));

        return newMessage;
    }

    // #-----------------------------------------------------------------------#
    // #                     7 - Input Validation Handlers                     #
    // #-----------------------------------------------------------------------#

    // Validate IPv4 address
    public static boolean validateIPAddress(String internetProtocolAddress) {
        InetAddressValidator ipValidator = InetAddressValidator.getInstance();

        // Validate an IPv4 address - validation for null handled.
        if (!ipValidator.isValidInet4Address(internetProtocolAddress)) {
            displayWarning("Please ensure the input entered is a valid IPv4 address.");
            return false;
        }

        return true;
    }

    // Validate port - usable, numeric and not null.
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

    // Validate identifier - no special characters and not null.
    public static boolean validateIdentifier(String networkPort) {
        RegexValidator typeValidator = new RegexValidator("^[a-zA-Z0-9_]*$");

        // Validate no non-alphanumeric characters.
        if (!typeValidator.isValid(networkPort) || networkPort.isEmpty()) {
            displayWarning(
                    "Please ensure the ID entered correctly (and doesn't contain special characters or spaces except underscores).");
            return false;
        }

        // If code execution reached, completed validation.
        return true;
    }

    // #-----------------------------------------------------------------------#
    // #                         8 - Server Log Handler                        #
    // #-----------------------------------------------------------------------#

    public static void registerServerLog(JTable serverLog, String sourceClient, String destinationClient,
            String requestType, String requestPayload) {
        // Get model of the existing server log table.
        DefaultTableModel model = (DefaultTableModel) serverLog.getModel();

        // Add new row to the table with the request payload.
        model.addRow(new Object[] { sourceClient, destinationClient, requestType, requestPayload });
    }

}

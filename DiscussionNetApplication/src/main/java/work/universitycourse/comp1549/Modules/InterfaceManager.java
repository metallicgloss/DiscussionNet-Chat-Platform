package work.universitycourse.comp1549.Modules;

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
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Adnan Turna
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
        showMessageDialog(null, stackTrace + " " + errorMessage);
    }
}

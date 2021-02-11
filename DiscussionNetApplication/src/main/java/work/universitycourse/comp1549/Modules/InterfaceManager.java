package work.universitycourse.comp1549.Modules;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.*;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Adnan Turna
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class InterfaceManager {
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
}

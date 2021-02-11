package work.universitycourse.comp1549.Modules;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.reflect.*;
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
        currentWindow.dispose();     
        targetWindow.setLocationRelativeTo(currentWindow);
        targetWindow.setVisible(true);     
    }
}

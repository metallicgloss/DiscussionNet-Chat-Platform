/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.universitycourse.comp1549.Modules;

import JButton;
import JFrame;
import JPanel;
import JTable;
import JTextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gabriel
 */
public class InterfaceManagerTest {
    
    public InterfaceManagerTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of detectExitRequest method, of class InterfaceManager.
     */
    @Test
    public void testDetectExitRequest() {
        System.out.println("detectExitRequest");
        JPanel currentWindow = null;
        InterfaceManager.detectExitRequest(currentWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeWindow method, of class InterfaceManager.
     */
    @Test
    public void testChangeWindow() {
        System.out.println("changeWindow");
        JFrame currentWindow = null;
        JFrame targetWindow = null;
        InterfaceManager.changeWindow(currentWindow, targetWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buttonHover method, of class InterfaceManager.
     */
    @Test
    public void testButtonHover() {
        System.out.println("buttonHover");
        JButton targetButton = null;
        Boolean buttonState = null;
        String buttonSize = "";
        InterfaceManager.buttonHover(targetButton, buttonState, buttonSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayError method, of class InterfaceManager.
     */
    @Test
    public void testDisplayError() {
        System.out.println("displayError");
        Exception errorString = null;
        String errorMessage = "";
        InterfaceManager.displayError(errorString, errorMessage);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toggleTextFieldFocus method, of class InterfaceManager.
     */
    @Test
    public void testToggleTextFieldFocus() {
        System.out.println("toggleTextFieldFocus");
        JTextField TextField = null;
        Boolean toggleStatus = null;
        InterfaceManager.toggleTextFieldFocus(TextField, toggleStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerServerLog method, of class InterfaceManager.
     */
    @Test
    public void testRegisterServerLog() {
        System.out.println("registerServerLog");
        JTable serverLog = null;
        String sourceClient = "";
        String destinationClient = "";
        String requestType = "";
        String requestPayload = "";
        InterfaceManager.registerServerLog(serverLog, sourceClient, destinationClient, requestType, requestPayload);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

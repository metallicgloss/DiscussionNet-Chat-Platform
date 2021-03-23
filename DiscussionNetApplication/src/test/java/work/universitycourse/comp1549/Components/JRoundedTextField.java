/*
 Unsure how to write tests for GUI stuff
 */
package work.universitycourse.comp1549.Components;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.JTextField;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import work.universitycourse.comp1549.Components.JRoundedTextField;

/**
 *
 * @author Gabriel
 */
public class JRoundedTextField {
    
    public JRoundedTextField() {
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
     * Test of paintComponent method, of class RoundJTextField.
     */
    @Test
    public void testPaintComponent() {
        System.out.println("paintComponent");
        Graphics g = null;
        JRoundedTextField instance = new JRoundedTextField();
        //instance.paintComponent(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of paintBorder method, of class RoundJTextField.
     */
    @Test
    public void testPaintBorder() {
        System.out.println("paintBorder");
        Graphics g = null;
        JRoundedTextField instance = new JRoundedTextField();
        //instance.paintBorder(g);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class RoundJTextField.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        int x = 0;
        int y = 0;
        JRoundedTextField instance = new JRoundedTextField();
        boolean expResult = false;
        //boolean result = instance.contains(x, y);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

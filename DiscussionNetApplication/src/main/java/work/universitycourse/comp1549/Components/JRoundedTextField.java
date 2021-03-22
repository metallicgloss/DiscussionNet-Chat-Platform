package work.universitycourse.comp1549.Components;

import java.awt.Color;
import java.awt.geom.RoundRectangle2D;
import java.awt.Graphics;
import java.awt.Shape;
import javax.swing.JTextField;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */

// #---------------------------------------------------------------------------#
// #                                  Contents                                 #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                             JRoundedTextField                             #
// #     Inherits from JTextField and overrides border to display rounded.     #
// #                                                                           #
// #                           1 - Paint Component                             #
// #                           2 - Paint Border                                #
// #                           3 - Set "Contains"                              #
// #                                                                           #
// #---------------------------------------------------------------------------#

@SuppressWarnings("serial")
public class JRoundedTextField extends JTextField {

    private Shape shape;

    public JRoundedTextField() {
        // Disable opaque background on initialisation.
        setOpaque(false);
    }

    // #-----------------------------------------------------------------------#
    // #                     1 - Paint Component                               #
    // #-----------------------------------------------------------------------#

    // Adjusts the border radius of the contents to prevent overlap with border.
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());

        // Border radius of 15.
        g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        super.paintComponent(g);
    }

    // #-----------------------------------------------------------------------#
    // #                     2 - Paint Border                                  #
    // #-----------------------------------------------------------------------#

    // Sets the colour and border (with radius) of textbox.
    protected void paintBorder(Graphics g) {
        g.setColor(new Color(230, 230, 231));

        // Border radius of 15.
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
    }

    // #-----------------------------------------------------------------------#
    // #                     3 - Set "Contains"                                #
    // #-----------------------------------------------------------------------#

    // Float element to align correctly within updated border.
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 15, 15);
        }
        return shape.contains(x, y);
    }

}

/**
 * This class implements this cool idea: This class allows the user to control the game, and actively interact with it.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

public class View extends JPanel {
    private Control control;
    private GameState state;

    /**
     * This method creates the JFrame and the menu bar and the menu items and the action listeners for the menu items.
     * @param control  The control object that is used to control the game.
     * @param state The state object that is used to keep track of the game state.
     */
    public View(Control control, GameState state) {
        this.control = control;
        this.state = state;

        JFrame frame = new JFrame("Tower Defense");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * This method paints the background and the objects on the screen.
     * @param g  the Graphics context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // Draw the every object in the game.
        for (Animatable a : state.getCurrentObjects())
            a.draw(g);

    }
}

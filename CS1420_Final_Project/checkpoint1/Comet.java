/**
 * This class implements this cool idea: THis class controls the comet and allows it to move across the screen.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */

package checkpoint1;

import java.awt.*;

public class Comet implements Animatable {
    private Control control;
    private GameState state;
    private double percentage;  // The position along the path
    private int test;  // Remove this later

    /**
     * This is the constructor for the comet class.
     * @param control The control class
     * @param state The game state
     */
    public Comet(Control control, GameState state) {
        this.control = control;
        this.state = state;
        percentage = 0;
    }

    /**
     * This method updates the comet's position.
     * @param elapsedTime The time elapsed since the last update
     */
    @Override
    public void update(double elapsedTime) {
        percentage += 0.001;  // Slightly different values should be used here for different object types
    }

    /**
     * This method draws the comet.
     * @param g The graphics object
     */
    @Override
    public void draw(Graphics g) {
        try {
            Point loc = control.getPath().convertToCoordinates(percentage);
            g.drawImage(control.loadImage("comet.png"), loc.x, loc.y, null);
        } catch (Exception e) {
            System.out.println("Could not access the comet file");
        }

    }
}

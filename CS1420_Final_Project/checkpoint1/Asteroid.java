/**
 * This class implements this cool idea: This class is respinsible for the asteroid moving across the screen and updating the points.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import java.awt.*;

public class Asteroid implements Animatable {
    private Control control;
    private GameState state;
    private double percentage;  // The position along the path
    private int test;  // Remove this later

    /**
     * This method initializes all of the variables.
     * @param control This is the control class.
     * @param state This is the game state.
     */
    public Asteroid(Control control, GameState state) {
        this.control = control;
        this.state = state;
        percentage = 0;
    }

    /**
     * This method updates the percentage of the asteroid.
     * @param elapsedTime This is the time elapsed.
     */
    @Override
    public void update(double elapsedTime) {
        percentage += 0.001;  // Slightly different values should be used here for different object types
    }

    /**
     * This method draws the asteroid.
     * @param g This is the graphics object.
     */
    @Override
    public void draw(Graphics g) {
        try {
            Point loc = control.getPath().convertToCoordinates(percentage);
            g.drawImage(control.loadImage("asteroid.png"), loc.x, loc.y, null);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not access the asteroid file");
        }


    }
}

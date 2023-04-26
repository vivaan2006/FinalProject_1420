/**
 * This class implements this cool idea: It provides the background image for the game.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import java.awt.*;

public class Background implements Animatable {
    private Control control;
    private GameState state;
    /**
     * This method implements this cool idea: It creates the background image.
     * @param control The control object.
     * @param state The state object.
     */
    public Background(Control control, GameState state) {
        this.control = control;
        this.state = state;
    }

    /**
     * This method is supposed to update the background image, but it doesn't do anything becuase the bakcground
     * image is static.
     * @param elapsedTime The time that has passed since the last update.
     */
    @Override
    public void update(double elapsedTime) {

    }

    /**
     * This method draws the background image.
     * @param g The graphics object.
     */
    @Override
    public void draw(Graphics g) {
        try {
            g.drawImage(control.loadImage("background.png"), 0, 0, null);
        } catch (Exception e) {
            System.out.println("Could not load background File");
        }
    }
}

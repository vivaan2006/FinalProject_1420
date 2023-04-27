/**
 * The Background class is responsible for drawing the background. It is an Animatable object and never moves.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Background extends GameObject {
    private Control control;
    private GameState state;

    public Background(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double timeElapsed) {
    }

    /**
     * Draws the background image.
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        BufferedImage background = control.getImage("background.png"); // Draws the Image
        g.drawImage(background, 0, 0, null);
    }

    @Override
    public Point getLocation() {
        return null;
    }
}

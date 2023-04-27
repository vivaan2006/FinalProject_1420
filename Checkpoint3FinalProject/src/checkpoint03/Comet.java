/**
 * The Comet class is responsible for drawing the comet. It is an Animatable object and moves along the path.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Comet extends GameObject implements Targetable {
    private Control control;
    private GameState state;
    private double pathPercentage;

    public Comet(Control control, GameState state) {
        this.control = control;
        this.state = state;
        pathPercentage = 0.0;
    }

    /**
     * Updates the comet's position along the path.
     * @param timeElapsed the time elapsed since the last update
     */
    @Override
    public void update(double timeElapsed) {
        pathPercentage += (1.00 / 6.0) * timeElapsed; // move 1/6 of the path per second
        if (pathPercentage >= 1.0) {
            state.adjustCityCount(-1); // decrease city count
            this.hasExpired = true; // remove comet
        }
    }

    /**
     * Draws the comet at its current location.
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        BufferedImage comet = control.getImage("comet.png"); // load comet image
        Point loc = control.getPath().convertToCoordinates(pathPercentage); // get comet location
        g.drawImage(comet, loc.x - comet.getWidth() / 2, loc.y - comet.getHeight() / 2, null);
    }

    @Override
    public Point getLocation() {
        return null;
    }

    /**
     * Returns the location of the comet at a future time.
     * @param additionalTime the amount of time to look ahead
     * @return the location of the comet at a future time
     */
    public Point getFutureLocation(double additionalTime) {
        // move 1/6 of the path per second
        return control.getPath().convertToCoordinates(pathPercentage + (1.00 / 6.0) * additionalTime);
    }

    @Override
    public void takeDamage(double damage) {

    }
}

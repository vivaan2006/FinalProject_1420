/**
 * The Asteroid class is responsible for creating the asteroid and updating it. This class implements the Animatable
 * and Targetable interfaces allowing it to update and draw the asteroid and also be targeted by the towers.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Asteroid extends GameObject implements Targetable {
    private Control control;
    private GameState state;
    private double pathPercentage;

    public Asteroid(Control control, GameState state) {
        this.control = control;
        this.state = state;
        pathPercentage = 0.0;
    }

    /**
     * This method updates the asteroid's location and checks if it has reached the end of the path. If it has been
     * expired, the asteroid is removed from the game.
     * @param timeElapsed the time elapsed since the last update
     */
    @Override
    public void update(double timeElapsed) {
        pathPercentage += (1.00 / 10.0) * timeElapsed; // move 1/10 of the path per second
        if (pathPercentage >= 1.0) {
            state.adjustCityCount(-1); // decrease city count
            this.hasExpired = true; // remove asteroid
        }
    }

    /**
     * This method draws the asteroid at its current location.
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
        BufferedImage asteroid = control.getImage("asteroid.png"); // load asteroid image
        Point loc = control.getPath().convertToCoordinates(pathPercentage); // get asteroid location
        g.drawImage(asteroid, loc.x - asteroid.getWidth() / 2, loc.y - asteroid.getHeight() / 2, null);
    }

    /**
     * This method returns the location of the asteroid.
     * @return none
     */
    @Override
    public Point getLocation() {
        return null;
    }

    /**
     * This method returns the location of the asteroid at a future time.
     * @param additionalTime the amount of time to look ahead
     * @return the location of the asteroid at a future time
     */
    @Override
    public Point getFutureLocation(double additionalTime) {
        return control.getPath().convertToCoordinates(pathPercentage + (1.00 / 10.0) * additionalTime);
    }

    /**
     * This method returns the amount of damage the asteroid will do to the city.
     * @param damage none - unused
     */
    @Override
    public void takeDamage(double damage) {

    }
}

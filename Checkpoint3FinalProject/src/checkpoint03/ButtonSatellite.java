/**
 * This class represents a satellite button. When clicked, it will add a satellite to the game.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class ButtonSatellite extends GameObject implements Clickable {
    private Control control;
    private GameState state;

    public ButtonSatellite(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double timeElapsed) {

    }

    /**
     * Draws the satellite button.
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
//         g.setColor(Color.BLACK);
//         g.fillRoundRect(630, 400, 75, 75, 10, 10);
//         g.setColor(Color.GRAY);
//         g.fillRoundRect(632, 402, 71, 71, 10, 10);

        g.drawImage(control.getImage("gunTower.png.tiff"), 640, 410, null); // Draws the Image
    }

    @Override
    public Point getLocation() {
        return null;
    }

    /**
     * If the button is clicked, it will add a satellite to the game. Under the condition that the player has enough
     * money.
     * @return true if the button is clicked, false otherwise
     */
    @Override
    public boolean consumeClick() {
        Point mouseLoc = state.getMouseLoc();
        if (mouseLoc.x >= 630 && mouseLoc.x <= 630 + 75 &&
                mouseLoc.y >= 400 && mouseLoc.y <= 400 + 75) // If the mouse is clicked on the button
        {
            if(state.getMoney() < 100) {
                return false;
            }else{
                state.adjustMoney(-100);
                state.addGameObject(new Satellite(control, state)); // Adds a satellite to the game
            }
            return true;
        }

        return false;
    }
}

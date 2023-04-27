/**
 * The ButtonSatellite2 class is responsible for drawing the button for the satellite2.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class ButtonSatellite2 extends GameObject implements Clickable {
    private Control control;
    private GameState state;

    public ButtonSatellite2(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double timeElapsed) {

    }

    /**
     * Draws the button for the satellite2.
     * @param g the graphics object to draw on
     */
    @Override
    public void draw(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRoundRect(630, 400, 75, 75, 10, 10);
//        g.setColor(Color.GRAY);
//        g.fillRoundRect(632, 402, 71, 71, 10, 10);

        g.drawImage(control.getImage("comet.png"), 740, 410, null); // Draws the Image
    }

    @Override
    public Point getLocation() {
        return null;
    }

    /**
     * If the button is clicked, it will add a satellite2 to the game. Under the condition that the player has enough
     * money.
     * @return true if the button is clicked, false otherwise
     */
    @Override
    public boolean consumeClick() {
        Point mouseLoc = state.getMouseLoc();
        if (mouseLoc.x >= 730 && mouseLoc.x <= 730 + 75 &&
                mouseLoc.y >= 400 && mouseLoc.y <= 400 + 75) // If the mouse is clicked on the button
        {
            if(state.getMoney() < 100) {
                return false;
            }else{
                state.adjustMoney(-100);
                state.addGameObject(new Satellite(control, state)); // Adds a satellite2 to the game
            }
            return true;
        }
        return false;
    }
}

/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class RestartButton  extends GameObject implements Clickable {
    private Control control;
    private GameState state;

    public RestartButton(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }
    @Override
    public boolean consumeClick() {
        Point loc = state.getMouseLoc();
        if(loc.x > 630 && loc.x < 705 && loc.y > 400 && loc.y < 475)
        {
            System.out.println("Restarting game");
            return true;
        }
        return false;
    }

    @Override
    public void update(double timeElapsed) {

    }

    @Override
    public void draw(Graphics g) {

         g.setColor(Color.BLACK);
         g.fillRoundRect(630, 400, 75, 75, 10, 10);
         g.setColor(Color.GRAY);
         g.fillRoundRect(632, 402, 71, 71, 10, 10);
    }

    @Override
    public Point getLocation() {
        return null;
    }
}

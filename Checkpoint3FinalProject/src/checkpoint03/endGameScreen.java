/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class endGameScreen extends GameObject
{
    private Control control;
    private GameState state;

    public endGameScreen(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }

    @Override
    public void update(double timeElapsed) {
        // nothing
    }


    @Override
    public void draw(Graphics g) {
        RestartButton restartButton = new RestartButton(control, state);
        g.setColor(Color.WHITE);
        g.fillRect(600, 0, 200, 600);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Game Ended", 630, 50);
        g.drawString("Points: " + state.getScore(), 630, 100);
        g.drawString("Money: " + state.getMoney(), 630, 150);
        g.drawString("Cities: " + state.getCityCount(), 630, 200);
//        restartButton.draw(g);

    }

    @Override
    public Point getLocation() {
        return null;
    }
}

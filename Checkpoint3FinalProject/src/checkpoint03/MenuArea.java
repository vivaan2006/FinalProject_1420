package checkpoint03;

import java.awt.*;

public class MenuArea extends GameObject {
    private Control control;
    private GameState state;

    public MenuArea(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
    }
    //tower1, below 2 method
    public void setUpMenuArea() {
        state.addGameObject(new ButtonSatellite(control, state));
    }
    public void setUpMenuObject2() {
        state.addGameObject(new ButtonSatellite2(control, state));
    }

    @Override
    public void update(double timeElapsed) {
        // nothing
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(600, 0, 200, 600);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Space Defence", 630, 50);
        g.drawString("Points: " + state.getCityCount(), 630, 100);
    }

    @Override
    public Point getLocation() {
        return null;
    }
}

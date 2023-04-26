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

    @Override
    public void draw(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRoundRect(630, 400, 75, 75, 10, 10);
//        g.setColor(Color.GRAY);
//        g.fillRoundRect(632, 402, 71, 71, 10, 10);

        g.drawImage(control.getImage("comet.png"), 740, 410, null);
    }

    @Override
    public Point getLocation() {
        return null;
    }

    @Override
    public boolean consumeClick() {
        Point mouseLoc = state.getMouseLoc();
        if (mouseLoc.x >= 730 && mouseLoc.x <= 730 + 75 &&
                mouseLoc.y >= 400 && mouseLoc.y <= 400 + 75) {
            state.addGameObject(new Satellite(control, state));
            return true;
        }
        return false;
    }
}

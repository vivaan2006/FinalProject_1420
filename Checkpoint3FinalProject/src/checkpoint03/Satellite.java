/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class Satellite extends GameObject implements Clickable {
    private Control control;
    private GameState state;
    private Point location;
    private boolean isMoving;
    private double cooldownTime;  // seconds
    private boolean isSelected;
    private boolean isTower; // added flag to differentiate between tower and comet
    public Satellite(Control control, GameState state) {
        super();
        this.control = control;
        this.state = state;
        isMoving = true;
        isSelected = false;
        isTower = true; // tower is initially selected
    }

    @Override
    public void update(double timeElapsed) {
        cooldownTime -= timeElapsed;

        if (isMoving)
            location = state.getMouseLoc();
        else {
            // Target an entity
            double futureTime = 0.75;
            Targetable t = state.getNearestTargetable(location, futureTime);

            if (t != null) {
                Point targetLoc = t.getFutureLocation(futureTime);
                double dist = targetLoc.distance(location);

                if (dist < 110 && cooldownTime <= 0) {
                    // Fire a shot from here to the target
                    cooldownTime = 0.8;
                    state.addGameObject(new PhotonTorpedo(state, control, location, targetLoc));
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (location != null) {
            if (isTower) {
                drawCenteredImage(g, control.getImage("gunTower.png.tiff"), location.x, location.y, 1);
            } else {
                drawCenteredImage(g, control.getImage("comet.png"), location.x, location.y, 1);
            }
        }

        if (!isMoving && location != null) {
            Targetable t = state.getNearestTargetable(location, 2);

            if (t != null) {
                Point p = t.getFutureLocation(2);
                g.setColor(new Color(1.0f, 0.0f, 1.0f));
                g.drawLine(location.x, location.y, p.x, p.y);

                Point q = t.getFutureLocation(0);
                g.setColor(new Color(0.0f, 1.0f, 0.0f));
                g.drawLine(location.x, location.y, q.x, q.y);
            }
        }
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public boolean consumeClick() {
        if (isMoving) {
            Point mouseLoc = state.getMouseLoc();
            if (mouseLoc.x < 0 || mouseLoc.y < 0 || mouseLoc.x > 600 || mouseLoc.y > 600)
                hasExpired = true;
            isMoving = false;
            isSelected = true;
            isTower = mouseLoc.y < 300; // set flag based on where user clicked
            return true;
        }

        return false;
    }
}

/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public class PhotonTorpedo extends GameObject {
    GameState state;
    Control control;
    Point startLoc, endLoc;
    Path myPath;
    double percentage;

    public PhotonTorpedo(GameState state, Control control, Point location, Point targetLoc) {
        this.state = state;
        this.control = control;
        this.startLoc = location;
        this.endLoc = targetLoc;
        myPath = new Path();
        myPath.add(startLoc.x, startLoc.y);
        myPath.add(endLoc.x, endLoc.y);
        percentage = 0;
    }

    @Override
    public void update(double timeElapsed) {
        percentage += (1.00 / 0.75) * timeElapsed;

        Point pos = myPath.convertToCoordinates(percentage);
        Targetable t = state.getNearestTargetable(pos, 0);
        if (t != null) {
            Point tLoc = t.getFutureLocation(0);
            if (tLoc.distance(pos) < 20) {
                state.adjustMoney(10);
                state.adjustScore(100);
                this.hasExpired = true;
                ((GameObject) t).hasExpired = true;
            }
        }

        if (percentage >= 1.0)
            hasExpired = true;
    }

    @Override
    public void draw(Graphics g) {
        Point pos = myPath.convertToCoordinates(percentage);
        drawCenteredImage(g, control.getImage("photon.png"), pos.x, pos.y, 1);
    }

    @Override
    public Point getLocation() {
        return null;
    }
}

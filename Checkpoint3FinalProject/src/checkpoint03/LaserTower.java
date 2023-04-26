package checkpoint03;

import java.awt.*;

public class LaserTower extends GameObject implements Targetable {
    private GameState state;
    private Point location;
    private double range;
    private double cooldownTime;
    private double damage;

    public LaserTower(GameState state, Point location) {
        super();
        this.state = state;
        this.location = location;
        this.range = 200;
        this.cooldownTime = 0.5;
        this.damage = 10;
    }

    @Override
    public void update(double timeElapsed) {
        cooldownTime -= timeElapsed;

        if (cooldownTime <= 0) {
            Targetable target = state.getNearestTargetable(location, range);
            if (target != null) {
                Point targetLoc = target.getFutureLocation(cooldownTime);
                double dist = targetLoc.distance(location);

                if (dist <= range) {
                    target.takeDamage(damage);
                    cooldownTime = 0.5;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        Control control = new Control();
        drawCenteredImage(g, control.getImage("comet.png"), location.x, location.y, 1);
    }

    @Override
    public Point getLocation() {
        return location;
    }

    @Override
    public Point getFutureLocation(double timeElapsed) {
        return location;
    }

    @Override
    public void takeDamage(double damage) {

    }

}

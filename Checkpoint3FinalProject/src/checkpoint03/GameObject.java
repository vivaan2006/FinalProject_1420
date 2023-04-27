/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.awt.image.BufferedImage;

abstract public class GameObject implements Animatable {
    protected boolean hasExpired;

    public GameObject() {
        hasExpired = false;
    }

    public boolean hasExpired() {
        return hasExpired;
    }

    protected void drawCenteredImage(Graphics k, BufferedImage image, int x, int y, double scale) {
        int width = (int) (image.getWidth() * scale);
        int height = (int) (image.getHeight() * scale);
        int nx = x - width / 2;
        int ny = y - height / 2;

        k.drawImage(image, nx, ny, width, height, null);
    }

    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public abstract void update(double timeElapsed);
    public abstract void draw(Graphics g);

    public abstract Point getLocation();
}

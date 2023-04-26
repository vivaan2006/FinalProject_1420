/**
 * This class implements this cool idea: This is an interface that consists of the animation and updates the points
 * of the objects.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import java.awt.*;

public interface Animatable {
    public void update(double elapsedTime);

    public void draw(Graphics g);
}

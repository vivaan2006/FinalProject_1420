/**
 * The Animatable interface is responsible for updating and drawing the objects.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */
package checkpoint03;

import java.awt.*;

public interface Animatable {
    void update(double timeElapsed);

    void draw(Graphics g);
}

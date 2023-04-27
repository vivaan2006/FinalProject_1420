/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;

public interface Targetable {
    Point getFutureLocation(double additionalTime);

    void takeDamage(double damage);
}

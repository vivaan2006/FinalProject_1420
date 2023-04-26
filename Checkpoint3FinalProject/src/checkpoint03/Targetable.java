package checkpoint03;

import java.awt.*;

public interface Targetable {
    Point getFutureLocation(double additionalTime);

    void takeDamage(double damage);
}

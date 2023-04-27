/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontLoader {
    public static Font loadFont(String filename, int size) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, new File(filename)).deriveFont(Font.PLAIN, size);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return font;
    }
}

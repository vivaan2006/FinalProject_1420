package checkpoint03;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class View extends JPanel {
    // Fields

    private BufferedImage background;
    private Control control;
    private GameState state;

    // Constructor

    public View(Control control, GameState state) {
        this.control = control;
        this.state = state;

        JFrame frame = new JFrame("");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the size of this panel.
        Dimension d = new Dimension(800, 600);
        this.setMinimumSize(d);
        this.setPreferredSize(d);
        this.setMaximumSize(d);
        frame.setContentPane(this);
        frame.pack();
        frame.setLocationRelativeTo(null);  // Centers window
        frame.setVisible(true);
    }

    // Paint
    public void paint(Graphics g) {
        for (Animatable a : state.getCurrentObjects())
            a.draw(g);
        if (state.getCityCount() == 0) {
            Animatable a = new endGameScreen(control, state);
            a.draw(g);
        }
    }
}

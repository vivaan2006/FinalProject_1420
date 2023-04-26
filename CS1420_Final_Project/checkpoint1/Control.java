/**
 * This class implements this cool idea: This class is responsible for running the game and allowing the spaceship
 * to move across the screen.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.SortedMap;

public class Control implements Runnable, ActionListener {

    private GameState state;
    private View view;
    private Path path;
    ArrayList<Point> points = new ArrayList<Point>();

    /**
     * This is the constructor for the Control class. It creates a new GameState object, a new View object,
     * and a new Path object.
     */
    public Control() {
        SwingUtilities.invokeLater(this);
    }

    /**
     * This method is responsible for running the game.
     */
    @Override
    public void run() {
        System.out.println("TowerDefense game start");
        Scanner pathScanner = null;
        try {
            pathScanner = new Scanner(new File("pathTest.txt"));
            path = new Path(pathScanner);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be loaded.");
        }
        state = new GameState();
        view = new View(this, state);
        // This is the main game loop.  It will run forever. It is responsible for creating the next frame.
        state.startFrame();  // Prepares the creation of the 'next' frame
        state.addGameObject(new Background(this, state));  // Add one background object to our list
        state.addGameObject(new Asteroid(this, state));  // Add one asteroid object to our list
        state.addGameObject(new Comet(this, state)); // Add one comet object to our list
        state.finishFrame();    // Mark the next frame as ready
        view.repaint();       // Draw it.
        Timer t = new Timer(16, this);  // Triggers every 16 milliseconds, reports actions to 'this' object.
        t.start();
    }

    /**
     * This method is responsible for loading the image.
     * @param filename The name of the file
     * @return The buffered image
     * @throws IOException If the file cannot be loaded
     */
    public BufferedImage loadImage(String filename) throws IOException {
        BufferedImage background = javax.imageio.ImageIO.read(new File(filename));
        return background;
    }

    /**
     * This method is responsible for returning the path.
     * @return The path
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * This method is responsible for processing the event.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        state.startFrame();
        for (Animatable a : state.getCurrentObjects())
            a.update(0);
        state.finishFrame();
        view.repaint();
    }
}

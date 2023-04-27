/**
 * This Class controls the game and is responsible for updating the game state and drawing the game.
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Control implements Runnable, ActionListener, MouseListener, MouseMotionListener {
    private View view;
    private GameState state;
    private Timer timer;
    private Path path;
    private TreeMap<String, BufferedImage> imageCache;
    public boolean mousePressed;

    private Map<String, BufferedImage> imageMap;
    private Map<String, Font> fontMap;

    public Control() {
        imageMap = new HashMap<String, BufferedImage>();
        fontMap = new HashMap<String, Font>();
    }

    /**
     * This method is responsible for loading images.
     * @param name the name of the image
     * @param filename the filename of the image
     */
    public void loadImage(String name, String filename) {
        BufferedImage image = ImageLoader.loadImage(filename);
        imageMap.put(name, image);
    }

    /**
     * This method is responsible for loading fonts.
     * @param name THe name of tjhe font
     * @param filename The filename of the font
     * @param size The size of the font
     */
    public void loadFont(String name, String filename, int size) {
        Font font = FontLoader.loadFont(filename, size);
        fontMap.put(name, font);
    }

    /**
     * This method is responsible for getting the image.
     * @param name The name of the image
     * @return The image
     */
    public Font getFont(String name) {
        return fontMap.get(name);
    }

    // Adds the PhotonTower class to the game state
    public void addPhotonTower(Point location, GameState state) {
        //state.addGameObject(new ButtonPhotonTorpedo(this, state));
    }

    /**
     * This method is responsible for getting the image.
     */
    public void run() {
        imageCache = new TreeMap<String, BufferedImage>();
        mousePressed = false;

        path = loadPath("path.txt"); // Load the path from the file

        state = new GameState(); // Create the game state
        view = new View(this, state); // Create the view
        view.addMouseListener(this);
        view.addMouseMotionListener(this);

        state.startFrame(); // Prepares the creation of the 'next' frame

        MenuArea menuArea = new MenuArea(this, state); // Creates the menu area
        // Adds the game objects to the game state
        state.addGameObject(new Background(this, state));
        state.addGameObject(menuArea);
        state.addGameObject(new GeneratorAsteroid(this, state));
        state.addGameObject(new GeneratorComet(this, state));

        menuArea.setUpMenuArea();
        menuArea.setUpMenuObject2();

        state.finishFrame(); // Finishes the creation of the 'next' frame

        timer = new Timer(16, this); // Create a timer for 60 fps animation
        timer.start();
    }

    // Loads an image

    // Load the background image.

    /**
     * This method is responsible for loading the image.
     * @param filename The filename of the image
     * @return The image
     */
    public BufferedImage loadImage(String filename) {
        System.out.println("Loading " + filename);
        try {
            return javax.imageio.ImageIO.read(new File(filename));
        } catch (IOException e) {
            System.out.println("Could not read " + filename);
            return null;
        }
    }

    /**
     * This method is responsible for getting the image.
     * @param filename The filename of the image
     * @return The image
     */
    public BufferedImage getImage(String filename) {
        if (!imageCache.containsKey(filename)) {
            BufferedImage b = loadImage(filename);
            imageCache.put(filename, b);
        }

        return imageCache.get(filename); // Return the image
    }

    // Load a path

    /**
     * This method is responsible for loading the path.
     * @param filename The filename of the path
     * @return The path
     */
    private Path loadPath(String filename) {
        try {
            Scanner in = new Scanner(new File(filename)); // Open the file
            Path p = new Path(in); // Create the path
            in.close();
            return p;
        } catch (IOException e) {
            System.out.println("Could not load the path.");

            return null;
        }
    }

    // Get the path

    /**
     * This method is responsible for returning the path.
     * @return The path
     */
    public Path getPath() {
        return path;
    }

    /**
     * This method is responsible for getting the image.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Main update loop

        if(state.getCityCount() > 0){
            state.startFrame();
            // Update all objects
            for (Animatable a : state.getCurrentObjects())
                a.update(state.getElapsedTime());

            if (mousePressed)
                // Check for clicks
                for (int pos = state.getCurrentObjects().size() - 1;
                     pos >= 0; pos--) {
                    Animatable a = state.getCurrentObjects().get(pos);
                    // Check if the object is clickable
                    if (a instanceof Clickable) {
                        Clickable c = (Clickable) a;
                        if (c.consumeClick())
                            break;
                    }
                }

            mousePressed = false;

            state.finishFrame();

            view.repaint();
        }else{
//            state.addGameObject(new endGameScreen(this, state));
//            view.repaint();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * This method is responsible for getting the image.
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        state.setMouseLocation(new Point(e.getX(), e.getY()));
        mousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * This method returns the mouse locations
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        state.setMouseLocation(new Point(e.getX(), e.getY()));
    }

    /**
     * This method returns the mouse locations
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        state.setMouseLocation(new Point(e.getX(), e.getY()));
    }
}

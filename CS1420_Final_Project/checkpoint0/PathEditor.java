package checkpoint0;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PathEditor extends JPanel implements Runnable, MouseListener, MouseMotionListener, ActionListener {
    // declaring and initialzing all the variables used.
    private BufferedImage background;
    private JMenuBar menuBar;
    private ArrayList<Point> points = new ArrayList<>();
    private Point previousPoint;

    /**
     * This method creates the JFrame and the menu bar and the menu items and the action listeners for the menu items.
     * It also sets things like the size, location and visibility of the frame.
     */
    @Override
    public void run() {
        JFrame f = new JFrame("BTD Battles");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem loadMenuItem = new JMenuItem("Load");
        loadMenuItem.addActionListener(this);
        JMenuItem clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(this);
        JMenuItem saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(this);
        fileMenu.add(loadMenuItem);
        fileMenu.add(clearMenuItem);
        fileMenu.add(saveMenuItem);
        f.setJMenuBar(menuBar);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        // 'this' is our Runnable object, but it is also our JPanel.
        f.setContentPane(this);
        this.setMinimumSize(new Dimension(600, 600));
        this.setPreferredSize(this.getMinimumSize());
        try {
            background = javax.imageio.ImageIO.read(new File("background.png"));
        } catch (IOException e) {
            // Do something sensible here.
            e.printStackTrace();
        }
        f.pack();
        f.setLocationRelativeTo(null);  // Centers window
        f.setVisible(true);
    }

    /**
     * This method allows the users drawing to load on the screen and it defines those points, and adds size and color to the line
     *
     * @param g the point that is used to paint the line drawn.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Serif", Font.BOLD, 100));
        g.drawImage(background, 0, 0, null);

        Path path = new Path();
        for (Point p : points) {
            path.add(p.x, p.y);
        }
        path.draw(g);
    }

    /**
     * This method consists of the events that happens after the mouse is clicked, here nothing
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    /**
     * This method consists of the events that happens after the mouse is pressed, here it adds the previous points to the array list and repaints the screen
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        previousPoint = e.getPoint();
        points.add(previousPoint);
        repaint();
    }

    /**
     * This method consists of the events that happens after the mouse is clicked, here it sets the last point to null to start a new path
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        previousPoint = null;
    }

    /**
     * // This method consists of the events that happens after the mouse enters something, here nothing
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * This method consists of the events that happens after the mouse is exited from frame, here nothing
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * This method consists of the events that happens after the mouse is dragged, here it adds the new points to the array list and repaints the screen
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        Point newPoint = e.getPoint();
        points.add(newPoint);
        repaint();
        previousPoint = newPoint;
    }

    /**
     * This method consists of the events that happens after the mouse is moved, here nothing
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * This is the main method that runs the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new PathEditor());
    }

    /**
     * This method consists of the events that happens after the action is performed, here it loads the file, clears the screen and saves the file
     * It then provides the actions that allow those menuItems to work.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String request = e.getActionCommand();
        if (request.equals("Clear")) {
            points.clear();
            repaint();
        } else if (request.equals("Load")) {
            JFileChooser fileChooser = new JFileChooser();
            int fileValue = fileChooser.showOpenDialog(this);
            if (fileValue == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                points.clear();
                try {
                    Scanner read = new Scanner(file);
                    while (read.hasNextLine()) {
                        String line = read.nextLine();
                        String[] parts = line.split(",");
                        int x = Integer.parseInt(parts[0]);
                        int y = Integer.parseInt(parts[1]);
                        points.add(new Point(x, y));
                    }
                    read.close();
                    repaint();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        } else if (request.equals("Save")) {
            JFileChooser fileChooser = new JFileChooser();
            int fileValue = fileChooser.showSaveDialog(this);
            if (fileValue == JFileChooser.APPROVE_OPTION) {
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter(file);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                for (Point point : points) {
                    writer.println(point.x + "," + point.y);
                }
                writer.close();

            }
        }

    }
}
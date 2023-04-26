/**
 * This class implements this cool idea: This class creates the path of the overall program and draws it.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Path {
    private ArrayList<Point> pathPoints;
    private int size;

    /**
     * This method creates the path of the overall program and draws it.
     */
    public Path() {
        pathPoints = new ArrayList<Point>();
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param in This is the scanner that reads the file.
     */
    public Path(Scanner in) {
        pathPoints = new ArrayList<Point>();
        this.size = in.nextInt();
        for (int i = 0; i < size; i++) {
            pathPoints.add(new Point(in.nextInt(), in.nextInt()));
        }
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @return This returns the number of points in the path.
     */
    public int getPointCount() {
        return pathPoints.size();
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param n This is the number of points in the path.
     * @return This returns the x coordinate of the point.
     */
    public int getX(int n) {
        return pathPoints.get(n).x;
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param n This is the number of points in the path.
     * @return  This returns the y coordinate of the point.
     */
    public int getY(int n) {
        return pathPoints.get(n).y;
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param n This is the number of points in the path.
     * @return This returns the point.
     */
    public Point getPoint(int n) {
        return pathPoints.get(n);
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param x This is the x coordinate of the point.
     * @param y This is the y coordinate of the point.
     */
    public void add(int x, int y) {
        pathPoints.add(new Point(x, y));
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @return This returns the path.
     */
    public String toString() {
        String result = "" + getPointCount() + "\n"; //loop through points
        for (Point p : pathPoints)
            result += p.x + " " + p.y + "\n";
        return result;
    }

    /**
     * This method creates the path of the overall program and draws it.
     * @param g This is the graphics object.
     */
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        Point last = null;
        for (Point p : pathPoints) {
            if (last != null) {
                g.drawLine(last.x, last.y, p.x, p.y); //drawing the lines 
                g.drawLine(last.x, last.y + 1, p.x, p.y + 1);
                g.drawLine(last.x + 1, last.y, p.x + 1, p.y);
                g.drawLine(last.x + 1, last.y + 1, p.x + 1, p.y + 1);
            }
            last = p;
        }
    }

    /**
     * Given a percentage between 0% and 100%, this method calculates
     * the location along the path that is exactly this percentage
     * along the path. The location is returned in a Point object
     * (integer x and y), and the location is a screen coordinate.
     * <p>
     * If the percentage is less than 0%, the starting position is
     * returned. If the percentage is greater than 100%, the final
     * position is returned.
     * <p>
     * Callers must not change the x or y coordinates of any returned
     * point object (or the caller could be changing the path).
     *
     * @param percentTraveled a distance along the path
     * @return the screen coordinate of this position along the path
     */
    public Point convertToCoordinates(double percentTraveled) {
        // initial conditions
        if (percentTraveled < 0.0) {
            return pathPoints.get(0);
        } else if (percentTraveled > 1.0) {
            return pathPoints.get(pathPoints.size() - 1);
        }
        // find the total length of the path and stores each segment's length
        double totalPathLength = 0.0;
        double[] segmentLengths = new double[pathPoints.size() - 1];
        for (int i = 0; i < segmentLengths.length; i++) {
            Point p1 = pathPoints.get(i);
            Point p2 = pathPoints.get(i + 1);
            double segmentLength = p1.distance(p2);
            segmentLengths[i] = segmentLength;
            totalPathLength += segmentLength;
        }
        // finds the total distance traveled and the needed distance traveled and does calculations to find the x and y
        // point of that distance.
        double distanceTraveled = totalPathLength * percentTraveled;
        double segmentStartDistance = 0.0;
        int currentSegment = 0;
        while (currentSegment < segmentLengths.length && distanceTraveled > segmentStartDistance + segmentLengths[currentSegment]) {
            segmentStartDistance += segmentLengths[currentSegment];
            currentSegment++;
        }
        // does percent calculations on the specified segemnt.
        double segmentPercent = 0.0;
        if (currentSegment < segmentLengths.length) {
            segmentPercent = (distanceTraveled - segmentStartDistance) / segmentLengths[currentSegment];
        } else {
            segmentPercent = 1.0;
        }

        Point p1 = pathPoints.get(currentSegment);
        Point p2 = pathPoints.get(currentSegment + 1);
        double x = (1.0 - segmentPercent) * p1.x + segmentPercent * p2.x;
        double y = (1.0 - segmentPercent) * p1.y + segmentPercent * p2.y;

        return new Point((int) x, (int) y);
    }

}
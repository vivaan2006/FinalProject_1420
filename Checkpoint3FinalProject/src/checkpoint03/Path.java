/**
 *
 * @Author: Wallace McCarthy and Vivaan Rajesh
 * @Version: April 27, 2023
 */

package checkpoint03;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Path {
    private ArrayList<Point> pathPoints;

    private double totalPathLength;  // Hack - only computed on construction

    public Path() {
        pathPoints = new ArrayList<Point>();
    }

    public Path(Scanner in) {
        pathPoints = new ArrayList<Point>();

        int size = in.nextInt();
        for (int i = 0; i < size; i++)
            pathPoints.add(new Point(in.nextInt(), in.nextInt()));

        // Compute the path length.

        totalPathLength = 0;
        for (int i = 1; i < pathPoints.size(); i++) {
            Point start = pathPoints.get(i - 1);  // Extract segment start/end
            Point end = pathPoints.get(i);

            totalPathLength += start.distance(end);
        }
    }

    public int getPointCount() {
        return pathPoints.size();
    }

    public int getX(int n) {
        return pathPoints.get(n).x;
    }

    public int getY(int n) {
        return pathPoints.get(n).y;
    }

    public void add(int x, int y) {
        pathPoints.add(new Point(x, y));

        totalPathLength = 0;
        for (int i = 1; i < pathPoints.size(); i++) {
            Point start = pathPoints.get(i - 1);  // Extract segment start/end
            Point end = pathPoints.get(i);

            totalPathLength += start.distance(end);
        }
    }

    public String toString() {
        String result = "" + getPointCount() + "\n";
        for (Point p : pathPoints)
            result += p.x + " " + p.y + "\n";
        return result;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        Point last = null;
        for (Point p : pathPoints) {
            if (last != null) {
                g.drawLine(last.x, last.y, p.x, p.y);
                g.drawLine(last.x, last.y + 1, p.x, p.y + 1);
                g.drawLine(last.x + 1, last.y, p.x + 1, p.y);
                g.drawLine(last.x + 1, last.y + 1, p.x + 1, p.y + 1);
            }
            last = p;
        }

    }

    /**
     * Returns a Point, or x, y coordinates, of some position along this
     * path.  The position is given as a percentage.  0.0 means the
     * first position on the path, and 1.0 means the last position on the
     * path.
     *
     * @param percentage a distance along the path, as a percentage
     * @return the x, y coordinate (as a Point object) of this position on the path
     */
    public Point convertToCoordinates(double percentage) {
        if (pathPoints.size() < 2)  // empty path - return 0,0
            return new Point(0, 0);
        if (percentage <= 0.0)
            return new Point(pathPoints.get(0));  // Make a new Point object that copies ours

        if (percentage >= 1.0)
            return new Point(pathPoints.get(pathPoints.size() - 1));  // Make a new Point object that copies ours

        // Convert the percentage to a distance.

        double distanceToTravel = totalPathLength * percentage;
        Point start = pathPoints.get(0);  // Segment points for the current segment.
        Point end = pathPoints.get(0);
        double totalDistance = 0;   // Accumulated distance
        double segmentLength = 1;   // Length of the current segment.

        for (int i = 1; i < pathPoints.size(); i++) {
            // Extract segment start/end points
            start = pathPoints.get(i - 1);
            end = pathPoints.get(i);

            // Compute the length of this segment, combine it with the total.

            segmentLength = start.distance(end);
            totalDistance = totalDistance + segmentLength;
            if (totalDistance > distanceToTravel)
                break;
        }
        double excessDistance = totalDistance - distanceToTravel;
        double segmentPercentage = excessDistance / segmentLength;   // Will be between [0..1]
        double targetX = (segmentPercentage) * start.x + (1 - segmentPercentage) * end.x;
        double targetY = (segmentPercentage) * start.y + (1 - segmentPercentage) * end.y;

        Point result = new Point((int) targetX, (int) targetY);
        return result;
    }

}

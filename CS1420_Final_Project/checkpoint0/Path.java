package checkpoint0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Path {
    private ArrayList<Point> pathPoints;

    public Path() {
        pathPoints = new ArrayList<Point>();
    }

    public Path(Scanner in) {
        pathPoints = new ArrayList<Point>();
        int size = in.nextInt();
        for (int i = 0; i < size; i++)
            pathPoints.add(new Point(in.nextInt(), in.nextInt()));
    }

    public int getPointCount() {
        return pathPoints.size();
    }

    public int getX(int n) {
        return pathPoints.get(n).x;
    }

    public int getY(int n) {
        return pathPoints.get(n).x;
    }

    public Point getPoint(int n) {
        return pathPoints.get(n);
    }

    public void add(int x, int y) {
        pathPoints.add(new Point(x, y));
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
}
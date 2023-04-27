package checkpoint03;

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class GameState {
    // Fields

    private List<GameObject> currentFrameObjects;
    private List<GameObject> nextFrameObjects;
    private int cityCount;



    private int money = 100;
    private Point mouseLocation;

    private long lastFrameStartTime;
    private double elapsedTime;
    private int score;
    public int getScore() {
        return score;
    }
    public void adjustScore(int amount) {
        score += amount;
    }

    public GameState() {
        currentFrameObjects = new ArrayList<GameObject>();
        cityCount = 1;

        lastFrameStartTime = System.currentTimeMillis();
        elapsedTime = 0;
    }

    public int getCityCount() {
        return cityCount;
    }

    public void adjustCityCount(int amount) {
        cityCount += amount;
    }

    public Point getMouseLoc() {
        return mouseLocation;
    }

    public void setMouseLocation(Point mouseLoc) {
        this.mouseLocation = mouseLoc;
    }

    public List<GameObject> getCurrentObjects() {
        return currentFrameObjects;
    }

    public double getElapsedTime() {
        return elapsedTime;
    }

    public void startFrame() {
        // Calculate how much time has elapsed since the previous start frame
        long currentFrameStartTime = System.currentTimeMillis();
        elapsedTime = (currentFrameStartTime - lastFrameStartTime) / 1000.0;
        lastFrameStartTime = currentFrameStartTime;

        nextFrameObjects = new ArrayList<GameObject>();    // Creates empty list
        nextFrameObjects.addAll(currentFrameObjects);      // Add all the current ones to the new list.
    }


    public void addGameObject(GameObject a) {
        nextFrameObjects.add(a);
    }

    public void finishFrame() {
        // Remove any current objects that are expired from the next fram

        for (GameObject go : currentFrameObjects)
            if (go.hasExpired())
                nextFrameObjects.remove(go);

        currentFrameObjects = nextFrameObjects;
        nextFrameObjects = null;  // This makes it clear there is only a current list now.
    }

    /**
     * Returns the amount of money the player has.
     * @return the amount of money the player has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Adjusts the amount of money the player has by the given amount.
     * @param money the amount to adjust the player's money by.
     */
    public void adjustMoney(int money) {
        this.money += money;
    }

    /**
     * Finds the closest targetable object to the given location
     * from the current frame.  If there are no targetable objects,
     * null is returned.
     *
     * @param loc
     * @param additionalTime
     * @return
     */
    public Targetable getNearestTargetable(Point loc, double additionalTime) {
        Targetable closest = null;

        for (GameObject go : currentFrameObjects) {
            if (!(go instanceof Targetable))
                continue;

            Targetable t = (Targetable) go;

            if (go.hasExpired())
                continue;

            if (closest == null) {
                closest = t;
                continue;
            }

            double closestDist = loc.distance(closest.getFutureLocation(additionalTime));
            double tDist = loc.distance(t.getFutureLocation(additionalTime));

            if (tDist < closestDist)
                closest = t;
        }

        return closest;
    }
}

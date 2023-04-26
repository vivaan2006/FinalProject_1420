/**
 * This class implements this cool idea: This class starts the frame of the program, and animates the objects.
 *
 * @authors Vivaan Rajesh, Wallace McCarthy
 * @version April 3 2023
 */
package checkpoint1;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private List<Animatable> currentFrameObjects;
    private List<Animatable> nextFrameObjects;

    /**
     * This method implements this cool idea: This method creates the game state.
     */
    public GameState() {
        currentFrameObjects = new ArrayList<Animatable>();
    }

    /**
     * This method implements this cool idea: This method returns the current objects.
     * @return currentFrameObjects
     */
    public List<Animatable> getCurrentObjects() {
        return currentFrameObjects;
    }

    /**
     * This method implements this cool idea: This method starts the frame of the program.
     */
    public void startFrame() {
        nextFrameObjects = new ArrayList<Animatable>(); // Creates empty list
        nextFrameObjects.addAll(currentFrameObjects);   // Add all the current ones to the new list.
    }

    /**
     * This method implements this cool idea: This method animates the objects.
     */
    public void finishFrame() {
        currentFrameObjects = nextFrameObjects;
        nextFrameObjects = null;  // This makes it clear there is only a current list now.
    }

    /**
     * This method implements this cool idea: This method adds the game objects.
     * @param a the game object
     */
    public void addGameObject(Animatable a) {
        nextFrameObjects.add(a);
    }

}

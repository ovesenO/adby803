package game;


//Important to implement Serializable interface.
public class SavedInfo implements java.io.Serializable
{
    public int score, health, xPos, levelIndex;
    private int currentShip;

    /***
     * Default saved info constructor
     */
    public SavedInfo() {}

    /***
     * Constructs a new save with paraameters
     *
     * @param score         Player score
     * @param health        Player health
     * @param xPos          Player x position
     * @param levelIndex    Current Level from level Index
     * @param currentShip   Current ship model in int
     */
    public SavedInfo(int score, int health, int xPos, int levelIndex, int currentShip)
    {
        this.score = score;
        this.health = health;
        this.levelIndex = levelIndex;
        this.xPos = xPos;
        this.currentShip = currentShip;
    }

    public int getCurrentShip(){return currentShip;}
}
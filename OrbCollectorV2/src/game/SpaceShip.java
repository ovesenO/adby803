package game;

import city.cs.engine.*;
import city.cs.engine.Shape;
import game.gui.GameUpdateGUI;


public class SpaceShip extends Walker implements  CollisionListener
{
    public static boolean loadLevelTrue = false;
    public static Level savedLevel;
    public static int score;
    public static int health = 3;
    public float aliveTime;
    public static int xPos;
    public int currentShip;
    public BodyImage currentShipImage;

    private SoundClip collectSound;
    private EntityGenerator et;
    private SavedInfo SI;
    private GameUpdateGUI GUGUI;
    private Game gem;
    

    S_GameState gameState = S_GameState.getInstance();

    // A very forgiving hit box for player...
    private static final Shape shipShape = new PolygonShape
            (
            -0.11f,2.8f,
            0.87f,1.48f,
            0.99f,0.29f,
            0.24f,-2.32f,
            -1.12f,-2.27f,
            -1.24f,1.21f
            );//The game is hard enough as it is...

    /***
     *  Initializes necessary data for SpaceShip ( player )
     *
     * @param world     City Engine world
     * @param et        Entity Generator
     * @param GUGUI     GameUpdateGUI
     */
    public SpaceShip(World world, EntityGenerator et, GameUpdateGUI GUGUI)
    {
        super(world, shipShape);
        this.currentShipImage = new BodyImage("data/ShipForward.png", 3);
        this.et = et;
        this.GUGUI = GUGUI;
        
        addImage(currentShipImage);
    }

    // Handle Collision Events
    @Override
    public void collide(CollisionEvent e)
    {
        // Orb collision
        if(e.getOtherBody() instanceof Orb){
            Orb orbInstance = (Orb) e.getOtherBody();
            if(health == 3)
            {
                //gui.updateScore(score); Supposed to update the score and show it
                orbInstance.collectOrb(this);
                et.updateOrbs();
            }
            if(health < 3)
            {
                health++;
                orbInstance.healSound();
                orbInstance.destroy();
                et.updateOrbs();
            }
        }
        // Asteroid collision
        if(e.getOtherBody() instanceof  Asteroid)
        {
            Asteroid instance = (Asteroid)  e.getOtherBody();
            instance.OnCollision(this);
        }
        if(this.health <1)
        {
            GUGUI.resetLevel();
        }
    }

    // Updates current ship image to
    public BodyImage updateCurrentShip(int newShip)
    {
        System.out.println("NEW SHIP IS"+newShip);
        currentShip = newShip;

        removeAllImages();
        switch(currentShip)
        {
            case 0:
            currentShipImage = new BodyImage("data/ShipForward.png", 3);
            addImage(currentShipImage);
            break;
            case 1:
            currentShipImage = new BodyImage("data/Ship1Forward.png", 3);
            addImage(currentShipImage);
            break;
            case 2:
            currentShipImage = new BodyImage("data/Asteroid.png", 3);
            addImage(currentShipImage);
            break;
        }
        return currentShipImage;
    }

    public void decrementHealth() {
        health--;
    }
}

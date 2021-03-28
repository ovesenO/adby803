package game;


import city.cs.engine.BodyImage;
import game.gui.GameUpdateGUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//NEW CLASS - For controlling the above ship
public class SpaceShipController implements KeyListener
{
    public S_GameState gameState = S_GameState.getInstance();
    public SpaceShip player;
    public boolean isVisible = false;

    GameUpdateGUI GUGUI;
    //Controls
    private int leftKey;
    private int rightKey;
    //speed variables
    private int speedBoost;
    private int speedBoost2;
    private int shipSpeed = 5;

    /***
     *
     * @param p     Space Ship ( player )
     */
    public SpaceShipController(SpaceShip p)
    {
        player = p;
    }

    @Override
    public void keyTyped(KeyEvent e) { }


    /*
    If one hits the same direction twice one gains additional speed
    when one changes direction it is instant instead of stopping and then going
    the speed boost is also reset when one changes dir
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // Ship go left
        if (e.getKeyCode() == leftKey) {
            setNewLeftKey();
            speedBoost2 =0;
            player.xPos = 0;
            speedBoost+=3;
            player.xPos -= shipSpeed+speedBoost;

        }
        // Ship go right
        if (e.getKeyCode() == rightKey) {
            setNewRightKey();
            speedBoost = 0;
            player.xPos = 0;
            speedBoost2+=3;
            player.xPos += shipSpeed+speedBoost2;
        }

        //  Toggle Menu
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            isVisible = !isVisible;
        }
    }

    //keeps space ship within bounds
    public void boundControl()
    {
        if (player.getPosition().x < -18.3)
        {
            player.xPos = 0;
            player.xPos++;
        }

        if (player.getPosition().x > +18.3)
        {
            player.xPos = 0;
            player.xPos--;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    //Sets var leftKey to random num in ascii range
    public void setNewLeftKey()
    {
        leftKey = getRandomNumber(65, 79);
        BodyImage image = new BodyImage("data/ShipLeft.png", 3);
    }

    //Sets var rightkey to random num in ascii range - Note keys in a different range from leftkey
    public void setNewRightKey()
    {
        rightKey = getRandomNumber(79, 91);
        BodyImage image = new BodyImage("data/ShipRight.png", 3);        
    }

    //random int in a range
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    // ----- Getters -----
    public char getLeftKey(){return (char)leftKey;}
    public char getRightKey(){return (char)rightKey;}
}

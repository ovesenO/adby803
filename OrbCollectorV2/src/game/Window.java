package game;
import city.cs.engine.*;

import javax.swing.*;
import java.awt.*;
public class Window extends UserView
{// bg = background
    private float backgroundY1;
    private float backgroundY2;
    private Image background;

    /***
     *Initializes whats necessary for two backgrounds
     *
     * @param w         City Engine world
     * @param width     world width
     * @param height    world height
     * @param bgy1      background y position 1
     * @param bgy2      background y position 2
     */
    public Window(World w, int width, int height, int bgy1, int bgy2)
    {
        super(w, width, height);
        background = new ImageIcon("data/SpacebackPlus20Pix.png").getImage();
        backgroundY1 = bgy1;
        backgroundY2 = bgy2;
        //background = g.getScaledInstance(1400, 1000, Image.SCALE_SMOOTH);
    }

    // draws both backgrounds
    protected void paintBackground(Graphics2D g) {
        //shitty tilemap - To do : Tilemap up to scale in krita
        g.drawImage(background, 0, (int)backgroundY1, this);
        g.drawImage(background, 0, (int)backgroundY2, this);

    }


    /*
    updates position of background at background scroll speed
    to create a scrolling effect instead of using a gif...
    each background is custom created and making 700 gifs is ♿
     */
    public void updateBackground(float scrollSpeed)
    {
        Time time = new Time();
        time.updateTime();
        backgroundY1 += time.deltaTime * scrollSpeed;
        backgroundY2 += time.deltaTime * scrollSpeed;
        if(backgroundY1 >= 1009){backgroundY1 = -1009;}
        if(backgroundY2 >= 1009){backgroundY2 = -1009;}

    }
    // Changes background
    public void changeWindowBackGround(Image background)
    {
        this.background = background;
    }
}


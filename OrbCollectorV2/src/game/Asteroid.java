package game;
import city.cs.engine.*;
import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Asteroid extends DynamicBody implements KeyListener
{
    private BodyImage image;
    private SoundClip negateHealthSound;

    /***
     *  Asteroid constructor - gives asteroid class its necessary data
     *
     * @param world                 City engine world
     * @param shape                 Asteroid hit box - circle
     * @param image                 Asteroid png
     * @param negateHealthSound     Sound to play when asteroid hits player
     */
    public Asteroid(World world, Shape shape,BodyImage image, String negateHealthSound)
    {
        super(world, shape);
        try {
            this.negateHealthSound = new SoundClip("data/4.wav");   // Open an audio input stream
            this.negateHealthSound.stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        addImage(image);
    }

    //when asteroid hits player
    public void OnCollision(SpaceShip player)
    {
        //When hit by asteroid hp--
        negateHealthSound.play();
        player.decrementHealth();
        //updates the health

        this.destroy();
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(this.getPosition().y >= 200){this.destroy();}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

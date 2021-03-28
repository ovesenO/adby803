package game;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Orb extends DynamicBody {
    private int orbValue;
    private SoundClip orbCollectSound;

    /***
     *Initializes necessary parameters for orbs
     *
     * @param world             City Engine world
     * @param shape             Circle ( orbs are round )
     * @param image             Orb png
     * @param orbValue          Amount of score gained from orb
     * @param orbCollectSound   Sound clip played on contact with player
     */
    public Orb(World world, Shape shape, BodyImage image, int orbValue, String orbCollectSound)
    {
        super(world, shape);
        this.orbValue = orbValue;
        try {
            this.orbCollectSound = new SoundClip(orbCollectSound);   // Open an audio input stream
            this.orbCollectSound.stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
        setGravityScale(0);

        addImage(image);
    }

    //Called from the player when detects collision with an orb
    public void collectOrb(SpaceShip player)
    {
        //keeps player y position consistent
        Vec2 playerPosFixture = player.getPosition();
        playerPosFixture.y = -17;
        player.setPosition(playerPosFixture);

        // updates players score
        player.score+=orbValue;
        orbCollectSound.play();
        this.destroy();
    }
    public void healSound()
    {
        orbCollectSound.play();
    }
}

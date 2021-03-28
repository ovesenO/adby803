package game;



import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Level
{
    public List<WeightRarity<String, Integer, Integer>> allEntities = new ArrayList<WeightRarity<String, Integer, Integer>>();
    public String name;
    public SoundClip ost;
    public int lvlUpScore;

    private Image backGround;
    private Window windowInstance;
    private float shipSpeed;//background scroll speed is 'ship speed'
    private float spawnRate;
    private int weightSum;

    /***
     *
     * @param LevelName     All entities that exist in level shall be types here by user
     * @param backGround    Designated background for level
     * @param shipSpeed     The scroll speed of the background
     * @param spawnRate     Time interval between each entity spawn
     * @param window        City Engine world
     * @param ost           Level music to be looped
     * @param lvlUpScore    Required score to pass level
     */
    public Level(String LevelName, Image backGround, float shipSpeed, float spawnRate, Window window, String ost, int lvlUpScore)
    {
        this.name = LevelName;
        this.backGround = backGround;
        this.shipSpeed = shipSpeed;
        this.spawnRate = spawnRate;
        this.windowInstance = window;
        this.lvlUpScore = lvlUpScore;
       //this.ost = new SoundClip(ost);
        try {
            this.ost = new SoundClip(ost);   // Open an audio input stream
            this.ost.loop();  // Set it to continous playback (looping)
            this.ost.setVolume(0.1);
            this.ost.stop();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

    }

    /***
     * Adds entity to level.
     * example level.addNewEntity("spawn brain", 100);
     *
     * @param entityName    Requires specific entity name from Entity Generator
     * @param entityWeight  Sets the rarity weight of entity
     */
    public void addNewEntity(String entityName, int entityWeight)
    {
        weightSum += entityWeight;
        allEntities.add(new WeightRarity<String, Integer, Integer>(entityName, entityWeight, weightSum));
    }

    // scrolls background down & changes background when level changes
    public void backgroundUpdate()
    {
        windowInstance.updateBackground(shipSpeed);
        windowInstance.changeWindowBackGround(backGround);
    }

    //----- getters -----
    public float getSpawnRate() { return spawnRate; }
    public int getWeightSum() { return weightSum; }
    public String getLevelName(){return name;}
}

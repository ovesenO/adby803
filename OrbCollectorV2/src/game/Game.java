package game;

import game.gui.*;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.IOException;

public class Game {
    /// S_ = SINGLETON
    public S_GameState gameState = S_GameState.getInstance();

    private EntityGenerator entityGenerator;

    public SelectShipGUI SSGUI;
    public GameUpdateGUI GUGUI;
    public GuiSystem guiSys;
    public SpaceShip player;
    public SpaceShipController playerController;
    public Time time;
    public SaveSystem saveSystem;
    public int currentLevelIndex = 0;
    public Level currentLevel;
    public int lvlUppScore =15;

    private SoundClip menuButtonSound;
    private Window window;
    private World world;

    private Level allLevels[] = new Level[7], LevelMenu;

    public Game(SaveSystem saveSystem)
    {
        this.saveSystem = saveSystem;
    }

    //Initializes variables that on need to be initialized once
    public void ProgramSetup()
    {
        //sound clip for buttons
        try {
            menuButtonSound = new SoundClip("data/1.wav");
        } catch (UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        saveSystem.Load();
        currentLevelIndex = saveSystem.currentSave.levelIndex;

        // ----- initializations that does not need to be repeated -----
        world = new World();
        window = new Window(world, 800, 1000, 0, -1000);
        guiSys = new GuiSystem(window);
        entityGenerator = new EntityGenerator(world, new Vec2(-18, 18), 29, currentLevel);
        GUGUI = new GameUpdateGUI(saveSystem, this);

        //----- JFrame setup -----
        final JFrame frame = new JFrame("Orb Collector - Main Menu");
        frame.setSize(800, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(window); 

        // Changes the game state to MainMenuStart
        gameState.GameState = GameStates.MAIN_MENU_START;
    }    

    //Initializes variables necessary for MainMenuUpdate
    public void MainMenuStart() {
        // Reset current level
        if(currentLevel != null)
        {
            // Stop the music from the gameUpdate() when returning to main menu
            currentLevel.ost.stop();
            entityGenerator.clearEntities(GUGUI.getResettingEntities());
            GUGUI.setresettingEntities(GUGUI.getResettingEntities());
        }
        // Clears old entities
        entityGenerator.clearEntities(GUGUI.getResettingEntities());
        GUGUI.setresettingEntities(GUGUI.getResettingEntities());
        
        // sets gui to MainMenuGui
        guiSys.InsertGui(new MainMenuGUI(saveSystem, this));
        
        // Initializes custom level menu
        LevelMenu = new Level("menu",new ImageIcon("data/levelOneBack.png").getImage(), 60f, 1f, window, "data/realities.wav", 99999);
        currentLevel = LevelMenu;
        currentLevel.ost.play();
        world.start();

        // Changes the game state to MainMenuUpdate ( initialization is done )
        gameState.GameState = GameStates.MAIN_MENU_UPDATE;
    }

    // Runs per frame when game state is in MainMenuUpdate
    public void MainMenuUpdate() 
    {
        entityGenerator.pushLevel(currentLevel);
        currentLevel = LevelMenu;
        entityGenerator.entityBehaviour();
        window.requestFocus();

        // Updates current gui
        guiSys.UpdateGui();
    }

    //Initializes variables necessary for GameUpdate
    public void GameStart() {
        //----- SpaceShip Player setup -----
        player = new SpaceShip(world, entityGenerator, GUGUI);
        playerController = new SpaceShipController(player);
        player.score = saveSystem.currentSave.score;
        player.currentShip = saveSystem.currentSave.getCurrentShip();
        player.currentShipImage = player.updateCurrentShip(saveSystem.currentSave.getCurrentShip());
        System.out.println("player current ship" +player.currentShip);
        player.setGravityScale(0);
        player.setPosition(new Vec2(saveSystem.currentSave.xPos, -17));
        player.addCollisionListener(player);
        playerController.setNewLeftKey();
        playerController.setNewRightKey();
        playerController.isVisible = false;
        player.setLinearVelocity(new Vec2(0, 0));
        playerController.boundControl();
        window.addKeyListener(playerController);

        if(currentLevel != null)
        {
            currentLevel.ost.stop();
            entityGenerator.clearEntities(GUGUI.getResettingEntities());
            GUGUI.setresettingEntities(GUGUI.getResettingEntities());
        }
        //if this is true deletes all asteroids and orbs in game
        entityGenerator.clearEntities(GUGUI.getResettingEntities());
        GUGUI.setresettingEntities(GUGUI.getResettingEntities());
        currentLevel.ost.stop();
        respawnPlayer();

        // sets gui to GameUpdateGui
        guiSys.InsertGui(GUGUI);

        //----- Level Setup -----
        /**
         * A level is a collection of data necessary to create each level.
         * this data includes background, soundtrack, entities, entity spawn rate, entity rarity...etc
         */
        allLevels[0] = new Level(
            "<html>Entities : OrangeOrb</html>",
            new ImageIcon("data/levelOneBack.png").getImage(), 60f, 1f, window, "data/realities.wav", 15);
        allLevels[0].addNewEntity("OrangeOrb", 10);
        //allLevels[0].addNewEntity("SmallAsteroid", 300);

        // --------------------------------------------------------------------------------

        allLevels[1] = new Level("<html>Entities : PinkOrb<br> </html>",
        new ImageIcon("data/levelTwoBack.png").getImage(), 60f, 1f, window,"data/DIINKIID_2_v2.wav", 25);
        allLevels[1].addNewEntity("PinkOrb", 10);

        // ------------------------------------------------------------------------------

        allLevels[2] = new Level("<html>Entities : Asteroid <br> GreenOrb</html>",
        new ImageIcon("data/levelThreeBack.png").getImage(), 60f, 1f, window,
        "data/id_32_rev_3_with_acid_bark.wav", 39);
        allLevels[2].addNewEntity("GreenOrb", 10);
        allLevels[2].addNewEntity("SmallAsteroid", 3);

        // ------------------------------------------------------------------------------

        allLevels[3] = new Level("<html>Entities : Asteroid OrangeOrb <br> GreenOrb PinkOrb</html>",
        new ImageIcon("data/levelFourBack.png").getImage(), 60f, 1f, window,
                "data/realitiesfaster.wav",65);
                allLevels[3].addNewEntity("GreenOrb", 10);
                allLevels[3].addNewEntity("OrangeOrb", 10);
                allLevels[3].addNewEntity("PinkOrb", 6);
                allLevels[3].addNewEntity("SmallAsteroid", 5);

        // -------------------------------------------------------------------------------

        allLevels[4] = new Level("<html>Entities : Asteroid OrangeOrb <br> GreenOrb PinkOrb CyanOrb </html>",
        new ImageIcon("data/levelFiveBack.png").getImage(), 80f, 0.8f, window, "data/realities.wav",100);
        allLevels[4].addNewEntity("GreenOrb", 10);
        allLevels[4].addNewEntity("OrangeOrb", 10);
        allLevels[4].addNewEntity("PinkOrb", 10);
        allLevels[4].addNewEntity("CyanOrb", 6);
        allLevels[4].addNewEntity("SmallAsteroid", 10);

        // ---------------------------------------------------------------------

        allLevels[5] = new Level("<html>Entities : Asteroid OrangeOrb <br> GreenOrb PinkOrb CyanOrb </html>",
        new ImageIcon("data/levelSixBack.png").getImage(), 100f, 0.6f, window,
                "data/id_32_rev_3_with_acid_bark.wav", 180);
        allLevels[5].addNewEntity("GreenOrb", 10);
        allLevels[5].addNewEntity("OrangeOrb", 10);
        allLevels[5].addNewEntity("PinkOrb", 10);
        allLevels[5].addNewEntity("CyanOrb", 6);
        allLevels[5].addNewEntity("SmallAsteroid", 30);

        // ---------------------------------------------------------------------

        allLevels[6] = new Level("<html>Entities : Asteroid OrangeOrb <br> GreenOrb PinkOrb CyanOrb</html>",
        new ImageIcon("data/levelSevenBack.png").getImage(), 120f, 0.4f, window,
                "data/realitiesfaster.wav", 99999999);
        allLevels[6].addNewEntity("GreenOrb", 10);
        allLevels[6].addNewEntity("OrangeOrb", 10);
        allLevels[6].addNewEntity("PinkOrb", 10);
        allLevels[6].addNewEntity("CyanOrb", 6);
        allLevels[6].addNewEntity("SmallAsteroid", 50);

        // ---------------------------------------------------------------------

        // Used time for spawn frequency
        time = new Time();

        // Set current level to saved level ( index based saving ™ )
        currentLevel = allLevels[saveSystem.currentSave.levelIndex];
        currentLevel.ost.play();
        world.start();

        //... you know what this is
        gameState.GameState = GameStates.GAME_UPDATE;

    }

    public void GameEnd() {
        //need I explain myself?
        gameState.GameState = GameStates.MAIN_MENU_START;
    }

    public void GameUpdate()
    {

        if (player.score >= allLevels[currentLevelIndex].lvlUpScore)
        {
            currentLevelIndex++;
            if (currentLevelIndex < allLevels.length)
            {
                currentLevel.ost.stop();
                currentLevel = allLevels[currentLevelIndex];
                entityGenerator.pushLevel(currentLevel);
                currentLevel.ost.play();
            }
        }

        // updates player movement
        player.setLinearVelocity(new Vec2(player.xPos, 0));
        // manages player movement range
        playerController.boundControl();

        // gives current level to entity generator
        entityGenerator.pushLevel(currentLevel);
        // background scrolling
        currentLevel.backgroundUpdate();
        // updates all entities
        entityGenerator.entityBehaviour();
        // updates current gui system
        guiSys.UpdateGui();

        window.requestFocus();
    }

    //resets player position to saved player position
    private void respawnPlayer() { player.setPosition(new Vec2(saveSystem.currentSave.xPos, -17)); }

    public void playMenuSound() { menuButtonSound.play(); }
}
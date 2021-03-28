package game.gui;
import game.*;

import game.S_GameState;
import game.Window;
import javax.swing.*;
import org.jbox2d.common.Vec2;
import java.awt.*;


public class GameUpdateGUI implements IGUI
{
    private SaveSystem saveSystem;
    private Game gem;
    public GameUpdateGUI(SaveSystem saveSystem, Game gem)
    {
        this.saveSystem = saveSystem;
        this.gem = gem;
    }
    S_GameState gameState = S_GameState.getInstance();

    private boolean resettingEntities;
    private Window window;
    private GuiSystem guiSys;

    private JButton SaveAndExitButton;
    private JButton RestartButton;

    private JLabel LeftLetterText;
    private JLabel RightLetterText;
    private JLabel PlayerScoreText;
    private JLabel PlayerHealthIcon;
    private JLabel GameTimeText;
    private JLabel CurrentLevelText;
    private JLabel LevelDetailsText;
    

    private ImageIcon oneHealth = new ImageIcon("data/1health.png");
    private ImageIcon twoHealth = new ImageIcon("data/2health.png");
    private ImageIcon threeHealth = new ImageIcon("data/3health.png");

    public boolean resetLevel; 

    @Override
    public void onGuiStart(Window window, GuiSystem guiSys) 
    {
        resetLevel = false;
        this.window = window;
        this.guiSys = guiSys;

        window.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // TODO Auto-generated method stub
        SaveAndExitButton = new JButton("Save & Exit");
        SaveAndExitButton.setBounds(300, 350, 150, 40);
        SaveAndExitButton.setFont(new Font("", Font.BOLD, 20));
        SaveAndExitButton.setForeground(Color.orange);
        SaveAndExitButton.setBackground(Color.gray);

        RestartButton = new JButton("Restart Game");
        RestartButton.setBounds(275, 270, 200, 40);
        RestartButton.setFont(new Font("", Font.BOLD, 20));
        RestartButton.setForeground(Color.orange);
        RestartButton.setBackground(Color.gray);

        LeftLetterText = new JLabel("<- S    ");
        LeftLetterText.setBounds(200, 15, 400, 380);
        LeftLetterText.setFont(new Font("", Font.BOLD, 20));
        LeftLetterText.setForeground(Color.white);

        RightLetterText = new JLabel("    L ->");
        RightLetterText.setFont(new Font("", Font.BOLD, 20));
        RightLetterText.setBounds(500, 15, 400, 380);
        RightLetterText.setForeground(Color.white);

        PlayerHealthIcon = new JLabel(threeHealth);
        PlayerHealthIcon.setFont(new Font("", Font.BOLD, 20));
        PlayerHealthIcon.setBounds(500, -140, 400, 380);
        PlayerHealthIcon.setForeground(Color.white);

        PlayerScoreText = new JLabel("SCORE = 0");
        PlayerScoreText.setFont(new Font("", Font.BOLD, 20));
        PlayerScoreText.setBounds(5, -35, 300, 100);
        PlayerScoreText.setForeground(Color.white);

        CurrentLevelText = new JLabel("Current Level : ");
        CurrentLevelText.setFont(new Font("", Font.BOLD, 20));
        CurrentLevelText.setBounds(5, 870, 300, 100);
        CurrentLevelText.setForeground(Color.white);

        LevelDetailsText = new JLabel("Next Level in x score");
        LevelDetailsText.setFont(new Font("", Font.BOLD, 15));
        LevelDetailsText.setBounds(550, 850, 300, 100);
        LevelDetailsText.setForeground(Color.white);

        GameTimeText = new JLabel("");
        GameTimeText.setFont(new Font("", Font.BOLD, 20));
        GameTimeText.setBounds(5, -10, 300, 100);
        GameTimeText.setForeground(Color.white);

        window.setLayout(null);

        RestartButton.setVisible(gem.playerController.isVisible);
        SaveAndExitButton.setVisible(gem.playerController.isVisible);

        window.add(LeftLetterText);
        window.add(RightLetterText);
        window.add(PlayerHealthIcon);
        window.add(RestartButton);
        window.add(SaveAndExitButton);
        window.add(PlayerScoreText);
        window.add(CurrentLevelText);
        window.add(LevelDetailsText);

        
    }

    @Override
    public void onGuiUpdate() 
    {
        LevelDetailsText.setText(gem.currentLevel.getLevelName());
        
        CurrentLevelText.setText("Level = : " + (gem.currentLevelIndex+1));

        // TODO Auto-generated method stub
        PlayerStatsUpdate(gem.playerController.player.health);
        //PlayerScoreText.setText("SCORE = "+playerController.player.score);

        RightLetterText.setText(gem.playerController.getRightKey() + " -->");
        LeftLetterText.setText("<-- " + gem.playerController.getLeftKey());

        RestartButton.setVisible(gem.playerController.isVisible);
        SaveAndExitButton.setVisible(gem.playerController.isVisible);

        if(RestartButton.getModel().isPressed())
        {
            //gem.player.xPos = saveSystem.currentSave.xPos;
            gem.player.setLinearVelocity(new Vec2(0,0));
            resetLevel = !resetLevel;
            //resetLevel(resetLevel);
            resetLevel();
        }
        
        if(SaveAndExitButton.getModel().isPressed())
        {
            gem.currentLevel.ost.stop();
            saveSystem.Save(new SavedInfo(gem.player.score, gem.player.health, gem.player.xPos, gem.currentLevelIndex, gem.player.currentShip));
            gem.player.destroy();
            
            setresettingEntities(true);

            gameState.GameState = GameStates.MAIN_MENU_START;
        }
    }

    @Override
    public void onGuiExit() 
    {
        // TODO Auto-generated method stub
        window.removeAll();
    }

    public void PlayerStatsUpdate(int currentHP)
    {
        PlayerScoreText.setText("SCORE : " + gem.playerController.player.score);
        switch(currentHP)
        {
            case 1:
            PlayerHealthIcon.setIcon(oneHealth);
        break;
        case 2:
        PlayerHealthIcon.setIcon(twoHealth);
            break;
            case 3:
            PlayerHealthIcon.setIcon(threeHealth);
            break;
        }
    }

    public void levelTexthandler(String currentLevel)
    {
    
        CurrentLevelText.setText("Current Level :  "+ currentLevel);
        //LevelDetailsText.setText("next lvl = "+nextLevel);
    }


    public Window getWindow()
    {
        return window;
    }
    public boolean getResettingEntities(){return resettingEntities;}
    public void setresettingEntities(boolean rE){resettingEntities = rE;}
    public void resetLevel( )
    {
        gem.currentLevel.ost.stop();
        gem.currentLevelIndex = 0;
        //gem.playMenuSound();
        //gem.playerController.isVisible = false;
        setresettingEntities(true);
        saveSystem.resetSave();
        gem.player.destroy();
        gameState.GameState = GameStates.GAME_START;
    }
}

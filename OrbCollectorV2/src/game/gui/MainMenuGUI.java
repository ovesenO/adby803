package game.gui;
import game.*;

import game.S_GameState;
import game.Window;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;


public class MainMenuGUI implements IGUI
{
    private Game gem;
    private SaveSystem saveSystem;
    public MainMenuGUI(SaveSystem saveSystem, Game gem)
    {
        this.gem = gem;
        this.saveSystem = saveSystem;
    }
    S_GameState gameState = S_GameState.getInstance();

    private JButton NewGameButton;
    private JButton LoadButton;
    private JButton ShopButton;
    private JButton HelpButton;
    private JButton SelectSaveButton1;
    private JButton SelectSaveButton2;

    private JLabel CurrentSaveText;


    private Window window;
    GuiSystem guiSys;
    HelpGUI HelpMenu;

    private boolean inHelp;

    @Override
    public void onGuiStart(Window window, GuiSystem guiSys) {
        // TODO Auto-generated method stub
        this.window = window;
        this.guiSys = guiSys;
        window.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        //--------------------------------
        NewGameButton = new JButton("Start New Game");
        NewGameButton.setBounds(300, 250, 150, 40);
        NewGameButton.setFont(new Font("", Font.BOLD, 20));
        NewGameButton.setForeground(Color.white);
        NewGameButton.setBackground(Color.orange);

        // probably going to put buttons below the ship to change the graphics of what player will be instatiated with
        ShopButton = new JButton("Select Ship");
        ShopButton.setBounds(300, 300, 150, 40);
        ShopButton.setFont(new Font("", Font.BOLD, 20));
        ShopButton.setForeground(Color.white);
        ShopButton.setBackground(Color.orange);

        HelpButton = new JButton("Help");
        HelpButton.setBounds(300, 400, 150, 40);
        HelpButton.setFont(new Font("", Font.BOLD, 20));
        HelpButton.setForeground(Color.white);
        HelpButton.setBackground(Color.orange);

        LoadButton = new JButton("Load Game");
        LoadButton.setBounds(300, 350, 150, 40);
        LoadButton.setFont(new Font("", Font.BOLD, 20));
        LoadButton.setForeground(Color.white);
        LoadButton.setBackground(Color.orange);

        SelectSaveButton1 = new JButton("Select Save 1");
        SelectSaveButton1.setBounds(150, 700, 150, 40);
        SelectSaveButton1.setFont(new Font("", Font.BOLD, 15));
        SelectSaveButton1.setForeground(Color.white);
        SelectSaveButton1.setBackground(Color.orange);

        
        SelectSaveButton2 = new JButton("Select Save 2");
        SelectSaveButton2.setBounds(450, 700, 150, 40);
        SelectSaveButton2.setFont(new Font("", Font.BOLD, 15));
        SelectSaveButton2.setForeground(Color.white);
        SelectSaveButton2.setBackground(Color.orange);

        //--------------------------------------------------

        CurrentSaveText = new JLabel("File loaded :"+saveSystem.currentSaveIndex);
        CurrentSaveText.setFont(new Font("", Font.BOLD, 20));
        CurrentSaveText.setBounds(5, -10, 300, 100);
        CurrentSaveText.setForeground(Color.white);
        
        
        window.setLayout(null);
        window.add(NewGameButton);
        window.add(ShopButton);
        window.add(HelpButton);
        window.add(LoadButton);
        window.add(SelectSaveButton1);
        window.add(SelectSaveButton2);
        window.add(CurrentSaveText);
        

    }

    @Override
    public void onGuiUpdate() {
        CurrentSaveText.setText("File loaded :"+saveSystem.currentSaveIndex);
        // TODO Auto-generated method stub
        if(NewGameButton.getModel().isPressed())
        {
            gem.playMenuSound();
            gameState.GameState = GameStates.GAME_START;
            System.out.println("Game State Changed From MainMenuUpdate  to  GameStart");
        }
        if(ShopButton.getModel().isPressed())
        {
            guiSys.InsertGui(new SelectShipGUI(saveSystem, gem));
            gem.playMenuSound();
        }
        if(HelpButton.getModel().isPressed())
        {
            gem.playMenuSound();
            inHelp = !inHelp;
            guiSys.InsertGui(new HelpGUI(gem));
        }
        if(LoadButton.getModel().isPressed())
        {
            gem.playMenuSound();
            saveSystem.Load();
            gameState.GameState = GameStates.GAME_START;
        }
        if(SelectSaveButton1.getModel().isPressed())
        {
            gem.playMenuSound();
            saveSystem.currentSaveIndex = 1;
            gem.player.score = saveSystem.currentSave.score;
            saveSystem.Load();
        }
        if(SelectSaveButton2.getModel().isPressed())
        {
            gem.playMenuSound();
            saveSystem.currentSaveIndex = 2;
            gem.player.score = saveSystem.currentSave.score;
            saveSystem.Load();
        }

    }

    @Override
    public void onGuiExit() {
        // TODO Auto-generated method stub
        window.removeAll();
    }
    
}

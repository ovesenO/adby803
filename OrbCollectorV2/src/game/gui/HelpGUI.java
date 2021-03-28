package game.gui;
import game.*;

import game.S_GameState;
import game.Window;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;

public class HelpGUI implements IGUI
{
    private Game gem;
    private SpaceShipController playerController;
    public HelpGUI(Game gem)
    {
        this.gem = gem;
    }
    S_GameState gameState = S_GameState.getInstance();

    Window window;
    GuiSystem guiSys;

    private JButton ExitHepl;

    private JLabel TitlePlayerHealth;
    private JLabel InfoPlayerHealth;
    private JLabel TitlePlayerMovement;
    private JLabel InfoPlayerMovement;
    private JLabel TitleEntities;
    private JLabel InfoEntities;
    private JLabel TitleLevels;
    private JLabel InfoLevels;
    private JLabel TipsText;

    @Override
    public void onGuiStart(Window window, GuiSystem guiSys) 
    {
        this.window = window;
        this.guiSys = guiSys;

        window.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // TODO Auto-generated method stub

        TitlePlayerHealth = new JLabel("Player Health :");
        TitlePlayerHealth.setFont(new Font("", Font.BOLD, 23));
        TitlePlayerHealth.setBounds(5, -20, 300, 100);
        TitlePlayerHealth.setForeground(Color.white);

        InfoPlayerHealth = new JLabel("<html>The player is the ship at the bottom middle of the screen. <br> The player starts off with three lives which are represented by the spaceship icons at the top right of the screen.. <br> players' lives can be regained by collecting orbs when lives are less than 3. /n Game ends if player runs out of lives </html>");
        InfoPlayerHealth.setFont(new Font("", Font.BOLD, 20));
        InfoPlayerHealth.setBounds(20, 50, 300, 300);
        InfoPlayerHealth.setForeground(Color.white);    

        //---------------------------------------------------------------

        TitlePlayerMovement = new JLabel("Player Controls");
        TitlePlayerMovement.setFont(new Font("", Font.BOLD, 23));
        TitlePlayerMovement.setBounds(5, 500, 300, 100);
        TitlePlayerMovement.setForeground(Color.white);    

        InfoPlayerMovement = new JLabel("<html>There are two chars with arrows in the middle of the screen. <br> click on the char with the left arrow and the space ship will move to the left & right arrow char to the right. <br> Warning : the char changes after it is pressed</html>");
        InfoPlayerMovement.setFont(new Font("", Font.BOLD, 20));
        InfoPlayerMovement.setBounds(20, 570, 300, 300);
        InfoPlayerMovement.setForeground(Color.white);    

        //---------------------------------------------------------------

        TitleEntities = new JLabel("Game Entities");
        TitleEntities.setFont(new Font("", Font.BOLD, 23));
        TitleEntities.setBounds(480, -20, 300, 100);
        TitleEntities.setForeground(Color.white);    

        InfoEntities = new JLabel("<html>Orbs : <br> There are different color orbs which all give different amounts of score <br> Asteroids : <br> negate player dscore</html>");
        InfoEntities.setFont(new Font("", Font.BOLD, 20));
        InfoEntities.setBounds(495, 50, 300, 190);
        InfoEntities.setForeground(Color.white);    

        //---------------------------------------------------------------

        TitleLevels = new JLabel("Level System");
        TitleLevels.setFont(new Font("", Font.BOLD, 23));
        TitleLevels.setBounds(480, 500, 300, 100);
        TitleLevels.setForeground(Color.white);    

        InfoLevels = new JLabel("<html>There are 7 different levels <br> once one reaches a certain amount of score the next level will be loaded in <br> the final level does not end</html>");
        InfoLevels.setFont(new Font("", Font.BOLD, 20));
        InfoLevels.setBounds(495, 570, 300, 190);
        InfoLevels.setForeground(Color.white);    

        TipsText = new JLabel("<html>If you click 'esc'<br> key while game is running. <br> A menu will open up</html>");
        TipsText.setFont(new Font("", Font.BOLD, 18));
        TipsText.setBounds(310, 420, 160, 180);
        TipsText.setForeground(Color.green); 

        //---------------------------------------------------------------

        ExitHepl = new JButton("<html>Return To <br> Main Menu</html>");
        ExitHepl.setBounds(300, 350, 150, 80);
        ExitHepl.setFont(new Font("", Font.BOLD, 20));
        ExitHepl.setForeground(Color.orange);
        ExitHepl.setBackground(Color.gray);


        window.add(TitlePlayerHealth);
        window.add(InfoPlayerHealth);

        window.add(TitlePlayerMovement);
        window.add(InfoPlayerMovement);

        window.add(TitleEntities);
        window.add(InfoEntities);

        window.add(TitleLevels);
        window.add(InfoLevels);

        window.add(ExitHepl);

        window.add(TipsText);
    }
    @Override
    public void onGuiUpdate() {
        // TODO Auto-generated method stub
        if(ExitHepl.getModel().isPressed())
        {
            if(ExitHepl.getModel().isPressed())
            {
                //inHelp = !inHelp;
                gem.playMenuSound();
                gameState.GameState = GameStates.MAIN_MENU_START;
            }
        }
    }
    @Override
    public void onGuiExit() {
        // TODO Auto-generated method stub
        window.removeAll();
    }

    
}

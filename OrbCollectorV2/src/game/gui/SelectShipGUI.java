package game.gui;
import game.*;

import game.S_GameState;
import game.Window;

import java.awt.Color;

import javax.swing.*;

import java.awt.*;

public class SelectShipGUI implements IGUI{
    private Game gem;
    private SaveSystem saveSystem;
    S_GameState gameState = S_GameState.getInstance();
    public SelectShipGUI(SaveSystem saveSystem, Game gem)
    {
        this.saveSystem = saveSystem;
        this.gem = gem;
    }

    Window window;
    GuiSystem guiSys;
    
    private JButton Ship1Text;
    private JButton Ship2Text;
    private JButton Ship3Text;
    private JButton ExitSelectShip;

    private ImageIcon Ship1Icon = new ImageIcon("data/ShipForward.png");
    private ImageIcon Ship2Icon = new ImageIcon("data/Ship1Forward.png");
    private ImageIcon Ship3Icon = new ImageIcon("data/Asteroid.png");

    private JLabel Option1;
    private JLabel Option2;
    private JLabel Option3;
    private JLabel SavedScoreText;

    @Override
    public void onGuiStart(Window window, GuiSystem guiSys) {
        // TODO Auto-generated method stub
        this.window = window;
        this.guiSys = guiSys;

        int ship1Cost = 0;        
        int ship2Cost = 200;        
        int ship3Cost = 500;        
        
        Ship1Text = new JButton("<html>Select this ship <br>Cost : <html>"+ ship1Cost);
        Ship1Text.setBounds(50, 160, 200, 60);
        Ship1Text.setFont(new Font("", Font.BOLD, 20));
        Ship1Text.setForeground(Color.orange);
        Ship1Text.setBackground(Color.gray);

        Ship2Text = new JButton("<html>Select this ship <br>Cost : <html>"+ ship2Cost);
        Ship2Text.setBounds(300, 160, 180, 60);
        Ship2Text.setFont(new Font("", Font.BOLD, 20));
        Ship2Text.setForeground(Color.orange);
        Ship2Text.setBackground(Color.gray);

        Ship3Text = new JButton("<html>Select this ship <br>Cost : <html>"+ ship3Cost);
        Ship3Text.setBounds(550, 160, 200, 60);
        Ship3Text.setFont(new Font("", Font.BOLD, 20));
        Ship3Text.setForeground(Color.orange);
        Ship3Text.setBackground(Color.gray);

        //----------------------------------------------

        SavedScoreText = new JLabel("Current Score = "+ saveSystem.currentSave.score);
        SavedScoreText.setBounds(5, 5, 400, 24);
        SavedScoreText.setFont(new Font("", Font.BOLD, 24));
        SavedScoreText.setForeground(Color.white);

        Option1 = new JLabel(scaleImage(Ship1Icon,100 ,60));
        Option1.setBounds(50, 180, 200, 200);
        Option2 = new JLabel(scaleImage(Ship2Icon,100 ,60));
        Option2.setBounds(300, 180, 200, 200);
        Option3 = new JLabel(scaleImage(Ship3Icon,80 ,80));
        Option3.setBounds(550, 180, 200, 200);

        ExitSelectShip = new JButton("<html>Return To <br> Main Menu</html>");
        ExitSelectShip.setBounds(300, 350, 150, 80);
        ExitSelectShip.setFont(new Font("", Font.BOLD, 20));
        ExitSelectShip.setForeground(Color.orange);
        ExitSelectShip.setBackground(Color.gray);
        

        window.add(SavedScoreText);
        window.add(Option1);
        window.add(Option2);
        window.add(Option3);
        window.add(Ship1Text);
        window.add(Ship2Text);
        window.add(Ship3Text);
        window.add(ExitSelectShip);
        
    }

    @Override
    public void onGuiUpdate() 
    {
        SavedScoreText.setText("Current Score = "+ saveSystem.currentSave.score);
        // TODO Auto-generated method stub
        if(Ship1Text.getModel().isPressed())
        {
            changeShipCost(0, 0);
            gem.playMenuSound();
        }
        if(Ship2Text.getModel().isPressed())
        {
            changeShipCost(100, 1);
            gem.playMenuSound();
        }
        if(Ship3Text.getModel().isPressed())
        {
            changeShipCost(500, 2);

            gem.playMenuSound();
        }
        if(ExitSelectShip.getModel().isPressed())
        {
            //inHelp = !inHelp;
            window.remove(SavedScoreText);
            window.remove(Option1);
            window.remove(Option2);
            window.remove(Option3);
            window.remove(Ship1Text);
            window.remove(Ship2Text);
            window.remove(Ship3Text);
            window.remove(ExitSelectShip);
            gem.playMenuSound();
            gameState.GameState = GameStates.MAIN_MENU_START;
        }
    }

    // Negates score when one changes ship
    private void changeShipCost(int shipChangeCost, int shipNum)
    {
        if(saveSystem.currentSave.score >= shipChangeCost)
        {
            saveSystem.currentSave.score -= shipChangeCost;
            saveSystem.Save(new SavedInfo(saveSystem.currentSave.score,
                    saveSystem.currentSave.health, saveSystem.currentSave.xPos,
                    saveSystem.currentSave.levelIndex, shipNum));
            gem.player.updateCurrentShip(shipNum);

            //System.out.println((gem.player.score + shipChangeCost));
        }
    }

    @Override
    public void onGuiExit() {
        // TODO Auto-generated method stub
        
    }

    // Scale image
    public ImageIcon scaleImage(ImageIcon icon, int W, int H)//https://stackoverflow.com/questions/6714045/how-to-resize-jlabel-imageicon
    {
        int w = 1, h = 1;
        int nw = icon.getIconWidth();
        int nh = icon.getIconHeight();

        if(icon.getIconWidth() > w)
        {
          nw = w;
          nh = (nw * icon.getIconHeight()) / icon.getIconWidth();
        }

        if(nh > h)
        {
          nh = h;
          nw = (icon.getIconWidth() * nh) / icon.getIconHeight();
        }

        nh *= W;
        nw *= H;
        return new ImageIcon(icon.getImage().getScaledInstance(nw, nh, Image.SCALE_DEFAULT));
    }
}

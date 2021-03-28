package game.gui;

import game.Window;

public interface IGUI 
{
    /***
     * Get called once gui is inserted into guisystem
     *
     * @param window    Window Class to display Gui on ( Window inherits JPanel )
     * @param guiSys    Instantiated GuiSystem class
     */
    public void onGuiStart(Window window, GuiSystem guiSys);
    // Get called once per frame
    public void onGuiUpdate();
    // Gets called when gui is removed from gui system
    public void onGuiExit();
    
}

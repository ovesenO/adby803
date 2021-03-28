package game.gui;

import game.*;

public class GuiSystem 
{
    private IGUI currentGui;
    private Window window;

    public GuiSystem(Window window)
    {
        this.window = window;
    }

    // Updates current gui
    public void UpdateGui()
    {
        if(currentGui != null)
        {
            currentGui.onGuiUpdate();
        }
    }

    // swaps current gui
    public void InsertGui(IGUI newGui)
    {
        if(currentGui != null)
        {
            currentGui.onGuiExit();
        }

        currentGui = newGui;
        currentGui.onGuiStart(window, this);
    }
}

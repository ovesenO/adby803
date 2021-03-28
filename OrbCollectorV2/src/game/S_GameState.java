package game;

// Use singleton pattern for game states
// Neccessary pattern to use to access GameState in multiple places
public class S_GameState 
{
    private static S_GameState instance = null;
    public GameStates GameState = GameStates.PROGRAM_SETUP;

    public static S_GameState getInstance()
    {
        if(instance == null)
        {
            instance = new S_GameState();
        }
        return instance;
    }
    
}

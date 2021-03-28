package game;


//main
public class EntryPoint{
 
    public static Game G;
    public static SaveSystem saveSystem = new SaveSystem();

    public static S_GameState gameState = S_GameState.getInstance();

    /*
    Entry point to the game.
    uses switch statement to switch between different states of the game
    eg.. go from setting up main menu into main menu update.
     */
    public static void main(String[] args)
    {
        G = new Game(saveSystem);
        while(true)
        {
            switch(gameState.GameState)
            {
                case PROGRAM_SETUP:
                G.ProgramSetup();
                break;
                case MAIN_MENU_START:
                G.MainMenuStart();
                break;
                case MAIN_MENU_UPDATE:
                G.MainMenuUpdate();
                break;
                case GAME_START:
                G.GameStart();
                break;
                case GAME_END:
                G.GameEnd();
                break;
                case GAME_UPDATE:
                G.GameUpdate();
                break;
                case GAME_PAUSED:
                break;
            }
        }
    }
}
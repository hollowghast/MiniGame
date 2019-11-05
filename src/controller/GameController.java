package controller;

import dal.LocalFileAccess;
import graphics.GameMenu;
import graphics.MainFrame;
import graphics.MainMenu;
import graphics.SettingsMenu;
import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import settings.FilesEnum;


//in progress
//change to statebasedgame
public class GameController extends StateBasedGame
{

    private static final String GAME_TITLE = "Basic First Slick Game";
    
    
    // Game state identifiers
    public static final int MAIN_GAME = 0;
    public static final int MAIN_MENU = 1;
    public static final int SETTINGS_MENU = 2;
    public static final int GAME_MENU = 3;
    
    public GameController()
            throws SlickException
    {
        super(GAME_TITLE);
    }


    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        this.addState(new MainMenu());
        this.addState(new SettingsMenu());
        this.addState(new GameMenu());
        this.addState(new MainFrame()); //first added will be started first
    }
}

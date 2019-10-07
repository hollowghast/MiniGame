package controller;

import graphics.MainFrame;
import graphics.MainMenu;
import graphics.SettingsMenu;
import org.newdawn.slick.*;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


//in progress
//change to statebasedgame
public class GameController extends StateBasedGame
{

    private static final String GAME_TITLE = "Basic First Slick Game";
    
    
    // Game state identifiers
    public static final int MAIN_GAME = 0;
    public static final int MAIN_MENU = 1;
    public static final int SETTINGS_MENU = 2;
    

    private final MainMenu menu;
    private final MainFrame game;
    
    public GameController()
            throws SlickException
    {
        super(GAME_TITLE);
        
        menu = new MainMenu();
        game = new MainFrame();
    }

    @Override
    protected void postUpdateState(GameContainer container, int delta) throws SlickException
    {
        //change to enum
        GameState curr = super.getCurrentState();
        if(curr == menu){
            //this.enterState(MAIN_GAME);
        }
        else if(curr == game){
            
        }
        
    }

    
    
    @Override
    protected void preUpdateState(GameContainer container, int delta) throws SlickException
    {
        super.preUpdateState(container, delta); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void postRenderState(GameContainer container, Graphics g) throws SlickException
    {
        super.postRenderState(container, g); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void preRenderState(GameContainer container, Graphics g) throws SlickException
    {
        super.preRenderState(container, g); //To change body of generated methods, choose Tools | Templates.
    }
    

    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        this.addState(menu);
        this.addState(new SettingsMenu());
        this.addState(game); //first added will be started first
    }
}

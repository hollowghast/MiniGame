package controller;

import graphics.MainFrame;
import graphics.MainMenu;
import java.io.File;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;


//in progress
//change to statebasedgame
public class GameController extends StateBasedGame
{

    // Game state identifiers
    public static final int MAIN_GAME = 0;
    public static final int MAIN_MENU = 1;
    //public static final int GAME         = 2;

    public GameController()
            throws SlickException
    {
        super("Main Controller");
    }

    @Override
    protected void postUpdateState(GameContainer container, int delta) throws SlickException
    {
        super.postUpdateState(container, delta); //To change body of generated methods, choose Tools | Templates.
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
        this.addState(new MainMenu());
        this.addState(new MainFrame()); //first added will be started first
    }
}

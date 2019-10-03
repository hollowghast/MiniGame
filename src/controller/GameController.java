package controller;

import graphics.MainFrame;
import java.io.File;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.ImageData;
import org.newdawn.slick.state.StateBasedGame;


//in progress
//change to statebasedgame
public class GameController extends StateBasedGame
{

    // Game state identifiers
    public static final int MAIN_GAME = 0;
    public static final int MAIN_MENU = 1;
    //public static final int GAME         = 2;

    // Application Properties
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int FPS = 45;
    public static final double VERSION = 2019.10;



    public GameController()
            throws SlickException
    {
        super("Main Controller");
    }


    public static void main(String[] args)
    {
        System.setProperty("org.lwjgl.librarypath", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());
        //System.setProperty("java.library.path", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());

        try
        {
            AppGameContainer container = new AppGameContainer(new GameController(), WIDTH, HEIGHT, false);
            container.setTargetFrameRate(FPS);
            container.setShowFPS(true);
            container.start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    @Override
    public void initStatesList(GameContainer container) throws SlickException
    {
        this.addState(new MainFrame()); //first added will be started first
    }
}

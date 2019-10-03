package controller;

import java.io.File;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Game;
import org.newdawn.slick.SlickException;

/**
 *
 * @author owner
 */
public class MainController extends AppGameContainer
{
    // Application Properties
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final int FPS = 45;
    public static final double VERSION = 2019.10;
    
    public MainController(Game game) throws SlickException
    {
        super(game);
    }


    public static void main(String[] args)
    {
        System.setProperty("org.lwjgl.librarypath", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());
        //System.setProperty("java.library.path", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());

        try
        {
            MainController controller = new MainController(new GameController());
            controller.setDisplayMode(WIDTH, HEIGHT, false);
            controller.setTargetFrameRate(FPS);
            controller.setShowFPS(true);
            controller.start();
        } catch (SlickException e)
        {
            e.printStackTrace();
        }

    }

}

package controller;

import graphics.MainFrame;
import java.io.File;
import org.lwjgl.input.Cursor;
import org.newdawn.slick.*;
import org.newdawn.slick.opengl.ImageData;


//in progress
public class GameController extends AppGameContainer
{

    private KeyListener keyListener;

    public GameController(Game game)
            throws SlickException
    {
        super(game);
        init();
    }

    public GameController(Game game, int width, int height, boolean fullscreen)
            throws SlickException
    {
        super(game, width, height, fullscreen);
        init();
    }

    private void init()
            throws SlickException
    {
        this.setDisplayMode(300, 300, false);
        this.setTargetFrameRate(45);
    }


    public static void main(String[] args)
    {
        System.setProperty("org.lwjgl.librarypath", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());
        //System.setProperty("java.library.path", new File("E:\\POS\\Projects\\APIs\\slick").getAbsolutePath());
        
        try
        {
            new GameController(new MainFrame("Basic Game"), 300, 300, false).start();
        } catch (Exception e)
        {
            e.printStackTrace();
        }


    }


    @Override
    public int getScreenWidth()
    {
        return this.getWidth();
    }

    @Override
    public int getScreenHeight()
    {
        return this.getHeight();
    }

    @Override
    public void setIcon(String ref) throws SlickException
    {
    }

    @Override
    public void setIcons(String[] refs) throws SlickException
    {
    }

    @Override
    public void setMouseCursor(String ref, int hotSpotX, int hotSpotY) throws SlickException
    {
    }

    @Override
    public void setMouseCursor(ImageData data, int hotSpotX, int hotSpotY) throws SlickException
    {
    }

    @Override
    public void setMouseCursor(Image image, int hotSpotX, int hotSpotY) throws SlickException
    {
    }

    @Override
    public void setMouseCursor(Cursor cursor, int hotSpotX, int hotSpotY) throws SlickException
    {
    }

    @Override
    public void setDefaultMouseCursor()
    {
    }

    @Override
    public void setMouseGrabbed(boolean grabbed)
    {
    }

    @Override
    public boolean isMouseGrabbed()
    {
        return false;
    }

    @Override
    public boolean hasFocus()
    {
        return true;
    }
}

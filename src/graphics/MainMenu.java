package graphics;

import java.util.Random;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author owner
 */
public class MainMenu extends BasicGameState implements MouseListener
{
    /**
     * ID for MainMenu
     */
    private static final int ID = 1;

    private boolean close;

    public MainMenu()
    {
        close = false;
    }
    
    
    
    @Override
    public int getID()
    {
        return MainMenu.ID;
    }

    
    
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        //init menu
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        //draw Text
        Random randy = new Random();
        g.drawLine(randy.nextInt(120)+10, randy.nextInt(120)+10, 
                randy.nextInt(120)+10, randy.nextInt(120)+10);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        //check for mouse-input
        if(close){
            //this.leave(container, game);
            container.exit();
        }
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
        //todo: check if left click on menu item
        //open next menu
        //super.mouseClicked(button, x, y, clickCount);
        
        if(x < 100 && y < 100 && button == org.newdawn.slick.Input.MOUSE_LEFT_BUTTON){
            JOptionPane.showConfirmDialog(null, "test", "Title", JOptionPane.INFORMATION_MESSAGE);
            close = true;
        }
    }
    
    
}

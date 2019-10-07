package graphics;

import java.util.Random;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author owner
 */
public class SettingsMenu extends AbstractMenu
{

    private final static int ID = 2;
    
    @Override
    public int getID()
    {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        Random r = new Random();
        g.drawRect(r.nextInt(container.getWidth()), r.nextInt(container.getHeight()), r.nextInt(container.getWidth()), r.nextInt(container.getHeight()));
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
    {
        
    }
    
}

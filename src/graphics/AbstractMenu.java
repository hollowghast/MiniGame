package graphics;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public abstract class AbstractMenu extends BasicGameState
        implements MouseListener
{
    
    protected static final int DEFAULT_CHAR_HEIGHT = 20;
    protected static final int DEFAULT_CHAR_WIDTH = 12;
    
    protected Vector2f lastMenuPosition;
    
    protected List<MenuItem> menuItems = new ArrayList<MenuItem>()
    {
        @Override
        public boolean add(MenuItem e)
        {
            return super.add(e);
        }
    };

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
            throws SlickException
    {
        //check for clicked menues
        
        for (MenuItem mi : menuItems)
        {
            if (mi.isClicked())
            {
                mi.switchState(container, game);
            }
        }
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
            throws SlickException
    {
        //System.out.println(g.getFont().getWidth("A"));
        for (MenuItem mi : menuItems)
        {
            //g.setFont(new TrueTypeFont(java.awt.Font.decode(Font.SERIF), true));
            g.drawString(mi.getText(), mi.getX(), mi.getY());
        }
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
            throws SlickException
    {
        lastMenuPosition = new Vector2f(container.getWidth() / 8,
                container.getHeight() / 10);
        
        //init menu
        menuItems = new ArrayList<>();
    }
    
    
    
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount)
    {
        //todo: check if left click on menu item
        //check all menuitems
        
        //System.out.println("Mouse click: x: " + x + " ; y: " + y);
        for (MenuItem mi : menuItems)
        {
            if (mi.isAt(x, y)) //width and height of menu item has to be repaired
            {
                //mark as clicked
                mi.setClicked(true);
                break;
            }
        }
    }
    
    protected void syncLastMenuPosition(){
        lastMenuPosition = (new Vector2f(lastMenuPosition.x,
                lastMenuPosition.y + DEFAULT_CHAR_HEIGHT));
    }
    
    
    
}

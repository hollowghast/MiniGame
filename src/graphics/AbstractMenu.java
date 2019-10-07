package graphics;

import java.util.ArrayList;
import java.util.List;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;

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

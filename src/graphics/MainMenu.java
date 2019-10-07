package graphics;

import controller.GameController;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author owner
 */
public class MainMenu extends AbstractMenu
{

    /**
     * ID for MainMenu
     */
    private static final int ID = 1;

    @Override
    public int getID()
    {
        return MainMenu.ID;
    }


    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        lastMenuPosition = new Vector2f(container.getWidth() / 8,
                container.getHeight() / 10);

        //init menu
        menuItems = new ArrayList<>();
        
        String title = "Game";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                //container.exit();
                System.out.println("Game Menu");
                
            }

        });
        syncLastMenuPosition();
        
        title = "Settings";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                //container.exit();
                //System.out.println("Settings");
                game.enterState(GameController.SETTINGS_MENU);
            }

        });
        syncLastMenuPosition();
        
        title = "Exit";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                System.out.println("Exit");
                container.exit();
            }

        });
        syncLastMenuPosition();
        
        //game.enterState(0);
        
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
    {
        //System.out.println(g.getFont().getWidth("A"));
        for (MenuItem mi : menuItems)
        {
            //g.setFont(new TrueTypeFont(java.awt.Font.decode(Font.SERIF), true));
            g.drawString(mi.getText(), mi.getX(), mi.getY());
        }

//        Random randy = new Random();
//        g.drawLine(randy.nextInt(120)+10, randy.nextInt(120)+10, 
//                randy.nextInt(120)+10, randy.nextInt(120)+10);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
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
    
}

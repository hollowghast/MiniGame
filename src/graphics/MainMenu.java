package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
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
        super.init(container, game);
        
        String title = "Game";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
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
                super.switchState(container, game);
                //container.exit();
                //System.out.println("Settings");
                
                ColorChangingDialog ccd = new ColorChangingDialog(null, true);
                //ccd.setLocation((int) this.getX(),(int) this.getY());
                //ccd.setLocation((int) super.getX(),(int) super.getY());
                //ccd.setLocation(container.getScreenWidth()/2, container.getHeight()/2);
                ccd.setLocation(container.getWidth(), container.getHeight());
                ccd.setTitle("Set Colors");
                ccd.setVisible(true);
                ccd.dispose();
                
                //game.enterState(GameController.SETTINGS_MENU);
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
                super.switchState(container, game);
                //System.out.println("Exit");
                container.exit();
            }

        });
        syncLastMenuPosition();
        
        //game.enterState(0);
        
    }

    
}

package graphics;

import controller.GameController;
import static graphics.AbstractMenu.DEFAULT_CHAR_WIDTH;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
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
        super.init(container, game);
        
        String title = "Switch Color";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
                //container.exit();
                System.out.println("Color Menu");
                
                ColorChangingDialog ccd = new ColorChangingDialog(null, true);
                ccd.setLocation(container.getWidth(), container.getHeight());
                ccd.setTitle("Set Colors");
                ccd.setVisible(true);
                ccd.dispose();
            }

        });
        syncLastMenuPosition();
        
        title = "Back";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
                //container.exit();
                game.enterState(GameController.MAIN_MENU);
            }

        });
        syncLastMenuPosition();
    }
}

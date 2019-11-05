package graphics;

import controller.GameController;
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
                
                game.enterState(GameController.GAME_MENU);
                
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
                super.switchState(container, game);
                //System.out.println("Exit");
                container.exit();
            }

        });
        syncLastMenuPosition();
        
        
    }

    
}

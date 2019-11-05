/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import controller.GameController;
import dal.LocalFileAccess;
import dal.NameOfTable;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author owner
 */
public class GameMenu extends AbstractMenu
{

    private static final int ID = 3;

    @Override
    public int getID()
    {
        return ID;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.init(container, game);

        String title = "Main Game";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
                //container.exit();
                System.out.println("Start new game");

                game.enterState(GameController.MAIN_GAME);
            }

        });
        syncLastMenuPosition();

        title = "Save Game";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
                //container.exit();
                System.out.println("Save game");
                try
                {
                    LocalFileAccess.DBAccess.DBWriter.insertDataIntoTable(MainFrame.getGameData(), NameOfTable.GAME);
                    JOptionPane.showMessageDialog(null, "Successfully written to DB.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        syncLastMenuPosition();

        title = "Load Game";
        menuItems.add(new MenuItem(lastMenuPosition.x,
                lastMenuPosition.y, title.length() * DEFAULT_CHAR_WIDTH,
                DEFAULT_CHAR_HEIGHT, title)
        {
            @Override
            protected void switchState(GameContainer container, StateBasedGame game)
            {
                super.switchState(container, game);
                //container.exit();
                System.out.println("Load game");
                try
                {
                    Properties gameData = LocalFileAccess.DBAccess.DBReader.readLastGame();
                    if(gameData == null) throw new NullPointerException("Error while loading values from DB");
                    JOptionPane.showMessageDialog(null, "Successfully loaded data from DB.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    MainFrame.changeGameData(gameData);
                    System.out.println("done");
                } catch (SQLException ex)
                {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Failure", JOptionPane.ERROR_MESSAGE);
                }
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

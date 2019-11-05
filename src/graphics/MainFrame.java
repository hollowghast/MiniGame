package graphics;

import controller.GameController;
import gameobjects.Ball;
import gameobjects.Enemy;
import dal.ColumnNameForTableGameInDB;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import org.jdom2.JDOMException;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class is mainly used for graphics
 *
 * @author Daniel
 */
public class MainFrame extends BasicGameState
{

    /**
     * id for mainWindow
     */
    private static final int ID = 0;

    private static final EnemyInventory ENEMY_INVENTORY;

    static
    {
        ENEMY_INVENTORY = new EnemyInventory();
    }

    /**
     * Message shown in upper right corner of MainFrame
     *
     * @see MainFrame
     */
    private final static String SCORE_MESSAGE = "Score: ";
    private static long score = 0;

    private static Ball mainPlayer;


    @Override
    public int getID()
    {
        return MainFrame.ID;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException
    {
        mainPlayer = new Ball(gc.getWidth() / 2, gc.getHeight() / 2);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException
    {
        try
        {
            grphcs.setColor(new org.newdawn.slick.Color(ColorInventory.getColorFor("text").getRGB()));

            grphcs.drawString(SCORE_MESSAGE + score, gc.getWidth() - gc.getWidth() / 2, 0);

            grphcs.setColor(new org.newdawn.slick.Color(ColorInventory.getColorFor("player").getRGB()));
            grphcs.fill(mainPlayer);

            for (Enemy e : ENEMY_INVENTORY.getEnemies())
            {
                grphcs.setColor(e.getFillColor());
                grphcs.fill(e);
            }
        } catch (JDOMException ex)
        {
            ex.printStackTrace();
        }

    }


    @Override
    public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException
    {
        // move input to InputProcessor

        Vector2f newPos = new Vector2f(mainPlayer.getX(), mainPlayer.getY());
        //up
        if (gc.getInput().isKeyDown(Input.KEY_W))
        {
            newPos.y -= Ball.SPEED;
            if (newPos.y < 0f)
            {
                newPos.y = 0f;
            }
        }
        //down
        if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            newPos.y += Ball.SPEED;
            if (newPos.y > gc.getHeight() - mainPlayer.getHeight())
            {
                newPos.y = gc.getHeight() - mainPlayer.getHeight();
            }
        }
        //left
        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            newPos.x -= Ball.SPEED;
            if (newPos.x < 0)
            {
                newPos.x = 0;
            }
        }
        //right
        if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            newPos.x += Ball.SPEED;
            if (newPos.x > gc.getWidth() - mainPlayer.getWidth())
            {
                newPos.x = gc.getWidth() - mainPlayer.getWidth();
            }
        }
        mainPlayer.setX(newPos.x);
        mainPlayer.setY(newPos.y);


        if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
        {
            game.enterState(GameController.MAIN_MENU);
        }

        Iterator<Enemy> enemyIterator = ENEMY_INVENTORY.getEnemies().iterator();
        Enemy e = null;
        while (enemyIterator.hasNext())
        {
            e = enemyIterator.next();
            if (mainPlayer.intersects(e))
            {
                enemyIterator.remove();
                score += e.getEnemyValue();
                ENEMY_INVENTORY.addRandom(
                        new Enemy(
                                new Random()
                                        .nextInt(gc.getWidth() - Enemy.DEFAULT_WIDTH),
                                new Random()
                                        .nextInt(gc.getHeight() - Enemy.DEFAULT_HEIGHT)
                        )
                );
                break;
            }
        }
    }


    public static Properties getGameData()
    {
        Properties props = new Properties();
        props.setProperty(ColumnNameForTableGameInDB.BALLPOSX.toString(), "" + (int) mainPlayer.getX());
        props.setProperty(ColumnNameForTableGameInDB.BALLPOSY.toString(), "" + (int) mainPlayer.getY());
        props.setProperty(ColumnNameForTableGameInDB.CURRSCORE.toString(), "" + score);
        Enemy e = ENEMY_INVENTORY.getEnemies().get(0);
        props.setProperty(ColumnNameForTableGameInDB.ENEMYPOSX.toString(), "" + (int) e.getX());
        props.setProperty(ColumnNameForTableGameInDB.ENEMYPOSY.toString(), "" + (int) e.getY());
        return props;
    }

    public static void changeGameData(Properties newData)
    {
        String dataValue;
        Integer value;
        Properties ordered = new Properties();
        System.out.println(newData.get(new String()));
        
        for (String name : ColumnNameForTableGameInDB.getNecessaryColumnsInOrder().split(","))
        {
            name = name.replace('\"', '\0');
            name = name.replace(',', '\0');
            name = name.trim();

            dataValue = ""+newData.get(name);
            System.out.println(dataValue);
            value = Integer.parseInt(dataValue);
            
            ordered.put(name, value);
        }
        
        
        mainPlayer.setX(Float.parseFloat(""+ordered.get(ColumnNameForTableGameInDB.BALLPOSX.toString())));
        mainPlayer.setY(Float.parseFloat(""+ordered.get(ColumnNameForTableGameInDB.BALLPOSY.toString())));
        Enemy e = ENEMY_INVENTORY.getEnemies().get(0);
        e.setX(Float.parseFloat(""+ordered.get(ColumnNameForTableGameInDB.ENEMYPOSX.toString())));
        e.setY(Float.parseFloat(""+ordered.get(ColumnNameForTableGameInDB.ENEMYPOSY.toString())));
        ENEMY_INVENTORY.getEnemies().set(0, e);
        score = Long.parseLong(""+ordered.get(ColumnNameForTableGameInDB.BALLPOSX.toString()));
    }
}

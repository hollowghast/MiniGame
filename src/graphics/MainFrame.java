package graphics;

import gameobjects.Ball;
import gameobjects.Enemy;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class holds basic game functionalities but mainly graphics
 * @author Daniel
 */
public class MainFrame extends BasicGameState
{
    /**
     * id for mainWindow
     */
    private static final int ID = 0;

    private final EnemyInventory enemyInv;

    /**
     * Message shown in upper right corner of MainFrame
     * @see MainFrame
     */
    private final static String SCORE_MESSAGE = "Score: ";
    private static int score = 0;

    private Ball mainPlayer;
    

    public MainFrame()
    {
        enemyInv = new EnemyInventory();
    }

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
        grphcs.setColor(Color.white);
        grphcs.drawString(SCORE_MESSAGE + score, gc.getWidth()-150, 0);
        
        grphcs.setColor(mainPlayer.getFillColor());
        grphcs.fill(mainPlayer);

        for (Enemy e : enemyInv.getEnemies())
        {
            grphcs.setColor(e.getFillColor());
            grphcs.fill(e);
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
            if(newPos.y < 0f){
                newPos.y = 0f;
            }
        }
        //down
        if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            newPos.y += Ball.SPEED;
            if(newPos.y > gc.getHeight()-mainPlayer.getHeight()){
                newPos.y = gc.getHeight()-mainPlayer.getHeight();
            }
        }
        //left
        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            newPos.x -= Ball.SPEED;
            if(newPos.x < 0){
                newPos.x = 0;
            }
        }
        //right
        if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            newPos.x += Ball.SPEED;
            if(newPos.x > gc.getWidth()-mainPlayer.getWidth()){
                newPos.x = gc.getWidth()-mainPlayer.getWidth();
            }
        }
        mainPlayer.setX(newPos.x);
        mainPlayer.setY(newPos.y);
        

        if (gc.getInput().isKeyDown(Input.KEY_ESCAPE))
        {
            gc.exit();
        }

        Iterator<Enemy> enemyIterator = enemyInv.getEnemies().iterator();
        Enemy e = null;
        while (enemyIterator.hasNext())
        {
            e = enemyIterator.next();
            if (mainPlayer.intersects(e))
            {
                enemyIterator.remove();
                score += e.getEnemyValue();
                enemyInv.addRandom(
                        new Enemy(
                            new Random()
                                    .nextInt(gc.getWidth()+
                                            Enemy.DEFAULT_WIDTH)-
                                    Enemy.DEFAULT_WIDTH, 
                            new Random()
                                    .nextInt(gc.getHeight()+
                                            Enemy.DEFAULT_HEIGHT)-
                                    Enemy.DEFAULT_HEIGHT));
                break;
            }
        }
    }

}

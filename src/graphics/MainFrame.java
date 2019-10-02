package graphics;

import gameobjects.Ball;
import gameobjects.Enemy;
import java.util.Iterator;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class MainFrame extends BasicGame
{

    private final EnemyInventory enemyInv;
    
    private final static String SCORE_MESSAGE = "Score: ";
    private static int score = 0;

    private Ball mainPlayer;

    private final Vector2f transition;

    public MainFrame(String gamename)
    {
        super(gamename);
        enemyInv = new EnemyInventory();
        transition = new Vector2f();
    }


    @Override
    public void init(GameContainer gc) throws SlickException
    {
        mainPlayer = new Ball(gc.getWidth() / 2, gc.getHeight() / 2);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException
    {
        // move input to InputProcessor
        //up
        if (gc.getInput().isKeyDown(Input.KEY_W))
        {
            transition.y -= Ball.SPEED;
            if(transition.y < 0){
                transition.y = 0;
            }
        }
        //down
        if (gc.getInput().isKeyDown(Input.KEY_S))
        {
            transition.y += Ball.SPEED;
            if(transition.y > gc.getHeight()-mainPlayer.getHeight()){
                transition.y = gc.getHeight()-mainPlayer.getHeight();
            }
        }
        //left
        if (gc.getInput().isKeyDown(Input.KEY_A))
        {
            transition.x -= Ball.SPEED;
            if(transition.x < 0){
                transition.x = 0;
            }
        }
        //right
        if (gc.getInput().isKeyDown(Input.KEY_D))
        {
            transition.x += Ball.SPEED;
            if(transition.x > gc.getWidth()-mainPlayer.getWidth()){
                transition.x = gc.getWidth()-mainPlayer.getWidth();
            }
        }
        mainPlayer.setX(transition.x);
        mainPlayer.setY(transition.y);

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


    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException
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

}

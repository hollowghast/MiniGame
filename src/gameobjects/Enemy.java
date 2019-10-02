package gameobjects;

import graphics.EnemyInventory;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends Rectangle
{

    public static final Color DEFAULT_FILLCOLOR = Color.cyan;
    private Color fillColor;

    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    
    private int enemyValue;
    
    private final int id;
    public int getID(){
        return id;
    }


    public Enemy(float x, float y)
    {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        id = EnemyInventory.nextID();
        enemyValue = id + new Random().nextInt(5);
    }

    public Color getFillColor()
    {
        if (fillColor == null)
        {
            return DEFAULT_FILLCOLOR;
        }
        return fillColor;
    }
    
    public int getEnemyValue(){
        return enemyValue;
    }
}

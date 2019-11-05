package gameobjects;

import graphics.EnemyInventory;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Rectangle;

public class Enemy extends Rectangle implements Observer
{

    public static final Color DEFAULT_FILLCOLOR = Color.cyan;
    private Color fillColor = DEFAULT_FILLCOLOR;

    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;
    
    private final long enemyValue;
    
    private ObservableSubject subject;
    
    private final long id;
    public long getID(){
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
        return fillColor;
    }
    
    public long getEnemyValue(){
        return enemyValue;
    }

    @Override
    public void update()
    {
        Object obj = subject.getValueFor(Enemy.class.getName());
        if(obj instanceof Color){
            fillColor = (Color) obj;
        }
    }

    @Override
    public void setObservableSubject(ObservableSubject sub)
    {
        subject = sub;
    }
    
}

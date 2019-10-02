package gameobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends Ellipse
{
    public static final int SPEED = 10;
    public static final int DIAMETER = 20;
    
    public static final Color DEFAULT_FILLCOLOR = Color.white;
    private Color fillColor;
    
    private final Vector2f position;
    
    public Ball(float x, float y)
    {
        super(x, y, DIAMETER, DIAMETER);
        position = new Vector2f(x, y);
    }
    
    public void translate(Vector2f transition){
        position.add(transition);
    }
    
     public Color getFillColor()
    {
        if (fillColor == null)
        {
            return DEFAULT_FILLCOLOR;
        }
        return fillColor;
    }
}

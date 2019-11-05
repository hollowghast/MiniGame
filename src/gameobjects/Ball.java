package gameobjects;

import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Ellipse;
import org.newdawn.slick.geom.Vector2f;

public class Ball extends Ellipse implements Observer
{
    public static final int SPEED = 10;
    public static final int DIAMETER = 20;
    
    public static final Color DEFAULT_FILLCOLOR = Color.white;
    private Color fillColor;
    
    private final Vector2f position;
    
    private ObservableSubject<Color> subject;
    
    public Ball(float x, float y)
    {
        super(x, y, DIAMETER, DIAMETER);
        position = new Vector2f(x, y);
    }
    
    public void translate(Vector2f transition){
        position.add(transition);
    }
    
    public void setColor(Color c){
        this.fillColor = c;
    }
    
    public Color getFillColor()
    {
        if (fillColor == null)
        {
            return DEFAULT_FILLCOLOR;
        }
        return fillColor;
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

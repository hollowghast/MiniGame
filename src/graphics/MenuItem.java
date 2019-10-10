package graphics;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author owner
 */
public abstract class MenuItem
{
    
    private final Vector2f startPosition, endPosition;
    private final String text;
    private boolean clicked;

    public MenuItem(float x, float y, float width, float height, String text)
    {
        
        startPosition = new Vector2f(x, y);
        endPosition = new Vector2f(x + width,
                y + height);
        this.text = text;
        clicked = false;
    }


    public String getText()
    {
        return text;
    }


    public boolean isAt(int x, int y){
        //System.out.println("mi startposition: " + startPosition);
        //System.out.println("mi endposition: " + endPosition);
        
        if(x > startPosition.x && x < endPosition.x){
            if(y > startPosition.y && y < endPosition.y){
                return true;
            }
        }
        return false;
    }
    
    public void setClicked(boolean clicked){
        this.clicked = clicked;
    }
    
    public boolean isClicked(){
        return clicked;
    }
    
    public float getX(){
        return this.startPosition.x;
    }
    
    public float getY(){
        return this.startPosition.y;
    }
    
    public float getWidth(){
        return this.startPosition.x + this.endPosition.x;
    }
    
    public float getHeight(){
        return this.startPosition.y  + this.endPosition.y;
    }
    
    protected void switchState(GameContainer container, StateBasedGame game){
        this.clicked = false;
    }
}

package gameobjects;

import java.util.Map;

/**
 *
 * @author owner
 */
public interface ObservableSubject<T>
{
    public void register(Observer obs);
    public void unregister(Observer obs);
    
    public void notifyObservers();
    
    public Map getValues();
    public T getValueFor(String key);
}

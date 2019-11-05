package graphics;

import dal.LocalFileAccess;
import gameobjects.ObservableSubject;
import gameobjects.Observer;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.jdom2.JDOMException;
import settings.FilesEnum;

/**
 *
 * @author owner
 */
public class ColorInventory implements ObservableSubject<Color>
{

    public static Map<String, Color> colors; 
    private static List<Observer> observers;
    
    static{
        colors = new TreeMap();
        observers = new ArrayList();
    }
    
    
    public static Color getColorFor(String name)
            throws JDOMException
    {
        if(colors.get(name) == null){
            //System.out.println("color gets loaded");
            loadColorOf(name);
        }
        return colors.get(name);
    }
    
    //delegating to dal
    public static void loadColorOf(String ename)
            throws JDOMException
    {
        LocalFileAccess.XMLAccess.setFile(new File(FilesEnum.COLORS.getPath()));
        try
        {//System.out.println("reading xml");
            //read file
            LocalFileAccess.XMLAccess.XMLReader.read();
            
            Color color = Color.decode(
                    LocalFileAccess.XMLAccess.XMLReader
                            .getElement("Color", ename, null, null)
                            .getText().trim());
            System.out.println(color);
            
            colors.put(ename, color);
            
        } catch (IOException ex)
        {
            System.err.println("File not accessible");
        }catch(JDOMException ex){
            throw ex;
        }
    }
    
    public static void saveColorOf(String ename, String hexCode)
            throws JDOMException
    {
        LocalFileAccess.XMLAccess.setFile(new File(FilesEnum.COLORS.getPath()));
        try
        {
            //read file
            LocalFileAccess.XMLAccess.XMLReader.read();
            LocalFileAccess.XMLAccess.XMLWriter.setValueFor(
                    LocalFileAccess.XMLAccess.XMLReader
                            .getElement(ename, null, null, null), hexCode);
            
        } catch (IOException | JDOMException ex)
        {
            ex.printStackTrace();
        }
        
        throw new JDOMException("No entry " + ename + " found");
    }

    @Override
    public void register(Observer obs)
    {
        observers.add(obs);
    }

    @Override
    public void unregister(Observer obs)
    {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers()
    {
        for(Observer o : observers){
            o.update();
        }
    }


    @Override
    public Color getValueFor(String key)
    {
        return colors.get(key);
    }

    @Override
    public Map getValues()
    {
        return colors;
    }
    
}

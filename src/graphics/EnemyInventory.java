package graphics;

import gameobjects.Enemy;
import java.util.ArrayList;
import java.util.List;

public class EnemyInventory
{
    private final List<Enemy> enemies;
    
    private static int lastID = 0;
    public static final synchronized int nextID(){
        lastID++;
        return lastID;
    }

    public EnemyInventory()
    {
        enemies = new ArrayList<>();
        
        //test
        enemies.add(new Enemy(120, 120));
    }
    
    public List<Enemy> getEnemies(){
        return enemies;
    }
    
    public void addRandom(Enemy e){
        enemies.add(e);
    }
    
//    public void remove(int id){
//        for(Enemy e : enemies){
//            if(e.getID() == id){
//                enemies.remove(e);
//            }
//        }
//        
//        for(int i = 0; i< enemies.size(); i++){
//            if(enemies.get(i).getID() == id){
//                enemies.remove(i);
//            }
//        }
//        
//    }
}

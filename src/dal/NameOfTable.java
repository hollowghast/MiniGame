/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 *
 * @author owner
 */
public enum NameOfTable
{
    GAME("Game"), SETTING("SETTING"), SETTING2GAME("SettingToGame");

    private String nameOfTable;
    private NameOfTable(String name)
    {
        this.nameOfTable = name;
    }

    @Override
    public String toString()
    {
        return "\"" + this.nameOfTable + "\"";// " for the dbms
    }
    
    public String getNeutralizedName(){
        return "'||\"" + this.nameOfTable + "\"||'";
    }
    
}

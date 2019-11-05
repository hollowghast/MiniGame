/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

/**
 * Used for saving and loading data to and from local/remote places(xml/db).
 *
 * @author owner
 */
public enum ColumnNameForTableGameInDB
{
    GAMEID("GameID", 1, false),
    BALLPOSX("BallPositionX", 2, true), BALLPOSY("BallPositionY", 3, true),
    ENEMYPOSX("EnemyPositionX", 4, true), ENEMYPOSY("EnemyPositionY", 5, true),
    CURRSCORE("CurrentScore", 6, true);

    private final String columnNameInDB;
    private final int orderingNumber;
    private final boolean necessary;

    private ColumnNameForTableGameInDB(String name, int i, boolean necessary)
    {
        this.columnNameInDB = name;
        this.orderingNumber = i;
        this.necessary = necessary;
    }

    @Override
    public String toString()
    {
        return this.columnNameInDB;
    }

    public String getQuotedName(){
        return "\"" + this.columnNameInDB + "\"";
    }
    
    private int getOrderingNumber()
    {
        return this.orderingNumber;
    }

    private boolean isNecessary()
    {
        return this.necessary;
    }

    public static String getColumnsInOrder()
    {
        String[] ordered = new String[values().length];
        for (int i = 0; i < values().length; i++)
        {
            ordered[(values()[i]).getOrderingNumber() - 1] = (values()[i]).toString();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ordered.length; i++)
        {
            sb.append("\"");
            sb.append(ordered[i]);
            sb.append("\"");
            if (i < ordered.length - 1)
            {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static String getNecessaryColumnsInOrder()
    {
        String[] ordered = new String[values().length];
        for (int i = 0; i < values().length; i++)
        {
            if ((values()[i]).isNecessary())
            {
                ordered[(values()[i]).getOrderingNumber() - 1] = (values()[i]).toString();
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ordered.length; i++)
        {
            if (ordered[i] != null)
            {
                sb.append("\"");
                sb.append(ordered[i]);
                sb.append("\"");
                if (i < ordered.length - 1)
                {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

}

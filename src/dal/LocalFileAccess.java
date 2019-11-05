package dal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 *
 * @author owner
 */
public class LocalFileAccess
{

    /**
     * before using XMLAccess always use setFile(File file) to indicate which
     * file to be used when using XMLAccess, notice that this class isnt
     * thread-safe
     */
    public static class XMLAccess
    {

        private static SAXBuilder builder;
        private static XMLOutputter out;

        private static Document doc;

        private static File file;


        /**
         * Sets the file to work with
         *
         * @param f
         */
        public static void setFile(File f)
        {
            try
            {
                f.createNewFile();
            } catch (IOException ex)
            {
                System.err.println("New file couldnt be created");
            }
            file = f;
        }


        public static class XMLWriter
        {

            static
            {
                out = new XMLOutputter(Format.getPrettyFormat());
            }

            public static void createNewDoc(String rootName)
            {
                doc = new Document(new Element(rootName));
            }

            public static void writeCurrentDocument()
                    throws IOException, JDOMException
            {
                out.output(doc, new FileWriter(file));
            }

            public static void setValueFor(Element e, String val)
            {
                e.setText(val);
            }

            public static void addElement(String name, String value)
            {
                Element e = new Element(name);
                e.setText(value);
                doc.getRootElement().addContent(e);
            }

            public static void addChild(String parentName, String name, String value)
            {
                Element c = new Element(name);
                c.setText(value);
                Element parent = null;
                try
                {
                    parent = XMLAccess.XMLReader.getElement(parentName, null, null, null);
                } catch (JDOMException ex)
                {

                }
                if (parent != null)
                {
                    parent.addContent(c);
                }
            }


            public static void emptyDocument()
            {
                doc.removeContent();
            }
        }

        public static class XMLReader
        {

            static
            {
                builder = new SAXBuilder();
            }

            public static void read()
                    throws IOException, JDOMException
            {
                doc = builder.build(file);
            }

            /**
             * Set null or an empty string for every parameter which is not
             * necessary
             * <br>
             * Names and values are <b>not</b> case sensitive
             *
             * @param eName Name of wanted node
             * @param pName Name of parent of the wanted node
             * @param attrKey Key for necessesary attribute
             * @param attrVal Value fitting for key of necessesary attribute
             * @return Wanted node
             * @throws org.jdom2.JDOMException
             */
            public static Element getElement(String eName,
                    String pName, String attrKey, String attrVal)
                    throws org.jdom2.JDOMException
            {
                if (eName != null)
                {
                    if (eName.equals(""))
                    {
                        throw new JDOMException("Empty string as name of wanted element");
                    }
                }
                if (pName != null)
                {
                    if (pName.equals(""))
                    {
                        pName = null;
                    }
                }

                if (attrKey != null)
                {
                    if (attrKey.equals(""))
                    {
                        attrKey = null;
                        attrVal = null;
                    } else if (attrVal.equals(""))
                    {
                        attrKey = null;
                        attrVal = null;
                    }
                }


                Element e = getSpecificElement(doc.getRootElement(), eName,
                        pName, attrKey, attrVal);
                if (e != null)
                {
                    return e;
                }

                throw new JDOMException("No Element with name " + eName + " found.");
            }


            private static Element getSpecificElement(Element node, String eName,
                    String pName, String attrKey, String attrVal)
            {
                if (node.getChild(eName) != null)
                {
                    if (checkElement(node.getChild(eName), eName, pName, attrKey, attrVal))
                    {
                        return node.getChild(eName);
                    }
                }

                if (!node.getChildren().isEmpty())
                {
                    for (Element e : node.getChildren())
                    {
                        Element el = getSpecificElement(e, eName, pName, attrKey, attrVal);
                        if (el != null)
                        {
                            if (checkElement(el, eName, pName, attrKey, attrVal))
                            {
                                return el;
                            }
                        }
                    }
                }

                return null;
            }

            private static boolean checkElement(
                    Element e, String eName, String pName, String attrKey, String attrVal)
            {
                if (e.getName().equalsIgnoreCase(eName))
                {
                    if (pName != null)
                    {
                        if (e.getParentElement() != null)
                        {
                            if (e.getParentElement().getName().equalsIgnoreCase(pName))
                            {
                                if (e.hasAttributes())
                                {
                                    if (attrKey != null)
                                    {
                                        if (e.getAttributeValue(attrKey).equalsIgnoreCase(attrVal))
                                        {
                                            return true;
                                        }
                                        return false;
                                    }
                                }
                                return true;
                            }
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }
    }

    //save game in xml format (easier to convert)
    /**
     * Class for working with db Invoke init() before use
     */
    public static class DBAccess
    {

        private static final String JDBC_DRIVER = "org.postgresql.Driver";

        private static final ConnectionHolder HOLDER;

        static
        {
            try
            {
                Class.forName(JDBC_DRIVER);
                System.out.println("Driver initialized");
            } catch (ClassNotFoundException ex)
            {
                System.err.println("Driver not found");
            }
            HOLDER = new ConnectionHolder();
        }


        private static class ConnectionHolder
        {

            private static final String URL = "jdbc:postgresql://localhost/jg4_s1_pos_xml_db_project_miniGame";
            private static final Properties DB_PROPERTIES;

            static
            {
                DB_PROPERTIES = new Properties();
                DB_PROPERTIES.put("user", "postgres");
                DB_PROPERTIES.put("password", "postgres");
            }

            private static Connection con;

            private ConnectionHolder()
            {
                try
                {
                    con = DriverManager.getConnection(URL, DB_PROPERTIES);
                } catch (SQLException ex)
                {
                    System.err.println("Wrong url or login information");
                }
            }


            public static Connection getConnectionInstance()
                    throws SQLException
            {
                if (con == null)
                {
                    new ConnectionHolder();
                }
                return con;
            }


            public static void close()
            {
                if (con != null)
                {
                    try
                    {
                        con.close();
                    } catch (SQLException ex)
                    {
                        System.err.println("Can't close connection");
                    }
                }
            }
        }

        public static class DBWriter
        {


            public static void insertDataIntoTable(Properties gameInfo, NameOfTable t)
                    throws SQLException
            {
                Connection con = ConnectionHolder.getConnectionInstance();

                //list of ints
                List<Integer> orderedValues = new ArrayList();
                System.out.println(gameInfo);
                System.out.println(gameInfo.getProperty("CurrentScore"));

                for (String name : ColumnNameForTableGameInDB.getNecessaryColumnsInOrder().split(","))
                {
                    name = name.replace('\"', '\0');
                    name = name.replace(',', '\0');
                    name = name.trim();

                    if (gameInfo.getProperty(name) == null)
                    {
                        orderedValues.add(null);
                    } else
                    {
                        orderedValues.add(Integer.parseInt(gameInfo.getProperty(name)));
                    }
                }


                //String with int values
                StringBuilder values = new StringBuilder();
                for (int i = 0; i < orderedValues.size(); i++)
                {
                    values.append(orderedValues.get(i));
                    if (i < orderedValues.size() - 1)
                    {
                        values.append(",");
                    }
                }

                //PreparedStatement ps = con.prepareStatement(SQL_Statement.INSERT_DATA.toString());

                Statement s = con.createStatement();
                String query = SQL_Statement.ST_INSERT_DATA.toString();
                query = query.replace("#tbl#", t.toString());
                query = query.replace("#cols#", ColumnNameForTableGameInDB.getNecessaryColumnsInOrder());
                query = query.replaceFirst("#vals#", values.toString());
                //query = query.replaceFirst("\\?", NameOfTable.GAME.toString());
                //query = query.replaceFirst("\\?", ColumnNameForTableGameInDB.getNecessaryColumnsInOrder());
                //query = query.replaceFirst("\\?", values.toString());
                System.out.println(query);
                s.execute(query);


//                ps.setString(1, NameOfTable.GAME.toString());
//                ps.setObject(2, values.toString());
//                System.out.println(ps);
//                if (ps.execute())
//                {
//                    System.out.println("Executed successfully");
//                }
            }


            public static void updateData()
            {

            }

        }

        public static class DBReader
        {

//            private static PreparedStatement readLastGame = null;
//
//            static
//            {
//                try
//                {
//                    readLastGame = ConnectionHolder.getConnectionInstance()
//                            .prepareStatement(SQL_Statement.PS_GET_DATA_WHERE.toString());
//
//                } catch (SQLException ex)
//                {
//                    System.err.println("Preparing statements failure");
//                }
//            }

            public static Properties readLastGame()
                    throws SQLException
            {
                Connection con = ConnectionHolder.getConnectionInstance();

                Properties gameData = null;

                Statement stmt = con.createStatement();
                String query = SQL_Statement.ST_GET_DATA.toString();

                query = query.replace("#cols#", "MAX(" + ColumnNameForTableGameInDB.GAMEID.getQuotedName() + ")");
                query = query.replace("#tbl#", NameOfTable.GAME.toString());
                System.out.println(query);
                if (stmt.execute(query))
                {
                    ResultSet rs = stmt.getResultSet();
                    if (rs.next())
                    {
                        int highestID = rs.getInt(1);

                        query = SQL_Statement.ST_GET_DATA_WHERE.toString();
                        query = query.replace("#cols#", ColumnNameForTableGameInDB.getNecessaryColumnsInOrder());
                        query = query.replace("#tbl#", NameOfTable.GAME.toString());
                        query = query.replace("#col#", ColumnNameForTableGameInDB.GAMEID.getQuotedName());
                        query = query.replace("#val#", "" + highestID);
                        System.out.println(query);
                        if (stmt.execute(query))
                        {
                            rs = stmt.getResultSet();
                            if (rs.next())
                            {
                                gameData = new Properties();

                                for (String name : ColumnNameForTableGameInDB.getNecessaryColumnsInOrder().split(","))
                                {
                                    name = name.replace('\"', '\0');
                                    name = name.replace(',', '\0');
                                    name = name.trim();

                                    gameData.put(name, rs.getInt(name));
                                }
                            }

                        }
                    }

                }

//                readLastGame.setString(1, "'||" + ColumnNameForTableGameInDB.getNecessaryColumnsInOrder() + "||'");
//                readLastGame.setString(2, NameOfTable.GAME.getNeutralizedName());
//                readLastGame.setString(3, ColumnNameForTableGameInDB.GAMEID.toString());
//                readLastGame.setString(4, "MAX("+ ColumnNameForTableGameInDB.GAMEID.toString() +")");
//                System.out.println(readLastGame);
//                readLastGame.execute();

                return gameData;
            }

            /**
             * Set null or an empty string for every parameter which is not
             * necessary
             * <br>
             * Names and values are <b>not</b> case sensitive
             *
             * @param eName Name of wanted node
             * @param pName Name of parent of the wanted node
             * @param attrKey Key for necessesary attribute
             * @param attrVal Value fitting for key of necessesary attribute
             * @return Wanted node
             * @throws org.jdom2.JDOMException
             */
            public static Element getElement(String eName,
                    String pName, String attrKey, String attrVal)
                    throws org.jdom2.JDOMException
            {
                if (eName != null)
                {
                    if (eName.equals(""))
                    {
                        throw new JDOMException("Empty string as name of wanted element");
                    }
                }
                if (pName != null)
                {
                    if (pName.equals(""))
                    {
                        pName = null;
                    }
                }

                if (attrKey != null)
                {
                    if (attrKey.equals(""))
                    {
                        attrKey = null;
                        attrVal = null;
                    } else if (attrVal.equals(""))
                    {
                        attrKey = null;
                        attrVal = null;
                    }
                }


                //Element e = getSpecificElement(doc.getRootElement(), eName,
                //        pName, attrKey, attrVal);
//                if (e != null)
//                {
//                    return e;
//                }
                throw new JDOMException("No Element with name " + eName + " found.");
            }


            private static Element getSpecificElement(Element node, String eName,
                    String pName, String attrKey, String attrVal)
            {
                if (node.getChild(eName) != null)
                {
                    if (checkElement(node.getChild(eName), eName, pName, attrKey, attrVal))
                    {
                        return node.getChild(eName);
                    }
                }

                if (!node.getChildren().isEmpty())
                {
                    for (Element e : node.getChildren())
                    {
                        Element el = getSpecificElement(e, eName, pName, attrKey, attrVal);
                        if (el != null)
                        {
                            if (checkElement(el, eName, pName, attrKey, attrVal))
                            {
                                return el;
                            }
                        }
                    }
                }

                return null;
            }

            private static boolean checkElement(
                    Element e, String eName, String pName, String attrKey, String attrVal)
            {
                if (e.getName().equalsIgnoreCase(eName))
                {
                    if (pName != null)
                    {
                        if (e.getParentElement() != null)
                        {
                            if (e.getParentElement().getName().equalsIgnoreCase(pName))
                            {
                                if (e.hasAttributes())
                                {
                                    if (attrKey != null)
                                    {
                                        if (e.getAttributeValue(attrKey).equalsIgnoreCase(attrVal))
                                        {
                                            return true;
                                        }
                                        return false;
                                    }
                                }
                                return true;
                            }
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
        }
    }


    public static void main(String[] args)
            throws SQLException
    {
        Properties data = new Properties();
        //cant put null as value
        //data.setProperty(ColumnNameForTableGameInDB.GAMEID.toString(), null);
        data.setProperty(ColumnNameForTableGameInDB.BALLPOSX.toString(), "10");
        data.setProperty(ColumnNameForTableGameInDB.BALLPOSY.toString(), "10");
        data.setProperty(ColumnNameForTableGameInDB.ENEMYPOSX.toString(), "50");
        data.setProperty(ColumnNameForTableGameInDB.ENEMYPOSY.toString(), "50");
        data.setProperty(ColumnNameForTableGameInDB.CURRSCORE.toString(), "1250");
        DBAccess.DBWriter.insertDataIntoTable(data, NameOfTable.GAME);

        DBAccess.DBReader.readLastGame();
    }
}

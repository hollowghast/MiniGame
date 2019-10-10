package dal;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Content;
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
        
        
            public static void emptyDocument(){
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


    public static void main(String[] args)
            throws IOException, JDOMException
    {
        XMLAccess.setFile(new File("src/test.xml"));
        XMLAccess.XMLWriter.createNewDoc("Root");
        XMLAccess.XMLWriter.addElement("Test", "Hello from XML");
        XMLAccess.XMLWriter.addChild("Test", "Child", "here is the child");
        XMLAccess.XMLWriter.writeCurrentDocument();
        
        XMLAccess.XMLWriter.emptyDocument();
        XMLAccess.XMLWriter.writeCurrentDocument();
    }
}

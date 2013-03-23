import  java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

import parser.html.ParserHTML;
import create.xml.CreateXML;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException {

        long startTime = System.currentTimeMillis();

        ParserHTML pars = new ParserHTML("/Users/nikita/Documents/bookmarks_chrome.html");
        ArrayList<String> URL = pars.getURL();
        ArrayList<String> name = pars.getName();

        //CreateXML creater = new CreateXML("/Users/nikita/Documents/test.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();
        Element rootElement = doc.createElement("bookmarks");

        for(int i = 0; i < URL.size() - 1; i++) {
            try {
                Element nameElementTitle = doc.createElement("title");
                nameElementTitle.appendChild(doc.createTextNode(name.get(i)));
                rootElement.appendChild(nameElementTitle);

                Element nameElementSite = doc.createElement("site");
                nameElementSite.appendChild(doc.createTextNode(URL.get(i)));
                rootElement.appendChild(nameElementSite);
            }
            catch (Exception error) {
                error.printStackTrace();
            }
        }

        doc.appendChild(rootElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream("/Users/nikita/Documents/test.xml")));

        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}

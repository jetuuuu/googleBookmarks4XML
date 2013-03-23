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
        ArrayList<String> date = pars.getDate();

        CreateXML creater = new CreateXML("/Users/nikita/Documents/test.xml");
        creater.init();

        for(int i = 0; i < URL.size() - 1; i++) {
            try {
                creater.saveToXML(name.get(i), URL.get(i), date.get(i));
            }
            catch (Exception error) {
                error.printStackTrace();
            }
        }

        creater.end();

        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}

import  java.io.*;
import java.util.ArrayList;

import parser.html.ParserHTML;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        long startTime = System.currentTimeMillis();

        ParserHTML pars = new ParserHTML("/Users/nikita/Documents/bookmarks_chrome.html");
        ArrayList<String> URL = pars.getURL();

        for (String s: URL)
            System.out.println(s);

        System.out.println("Time: " + (System.currentTimeMillis() - startTime));
    }
}

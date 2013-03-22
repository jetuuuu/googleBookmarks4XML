package parser.html;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 22.03.13
 * Time: 14:21
 * To change this template use File | Settings | File Templates.
 */
public class ParserHTML {

    private String dir;
    private ArrayList<String> listURL;
    private String patternURL = "<http:\\S*\\\\/>";

    public ParserHTML(String path) {
        listURL = new ArrayList<String>();
        this.dir = path;
    }

    private void parseURL() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dir)));

        String text;

        try {

            while ((text = reader.readLine()) != null) {
                //text = text.replaceAll();
                Pattern p = Pattern.compile(patternURL);
                Matcher m = p.matcher(text);

                if (m.matches()) {
                    this.listURL.add(m.group());
                }
            }

            reader.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public ArrayList<String> getURL() {

        try {
            this.parseURL();
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }

        return listURL;
    }

}



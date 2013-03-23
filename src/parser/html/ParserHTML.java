package parser.html;

import javax.imageio.stream.FileImageInputStream;
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
    private ArrayList<String> listName;
    private ArrayList<String> listDate;
    private String patternURL = "http:\\S+\"[^\"]*\"";
    private String patternName = "<a(.*)>(.*)<\\Sa>";
    private String patternDate = "[0-9]{10,70}";

    public ParserHTML(String path) {
        this.listURL = new ArrayList<String>();
        this.listName = new ArrayList<String>();
        this.listDate = new ArrayList<String>();
        this.dir = path;
    }

    private void parseURL() throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dir)));
        String text;

        try {

            while ((text = reader.readLine()) != null) {
                Pattern p = Pattern.compile(this.patternURL, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(text);

                if (m.find()) {
                    int length = m.group().toString().length();
                    text = m.group().toString().substring(0, length - 12);  //удаляем лишние элементы
                    this.listURL.add(text);
                }
            }

            reader.close();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    private void parseName() throws  FileNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dir)));
        String text;

        try {
            while((text = reader.readLine()) != null) {
                Pattern p = Pattern.compile(this.patternName, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(text);

                if (m.find())
                    this.listName.add(m.group(2));
            }

            reader.close();
        }
        catch(Exception error) {
            error.printStackTrace();
        }
    }

    private void parseDate() throws FileNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.dir)));
        String text;

        try {
            while((text = reader.readLine()) != null) {
                Pattern p = Pattern.compile(this.patternDate, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(text);

                if (m.find())
                    this.listDate.add(m.group());
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
        }
        catch (FileNotFoundException error) {
            error.printStackTrace();
        }

        return listURL;
    }

    public ArrayList<String> getName() {

        try {
            this.parseName();
        }
        catch (FileNotFoundException error) {
            error.printStackTrace();
        }

        return this.listName;

    }

    public ArrayList<String> getDate() {

        try {
            this.parseDate();
        }
        catch (Exception error) {
            error.printStackTrace();
        }

        return this.listDate;

    }
}
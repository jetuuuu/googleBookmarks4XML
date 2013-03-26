import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import java.util.Scanner;

import parser.html.ParserHTML;
import create.xml.CreateXML;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, ParserConfigurationException, TransformerException {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите путь к html файлу: ");

        ParserHTML pars = new ParserHTML(in.nextLine());
        ArrayList<String> URL = pars.getURL();
        ArrayList<String> name = pars.getName();
        ArrayList<String> date = pars.getDate();

        System.out.println("Введите путь сохранения: ");

        CreateXML creater = new CreateXML(in.nextLine());
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

    }
}

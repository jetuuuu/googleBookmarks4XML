package create.xml;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: nikita
 * Date: 23.03.13
 * Time: 16:05
 * To change this template use File | Settings | File Templates.
 */
public class CreateXML {

    private String dir;
    private DocumentBuilder builder;
    private Document doc;

    public CreateXML(String path) {
        this.dir = path;
    }

    private void paramLangXML() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
    }

    private void writeParamXML(String url, String name) throws FileNotFoundException, TransformerException {
        this.doc = this.builder.newDocument();
        Element rootElement = this.doc.createElement("bookmarks");

        Element nameElementTitle = this.doc.createElement("title");
        nameElementTitle.appendChild(this.doc.createTextNode(name));
        rootElement.appendChild(nameElementTitle);

        Element nameElementSite = this.doc.createElement("site");
        nameElementSite.appendChild(this.doc.createTextNode(url));
        rootElement.appendChild(nameElementSite);

        this.doc.appendChild(rootElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.transform(new DOMSource(this.doc), new StreamResult(new FileOutputStream(this.dir)));
    }

    public void saveToXML(String url, String name) {

        try {
            this.paramLangXML();
            this.writeParamXML(url, name);
        }
        catch (Exception error) {
            error.printStackTrace();
        }

    }

}

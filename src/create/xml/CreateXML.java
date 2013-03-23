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
    private Element rootElement;

    public CreateXML(String path) {
        this.dir = path;
    }

    private void paramLangXML() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        this.builder = factory.newDocumentBuilder();
    }

    private void writeParamXML(String url, String name) throws FileNotFoundException, TransformerException {

        Element nameElementTitle = this.doc.createElement("title");
        nameElementTitle.appendChild(this.doc.createTextNode(name));
        this.rootElement.appendChild(nameElementTitle);

        Element nameElementSite = this.doc.createElement("site");
        nameElementSite.appendChild(this.doc.createTextNode(url));
        this.rootElement.appendChild(nameElementSite);

    }

    public void init() {
        try {
            this.paramLangXML();
            this.doc = this.builder.newDocument();
            this.rootElement = this.doc.createElement("bookmarks");
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }

    public void saveToXML(String url, String name) {

        try {
            this.writeParamXML(url, name);
        }
        catch (Exception error) {
            error.printStackTrace();
        }

    }

    public void end() throws FileNotFoundException, TransformerException {

        this.doc.appendChild(this.rootElement);

        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.transform(new DOMSource(this.doc), new StreamResult(new FileOutputStream(this.dir)));
    }

}

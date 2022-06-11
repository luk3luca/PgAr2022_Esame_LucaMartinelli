package it.unibs.fp.game;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ReadXmlDomParser {
    private static final String FILENAME = "livello1.xml";

    public static Cella[][] creaMappa() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        Cella[][] mappa = new Cella[0][];
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(FILENAME));
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();
            int width = Integer.parseInt(root.getAttribute("width"));
            int height = Integer.parseInt(root.getAttribute("height"));
            mappa = new Cella[height][width];

            NodeList list = doc.getElementsByTagName("row");

            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                Element element = (Element) node;
                NodeList listCelle = element.getElementsByTagName("cell");
                for (int j = 0; j < listCelle.getLength(); j++) {
                    Node n = listCelle.item(j);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element e = (Element) n;
                        char c = e.getTextContent().charAt(0);
                        mappa[i][j] = new Cella(c);
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return mappa;
    }

}

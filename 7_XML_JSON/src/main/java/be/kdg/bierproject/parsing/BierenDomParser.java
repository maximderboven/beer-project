package be.kdg.bierproject.parsing;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Gisting;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

/**
 * @author Maxim Derboven
 * @version 1.0 21/11/2021 10:43
 */
public class BierenDomParser {
    private static String file;
    private static Bieren bieren = new Bieren();

    public static Bieren domReadXML(String fileName) {
        try {
            file = fileName;
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File(fileName));
            Element rootElement = doc.getDocumentElement();
            NodeList bierNodes = rootElement.getChildNodes();
            for (int i = 0; i < bierNodes.getLength(); i++) {
                if (bierNodes.item(i).getNodeType() != Node.ELEMENT_NODE) {
                    continue;
                }
                Element e = (Element) bierNodes.item(i);
                Gisting gisting = Gisting.valueOf(e.getAttribute("gisting"));
                String naam = e.getElementsByTagName("naam").item(0).getTextContent();
                LocalDate gebrouwenSinds = LocalDate.parse(e.getElementsByTagName("gebrouwenSinds").item(0).getTextContent());
                double alcoholPercentage = Double.parseDouble(e.getElementsByTagName("alcoholpercentage").item(0).getTextContent());
                int bitterheidsgraad = Integer.parseInt(e.getElementsByTagName("bitterheidsgraad").item(0).getTextContent());
                boolean trappist = Boolean.parseBoolean(e.getElementsByTagName("trappist").item(0).getTextContent());
                bieren.add(new Bier(naam,gisting,gebrouwenSinds,alcoholPercentage,bitterheidsgraad,trappist));
            }
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
        return bieren;
    }
}

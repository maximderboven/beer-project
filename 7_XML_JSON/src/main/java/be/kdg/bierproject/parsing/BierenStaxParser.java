package be.kdg.bierproject.parsing;

import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Gisting;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

/**
 * @author Maxim Derboven
 * @version 1.0 20/11/2021 19:51
 */
public class BierenStaxParser {
    XMLStreamWriter xmlStreamWriter;
    String path;

    public BierenStaxParser(String path) {
        try {
            //new IndentingXMLStreamWriter() voor pretty printing - macheert ni
            this.xmlStreamWriter = XMLOutputFactory.newInstance().createXMLStreamWriter
                    (new FileWriter(path, StandardCharsets.UTF_8));
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
        this.path = path;
    }

    void staxWriteXML() {
        try {
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeStartElement("bieren");
            for (Bier bier : Data.getData()) {
                xmlStreamWriter.writeStartElement("bier");
                xmlStreamWriter.writeAttribute("gisting", bier.getGisting().toString());

                //content toevoegen:
                writeElement(bier);

                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement(); // </bier>
            xmlStreamWriter.writeEndDocument(); // </bieren>

            xmlStreamWriter.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private void writeElement(Bier bier) throws XMLStreamException {
        xmlStreamWriter.writeStartElement("naam");
        xmlStreamWriter.writeCharacters(bier.getNaam());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("gebrouwenSinds");
        xmlStreamWriter.writeCharacters(bier.getGebrouwenSinds().toString());
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("alcoholpercentage");
        xmlStreamWriter.writeCharacters(String.valueOf(bier.getAlcoholPercentage()));
        xmlStreamWriter.writeEndElement();

        xmlStreamWriter.writeStartElement("bitterheidsgraad");
        xmlStreamWriter.writeCharacters(String.valueOf(bier.getBitterheidsgraad()));
        xmlStreamWriter.writeEndElement();
    }
}

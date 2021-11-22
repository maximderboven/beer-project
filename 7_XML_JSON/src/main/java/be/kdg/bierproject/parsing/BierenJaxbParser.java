package be.kdg.bierproject.parsing;

import be.kdg.bierproject.model.Bieren;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * @author Maxim Derboven
 * @version 1.0 21/11/2021 19:47
 */
public class BierenJaxbParser {
    public static void JaxbWriteXml(String file, Object root) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Bieren.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(root, new File(file));
            jaxbMarshaller.marshal(root, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static <T> T JaxbReadXml(String file, Class<T> typeParameterClass) {
        Bieren bieren = new Bieren();
        try {
            JAXBContext context = JAXBContext.newInstance(typeParameterClass);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            bieren = (Bieren) unmarshaller.unmarshal(new File(file));
            bieren.getBierenList().forEach(System.out::println);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return (T) bieren;
    }
}

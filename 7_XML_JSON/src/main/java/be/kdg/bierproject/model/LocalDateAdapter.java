package be.kdg.bierproject.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author Maxim Derboven
 * @version 1.0 21/11/2021 19:42
 */
import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    public LocalDate unmarshal(String myString) throws Exception {
        return LocalDate.parse(myString);
    }

    public String marshal(LocalDate myDate) throws Exception {
        return myDate.toString();
    }
}
package be.kdg.bierproject.parsing;

import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Maxim Derboven
 * @version 1.0 21/11/2021 11:44
 */
class ParserTest {
    Bieren bieren = new Bieren();

    @BeforeEach
    void setUp() {
        Data.getData().forEach(bieren::add);
    }

    @Test
    void testStaxDom() {
        new BierenStaxParser("./datafiles/bieren.xml").staxWriteXML();
        Bieren bieren2 = BierenDomParser.domReadXML("./datafiles/bieren.xml");
        assertArrayEquals(bieren.sortedOnName().toArray(),bieren2.sortedOnName().toArray());
    }
}
package be.kdg.bierproject.persist;

import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Maxim Derboven
 * 26/10/2021
 */
class BierenSerializerTest {

    Bieren bierenLijst;
    @BeforeEach
    void setUp() {
        bierenLijst = new Bieren();
        for (Bier b: Data.getData()) {
            bierenLijst.add(b);
        }
    }

    @Test
    void testSerialize() {
        BierenSerializer serializer = new BierenSerializer("bieren");
        assertDoesNotThrow(() -> serializer.serialize(bierenLijst),"Het serializen gaf een exception.");
    }

    @Test
    void testDeserialize() throws IOException, ClassNotFoundException {
        BierenSerializer serializer = new BierenSerializer("bieren");
        Bieren bierenLijst2 = serializer.deserialize();
        assertEquals(bierenLijst2,bierenLijst,"De objecten komen niet overeen");
    }
}
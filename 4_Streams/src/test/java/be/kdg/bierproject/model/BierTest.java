package be.kdg.bierproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Maxim Derboven
 * @version 1.0 21/10/2021 21:27
 */
class BierTest {
    private Bier b1;
    private Bier b2;

    @BeforeEach
    void setUp() {
        b1 = new Bier("Buval",Gisting.HOGE, LocalDate.of(2008 ,1,1),4.5,17,false);
        b2 = new Bier("Jupiler",Gisting.LAGE, LocalDate.of(1966 ,1,1),5.2,18,false);
    }

    @Test
    public void testEquals() {
        assertNotEquals(b1.hashCode(), b2.hashCode(), "Hashcode komt niet overeen");
        assertEquals(b2.hashCode(), b1.hashCode(), "Hashcode komt overeen");
    }

    @Test
    void testIllegalName() {


    }
    @Test
    void testlegalName() {

    }

    @Test
    void testCompareTo() {
    }
}
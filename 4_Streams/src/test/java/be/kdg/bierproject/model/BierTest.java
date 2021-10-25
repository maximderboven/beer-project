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
        Bier b3 = new Bier("Jupiler",Gisting.LAGE, LocalDate.of(1966 ,1,1),5.2,18,false);
        assertNotEquals(b1.hashCode(), b2.hashCode(), "De naam komt niet overeen");
        assertEquals(b2.hashCode(), b3.hashCode(), "De naam komt overeen");
    }

    @Test
    void testIllegalName() {
        assertThrows(IllegalArgumentException.class,()->b1.setNaam(""),"De naam mag niet leeg zijn");
    }
    @Test
    void testLegalName() {
        assertDoesNotThrow(()->b1.setNaam("Naam"),"De naam is correct ingevuld.");
    }

    @Test
    void testCompareTo() {
        Bier b3 = new Bier("Jupiler",Gisting.LAGE, LocalDate.of(1966 ,1,1),5.2,18,false);
        assertNotEquals(b2.compareTo(b1), b1.compareTo(b2), "2 bieren met een andere naam zijn niet hetzelfde.");
        assertEquals(0, b2.compareTo(b3), "2 bieren met dezelfde naam hebben zijn hetzelfde");
    }

    @Test
    void testAlcoholPercentage() {
        //assertEquals(b1.getAlcoholPercentage(),b2.getAlcoholPercentage(),"Zelfde alcoholpercentage");
        assertNotEquals(b1.getAlcoholPercentage(),b2.getAlcoholPercentage(),"Niet hetzelfde alcoholpercentage");
    }
}
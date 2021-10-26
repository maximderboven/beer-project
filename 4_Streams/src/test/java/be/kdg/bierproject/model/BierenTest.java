package be.kdg.bierproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Maxim Derboven
 * @version 1.0 23/10/2021 16:24
 */
class BierenTest {
    private Bieren bieren;

    @BeforeEach
    void setUp() {
        bieren = new Bieren();
    }

    @Test
    void testAdd() {
        assertDoesNotThrow(() -> bieren.add(new Bier("Bolleke", Gisting.HOGE, LocalDate.of(1833, 1, 1), 5.2, 23, false)), "Mogelijk om toe te voegen zonder exception");
        assertDoesNotThrow(() -> bieren.add(new Bier("Ciney", Gisting.HOGE, LocalDate.of(1978, 6, 23), 7, 0, false)), "Mogelijk om toe te voegen zonder exception");
        assertDoesNotThrow(() -> bieren.add(new Bier("Duvel", Gisting.HOGE, LocalDate.of(1970, 1, 1), 8.5, 32, false)), "Mogelijk om toe te voegen zonder exception");
        assertDoesNotThrow(() -> bieren.add(new Bier("Leffe Bruin", Gisting.HOGE, LocalDate.of(1952, 1, 1), 6.5, 15, false)), "Mogelijk om toe te voegen zonder exception");
        assertDoesNotThrow(() -> bieren.add(new Bier("Palm", Gisting.HOGE, LocalDate.of(1929, 1, 1), 5.2, 18, false)), "Mogelijk om toe te voegen zonder exception");
    }

    @Test
    void testRemove() {
        int size = bieren.getSize();
        bieren.remove("Palm");
        assertEquals(size, bieren.getSize(), "Succesvol verwijderd");
    }

    @Test
    void testSort() {
        Bieren b = new Bieren();
        b.add(new Bier("Bolleke", Gisting.HOGE, LocalDate.of(1833, 1, 1), 5.2, 23, false));
        b.add(new Bier("Duvel", Gisting.HOGE, LocalDate.of(1970, 1, 1), 8.5, 32, false));
        b.add(new Bier("Ciney", Gisting.HOGE, LocalDate.of(1978, 6, 23), 7, 0, false));
        List<Bier> b2Sorted = b.sortedBy(Bier::getNaam);
        assertAll(() -> assertEquals("Bolleke", b2Sorted.get(0).getNaam()), () -> assertEquals("Ciney", b2Sorted.get(1).getNaam()), () -> assertEquals("Duvel", b2Sorted.get(2).getNaam()));
    }

    @Test
    void testSort2() {
        Bieren b = new Bieren();
        b.add(new Bier("Bolleke", Gisting.HOGE, LocalDate.of(1833, 1, 1), 5.2, 23, false));
        b.add(new Bier("Duvel", Gisting.HOGE, LocalDate.of(1970, 1, 1), 8.5, 32, false));
        b.add(new Bier("Ciney", Gisting.HOGE, LocalDate.of(1978, 6, 23), 7, 0, false));
        List<Bier> b2 = new ArrayList<>();
        b2.add(new Bier("Bolleke", Gisting.HOGE, LocalDate.of(1833, 1, 1), 5.2, 23, false));
        b2.add(new Bier("Ciney", Gisting.HOGE, LocalDate.of(1978, 6, 23), 7, 0, false));
        b2.add(new Bier("Duvel", Gisting.HOGE, LocalDate.of(1970, 1, 1), 8.5, 32, false));
        assertArrayEquals(b.sortedBy(Bier::getNaam).toArray(), b2.toArray(), "De bieren zijn niet juist alfabetisch gesorteerd");
    }
}
package be.kdg.bierproject.persist;

import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Gisting;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Maxim Derboven
 * @version 1.0 28/10/2021 19:27
 */
class BierDbDaoTest {

    private static BierDbDao bierDbDao;

    @BeforeAll
    static void init() {
        bierDbDao = new BierDbDao("db/bierendb.db");
    }

    @BeforeEach
    void setUp() {
        for (Bier b : Data.getData()) {
            bierDbDao.insert(b);
        }
    }


    @AfterEach
    void tearDown() {
        bierDbDao.delete("*");
    }

    @AfterAll
    static void remove() {
        bierDbDao.close();
    }

    @Test
    void testInsert() {
        ArrayList<Bier> bieren = new ArrayList<>();
        for (Bier b : Data.getData()) {
            bieren.add(bierDbDao.retrieve(b.getNaam()));
        }
        assertEquals(Data.getData().size(), bieren.size(), "Niet alle items zitten in de db");
    }

    @Test
    void testRetrieveUpdate() {
        Bier b = bierDbDao.retrieve("Omer.");
        b.setNaam("Omer");
        assertTrue(bierDbDao.update(b),"Update is niet goed gebeurd");
        assertNotNull(bierDbDao.retrieve("Omer"),"Fout, Omer. is niet gewijzigd in Omer");
    }

    @Test
    void testDelete() {
        assertTrue(bierDbDao.delete("Duvel"), "Verwijderen werkt niet");
        ArrayList<Bier> bieren = new ArrayList<>();
        for (Bier b : Data.getData()) {
            bieren.add(bierDbDao.retrieve(b.getNaam()));
        }
        assertNotEquals(Data.getData().size() - 1, bieren.size(), "Niet goed verwijderd");
        assertNull(bierDbDao.retrieve("Duvel"), "Niet Goed verwijderd, bestaat alsnog");
        assertFalse(bierDbDao.delete("Duvel"), "2 keer verwijderen mogelijk");
    }

    @Test
    void testSort() {
        //Gaat niet juist gvesorteerd zijn: Database sorteerd ook nog op naam binnen zelfde alcoholpercentage.
        //fixed door alcoholpercentage gedetailleerder te maken
        Bieren bieren = new Bieren();
        Data.getData().forEach(bieren::add);
        assertArrayEquals(bieren.sortedOnAlcoholpercentage().toArray(),bierDbDao.sortedOnAlcholpercentage().toArray(), "Niet juist gesorteerd");
    }
}
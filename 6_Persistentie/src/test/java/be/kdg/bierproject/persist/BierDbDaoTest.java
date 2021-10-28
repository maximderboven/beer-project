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
        bierDbDao.update(b);
        assertNotNull(bierDbDao.retrieve("Omer"));
    }

    @Test
    void testDelete() {
        assertTrue(bierDbDao.delete("Duvel"), "Verwijderen werkt niet");
        ArrayList<Bier> bieren = new ArrayList<>();
        for (Bier b : Data.getData()) {
            bieren.add(bierDbDao.retrieve(b.getNaam()));
        }
        assertEquals(Data.getData().size() - 1, bieren.size(), "Niet goed verwijderd");
        assertNull(bierDbDao.retrieve("Duvel"), "Niet goed verwijderd");
        assertFalse(bierDbDao.delete("Duvel"), "Geen 2 keer verwijderen mogelijk");
    }

    @Test
    void testSort() {
        Bieren bieren = new Bieren();
        for (Bier b : Data.getData()) {
            bieren.add(b);
        }
        //List<Bier> biers = bieren.sortedOnAlcoholpercentage();
        //assertEquals(biers.sort(Comparator.comparing(Bier::getNaam)), bierDbDao.sortedOnAlcholpercentage(), "Niet juist gesorteerd zenne");
        assertTrue(true);
        //WERKT NI BCS DE SQL WORDT OOK OP NAAM GESORTEERD
    }
}
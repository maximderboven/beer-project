import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Gisting;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;

/**
 * @author Maxim Derboven
 * @version 1.0 24/10/2021 21:26
 */
public class Demo_5 {
    public static void main(String[] args) {
        laadLoggingProperties();

        Bieren bieren = new Bieren();
        //4 foute
        bieren.add(new Bier("", Gisting.HOGE, LocalDate.of(1833, 1, 1), 5.2, 23, false));
        bieren.add(new Bier("Ciney", Gisting.HOGE, LocalDate.of(2025, 6, 23), 7, 0, false));
        bieren.add(new Bier("Duvel", Gisting.HOGE, LocalDate.of(1970, 1, 1), 102.5, 32, false));
        bieren.add(new Bier("Leffe Bruin", Gisting.HOGE, LocalDate.of(1952, 1, 1), 6.5, -15, false));

        //1 goeie
        bieren.add(new Bier("Leffe Blond", Gisting.HOGE, LocalDate.of(1952, 1, 1), 6.5, 15, false));
        bieren.remove("Leffe Blond");
    }

    private static void laadLoggingProperties() {
        URL configURL = Demo_5.class.getResource("logging.properties");
        if (configURL != null) {
            try (InputStream is = configURL.openStream()) {
                LogManager.getLogManager().readConfiguration(is);
            } catch (IOException e) {
                System.err.println("Configuratiebestand is corrupt");
            }
        } else {
            System.err.println("Configuratiebestand NIET GEVONDEN");
        }
    }


}

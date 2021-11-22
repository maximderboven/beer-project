package be.kdg.bierproject.parsing;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 21/11/2021 20:57
 */
public class BierenGsonParser {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeJson(Bieren bieren, String fileName) {
        String jsonString = gson.toJson(bieren.getBierenList());
        try (FileWriter jsonWriter = new FileWriter(fileName)) {
            jsonWriter.write(jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Bieren readJson(String fileName) {
        Bieren bieren = new Bieren();
        try (BufferedReader data = new BufferedReader(new FileReader(fileName))) {
            Bier[] bierArray = gson.fromJson(data, Bier[].class);
            List<Bier> bierenlijst = Arrays.asList(bierArray);
            for (Bier bier : bierenlijst) {
                bieren.add(bier);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bieren;
    }
}

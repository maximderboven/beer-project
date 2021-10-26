package be.kdg.bierproject.persist;

import be.kdg.bierproject.model.Bieren;

import java.io.*;

/**
 * Maxim Derboven
 * 26/10/2021
 */
public class BierenSerializer {
    private final String FILENAME;

    public BierenSerializer(String filename) {
        FILENAME = filename;
    }

    public void serialize(Bieren bieren) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("db/"+FILENAME+".ser"));
        oos.writeObject(bieren);
    }

    public Bieren deserialize() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("db/"+FILENAME+".ser"));
        return (Bieren) ois.readObject();
    }
}

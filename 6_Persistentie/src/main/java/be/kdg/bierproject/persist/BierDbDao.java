package be.kdg.bierproject.persist;

import be.kdg.bierproject.model.Bier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Maxim Derboven
 * 26/10/2021
 */
public class BierDbDao implements BierDao {
    private Connection connection;
    private final Logger logger = Logger.getLogger("be.kdg.bierproject.persist.BierDbDao");

    public BierDbDao(String databasePath) {
        maakConnectie("db/myDatabase");
        createTable();
    }

    private void maakConnectie(String databasePath) {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "sa", "");
            System.out.println("Connection gemaakt");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Kan geen connectie maken met database " + databasePath);
            System.exit(1);
        }
    }

    public void close() {
        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            System.out.println("\nDatabase gesloten");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Probleem bij sluiten van database:" + e.getMessage());
        }
    }

    private void createTable() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE bierendb IF EXISTS");
            String createQuery = "CREATE TABLE bierendb " +
                    "(id INTEGER NOT NULL IDENTITY," +
                    "naam VARCHAR(30) NOT NULL," +
                    "gisting VARCHAR(30) NOT NULL," +
                    "gebrouwenSinds DATE NOT NULL," +
                    "alcoholpercentage DOUBLE NOT NULL," +
                    "trappist BIT NOT NULL," +
                    "bitterheidsgraad INT NOT NULL)";
            statement.execute(createQuery);
            System.out.println("Database aangemaakt");
        } catch (SQLException e) {
            logger.log(Level.SEVERE,"Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            System.exit(1);
        }
    }



    @Override
    public boolean insert(Bier bier) {
        return false;
    }

    @Override
    public boolean delete(String naam) {
        return false;
    }

    @Override
    public boolean update(Bier bier) {
        return false;
    }

    @Override
    public Bier retrieve(String naam) {
        return null;
    }

    @Override
    public List<Bier> sortedOn(String query) {
        return null;
    }
}

package be.kdg.bierproject.database;

import be.kdg.bierproject.data.Data;
import be.kdg.bierproject.exceptions.BierException;
import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Gisting;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Maxim Derboven
 * 26/10/2021
 */
public class BierDbDao implements BierDao {
    private static final Logger logger = Logger.getLogger("be.kdg.bierproject.database.BierDbDao");
    private Connection connection;
    private static BierDbDao instance;


    private BierDbDao(String databasePath) {
        maakConnectie(databasePath);
        createTable();
    }

    public static BierDbDao getInstance(String databasePath) {
        if (instance==null)
            instance = new BierDbDao(databasePath);
        return instance;
    }

    private void maakConnectie(String databasePath) {
        try {
            connection = DriverManager.getConnection("jdbc:hsqldb:file:" + databasePath, "sa", "");
            logger.info("Connectie gemaakt");
        } catch (SQLException e) {
            logger.warning("Error bij het sluiten van de connectie.");
            throw new BierException(e.getMessage());
        }
    }

    public void close() {
        if (connection == null) return;
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN COMPACT");
            statement.close();
            connection.close();
            logger.info("Database gesloten");
        } catch (SQLException e) {
            logger.warning("Error bij het sluiten van de connectie.");
            throw new BierException(e.getMessage());
        }
    }

    private void createTable(){
        try {
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet tables = dbm.getTables(null, null, "bierendb", null);
            if (!tables.next()) {
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
                logger.info("Databasde aangemaakt");
                Data.getData().forEach(this::insert);
            }
        } catch (SQLException e) {
            logger.warning("Onverwachte fout bij aanmaken tabel: " + e.getMessage());
            throw new BierException(e.getMessage());
        }
    }

    @Override
    public boolean insert(Bier bier) {
        if (bier.getId() >= 0) return false; //bier heeft al PK dus bestaat al in database
        try {
            PreparedStatement prepStatement = connection.prepareStatement(
                    "INSERT INTO bierendb (id, naam, gisting, gebrouwenSinds, alcoholpercentage, trappist, bitterheidsgraad) " +
                            "VALUES (NULL, ?, ?, ?, ?,?,?)");
            prepStatement.setString(1, bier.getNaam());
            prepStatement.setInt(2, bier.getGisting().ordinal());
            prepStatement.setDate(3, Date.valueOf(bier.getGebrouwenSinds()));
            prepStatement.setDouble(4, bier.getAlcoholPercentage());
            prepStatement.setInt(5, bier.isTrappist() ? 1 : 0);
            prepStatement.setInt(6, bier.getBitterheidsgraad());
            boolean result = prepStatement.executeUpdate() == 1;
            prepStatement.close();
            logger.info("Bier=" + bier.getNaam() + " succesvol toegevoegd");
            return result;
        } catch (SQLException e) {
            logger.warning("Fout bij create");
            throw new BierException(e.getMessage());
        }
    }

    @Override
    public boolean delete(String naam) {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected;
            if (!naam.equals("*")) {
                rowsAffected = statement.executeUpdate("DELETE FROM bierendb WHERE NAAM = '" + naam + "'");
            } else {
                rowsAffected = statement.executeUpdate("TRUNCATE TABLE bierendb");
            }
            statement.close();
            logger.info("Deleted" + naam);
            return (rowsAffected == 1);
        } catch (SQLException e) {
            logger.warning("Fout bij delete");
            throw new BierException(e.getMessage());
        }
    }

    @Override
    public boolean update(Bier bier) {
        try {
            PreparedStatement prepStatement = connection.prepareStatement(
                    "UPDATE bierendb SET naam = ?, gisting = ?, gebrouwenSinds = ?, alcoholpercentage = ?, trappist = ?, bitterheidsgraad = ? WHERE id = ?");
            prepStatement.setString(1, bier.getNaam());
            prepStatement.setInt(2, bier.getGisting().ordinal());
            prepStatement.setDate(3, Date.valueOf(bier.getGebrouwenSinds()));
            prepStatement.setDouble(4, bier.getAlcoholPercentage());
            prepStatement.setInt(5, bier.isTrappist() ? 1 : 0);
            prepStatement.setInt(6, bier.getBitterheidsgraad());
            prepStatement.setInt(7, bier.getId());
            boolean result = prepStatement.executeUpdate() == 1;
            prepStatement.close();
            logger.info("Bier=" + bier.getNaam() + " succesvol gewijzigd");
            return result;
        } catch (SQLException e) {
            logger.warning("Fout bij update");
            throw new BierException(e.getMessage());
        }
    }

    @Override
    public Bier retrieve(String naam) {
        List<Bier> bieren = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM bierendb WHERE NAAM ='" + naam + "'");
            while (rs.next()) {
                bieren.add(new Bier(
                        rs.getInt("ID"),
                        rs.getString("NAAM"),
                        Gisting.values()[rs.getInt("GISTING")],
                        rs.getDate("GEBROUWENSINDS").toLocalDate(),
                        rs.getDouble("ALCOHOLPERCENTAGE"),
                        rs.getInt("BITTERHEIDSGRAAD"),
                        rs.getInt("TRAPPIST") == 1
                ));
            }
            if (bieren.size() > 0) {
                logger.info("bieren verkregen met naam = " + naam);
                return bieren.get(0);
            }
        } catch (SQLException e) {
            logger.warning("Fout bij opvragen: Geen bier met volgende naam gevonden " + naam);
            throw new BierException(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Bier> sortedOn(String query) {
        List<Bier> bieren = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM bierendb ORDER BY " + query);
            while (rs.next()) {
                bieren.add(new Bier(
                        rs.getInt("ID"),
                        rs.getString("NAAM"),
                        Gisting.values()[rs.getInt("GISTING")],
                        rs.getDate("GEBROUWENSINDS").toLocalDate(),
                        rs.getDouble("ALCOHOLPERCENTAGE"),
                        rs.getInt("BITTERHEIDSGRAAD"),
                        rs.getInt("TRAPPIST") == 1
                ));
            }
            logger.info("Gesorteerde array verkregen.");
            return bieren;
        } catch (SQLException e) {
            logger.warning("Fout bij sorteren: niks gevonden");
            throw new BierException(e.getMessage());
        }
    }

    @Override
    public List<Bier> getAllBieren() {
        return sortedOn("naam");
    }

    public List<Bier> sortedOnName() {
        return sortedOn("naam");
    }

    public List<Bier> sortedOnAlcholpercentage() {
        return sortedOn("alcoholpercentage");
    }


}

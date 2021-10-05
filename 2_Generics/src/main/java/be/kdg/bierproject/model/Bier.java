package be.kdg.bierproject.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:13
 * @see "https://nl.wikipedia.org/wiki/Lijst_van_Belgische_bieren"
 * @since Week 1
 * @description Deze klasse bevat alle eigenschappen van een bier
 */
public class Bier implements Comparable<Bier> {
    /***
     * Deze attributen dienen als eigenschappen van een Biersoort
     * naam                 De naam van het bier.
     * gisting              De soort gisting van het bier (@see Enum Gisting).
     * gebrouwenSinds       Wanneer het bier voor het eerst gebrouwen is.
     * alcoholPercentageP   Percentage aan alcohol dat er in eht biertje zit.
     * bitterheidsgraad     Bitterheid uitgedrukt in EBU.
     * trappist             Boolean om te bepalen of het een trappistenbieris of niet.
     */
    private String naam;
    private Gisting gisting;
    private LocalDate gebrouwenSinds;
    private double alcoholPercentage;
    private int bitterheidsgraad;
    private boolean trappist;

    /***
     * Constructor voor een object van Bier.
     * @param naam              De naam van het bier.
     * @param gisting           De soort gisting van het bier (@see Enum Gisting).
     * @param gebrouwenSinds    Wanneer het bier voor het eerst gebrouwen is.
     * @param alcoholPercentage Percentage aan alcohol dat er in eht biertje zit.
     * @param bitterheidsgraad  Bitterheid uitgedrukt in EBU.
     * @param trappist          Boolean om te bepalen of het een trappistenbieris of niet.
     */
    public Bier(String naam, Gisting gisting, LocalDate gebrouwenSinds, double alcoholPercentage, int bitterheidsgraad, boolean trappist) {
        setNaam(naam);
        setGisting(gisting);
        setGebrouwenSinds(gebrouwenSinds);
        setAlcoholPercentage(alcoholPercentage);
        setBitterheidsgraad(bitterheidsgraad);
        setTrappist(trappist);
    }

    /***
     * Lege constructor voor het aanmaken van een onbekend biertje.
     */
    public Bier() {
        this("Onbekend",Gisting.ONBEKEND,LocalDate.now(),0,0,false);
    }

    /***
     * Methode om de naam aan te passen van het desbetreffende biertje
     * @param naam  Nieuwe naam meegeven
     * @throws IllegalArgumentException Wanneer de naam leeg is
     */
    public void setNaam(String naam) {
        if(naam == null || naam.equals(""))
            throw new IllegalArgumentException("De naam mag niet leeg zijn.");
        this.naam = naam;
    }

    /***
     * Methode om de gisting aan te passen van het desbetreffende biertje
     * @see Gisting Enum voor de soorten gisting
     * @param gisting  Nieuwe gisting meegeven
     * @throws IllegalArgumentException Wanneer de nieuwe gisting niet voorkomt in de enum
     */
    public void setGisting(Gisting gisting) {
        if(gisting != Gisting.HOGE && gisting != Gisting.LAGE && gisting != Gisting.SPONTAAN && gisting != Gisting.GEMENGDE)
            throw new IllegalArgumentException("Kies een juiste gisting.");
        this.gisting = gisting;
    }

    /***
     * Methode om de brouw datum aan te passen van het desbetreffende biertje
     * @param gebrouwenSinds  De nieuwe datum meegeven
     * @throws IllegalArgumentException Wanneer de de nieuwe datum in de toekomst ligt
     */
    public void setGebrouwenSinds(LocalDate gebrouwenSinds) {
        if(!gebrouwenSinds.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("De brouwdatum moet in het verleden liggen.");
        this.gebrouwenSinds = gebrouwenSinds;
    }

    /***
     * Methode om het alcoholpercentage aan te passen van het desbetreffende biertje
     * @param alcoholPercentage  Nieuwe alcoholpercentage meegeven
     * @throws IllegalArgumentException Wanneer het alcoholpercentage lager is dan 0 of hoger dan 100
     */
    public void setAlcoholPercentage(double alcoholPercentage) {
        if(alcoholPercentage <  0 || alcoholPercentage >= 100)
            throw new IllegalArgumentException("Alcoholpercentage kan niet lager zijn dan 0 en niet hoger dan 100.");
        this.alcoholPercentage = alcoholPercentage;
    }

    /***
     * Methode om de bitterheidsgraad aan te passen van het desbetreffende biertje
     * @param bitterheidsgraad  Nieuwe bitterheidsgraad meegeven
     * @throws IllegalArgumentException Wanneer de bitterheidsgraad kleiner is dan 0
     */
    public void setBitterheidsgraad(int bitterheidsgraad) {
        if(bitterheidsgraad <  0)
            throw new IllegalArgumentException("De EBU waarde moet tenminste 0 zijn.");
        this.bitterheidsgraad = bitterheidsgraad;
    }

    /***
     * Methode om de status van trappist aan te passen van het desbetreffende biertje
     * @param trappist  trappist ja of nee meegeven
     */
    public void setTrappist(boolean trappist) {
        this.trappist = trappist;
    }

    /***
     * Methode om de naam van het biertje te verkrijgen
     * @return naam (String)    Verkrijg de naam van het biertje
     */
    public String getNaam() {
        return naam;
    }

    /***
     * Methode om de naam van het biertje te verkrijgen
     * @see Gisting Enum waarin de soorten giting zijn opgesomd
     * @return naam (String)    Verkrijg de naam van het biertje
     */
    public Gisting getGisting() {
        return gisting;
    }

    /***
     * Methode om de brouwdatum van het biertje te verkrijgen
     * @return gebrouwenSinds (LocalDate)    Verkrijg de eerste brouwdatum van het biertje
     */
    public LocalDate getGebrouwenSinds() {
        return gebrouwenSinds;
    }

    /***
     * Methode om het alcoholpercentage van het biertje te verkrijgen
     * @return alcoholPercentage (double)    Verkrijg het alcoholpercentage van het biertje
     */
    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    /***
     * Methode om de bitterheidsgraad van het biertje te verkrijgen
     * @return bitterheidsgraad (int)    Verkrijg de bitterheidsgraad van het biertje
     */
    public int getBitterheidsgraad() {
        return bitterheidsgraad;
    }

    /***
     * Methode om de trapist status van het biertje te verkrijgen
     * @return trappist (boolean)    Verkrijg de trappist status van het biertje
     */
    public boolean isTrappist() {
        return trappist;
    }

    /***
     * We maken een biertsoort uniek op basis van zijn benaming
     * @param o Object dat vergeleken moet worden
     * @return boolean  Is hij gelijk, true or false
     */
    @Override
    public boolean equals(Object o) {
        //Uniek op basis van naam
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bier bier = (Bier) o;
        return Objects.equals(naam, bier.naam);
    }

    /***
     * 32 bit hash code dat een biertje uniek maakt
     * @return int unieke hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    /***
     * Vergelijkt 2 bieren op basis van de naam.
     * @param o Bier dat vergeleken moet worden
     * @return int
     *    * Als {@code o} alfabetisch voor de andere komt; return 1
     *    * Als {@code o} alfabetisch na de andere komt; return -1
     *    * Als {@code o} alfabetisch hetzelfde is; return 0
     */
    @Override
    public int compareTo(Bier o) {
        return o.getNaam().compareTo(this.getNaam());
    }

    /***
     * De opmaak van de toString formaten
     * @return String geformateerde string met de nodige informatie
     */
    @Override
    public String toString() {
        return String.format("%-25s Sinds:%-12s (%.2f°) Gisting: %-4s",naam,gebrouwenSinds.toString(),alcoholPercentage,gisting.toString());
    }
}
package be.kdg.bierproject.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:13
 */
@XmlType(propOrder = {"naam", "gebrouwenSinds", "alcoholPercentage", "bitterheidsgraad", "trappist"})
public class Bier implements Comparable<Bier> {
    private String naam;
    private Gisting gisting;
    private LocalDate gebrouwenSinds;
    private double alcoholPercentage;
    private int bitterheidsgraad;
    private boolean trappist;

    public Bier(String naam, Gisting gisting, LocalDate gebrouwenSinds, double alcoholPercentage, int bitterheidsgraad, boolean trappist) {
        setNaam(naam);
        setGisting(gisting);
        setGebrouwenSinds(gebrouwenSinds);
        setAlcoholPercentage(alcoholPercentage);
        setBitterheidsgraad(bitterheidsgraad);
        setTrappist(trappist);
    }

    public Bier() {
        this("Onbekend",Gisting.ONBEKEND,LocalDate.now(),0,0,false);
    }

    @XmlElement(name = "naam")
    public void setNaam(String naam) {
        if(naam == null || naam.equals(""))
            throw new IllegalArgumentException("De naam mag niet leeg zijn.");
        this.naam = naam;
    }

    @XmlAttribute(name = "gisting")
    public void setGisting(Gisting gisting) {
        if(gisting != Gisting.HOGE && gisting != Gisting.LAGE && gisting != Gisting.SPONTAAN && gisting != Gisting.GEMENGDE)
            throw new IllegalArgumentException("Kies een juiste gisting.");
        this.gisting = gisting;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlElement(name = "gebrouwen-sinds")
    public void setGebrouwenSinds(LocalDate gebrouwenSinds) {
        if(!gebrouwenSinds.isBefore(LocalDate.now()))
            throw new IllegalArgumentException("De brouwdatum moet in het verleden liggen.");
        this.gebrouwenSinds = gebrouwenSinds;
    }

    @XmlElement(name = "alcohol-percentage")
    public void setAlcoholPercentage(double alcoholPercentage) {
        if(alcoholPercentage <  0 || alcoholPercentage >= 100)
            throw new IllegalArgumentException("Alcoholpercentage kan niet lager zijn dan 0 en niet hoger dan 100.");
        this.alcoholPercentage = alcoholPercentage;
    }

    @XmlElement(name = "bitterheidsgraad")
    public void setBitterheidsgraad(int bitterheidsgraad) {
        if(bitterheidsgraad <  0)
            throw new IllegalArgumentException("De EBU waarde moet tenminste 0 zijn.");
        this.bitterheidsgraad = bitterheidsgraad;
    }

    @XmlElement(name = "trappist")
    public void setTrappist(boolean trappist) {
        this.trappist = trappist;
    }

    public String getNaam() {
        return naam;
    }

    public Gisting getGisting() {
        return gisting;
    }

    public LocalDate getGebrouwenSinds() {
        return gebrouwenSinds;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public int getBitterheidsgraad() {
        return bitterheidsgraad;
    }

    public boolean isTrappist() {
        return trappist;
    }

    //Uniek op basis van naam
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bier bier = (Bier) o;
        return Objects.equals(naam, bier.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    @Override
    public int compareTo(Bier o) {
        return o.getNaam().compareTo(this.getNaam());
    }

    @Override
    public String toString() {
        return String.format("%-25s Sinds:%-12s (%.2f°) Gisting: %-4s",naam,gebrouwenSinds.toString(),alcoholPercentage,gisting.toString());
    }
}
package be.kdg.bierproject.model;

import java.util.Objects;

/**
 * Maxim Derboven
 * 5/10/2021
 */
//Onder 'Drink' wordt er een alcoholisch drankje verstaan
public class Drink implements Comparable<Drink>{
    protected String naam;
    protected double alcoholPercentage;

    public void setNaam(String naam) {
        if (naam == null || naam.equals(""))
            throw new IllegalArgumentException("De naam mag niet leeg zijn.");
        this.naam = naam;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        if (alcoholPercentage < 0 || alcoholPercentage >= 100)
            throw new IllegalArgumentException("Alcoholpercentage kan niet lager zijn dan 0 en niet hoger dan 100.");
        this.alcoholPercentage = alcoholPercentage;
    }

    public String getNaam() {
        return naam;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    //Uniek op basis van naam
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drink bier = (Drink) o;
        return Objects.equals(naam, bier.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam);
    }

    @Override
    public int compareTo(Drink o) {
        return o.getNaam().compareTo(this.getNaam());
    }

}

package be.kdg.bierproject;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Bieren;
import be.kdg.bierproject.model.Drink;
import be.kdg.bierproject.reflection.ReflectionTools;

/**
 * @author Maxim Derboven
 * @version 1.0 5/10/2021 20:13
 */
public class Demo_3 {
    public static void main(String[] args) {
        ReflectionTools.classAnalysis(Drink.class,Bier.class,Bieren.class);
        System.out.println("Aangemaakt object door runAnnotated:");
        System.out.println(ReflectionTools.runAnnotated(Bier.class).toString());
    }
}
package be.kdg.bierproject.reflection;

/**
 * Maxim Derboven
 * 5/10/2021
 */
public class ReflectionTools {
    public static void classAnalysis(Class aClass) {
        System.out.printf("Analyse van de klasse: %s\n=======================================\n",aClass.getSimpleName());
        System.out.printf("Fully qualified name: %S\n",aClass.getName());



    }
}

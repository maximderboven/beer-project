package be.kdg.bierproject.reflection;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Maxim Derboven
 * 5/10/2021
 */
public class ReflectionTools {
    public static void classAnalysis(Class... aClasses) {
        for (Class aClass : aClasses) {
            System.out.printf("Analyse van de klasse: %s\n=======================================\n", aClass.getSimpleName());
            System.out.printf("Fully qualified name: %s\n", aClass.getName());
            System.out.printf("Naam van de superklasse: %s\n", aClass.getSuperclass().getSimpleName());
            System.out.printf("Naam van de package: %s\n", aClass.getPackageName());
            System.out.print("Interfaces: ");
            for (Class anInterface : aClass.getInterfaces()) {
                System.out.printf("%s ", anInterface.getSimpleName());
            }
            System.out.print("\nConstructors: ");
            for (Constructor aConstructor : aClass.getConstructors()) {
                System.out.printf("%s ", aConstructor.toGenericString());
            }
            System.out.print("\nAttributen: ");
            for (Field aField : aClass.getDeclaredFields()) {
                System.out.printf("%s(%s) ", aField.getName(),aField.getType().getSimpleName());
            }
            System.out.print("\nGetters: ");
            for (Method aGetter : aClass.getDeclaredMethods()) {
                if (aGetter.getName().startsWith("get")) {
                    System.out.printf("%s ", aGetter.getName());
                }
            }
            System.out.print("\nSetters: ");
            for (Method aSetter : aClass.getDeclaredMethods()) {
                if (aSetter.getName().startsWith("set")) {
                    System.out.printf("%s ", aSetter.getName());
                }
            }
            System.out.print("\nAndere methoden: ");
            for (Method aMethod : aClass.getDeclaredMethods()) {
                if (!aMethod.getName().startsWith("get") && !aMethod.getName().startsWith("set")) {
                    System.out.printf("%s ", aMethod.getName());
                }
            }
            System.out.println("");
        }
    }

    public static Object runAnnotated (Class aClass) {
        Object o = null;
        try {
            o = aClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        for (Method aMethod : aClass.getMethods()) {
                if (Arrays.toString(aMethod.getGenericParameterTypes()).equals("[class java.lang.String]") && aMethod.isAnnotationPresent(CanRun.class)) {
                    try {
                        aMethod.invoke(o, aMethod.getAnnotation(CanRun.class).value());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            return o;
    }
}

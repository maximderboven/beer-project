package be.kdg.bierproject.model;

import java.io.Serializable;
import java.util.*;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:14
 */
public class Bieren implements Serializable {
    private Set<Bier> bierenTreeSet = new TreeSet<>();

    private static final long serialVersionUID = 1L;

    public boolean add(Bier bier) {
        if (bierenTreeSet.contains(bier))
            return false;
        return bierenTreeSet.add(bier);
    }

    public boolean remove(String naam) {
        for (Iterator<Bier> iterator = bierenTreeSet.iterator(); iterator.hasNext(); ) {
            Bier b = iterator.next();
            if (b.getNaam().equals(naam)) {
                return bierenTreeSet.remove(b);
            }
        }
        return false;
    }

    public Bier search(String naam) {
        for (Bier bier : bierenTreeSet) {
            if (bier.getNaam().equals(naam)) {
                return bier;
            }
        }
        return null;
    }

    public List<Bier> sortedOnName() {
        List<Bier> bierenList = new ArrayList<>(bierenTreeSet);
        Collections.sort(bierenList, Collections.reverseOrder());
        return bierenList;
    }

    public List<Bier> sortedOnGebrouwenSinds() {
        List<Bier> bierenList = new ArrayList<>(bierenTreeSet);
        bierenList.sort(new BierCompareOnGebrouwenSinds());
        return bierenList;
    }

    public List<Bier> sortedOnAlcoholpercentage()  {
        List<Bier> bierenList = new ArrayList<>(bierenTreeSet);
        bierenList.sort(new BierenCompareOnAlcoholpercentage());
        return bierenList;
    }

    private static class BierCompareOnGebrouwenSinds implements Comparator<Bier> {
        @Override
        public int compare(Bier o1, Bier o2) {
            return o1.getGebrouwenSinds().compareTo(o2.getGebrouwenSinds());
        }
    }

    private static class BierenCompareOnAlcoholpercentage implements Comparator<Bier> {
        @Override
        public int compare(Bier o1, Bier o2) {
            return Double.compare(o1.getAlcoholPercentage(),o2.getAlcoholPercentage());
        }
    }

    public int getSize() {
        return bierenTreeSet.size();
    }


}

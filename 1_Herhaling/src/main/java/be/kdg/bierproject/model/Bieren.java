package be.kdg.bierproject.model;

import java.util.*;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:14
 */
public class Bieren {
    private Set<Bier> bierenTreeSet = new TreeSet<>();

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
        Collections.sort(bierenList, new Comparator<Bier>() {
            @Override
            public int compare(Bier o1, Bier o2) {
                return o1.getNaam().compareTo(o2.getNaam());
            }
        });
        return bierenList;
    }

    public List<Bier> sortedOnGebrouwenSinds() {
        List<Bier> bierenList = new ArrayList<>(bierenTreeSet);
        Collections.sort(bierenList, new Comparator<Bier>() {
            @Override
            public int compare(Bier o1, Bier o2) {
                return o1.getGebrouwenSinds().compareTo(o2.getGebrouwenSinds());
            }
        });
        return bierenList;
    }

    public List<Bier> sortedOnAlcoholpercentage()  {
        List<Bier> bierenList = new ArrayList<>(bierenTreeSet);
        Collections.sort(bierenList, new Comparator<Bier>() {
            @Override
            public int compare(Bier o1, Bier o2) {
                return Double.compare(o1.getAlcoholPercentage(),o2.getAlcoholPercentage());
            }
        });
        return bierenList;
    }

    public int getSize() {
        return bierenTreeSet.size();
    }


}

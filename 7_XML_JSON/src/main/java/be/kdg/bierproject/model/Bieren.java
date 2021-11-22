package be.kdg.bierproject.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:14
 */
@XmlRootElement(name = "bieren")
public class Bieren {

    private List<Bier> bierenList = new ArrayList<Bier>();

    public List<Bier> getBierenList() {
        return bierenList;
    }

    @XmlElement(name = "bier")
    public void setBierenList(List<Bier> bierenList) {
        this.bierenList = bierenList;
    }

    public boolean add(Bier bier) {
        if (bierenList.contains(bier))
            return false;
        return bierenList.add(bier);
    }

    public boolean remove(String naam) {
        for (Iterator<Bier> iterator = bierenList.iterator(); iterator.hasNext(); ) {
            Bier b = iterator.next();
            if (b.getNaam().equals(naam)) {
                return bierenList.remove(b);
            }
        }
        return false;
    }

    public Bier search(String naam) {
        for (Bier bier : bierenList) {
            if (bier.getNaam().equals(naam)) {
                return bier;
            }
        }
        return null;
    }

    public List<Bier> sortedOnName() {
        List<Bier> bierenList = new ArrayList<>(this.bierenList);
        Collections.sort(bierenList, Collections.reverseOrder());
        return bierenList;
    }

    public List<Bier> sortedOnGebrouwenSinds() {
        List<Bier> bierenList = new ArrayList<>(this.bierenList);
        bierenList.sort(new BierCompareOnGebrouwenSinds());
        return bierenList;
    }

    public List<Bier> sortedOnAlcoholpercentage()  {
        List<Bier> bierenList = new ArrayList<>(this.bierenList);
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
        return bierenList.size();
    }


}

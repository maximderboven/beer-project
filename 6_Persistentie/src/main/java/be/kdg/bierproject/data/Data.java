package be.kdg.bierproject.data;

import be.kdg.bierproject.model.Bier;
import be.kdg.bierproject.model.Gisting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Derboven
 * @version 1.0 23/09/2021 15:13
 */
public class Data {
    public static List<Bier> getData(){
        List<Bier> bieren = new ArrayList<>();
        bieren.add(new Bier("Bolleke",Gisting.HOGE, LocalDate.of(1833,1,1),5.3,23,false));
        bieren.add(new Bier("Ciney",Gisting.HOGE, LocalDate.of(1978,6,23),7,0,false));
        bieren.add(new Bier("Duvel",Gisting.HOGE, LocalDate.of(1970,1,1),8.5,32,false));
        bieren.add(new Bier("Leffe Bruin",Gisting.HOGE, LocalDate.of(1952,1,1),6.5,15,false));
        bieren.add(new Bier("Palm",Gisting.HOGE, LocalDate.of(1929,1,1),5.2,18,false));
        bieren.add(new Bier("La Chouffe",Gisting.HOGE, LocalDate.of(1982 ,1,1),8.1,0,false));
        bieren.add(new Bier("Grimbergen Blond",Gisting.HOGE, LocalDate.of(1993 ,1,1),6.7,22,false));
        bieren.add(new Bier("Hommelbier",Gisting.HOGE, LocalDate.of(1981 ,1,1),7.5,40,false));
        bieren.add(new Bier("Westvleteren Blond",Gisting.HOGE, LocalDate.of(1838 ,1,1),5.8,41,true));
        bieren.add(new Bier("Cornet",Gisting.HOGE, LocalDate.of(2014 ,8,26),8.6,25,false));
        bieren.add(new Bier("Corona",Gisting.LAGE, LocalDate.of(1925 ,1,1),4.5,0,false));
        bieren.add(new Bier("Brugse Zot",Gisting.HOGE, LocalDate.of(2005 ,1,1),6,23,false));
        bieren.add(new Bier("Cristal Alken",Gisting.LAGE, LocalDate.of(1928 ,1,1),5,20,false));
        bieren.add(new Bier("Omer.",Gisting.HOGE, LocalDate.of(2008 ,1,1),8,35,false));
        bieren.add(new Bier("Jupiler",Gisting.LAGE, LocalDate.of(1966 ,1,1),5.4,18,false));

        //dubbele
        //bieren.add(new Bier("Jupiler",Gisting.LAGE, LocalDate.of(1966 ,1,1),5.2,18,false));
        return bieren;
    }
}
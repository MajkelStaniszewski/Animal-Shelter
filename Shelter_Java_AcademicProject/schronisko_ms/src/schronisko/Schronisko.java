
package schronisko;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import schronisko.GUI.okno_wyboru;
import schronisko.GeneracjaDanych.wyjatek_pliku;
import schronisko.Zwierzaki.Zwierze;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import schronisko.Zwierzaki.Kot;
import schronisko.Zwierzaki.Pies;
import schronisko.Zwierzaki.Ptak;

public class Schronisko {
    //mapa z zwierzakami: key - ID, value - obiekt klasy Zwierze
    static LinkedHashMap<Integer, Zwierze> map;

    public Vector getVector() //Vector z danymi zwierząt
    {
        Vector data = new Vector();
        
        for (Zwierze i : map.values()) 
        {
                data.add(i.vector());
        }
        return data;
    }

    public void sortuj(String wybor)
    {
        Comparator <Zwierze> c = Comparator.comparing(Zwierze::getID); // Wstępna inicjalizacja ianczej pokazuje błąd
        
        switch (wybor) // sortuj po danym polu, w zależności od wybór zmienia się comparator
        {
            case "ID": 
                c = Comparator.comparing(Zwierze::getID);
            break;
            
            case "Gatunek":
               c = Comparator.comparing(Zwierze::getGatunek);
            break;
            
            case "Rasa":
               c = Comparator.comparing(Zwierze::getRasa);
            break;
            
            case "Wiek":
               c = Comparator.comparing(Zwierze::getWiek);
            break;
            
             case "Imie":
               c = Comparator.comparing(Zwierze::getImie);
            break;
            
            case "Czip":
                c = Comparator.comparing(Zwierze::getCzip);  
            break;
            
            case "Zdrowy":
               c = Comparator.comparing(Zwierze::getZdrowy);
            break;
            
            case "Plec":
                c = Comparator.comparing(Zwierze::getPlec);
            break;
            
            case "Sterylizowany":
              c = Comparator.comparing(Zwierze::getSterylizowany);  
            break;
            
            case "Waga":
               c = Comparator.comparing(Zwierze::getWaga);
            break;
        }
    // Zapisanie wyniku sortowania do nowej mapy
       Map<Integer, Zwierze> result = map.entrySet().stream().sorted(Map.Entry.comparingByValue(c)).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
       map.clear(); // wyczyszczenie starej mapy
       map.putAll(result); // przeniesienie wyniku sortowania do mapy
    }
    
    static public LinkedHashMap<Integer, Zwierze> getMap()
    {
        return map;
    }


    
    public void saveData()
    {
        try {
                FileWriter file = new FileWriter("D:/data/file.txt");

                for(Zwierze i : map.values())
                {
                     file.write(i.toString());
                }
                file.close();
            } catch (IOException ex) {
                Logger.getLogger(Schronisko.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public void loadData() throws Exception
    {
        String linia_danych;
        try {
            FileReader xd = new FileReader("D:/data/file.txt");
            BufferedReader file = new BufferedReader(xd);

            linia_danych = file.readLine();

            if(linia_danych == null) throw new wyjatek_pliku("Plik jest pusty. Proszę wygenrowanować dane!");


            while(linia_danych != null){
               StringTokenizer st = new StringTokenizer ( linia_danych ); // StringTokenizer do podziału wejściowego stringa na ciągi z infomracją o konkretnym polu, pola odzielone są spacją
               st.nextToken();
               String dane = st.nextToken();
               Zwierze x;
               x = null;
               switch (dane)
               {
                   case "Pies" -> x = new Pies(linia_danych);
                   case "Kot" -> x = new Kot(linia_danych);
                   case "Ptak" -> x= new Ptak(linia_danych);
               }            
               map.put(x.getID(), x);

               linia_danych = file.readLine();

            }

            file.close();
           

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Schronisko.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Schronisko.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        Schronisko schronisko;
        schronisko = new Schronisko();
        map = new LinkedHashMap<Integer, Zwierze>();
        new okno_wyboru( schronisko);
       
    }
    
}

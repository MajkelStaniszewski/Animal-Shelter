
package schronisko.GeneracjaDanych;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Generartor {
    
    private final String[] Psy = {"Bulterier","Shiba", "Cane", "Husky", "Owczarek", "Labrador", "Jack", "Weimarski", "Wyzel"};
    private final String[] Koty = {"Perski", "Coon", "Burmski", "Korat", "Byrtyjski", "Mieszaniec"};
    private final String[] Ptaki = {"Papuga", "Kanarek", "Golab", "Mewa", "Kakadu", "Kruk", "Kawka"};
    
    private String[] imiona_zwierzak = {"Beny", "Alex", "Hamurabi", "Postrach", "Wezuwiusz", "Szwajceneger", "Pompa", "Sztanga", "Lokiec", "Pieta", "Masa", "Pudzian", "Sali"};

    public void generateData(int ile, String sciezka) 
    {
        try{
            // File oraz Print Writer
            FileWriter writer = new FileWriter(sciezka);
            PrintWriter printer = new PrintWriter(writer);
            
            
            for (int i = 0; i < ile; i++) 
            {
                String s  = "";

                Random wylosowane;
                wylosowane = new Random();
                int przypadek = wylosowane.nextInt(3);

                switch (przypadek)
                {
                    case 2 -> {
                        s  = "" + i + " Ptak ";
                        s += Ptaki[wylosowane.nextInt(Ptaki.length)] + " ";
                    }

                    case 1 -> {
                        s  = "" + i + " Kot ";
                        s += Koty[wylosowane.nextInt(Koty.length)] + " ";
                    }

                    case 0 -> {
                        s  = "" + i + " Pies ";
                        s += Psy[wylosowane.nextInt(Psy.length)] + " ";
                       
                    }

                }

                s += (wylosowane.nextInt(9) + 1) + " ";
                s += imiona_zwierzak[wylosowane.nextInt(imiona_zwierzak.length)] + " ";
                s += (wylosowane.nextInt(2) == 1);
                s += " " + (wylosowane.nextInt(2) == 1);
                if(wylosowane.nextInt(2) == 1)
                    s+=" M";
                else
                    s+=" K";
                
                s += " " + (wylosowane.nextInt(2) == 1) + " ";
                
                s += (wylosowane.nextInt(5) + 1);
                printer.println(s); // Nowy wiersz - zapisanie do pliku
            }
            writer.close();  // zakończenie Print Writer
        }catch(Exception e) // wyjątek
        {
            System.out.println(e.getMessage());
        } 
    }
}

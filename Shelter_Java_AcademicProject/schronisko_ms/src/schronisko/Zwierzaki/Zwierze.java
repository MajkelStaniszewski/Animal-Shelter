
package schronisko.Zwierzaki;

import java.util.StringTokenizer;
import java.util.Vector;

abstract public class Zwierze {
    // Dane o zwierzeciu
    String imie;
    String gatunek;
    String rasa;
    Integer ID;
    int wiek;
    int waga;
    boolean plec;
    boolean sterylizowany;
    boolean czip;
    boolean zdrowy;


    static Vector usedID = new Vector();


    public static Vector getUsedID()
    {
        return usedID;
    }

    public Integer getID()
    {
        return ID;
    }

    public String getGatunek()
    {
        return gatunek;
    }

    public String getRasa()
    {
        return rasa;
    }

    public int getWiek()
    {
        return wiek;
    }

    public String getImie()
    {
        return imie;
    }

    public boolean getCzip()
    {
        return czip;
    }

    public boolean getZdrowy()
    {
        return zdrowy;
    }

    public boolean getPlec()
    {
        return plec;
    }

    public boolean getSterylizowany()
    {
        return sterylizowany;
    }

    public int getWaga()
    {
        return waga;
    }

    // Funkcja zwracająca Vector zawierający wartości wszystkich pól klasy
    public Vector vector()
    {
        Vector temporary = new Vector();
        temporary.add(ID);
        temporary.add(gatunek);
        temporary.add(rasa);
        temporary.add(wiek);
        temporary.add(imie);
        temporary.add(czip);
        temporary.add(zdrowy);
        if (plec)
            temporary.add("M");
        else
            temporary.add("K");
        temporary.add(sterylizowany);
        temporary.add(waga);

        return temporary;
    }

    // Konstruktor klasy zwierze
    public Zwierze(Integer ID, String gatunek, String rasa, int wiek, String imie, boolean czip, boolean zdrowy, boolean plec, boolean sterylizowany, int waga)
    {
               
        this.ID = ID;
        this.gatunek = gatunek;
        this.rasa = rasa;
        this.wiek = wiek;
        this.imie = imie;
        this.czip = czip;
        this.zdrowy = zdrowy;
        this.plec = plec;
        this.sterylizowany = sterylizowany;
        this.waga = waga;
        
        usedID.add(ID);
    }
    // Drugi konstruktor clasy zwierze, przyjmujący jako argumnet String posiadajacy info o obieckcie
    public Zwierze(String line)
    {
       StringTokenizer st = new StringTokenizer ( line ); // StringTokenizer do podziału wejściowego stringa na ciągi z infomracją o konkretnym polu, pola odzielone są spacją
       
       String dane = st.nextToken();
       this.ID = Integer.parseInt(dane);
       
       dane = st.nextToken();
       this.gatunek = dane;
       
       dane = st.nextToken();
       this.rasa = dane;
       
       dane = st.nextToken();
       this.wiek = Integer.parseInt(dane); // z String na int
       
       dane = st.nextToken();
       this.imie = dane;
       
       dane = st.nextToken();
       this.czip = dane.equals("true"); // Jeżeli string == 1 to true
      
       dane = st.nextToken();
       this.zdrowy = dane.equals("true");

       dane = st.nextToken();
       this.plec = dane.equals("M");
       
       dane = st.nextToken();
       this.sterylizowany = dane.equals("true");

       dane = st.nextToken();
       this.waga =  Integer.parseInt(dane); // z String na double
       
       usedID.add(ID);


    }

    // Funckjca zwracająca Stringa zawierającego wartości wszystkich pól klasy

    @Override
    public String toString() //zamiana booleana na stringa
    {
        String Splec;
        if (plec)
           Splec = "M";
        else
           Splec = "K";
        return "" + ID + " " + gatunek + " " + rasa + " " + wiek + " " + imie + " " + czip + " " + zdrowy + " " + Splec + " " + sterylizowany + " " + waga + "\n";
    }


    //Abstrakcyjna funkcja dajglos()
    public abstract void daj_glos();
}

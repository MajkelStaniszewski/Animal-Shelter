
package schronisko.Zwierzaki;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;


public class Pies extends Zwierze{

    //konstruktor psa
    public Pies(Integer ID, String gatunek, String rasa, int wiek, String imie, boolean czip, boolean zdrowy, boolean plec, boolean sterylizowany, int waga) {
        super(ID, gatunek, rasa, wiek, imie, czip, zdrowy, plec, sterylizowany, waga);
    }
    
    public Pies(String line) {
        super(line);
    }

    // Implementacja abstrakcyjnej metody daj_głos
    public void daj_glos()
    {
        System.out.println("Hał");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("").toURI().toURL()); // ścieżka do pliku audio
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream); // otworzenie pliku audio
            clip.start(); // Start audio
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

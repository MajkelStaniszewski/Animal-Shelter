
package schronisko.Zwierzaki;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Kot extends Zwierze {

    public Kot(Integer ID, String gatunek, String rasa, int wiek, String imie, boolean czip, boolean zdrowy, boolean plec, boolean sterylizowany, int waga) {
        super(ID, gatunek, rasa, wiek, imie, czip, zdrowy, plec, sterylizowany, waga);
    }
    public Kot(String line) {
        super(line);
    }


    public void daj_glos()
    {
        System.out.println("Miał");
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("").toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

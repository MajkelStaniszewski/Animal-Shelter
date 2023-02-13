
package schronisko.GUI;

import schronisko.Zwierzaki.Zwierze;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import schronisko.Zwierzaki.Kot;
import schronisko.Zwierzaki.Pies;
import schronisko.Zwierzaki.Ptak;


public class okno_oddania {
    JFrame frame;
    okno_wyboru oddanie_window;
    JComboBox gatunek;
    JComboBox wiek;
    JComboBox plec;
    JCheckBox zdrowy;

    JCheckBox wysterylizowany;
    JCheckBox zaszczepiony;
    JTextField rasa;
    JTextField imieZw;
    JTextField waga;
    JButton oddaj_do_schroniska;

    class dzialanie_przyciskow implements ActionListener{
        okno_oddania window;
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == oddaj_do_schroniska)
            {
                oddajZwierze();
            }
        }

        dzialanie_przyciskow(okno_oddania window){
            this.window = window;
        }
    }



    void oddajZwierze()
    {
        Integer i = 0;
        do
        {
            i++;
        }while(Zwierze.getUsedID().contains(i));

        boolean dol_plci;
        if (plec.getSelectedItem().equals("M"))
            dol_plci = true;
        else
            dol_plci = false;

        Zwierze x;
        x = null;
        switch (gatunek.getSelectedItem().toString())
        {
            case "Pies" -> x = new Pies(i, gatunek.getSelectedItem().toString(), rasa.getText(), Integer.parseInt(wiek.getSelectedItem().toString()) , imieZw.getText(), zaszczepiony.isSelected(), zdrowy.isSelected(), dol_plci, wysterylizowany.isSelected(), Integer.parseInt(waga.getText()));
            case "Kot" -> x= new Kot(i, gatunek.getSelectedItem().toString(), rasa.getText(), Integer.parseInt(wiek.getSelectedItem().toString()) , imieZw.getText(), zaszczepiony.isSelected(), zdrowy.isSelected(), dol_plci, wysterylizowany.isSelected(), Integer.parseInt(waga.getText()));
            case "Ptak" -> x = new Ptak(i, gatunek.getSelectedItem().toString(), rasa.getText(), Integer.parseInt(wiek.getSelectedItem().toString()) , imieZw.getText(), zaszczepiony.isSelected(), zdrowy.isSelected(), dol_plci, wysterylizowany.isSelected(), Integer.parseInt(waga.getText()));
        }

        oddanie_window.map.put(x.getID(), x);
        oddanie_window.schronisko.sortuj(oddanie_window.combo.getSelectedItem().toString());
        oddanie_window.refresh();
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
    }

    okno_oddania(okno_wyboru w){
        oddanie_window = w;
        String[] gatunki = {"Pies","Kot","Ptak"};
        String[] wieki = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        String[] plci = {"M","K"};

        frame = new JFrame();
        frame.setTitle("Adoptowanie zwierzaka");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,400);

        JPanel panel_glowny = new JPanel();
        JPanel dane = new JPanel();
        dane.setLayout(new GridLayout(4,1));

        JPanel zwierze = new JPanel();
        JLabel zwierzeLabel = new JLabel("DANE ZWIERZAKA:");

        oddaj_do_schroniska = new JButton();
        oddaj_do_schroniska.setText("Przekaz schronisku");
        oddaj_do_schroniska.addActionListener(new dzialanie_przyciskow(this));

        JPanel cechyzwierzaka = new JPanel();
        cechyzwierzaka.setLayout(new GridLayout(2,1));

        JPanel cechyTitle = new JPanel();
        cechyTitle.setLayout(new GridLayout(1,9));

        JLabel[] nazwy_kolumn= new JLabel[9];
        for(int i =0;i<9;i++)
        {
            switch (i){
                case 0 -> nazwy_kolumn[i] = new JLabel("Gatunek");
                case 1 -> nazwy_kolumn[i] = new JLabel("Rasa");
                case 2 -> nazwy_kolumn[i] = new JLabel("Wiek");
                case 3 -> nazwy_kolumn[i] = new JLabel("Imie");
                case 4 -> nazwy_kolumn[i] = new JLabel("Szczepiony");
                case 5 -> nazwy_kolumn[i] = new JLabel("Zdrowy");
                case 6 -> nazwy_kolumn[i] = new JLabel("Płeć");
                case 7 -> nazwy_kolumn[i] = new JLabel("Sterylizowany");
                case 8 -> nazwy_kolumn[i] = new JLabel("Waga");
            };
            cechyTitle.add(nazwy_kolumn[i]);
        }

        JPanel zestaw_cech = new JPanel();
        zestaw_cech.setLayout(new GridLayout(1,9));

        gatunek = new JComboBox(gatunki);
        zestaw_cech.add(gatunek);
        gatunek.addActionListener(new dzialanie_przyciskow(this));


        rasa = new JTextField("nieokreślona");
        zestaw_cech.add(rasa);

        wiek = new JComboBox(wieki);
        zestaw_cech.add(wiek);

        imieZw = new JTextField("nieokreślone");
        zestaw_cech.add(imieZw);

        zaszczepiony = new JCheckBox();
        zestaw_cech.add(zaszczepiony);

        zdrowy = new JCheckBox();
        zestaw_cech.add(zdrowy);

        plec = new JComboBox(plci);
        zestaw_cech.add(plec);

        wysterylizowany = new JCheckBox();
        zestaw_cech.add(wysterylizowany);

        waga = new JTextField("5");
        zestaw_cech.add(waga);

        cechyzwierzaka.add(cechyTitle);
        cechyzwierzaka.add(zestaw_cech);

        zwierze.add(zwierzeLabel);
        zwierze.add(cechyzwierzaka);
        zwierze.add(oddaj_do_schroniska);

        panel_glowny.add(zwierze);

        JLabel label1 = new JLabel(); //JLabel Creation
        label1.setIcon(new ImageIcon("Shelter.jpg")); //Sets the image to be displayed as an icon
        panel_glowny.add(label1);

        frame.add(panel_glowny, BorderLayout.CENTER);
        frame.setVisible(true);

    }

}

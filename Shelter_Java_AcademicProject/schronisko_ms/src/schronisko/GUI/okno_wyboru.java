
package schronisko.GUI;

import schronisko.Zwierzaki.Zwierze;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Vector;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import schronisko.Schronisko;
import schronisko.GeneracjaDanych.Generartor;


public class okno_wyboru {
    JButton daj_glos;
    int count = 0;
    boolean flag, flag1 = false;
    JLabel label;
    Schronisko schronisko;
    LinkedHashMap<Integer, Zwierze> map;
    JPanel listPanel;
    JScrollPane tabelka;
    JFrame frame;
    JComboBox combo;

    JTable table;

    JButton zaladuj_dane;
    JButton zapisz_dane;
    JButton wygeneruj_dane;
    JButton adopcja;
    JButton oddaj_zwierzaka;

    Integer selectedAnimal = null;
    Vector cechy_zwierzaka;



    public okno_wyboru(Schronisko schronisko)
    {

        label = new JLabel("Masz już tyle zwierzaków: 0");

        listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());

        
        this.schronisko = schronisko;
        
        map = schronisko.getMap();
        
        
        cechy_zwierzaka = new Vector();
        cechy_zwierzaka.add("Identyfikator");
        cechy_zwierzaka.add("Gatunek");
        cechy_zwierzaka.add("Rasa");
        cechy_zwierzaka.add("Wiek");
        cechy_zwierzaka.add("Imie");
        cechy_zwierzaka.add("Czip");
        cechy_zwierzaka.add("Zdrowy");
        cechy_zwierzaka.add("Plec");
        cechy_zwierzaka.add("Sterylizowany");
        cechy_zwierzaka.add("Waga");
        
        
        frame = new JFrame();            
        frame.setTitle("Schronisko MCHTR");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200,500);

        JPanel PanelMain = new JPanel();

        PanelMain.setLayout(new BorderLayout());

        JPanel PanelZwierzakow = new JPanel();
        adopcja = new JButton();
        adopcja.setText("Adopcja");
        adopcja.addActionListener(new AkcjaPrzyciski(this));
        adopcja.setEnabled(false);

        oddaj_zwierzaka = new JButton();
        oddaj_zwierzaka.setText("Oddanie");
        oddaj_zwierzaka.addActionListener(new AkcjaPrzyciski(this));

        PanelZwierzakow.setLayout(new BorderLayout());
        JPanel PanelWyboru = new JPanel();
        PanelWyboru.add(label);
        PanelWyboru.add(oddaj_zwierzaka);
        PanelWyboru.add(adopcja);

        wygeneruj_dane = new JButton();
        wygeneruj_dane.setText("Generuj losowe dane");
        wygeneruj_dane.addActionListener(new AkcjaPrzyciski( this));
        PanelWyboru.add(wygeneruj_dane);
        
        zaladuj_dane = new JButton();
        zaladuj_dane.setText("Załaduj dane");
        zaladuj_dane.addActionListener(new AkcjaPrzyciski(this));

        PanelWyboru.add(zaladuj_dane);

        zapisz_dane = new JButton();
        zapisz_dane.setText("Zapisz dane");
        zapisz_dane.addActionListener(new AkcjaPrzyciski( this));
        PanelWyboru.add(zapisz_dane);

        daj_glos = new JButton();
        daj_glos.setText("Daj głos");
        daj_glos.addActionListener(new AkcjaPrzyciski( this));
        PanelWyboru.add(daj_glos);
        PanelWyboru.setLayout(new GridLayout(2,4));

        JLabel sortLabel = new JLabel();
        sortLabel.setText("Sortowanie:");
        sortLabel.setHorizontalAlignment(JLabel.RIGHT);
        
       
        
        combo = new JComboBox(cechy_zwierzaka);
        combo.addActionListener(new AkcjaPrzyciski(this));
    
        PanelWyboru.add(sortLabel);
        PanelWyboru.add(combo);
        

        PanelZwierzakow.add(PanelWyboru,BorderLayout.NORTH);
        PanelZwierzakow.add(listPanel,BorderLayout.CENTER);


        PanelMain.add(PanelZwierzakow, BorderLayout.CENTER);
        
        frame.add(PanelMain, BorderLayout.CENTER);
        frame.setVisible(true);
        try {
            wczytajDane();
        } catch (Exception e) {
            flag1 = true;
            JOptionPane.showMessageDialog(null,"Plik file.txt jest pusty! Proszę wygenrowanować dane oraz ponownie uruchomić program!!","Warning",JOptionPane.WARNING_MESSAGE);
            zaladuj_dane.setVisible(false);
            daj_glos.setVisible(false);
            adopcja.setVisible(false);
            combo.setVisible(false);
            oddaj_zwierzaka.setVisible(false);
            zapisz_dane.setVisible(false);
           label.setVisible(false);
           sortLabel.setVisible(false);
            throw new RuntimeException(e);
        }
        aktualizujTabele();
        
    }
    
    class AkcjaPrzyciski implements ActionListener{
    okno_wyboru window;
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == daj_glos )
        {
            int i = table.getSelectedRow();
            if ( i != -1)
            {
                String ID = table.getValueAt(i, 0).toString(); // print first column value from selected row

                String gatunek = table.getValueAt(i,1).toString();
                System.out.println("To do wywołania głosu - ID: " + ID +" Gatunek: "+ gatunek);
            }
        }

        if(e.getSource()==adopcja)
        {
            count++;
            label.setText("Aktualnie posiadasz tyle zwierzaków: " + count);
            
            if(selectedAnimal != null)
                new okno_adopcji(window);
        }
        if(e.getSource()==combo)
        {
            schronisko.sortuj(combo.getSelectedItem().toString());
            window.refresh();
        }
        if(e.getSource()== oddaj_zwierzaka)
        {
            if(count !=0){
            count--;
            label.setText("Aktualnie posiadasz tyle zwierzaków: " + count);}
            new okno_oddania(window);
        }

        if(e.getSource()== wygeneruj_dane)
        {
            flag = true;
            if(flag == true && flag1 == false){
                zaladuj_dane.setVisible(true);
                daj_glos.setVisible(true);
                adopcja.setVisible(true);
                combo.setVisible(true);
                oddaj_zwierzaka.setVisible(true);
                zapisz_dane.setVisible(true);
                label.setVisible(true);
            }
            Generartor generator = new Generartor();
            generator.generateData(15, "D:/data/file.txt");
        }
        if(e.getSource()== zapisz_dane)
        {
            schronisko.saveData(); 
        }
        if(e.getSource()== zaladuj_dane)
        {
            try {
                window.wczytajDane();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,"Plik file.txt jest pusty! Proszę wygenrowanować dane!","Warning",JOptionPane.WARNING_MESSAGE);
                zaladuj_dane.setVisible(false);
                daj_glos.setVisible(false);
                adopcja.setVisible(false);
                combo.setVisible(false);
                oddaj_zwierzaka.setVisible(false);
                zapisz_dane.setVisible(false);
                label.setVisible(false);
                throw new RuntimeException(ex);

            }
            window.refresh();
            
        }

    }
    AkcjaPrzyciski(okno_wyboru window){
        this.window = window;
    }
    }

    void aktualizujTabele()
    {
        table = new JTable(schronisko.getVector(), cechy_zwierzaka);
        tabelka = new JScrollPane(table);
        listPanel.add(tabelka,  BorderLayout.CENTER);
        frame.setVisible(false);
        frame.setVisible(true);
        adopcja.setEnabled(false);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                int i  = table.getSelectedRow();
                if (i > -1) {
                    Integer s = Integer.parseInt(table.getValueAt(i, 0).toString());
                    selectedAnimal = s;
                    adopcja.setEnabled(true);
                }
            }
        });
    }

    void wczytajDane() throws Exception {
        schronisko.loadData();
        schronisko.sortuj(combo.getSelectedItem().toString());
    }
    void refresh()
    {
        usun_tabele();
        aktualizujTabele();
    }

    void usun_tabele()
    {
        tabelka.remove(table);
        listPanel.remove(tabelka);
    }

}


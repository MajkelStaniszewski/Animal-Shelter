
package schronisko.GUI;

import schronisko.Zwierzaki.Zwierze;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class okno_adopcji {
    okno_wyboru adopcja_window;
    Integer zwierzak_do_Adopcji;
    JFrame frame;

    class dzialanie_przyciskow_adopcja implements ActionListener{
        JButton button;
        okno_adopcji window;
        @Override
        public void actionPerformed(ActionEvent e) { 
            if("Adoptuj zwierzaka".equals(button.getText()))
            {
                Zwierze.getUsedID().remove(zwierzak_do_Adopcji);
                window.adopcja_window.schronisko.getMap().remove(zwierzak_do_Adopcji);
                window.adopcja_window.refresh();
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        }

        dzialanie_przyciskow_adopcja(JButton button, okno_adopcji window){
            this.button = button;
            this.window = window;
        }
    }
    
    okno_adopcji(okno_wyboru window){
        adopcja_window = window;
        zwierzak_do_Adopcji = adopcja_window.selectedAnimal;

        frame = new JFrame();            
        frame.setTitle("Okno adopcji");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000,400);

        JPanel main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(2,1,0,10));

        JPanel dane = new JPanel();
        dane.setLayout(new GridLayout(4,1));

        JPanel zwierze = new JPanel();
        zwierze.setLayout(new GridLayout(3,1));
        
        JLabel zwierzeLabel = new JLabel("Dane zwierzaka do adopcji:");
        zwierzeLabel.setHorizontalAlignment(JLabel.CENTER);
        
        JButton adoptuj = new JButton();
        adoptuj.setText("Adoptuj zwierzaka");
        adoptuj.addActionListener(new dzialanie_przyciskow_adopcja(adoptuj, this));
        

        Vector wybrany_zwierzak = new Vector();
        wybrany_zwierzak.add(window.map.get(zwierzak_do_Adopcji).vector());
        JTable table = new JTable(wybrany_zwierzak, window.cechy_zwierzaka);
        JScrollPane Tabelka = new JScrollPane(table);
        
        zwierze.add(zwierzeLabel);
        zwierze.add(Tabelka);
        zwierze.add(adoptuj);

        main_panel.add(zwierze);
        frame.add(main_panel, BorderLayout.CENTER);
        frame.setVisible(true);

    }

}

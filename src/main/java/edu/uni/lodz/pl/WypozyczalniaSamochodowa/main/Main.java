package edu.uni.lodz.pl.WypozyczalniaSamochodowa.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {
    @Setter
    private Pracownik zalogowanyPracownik;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JLabel jlabel;

    public Main(Pracownik zalogowanyPracownik) {
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 800));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        this.zalogowanyPracownik = zalogowanyPracownik;
        this.setVisible(true);
        jlabel.setText(zalogowanyPracownik.getNazwisko());
    }
}
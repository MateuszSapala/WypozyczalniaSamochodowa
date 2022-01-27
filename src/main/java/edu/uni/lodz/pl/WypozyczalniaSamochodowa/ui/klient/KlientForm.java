package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.klient;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;

import javax.swing.*;
import java.awt.*;

public class KlientForm extends JFrame {
    private final Repos repos;
    private Klient zalogowanyKlient;
    private JPanel panel;
    private JTabbedPane tabbedPane1;

    public KlientForm(Repos repos, Klient zalogowanyKlient) {
        this.repos = repos;
        this.zalogowanyKlient = zalogowanyKlient;
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 800));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

package edu.uni.lodz.pl.WypozyczalniaSamochodowa.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;

@Component
public class Main extends JFrame {
    @Setter
    private Pracownik zalogowanyPracownik;
    private JPanel panel;
    private JButton button1;

    public Main() {
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 250));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);
    }
}
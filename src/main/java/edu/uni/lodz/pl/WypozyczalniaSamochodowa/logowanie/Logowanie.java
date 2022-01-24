package edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.main.Main;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {
    private final Main main;
    private final LogowanieService logowanieService;
    private JPanel panel;
    private JButton buttonZaloguj;
    private JButton buttonAnuluj;
    private JTextField textFieldLogin;
    private JTextField textFieldHaslo;

    public Logowanie(Main main, LogowanieService logowanieService) {
        this.logowanieService = logowanieService;
        main.setVisible(false);
        this.main = main;

        setTitle("Logowanie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 250));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);

        buttonZaloguj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zaloguj();
            }
        });
        buttonAnuluj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                anuluj();
            }
        });
    }

    private void zaloguj() {
        Optional<Pracownik> pracownik = logowanieService.zaloguj(textFieldLogin.getText(), textFieldHaslo.getText());
        if (pracownik.isEmpty()) {
            return;
        }
        main.setZalogowanyPracownik(pracownik.get());
        main.setVisible(true);
        dispose();
    }

    private void anuluj() {
        dispose();
    }
}

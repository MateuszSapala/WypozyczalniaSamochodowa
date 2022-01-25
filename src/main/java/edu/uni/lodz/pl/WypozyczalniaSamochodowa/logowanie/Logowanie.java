package edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.main.Main;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {
    private final Main main;
    private final PracownikRepository pracownikRepository;
    private JPanel panel;
    private JButton buttonZaloguj;
    private JButton buttonAnuluj;
    private JTextField textFieldLogin;
    private JTextField textFieldHaslo;

    public Logowanie(Main main, PracownikRepository pracownikRepository) {
        this.pracownikRepository = pracownikRepository;
        main.setVisible(false);
        this.main = main;

        setTitle("Logowanie");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 250));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);

        buttonZaloguj.addActionListener(e -> zaloguj());
        buttonAnuluj.addActionListener(e -> anuluj());
    }

    private void zaloguj() {
        Optional<Pracownik> pracownikOptional = pracownikRepository.findByLoginAndHaslo(textFieldLogin.getText(), textFieldHaslo.getText());
        if (pracownikOptional.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Zła nazwa użytkownika lub hasło");
            return;
        }
        main.setZalogowanyPracownik(pracownikOptional.get());
        main.setVisible(true);
        dispose();
    }

    private void anuluj() {
        dispose();
    }
}

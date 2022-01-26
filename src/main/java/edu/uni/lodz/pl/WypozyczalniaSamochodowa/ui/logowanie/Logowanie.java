package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.logowanie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main.Main;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {
    private final Repos repos;
    private JPanel panel;
    private JButton buttonZaloguj;
    private JButton buttonAnuluj;
    private JTextField textFieldLogin;
    private JPasswordField passwordField;


    public Logowanie(Repos repos) {
        this.repos = repos;

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
        Optional<Pracownik> pracownikOptional = repos.getPracownikRepository().findByLoginAndHaslo(textFieldLogin.getText(), String.copyValueOf(passwordField.getPassword()));
        if (pracownikOptional.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Zła nazwa użytkownika lub hasło");
            return;
        }
        new Main(repos, pracownikOptional.get());
        dispose();
    }

    private void anuluj() {
        System.exit(0);
    }
}

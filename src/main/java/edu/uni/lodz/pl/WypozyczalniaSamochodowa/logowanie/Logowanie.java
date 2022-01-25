package edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.main.Main;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Optional;

@Component
public class Logowanie extends JFrame {
    private final PracownikRepository pracownikRepository;
    private JPanel panel;
    private JButton buttonZaloguj;
    private JButton buttonAnuluj;
    private JTextField textFieldLogin;
    private JPasswordField passwordField;


    public Logowanie(PracownikRepository pracownikRepository) {
        this.pracownikRepository = pracownikRepository;

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
        Optional<Pracownik> pracownikOptional = pracownikRepository.findByLoginAndHaslo(textFieldLogin.getText(), String.copyValueOf(passwordField.getPassword()));
        if (pracownikOptional.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Zła nazwa użytkownika lub hasło");
            return;
        }
        new Main(pracownikOptional.get());
        dispose();
    }

    private void anuluj() {
        System.exit(0);
    }
}

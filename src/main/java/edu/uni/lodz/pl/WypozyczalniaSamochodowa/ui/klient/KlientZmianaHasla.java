package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.klient;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

import static javax.swing.JOptionPane.showMessageDialog;

public class KlientZmianaHasla extends JFrame {
    private final Repos repos;
    private final Klient zalogowanyKlient;
    private final KlientForm klientForm;
    private JPanel panel;
    private JButton buttonZapisz;
    private JButton buttonAnuluj;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public KlientZmianaHasla(Repos repos, Klient zalogowanyKlient, KlientForm klientForm) {
        this.repos = repos;
        this.zalogowanyKlient = zalogowanyKlient;
        this.klientForm = klientForm;

        setTitle("Zmiana hasla");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(350, 200));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);
        buttonZapisz.addActionListener(e -> zmienHaslo());
        buttonAnuluj.addActionListener(e -> anuluj());
    }
    private void zmienHaslo(){
        if (passwordField1.getText().isEmpty() || passwordField2.getText().isEmpty()) {
            showMessageDialog(null, "Oba pola muszą być wypełnione!");
        }
        else if(!(passwordField1.getText().equals(passwordField2.getText()))){
            showMessageDialog(null, "Hasło i powtórzone hasło muszą być takie same!");
        }
        else if(hasloValidator(passwordField1.getText())==false){
            showMessageDialog(null,"Hasło musi zawierać od 8 do 20 znaków, minimum jedną małą literę, dużą literę, cyfrę i symbol!" );
        }
        else {
            showMessageDialog(null, "Hasło zmienione!");
            zalogowanyKlient.setHaslo(passwordField1.getText());
            repos.getKlientRepository().save(zalogowanyKlient);
            klientForm.zaladujDanePonownie();
            dispose();
        }
    }
    private void anuluj() { dispose(); }

    private boolean hasloValidator(String haslo){
        if(!(haslo.matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"))) return false;
        else return true;
    }
}

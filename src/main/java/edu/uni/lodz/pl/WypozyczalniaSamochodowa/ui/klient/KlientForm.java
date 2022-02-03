package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.klient;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.logowanie.Logowanie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

import static javax.swing.JOptionPane.showMessageDialog;

public class KlientForm extends JFrame {
    private final Repos repos;
    private Klient zalogowanyKlient;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldPesel;
    private JTextField textFieldLogin;
    private JPasswordField passwordField1;
    private JButton buttonZmienLogin;
    private JButton buttonZmienHaslo;

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
        uzupelnijDaneKontaKlienta(zalogowanyKlient);

        buttonZmienLogin.addActionListener(e -> zmianaLoginu());
        buttonZmienHaslo.addActionListener(e-> zmianaHasla());
    }
    private void uzupelnijDaneKontaKlienta(Klient klient) {
        textFieldImie.setText(klient.getImie());
        textFieldNazwisko.setText(klient.getNazwisko());
        textFieldPesel.setText(klient.getPesel());
        textFieldLogin.setText(klient.getLogin());
        passwordField1.setText(klient.getHaslo());
    }
    private void zmianaLoginu() {
            KlientZmianaLoginu klientZmianaLoginu= new KlientZmianaLoginu(repos, zalogowanyKlient, this);
            klientZmianaLoginu.setVisible(true);
    }
    private void zmianaHasla() {
        KlientZmianaHasla klientZmianaHasla= new KlientZmianaHasla(repos, zalogowanyKlient, this);
        klientZmianaHasla.setVisible(true);
    }
    void zaladujDanePonownie(){
        textFieldLogin.setText(zalogowanyKlient.getLogin());
        passwordField1.setText(zalogowanyKlient.getHaslo());
    }

}

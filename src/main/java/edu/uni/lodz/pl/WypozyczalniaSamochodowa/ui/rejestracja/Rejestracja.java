package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.rejestracja;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.KlientRepository;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.klient.KlientForm;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.PreparedStatement;
import java.util.Optional;

import static javax.swing.JOptionPane.showMessageDialog;

@Component
public class Rejestracja extends JFrame {
    private final Repos repos;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldPesel;
    private JTextField textFieldLogin;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    private JCheckBox checkBoxKobieta;
    private JCheckBox checkBoxMezczyzna;
    private JButton buttonUtworzKonto;
    private JButton buttonAnuluj;
    private JPanel panel;
    private JRadioButton radioButtonKobieta;
    private JRadioButton radioButtonMezczyzna;
    private PreparedStatement stat;

    public Rejestracja(Repos repos) {
        this.repos = repos;


        setTitle("Rejestracja");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 400));
        setResizable(false);
        add(panel);
        pack();
        setLocationRelativeTo(null);

        buttonUtworzKonto.addActionListener(e -> utworzKonto());
        buttonAnuluj.addActionListener(e -> anuluj());

    }

    private void anuluj() {
        dispose();
    }

    private void utworzKonto() {
        Plec plec;
        if(radioButtonKobieta.isSelected()) { plec = Plec.KOBIETA; }
        else { plec=Plec.MEZCZYZNA; }

        Optional<Klient> klientOptional = repos.getKlientRepository().findByLogin(textFieldLogin.getText());
        Optional<Klient> klientOptional1 = repos.getKlientRepository().findByPesel(textFieldPesel.getText());
        if (klientOptional.isPresent()) {
            showMessageDialog(null, "Podany login jest już zajęty!");
        }
        else if(klientOptional1.isPresent()) {
            showMessageDialog(null, "Podany pesel jest już użyty przez innego użytkownika!");
        }
        else if (textFieldImie.getText().isEmpty() || textFieldNazwisko.getText().isEmpty() || textFieldPesel.getText().isEmpty() || textFieldLogin.getText().isEmpty() || passwordField1.getText().isEmpty() || passwordField2.getText().isEmpty()) {
            showMessageDialog(null, "Wypełnij wszystkie pola!");
        }
        else if (!(passwordField1.getText().equals(passwordField2.getText()))) {
            showMessageDialog(null, "Podane hasło i powtórzone hasło nie są takie same!");
        }
        else if(peselValidator(textFieldPesel.getText())==false){
            showMessageDialog(null, "Błędny pesel!");
        }
        else if(hasloValidator(passwordField1.getText())==false){
            showMessageDialog(null,"Hasło musi zawierać od 8 do 20 znaków, minimum jedną małą literę, dużą literę, cyfrę i symbol!" );
        }
        else {
            Klient k1 = new Klient(textFieldImie.getText(), textFieldNazwisko.getText(), textFieldPesel.getText(), textFieldLogin.getText(), passwordField1.getText(), plec);
            showMessageDialog(null, "Konto utworzone!");
            repos.getKlientRepository().save(k1);
            dispose();
        }
    }

    private boolean peselValidator(String pesel) {
        if (pesel.length() != 11) {
            return false;
        } else if (!(pesel.matches("[+-]?\\d*(\\.\\d+)?"))) {
            return false;
        } else {
            return true;
        }
    }
    private boolean hasloValidator(String haslo){
        if(!(haslo.matches( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$"))) return false;
        else return true;
    }
}





package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Nadwozie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Paliwo;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Skrzynia;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Optional;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;


public class Main extends JFrame {
    private final Repos repos;
    private final AutoService autoService;
    private final PracownikService pracownikService;
    private final KlientService klientService;
    private final Pracownik zalogowanyPracownik;
    private final WypozyczenieService wypozyczenieService;
    private Integer idWybranegoAuta;
    private Integer idWybranegoPracownika;


    //<editor-fold desc="UI">
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTable tablePracownicy;
    private JScrollPane scrollPane;
    private JButton buttonPracownicyUsun;
    private JButton buttonPracownicyDodaj;
    private JButton buttonPracownicyEdytuj;
    private JTable tableAuta;
    private JButton buttonAutaDodaj;
    private JButton buttonAutaEdytuj;
    private JButton buttonAutaUsun;
    private JSpinner spinnerAutoCenaZaGodzine;
    private JTextField textFieldAutoMarka;
    private JTextField textFieldAutoModel;
    private JSpinner spinnerAutoRokProdukcji;
    private JComboBox<Nadwozie> comboBoxAutoNadwozie;
    private JComboBox<Paliwo> comboBoxAutoPaliwo;
    private JComboBox<Skrzynia> comboBoxAutoSkrzynia;
    private JTextField textFieldPracownikImie;
    private JTextField textFieldPracownikNazwisko;
    private JTextField textFieldPracownikPesel;
    private JTextField textFieldPracownikGodziny;
    private JTextField textFieldPracownikLogin;
    private JTextField textFieldPracownikHaslo;
    private JComboBox<Plec> comboBoxPracownikPlec;
    private JTable tableKlienci;
    private JTextField textFieldSzukajKlienta;
    private JButton buttonSzukajKlienta;
    private JTable tableRezerwacje;
    private DefaultTableColumnModel model;
    private JLabel jlabel;
    //</editor-fold>

    public Main(Repos repos, Pracownik zalogowanyPracownik) {
        this.repos = repos;
        this.zalogowanyPracownik = zalogowanyPracownik;
        this.pracownikService = new PracownikService(repos);
        this.autoService = new AutoService(repos);
        this.klientService = new KlientService(repos);
        this.wypozyczenieService = new WypozyczenieService(repos);

        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 600));
        setResizable(false);
        add(panel);
        zaladujDane();
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);

        //<editor-fold desc="ComboBoxes">
        comboBoxPracownikPlec.setModel(new DefaultComboBoxModel<>(Plec.values()));
        comboBoxAutoNadwozie.setModel(new DefaultComboBoxModel<Nadwozie>(Nadwozie.values()));
        comboBoxAutoPaliwo.setModel(new DefaultComboBoxModel<Paliwo>(Paliwo.values()));
        comboBoxAutoSkrzynia.setModel(new DefaultComboBoxModel<Skrzynia>(Skrzynia.values()));
        //</editor-fold>

        //<editor-fold desc="Buttons">
        buttonPracownicyDodaj.addActionListener(e -> dodajPracownika());
        buttonPracownicyEdytuj.addActionListener(e -> edytujPracownika());
        buttonPracownicyUsun.addActionListener(e -> usunPracownika());
        tablePracownicy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zaladujDaneWybranegoPracownika();
            }
        });

        buttonAutaDodaj.addActionListener(e -> dodajAuto());
        buttonAutaEdytuj.addActionListener(e -> edytujAuto());
        buttonAutaUsun.addActionListener(e -> usunAuto());
        tableAuta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zaladujDaneWybranegoAuta();
            }
        });
        buttonSzukajKlienta.addActionListener(e -> zaladujDaneSzukanegoKlienta());
        //</editor-fold>

        //<editor-fold desc="Spinners">
        spinnerAutoCenaZaGodzine.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        spinnerAutoRokProdukcji.setModel(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR)-5, 1900, Calendar.getInstance().get(Calendar.YEAR), 1));

        JSpinner.DefaultEditor spinnerEditorAutoCenaZaGodzine = (JSpinner.DefaultEditor)(spinnerAutoCenaZaGodzine.getEditor());
        spinnerEditorAutoCenaZaGodzine.getTextField().setHorizontalAlignment(JTextField.LEFT);
        JSpinner.DefaultEditor spinnerEditorAutoRokProdukcji = (JSpinner.DefaultEditor)(spinnerAutoRokProdukcji.getEditor());
        spinnerEditorAutoRokProdukcji.getTextField().setHorizontalAlignment(JTextField.LEFT);
        //</editor-fold>
    }

    private void zaladujDane() {
        zaladujDanePracownikow();
        zaladujDaneDlaAut();
        zaladujDaneKlientow();
        zaladujRezerwacje();
    }

    //<editor-fold desc="Pracownicy">
    public void zaladujDanePracownikow() {
        String[] columnNames = {"Imie", "Nazwisko"};
        Object[][] data = repos.getPracownikRepository()
                .findAll()
                .stream()
                .map(p -> new Object[]{p.getImie(), p.getNazwisko()})
                .toArray(Object[][]::new);
        tablePracownicy.setModel(pracownikService.tabelaPracownicy());
    }

    private void zaladujDaneWybranegoPracownika() {
        Pracownik p = pracownikService.pobierzWybranegoPracownikaZTabeli(tablePracownicy, panel);
        if (p == null) {
            return;
        }
        idWybranegoPracownika = p.getId();
        uzupelnijInputDlaPracownika(p);
    }

    private void uzupelnijInputDlaPracownika() {
        textFieldPracownikImie.setText("");
        textFieldPracownikNazwisko.setText("");
        textFieldPracownikPesel.setText("");
        textFieldPracownikGodziny.setText("");
        comboBoxPracownikPlec.setSelectedIndex(0);
        textFieldPracownikLogin.setText("");
        textFieldPracownikHaslo.setText("");
        idWybranegoAuta = null;
    }

    private void uzupelnijInputDlaPracownika(Pracownik p) {
        textFieldPracownikImie.setText(String.valueOf(p.getImie()));
        textFieldPracownikNazwisko.setText(String.valueOf(p.getNazwisko()));
        textFieldPracownikPesel.setText(String.valueOf(p.getPesel()));
        textFieldPracownikGodziny.setText(String.valueOf(p.getGodzinyPracy()));
        comboBoxPracownikPlec.setSelectedItem(p.getPlec());
        textFieldPracownikLogin.setText(String.valueOf(p.getLogin()));
        textFieldPracownikHaslo.setText(String.valueOf(p.getHaslo()));
    }

    private void dodajPracownika() {
        Pracownik pracownik = edytujDanePracownikaNaPodstawieInputu(new Pracownik());
        if (pracownik == null) {
            return;
        }
        try {
            repos.getPracownikRepository().save(pracownik);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage());
        }
        zaladujDanePracownikow();
        uzupelnijInputDlaPracownika();
    }

    private boolean sprawdzInputPracownika() {
        try {
            String imie = textFieldPracownikImie.getText();
            if (imie.length() < 2) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Wprowadzone imie ma zbyt malo znakow!");
            return false;
        }

        try {
            String nazwisko = textFieldPracownikNazwisko.getText();
            if (nazwisko.length() < 2) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Wprowadzone nazwisko ma zbyt malo znakow!");
            return false;
        }

        try {
            String pesel = textFieldPracownikPesel.getText();
            Float floatPesel = Float.parseFloat(pesel);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(panel, "Pesel musi skladac sie z samych cyfr!");
            return false;
        }

        try {
            String pesel = textFieldPracownikPesel.getText();
            if (pesel.length() != 11){
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Pesel musi byc ciagiem 11 cyfr!");
            return false;
        }
        try {
            String haslo = textFieldPracownikHaslo.getText();
            boolean isUpperCase = false;
            boolean isLowerCase = false;
            boolean isLength = false;
            boolean isSpecialChar = false;
            String specialCharactersString = "!@#$%&*()'+,-./:;<=>?[]^_`{|}";
            for (int i = 0; i < haslo.length(); i++){
                if (Character.isUpperCase(haslo.charAt(i))){
                    isUpperCase = true;
                }
                if (Character.isLowerCase(haslo.charAt(i))){
                    isLowerCase = true;
                }
                if (specialCharactersString.contains(Character.toString(haslo.charAt(i)))){
                    isSpecialChar = true;
                }
            }
            if (haslo.length() >= 8){
                isLength = true;
            }
            if (!isUpperCase || !isLowerCase || !isLength || !isSpecialChar) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Haslo powinno skladac sie z 8 znakow, w tym po jednej malej i" +
                    " duzej literze oraz znaku specjalnego!");
            return false;
        }
        return true;
    }

    private Pracownik edytujDanePracownikaNaPodstawieInputu(Pracownik pracownik) {
        if (!sprawdzInputPracownika()){
            return null;
        }
        pracownik.setImie(textFieldPracownikImie.getText());
        pracownik.setNazwisko(textFieldPracownikNazwisko.getText());
        pracownik.setPesel(textFieldPracownikPesel.getText());
//      pracownik.setGodzinyPracy(textFieldPracownikGodziny.getText());
        pracownik.setPlec((Plec) comboBoxPracownikPlec.getSelectedItem());
        pracownik.setLogin(textFieldPracownikLogin.getText());
        pracownik.setHaslo(textFieldPracownikHaslo.getText());
        return pracownik;
    }

    private void edytujPracownika() {
        Optional<Pracownik> p = repos.getPracownikRepository().findById(idWybranegoPracownika);
        if (p.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Pracownika nie ma w bazie");
            return;
        }
        Pracownik pracownik = p.get();
        pracownik = edytujDanePracownikaNaPodstawieInputu(pracownik);
        if (pracownik == null) {
            return;
        }
        try {
            repos.getPracownikRepository().save(pracownik);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage());
        }
        zaladujDanePracownikow();
        uzupelnijInputDlaPracownika();
    }

    private void usunPracownika() {
        repos.getPracownikRepository().deleteById(idWybranegoPracownika);
        zaladujDanePracownikow();
        uzupelnijInputDlaPracownika();
    }


    //</editor-fold>

    //<editor-fold desc="Auta">
    private void zaladujDaneDlaAut() {
        tableAuta.setModel(autoService.tabelaAuta());
    }

    private void zaladujDaneWybranegoAuta() {
        Auto auto = autoService.pobierzWybraneAutoZTabeli(tableAuta, panel);
        if (auto == null) {
            return;
        }
        idWybranegoAuta = auto.getId();
        uzupelnijInputDlaAut(auto);
    }

    private void uzupelnijInputDlaAut() {
        spinnerAutoCenaZaGodzine.setValue(1);
        textFieldAutoMarka.setText("");
        textFieldAutoModel.setText("");
        spinnerAutoRokProdukcji.setValue(Calendar.getInstance().get(Calendar.YEAR)-5);
        comboBoxAutoNadwozie.setSelectedIndex(0);
        comboBoxAutoPaliwo.setSelectedIndex(0);
        comboBoxAutoSkrzynia.setSelectedIndex(0);
        idWybranegoAuta = null;
    }

    private void uzupelnijInputDlaAut(Auto auto) {
        spinnerAutoCenaZaGodzine.setValue(auto.getCenaZaGodzine());
        textFieldAutoMarka.setText(auto.getMarka());
        textFieldAutoModel.setText(auto.getModel());
        spinnerAutoRokProdukcji.setValue(auto.getRokProdukcji());
        comboBoxAutoNadwozie.setSelectedItem(auto.getNadwozie());
        comboBoxAutoPaliwo.setSelectedItem(auto.getPaliwo());
        comboBoxAutoSkrzynia.setSelectedItem(auto.getSkrzynia());
    }

    private boolean sprawdzInputUzytkownika() {
        if (textFieldAutoMarka.getText().length() < 3) {
            JOptionPane.showMessageDialog(panel, "Marka ma zbyt mało znaków");
            return false;
        }
        if (textFieldAutoModel.getText().length() < 2) {
            JOptionPane.showMessageDialog(panel, "Model ma zbyt mało znaków");
            return false;
        }
        return true;
    }

    private Auto edytujDaneAutaNaPodstawieInputu(Auto auto) {
        if (!sprawdzInputUzytkownika()) {
            return null;
        }
        auto.setCenaZaGodzine((int) spinnerAutoCenaZaGodzine.getValue());
        auto.setMarka(textFieldAutoMarka.getText());
        auto.setModel(textFieldAutoModel.getText());
        auto.setRokProdukcji((int) spinnerAutoRokProdukcji.getValue());
        auto.setNadwozie((Nadwozie) comboBoxAutoNadwozie.getSelectedItem());
        auto.setPaliwo((Paliwo) comboBoxAutoPaliwo.getSelectedItem());
        auto.setSkrzynia((Skrzynia) comboBoxAutoSkrzynia.getSelectedItem());
        return auto;
    }

    private void dodajAuto() {
        Auto auto = edytujDaneAutaNaPodstawieInputu(new Auto());
        if (auto == null) {
            return;
        }
        repos.getAutoRepository().save(auto);
        zaladujDaneDlaAut();
        uzupelnijInputDlaAut();
    }

    private void edytujAuto() {
        Optional<Auto> a = repos.getAutoRepository().findById(idWybranegoAuta);
        if (a.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Auta nie ma w bazie");
            return;
        }
        Auto auto = a.get();
        auto = edytujDaneAutaNaPodstawieInputu(auto);
        if (auto == null) {
            return;
        }
        repos.getAutoRepository().save(auto);
        zaladujDaneDlaAut();
        uzupelnijInputDlaAut();
    }

    private void usunAuto() {
        repos.getAutoRepository().deleteById(idWybranegoAuta);
        zaladujDaneDlaAut();
        uzupelnijInputDlaAut();
    }
    //</editor-fold>

    private void zaladujDaneKlientow(){ tableKlienci.setModel(klientService.tabelaKlienci()); }

    private void zaladujDaneSzukanegoKlienta(){
        String szukane = textFieldSzukajKlienta.getText();
        if(szukane.isEmpty()){
            zaladujDaneKlientow();
        }
        else{
            List<Klient> klientOptional = repos.getKlientRepository().findByImieLikeOrNazwiskoLikeOrLoginLike(szukane);
            if (!(klientOptional.isEmpty())) {
                tableKlienci.setModel(klientService.tabelaSzukanychKlientów(szukane));
            }
            else {
                showMessageDialog(null, "Brak takiego klienta!");
            }
        }
    }
    private void zaladujRezerwacje(){ tableRezerwacje.setModel(wypozyczenieService.allWypozyczeniaTabela());}

}
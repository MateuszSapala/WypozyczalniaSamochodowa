package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Nadwozie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Paliwo;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Skrzynia;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy.GodzinyPracy;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static javax.swing.JOptionPane.showMessageDialog;


public class Main extends JFrame {
    private final Repos repos;
    private final AutoService autoService;
    private final PracownikService pracownikService;
    private final KlientService klientService;
    private final Pracownik zalogowanyPracownik;
    private final WypozyczenieService wypozyczenieService;
    private final GodzinyService godzinyService;

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
    private JTextField textFieldPracownikLogin;
    private JTextField textFieldPracownikHaslo;
    private JComboBox<Plec> comboBoxPracownikPlec;
    private JTable tableKlienci;
    private JTextField textFieldSzukajKlienta;
    private JButton buttonSzukajKlienta;
    private JTextField textFieldPonidzialekDoGodzinyPracy;
    private JTextField textFieldPonidzialekOdGodzinyPracy;
    private JTextField textFieldSrodaOdGodzinyPracy;
    private JTextField textFieldWtorekOdGodzinyPracy;
    private JTextField textFieldCzwartekOdGodzinyPracy;
    private JTextField textFieldPiatekOdGodzinyPracy;
    private JTextField textFieldSobotaOdGodzinyPracy;
    private JTextField textFieldWtorekDoGodzinyPracy;
    private JTextField textFieldSrodaDoGodzinyPracy;
    private JTextField textFieldCzwartekDoGodzinyPracy;
    private JTextField textFieldPiatekDoGodzinyPracy;
    private JTextField textFieldSobotaDoGodzinyPracy;
    private JButton button10_18;
    private JButton button9_17;
    private JButton button8_16;
    private JButton buttonDodajGodzinyPracy;
    private JButton buttonEdytujGodzinyPracy;
    private JButton buttonUsuńGodzinyPracy;
    private JButton button7_15;
    private JTextField textFieldSzukajGodziny;
    private JComboBox comboBoxGodzinyPracyPracownicy;
    private JTable tableRezerwacje;
    private DefaultTableColumnModel model;
    private JLabel jlabel;
    private java.sql.Time Time;
    //</editor-fold>

    public Main(Repos repos, Pracownik zalogowanyPracownik) {
        this.repos = repos;
        this.zalogowanyPracownik = zalogowanyPracownik;
        this.pracownikService = new PracownikService(repos);
        this.autoService = new AutoService(repos);
        this.klientService = new KlientService(repos);
        this.wypozyczenieService = new WypozyczenieService(repos);

        this.godzinyService = new GodzinyService(repos);
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
        spinnerAutoRokProdukcji.setModel(new SpinnerNumberModel(Calendar.getInstance().get(Calendar.YEAR) - 5, 1900, Calendar.getInstance().get(Calendar.YEAR), 1));

        JSpinner.DefaultEditor spinnerEditorAutoCenaZaGodzine = (JSpinner.DefaultEditor) (spinnerAutoCenaZaGodzine.getEditor());
        spinnerEditorAutoCenaZaGodzine.getTextField().setHorizontalAlignment(JTextField.LEFT);
        JSpinner.DefaultEditor spinnerEditorAutoRokProdukcji = (JSpinner.DefaultEditor) (spinnerAutoRokProdukcji.getEditor());
        spinnerEditorAutoRokProdukcji.getTextField().setHorizontalAlignment(JTextField.LEFT);
        //</editor-fold>


        buttonDodajGodzinyPracy.addActionListener(e -> dodajGodzinyPracy());
        buttonEdytujGodzinyPracy.addActionListener(e -> edytujGodzinyPracy());
        buttonUsuńGodzinyPracy.addActionListener(e -> usunGodzinyPracy());
        button7_15.addActionListener(e -> ustawGodzinyPracy7_15());
        button8_16.addActionListener(e -> ustawGodzinyPracy8_16());
        button9_17.addActionListener(e -> ustawGodzinyPracy9_17());
        button10_18.addActionListener(e -> ustawGodzinyPracy10_18());
        comboBoxGodzinyPracyPracownicy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zaladujDaneGodzinPracy();
            }
        });
    }

    private void zaladujDaneGodzinPracy(JTable tableGodziny, JPanel panel) {
    }


    private void zaladujDane() {
        zaladujDanePracownikow();
        zaladujDaneDlaAut();
        zaladujDaneKlientow();
        zaladujRezerwacje();
        zaladujDaneGodzinPracy();
    }

    //<editor-fold desc="Pracownicy">
    public void zaladujDanePracownikow() {
        String[] columnNames = {"Imie", "Nazwisko"};
        Object[][] data = repos.getPracownikRepository()
                .findAll()
                .stream()
                .map(p -> new Object[]{p.getImie(), p.getNazwisko()})
                .toArray(Object[][]::new);
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
        tablePracownicy.setModel(pracownikService.tabelaPracownicy());
        comboBoxGodzinyPracyPracownicy.setModel(pracownikService.comboBoxPracownicy());
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
        comboBoxPracownikPlec.setSelectedIndex(0);
        textFieldPracownikLogin.setText("");
        textFieldPracownikHaslo.setText("");
        idWybranegoAuta = null;
    }

    private void uzupelnijInputDlaPracownika(Pracownik p) {
        textFieldPracownikImie.setText(String.valueOf(p.getImie()));
        textFieldPracownikNazwisko.setText(String.valueOf(p.getNazwisko()));
        textFieldPracownikPesel.setText(String.valueOf(p.getPesel()));
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
            if (pesel.length() != 11) {
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
            for (int i = 0; i < haslo.length(); i++) {
                if (Character.isUpperCase(haslo.charAt(i))) {
                    isUpperCase = true;
                }
                if (Character.isLowerCase(haslo.charAt(i))) {
                    isLowerCase = true;
                }
                if (specialCharactersString.contains(Character.toString(haslo.charAt(i)))) {
                    isSpecialChar = true;
                }
            }
            if (haslo.length() >= 8) {
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
        if (!sprawdzInputPracownika()) {
            return null;
        }
        pracownik.setImie(textFieldPracownikImie.getText());
        pracownik.setNazwisko(textFieldPracownikNazwisko.getText());
        pracownik.setPesel(textFieldPracownikPesel.getText());
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
        spinnerAutoRokProdukcji.setValue(Calendar.getInstance().get(Calendar.YEAR) - 5);
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

    private void zeorwanieGodzinPracy() {
        textFieldPonidzialekOdGodzinyPracy.setText("");
        textFieldPonidzialekDoGodzinyPracy.setText("");
        textFieldWtorekOdGodzinyPracy.setText("");
        textFieldWtorekDoGodzinyPracy.setText("");
        textFieldSrodaOdGodzinyPracy.setText("");
        textFieldSrodaDoGodzinyPracy.setText("");
        textFieldCzwartekOdGodzinyPracy.setText("");
        textFieldCzwartekDoGodzinyPracy.setText("");
        textFieldPiatekOdGodzinyPracy.setText("");
        textFieldPiatekDoGodzinyPracy.setText("");
        textFieldSobotaOdGodzinyPracy.setText("");
        textFieldSobotaDoGodzinyPracy.setText("");
    }

    private void zaladujDaneGodzinPracy(){
        Optional<GodzinyPracy> godzinyPracyOptional = repos.getGodzinyPracyRepository().findByPracownik((Pracownik) comboBoxGodzinyPracyPracownicy.getSelectedItem());
        zeorwanieGodzinPracy();
        godzinyPracyOptional.ifPresent(this::uzupelnijInputDlaGodzin);
    }

    private void uzupelnijInputDlaGodzin(GodzinyPracy godzinyPracy) {
        textFieldPonidzialekOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getPoniedzialekOd()));
        textFieldPonidzialekDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getPoniedzialekDo()));
        textFieldWtorekOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getWtorekOd()));
        textFieldWtorekDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getWtorekDo()));
        textFieldSrodaOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getSrodaOd()));
        textFieldSrodaDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getSrodaDo()));
        textFieldCzwartekOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getCzwartekOd()));
        textFieldCzwartekDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getCzwartekDo()));
        textFieldPiatekOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getPiatekOd()));
        textFieldPiatekDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getPiatekDo()));
        textFieldSobotaOdGodzinyPracy.setText(String.valueOf(godzinyPracy.getSobotaOd()));
        textFieldSobotaDoGodzinyPracy.setText(String.valueOf(godzinyPracy.getSobotaDo()));
    }

    private GodzinyPracy edytujGodzinyPracyNaPodstawieInputu(GodzinyPracy godzPracy) {
        godzPracy.setPracownik((Pracownik) comboBoxGodzinyPracyPracownicy.getSelectedItem());
        godzPracy.setPoniedzialekOd(LocalTime.of(Integer.parseInt(textFieldPonidzialekOdGodzinyPracy.getText()),0) );
        godzPracy.setPoniedzialekDo(LocalTime.of(Integer.parseInt(textFieldPonidzialekDoGodzinyPracy.getText()),0) );
        godzPracy.setWtorekOd(LocalTime.of(Integer.parseInt(textFieldWtorekOdGodzinyPracy.getText()),0) );
        godzPracy.setWtorekDo(LocalTime.of(Integer.parseInt(textFieldWtorekDoGodzinyPracy.getText()),0) );
        godzPracy.setSrodaOd(LocalTime.of(Integer.parseInt(textFieldSrodaOdGodzinyPracy.getText()),0) );
        godzPracy.setSrodaDo(LocalTime.of(Integer.parseInt(textFieldSrodaDoGodzinyPracy.getText()),0) );
        godzPracy.setCzwartekOd(LocalTime.of(Integer.parseInt(textFieldCzwartekOdGodzinyPracy.getText()),0) );
        godzPracy.setCzwartekDo(LocalTime.of(Integer.parseInt(textFieldCzwartekDoGodzinyPracy.getText()),0) );
        godzPracy.setPiatekOd(LocalTime.of(Integer.parseInt(textFieldPiatekOdGodzinyPracy.getText()),0) );
        godzPracy.setPiatekDo(LocalTime.of(Integer.parseInt(textFieldPiatekDoGodzinyPracy.getText()),0) );
        godzPracy.setSobotaOd(LocalTime.of(Integer.parseInt(textFieldSobotaDoGodzinyPracy.getText()),0) );
        godzPracy.setSobotaDo(LocalTime.of(Integer.parseInt(textFieldSobotaDoGodzinyPracy.getText()),0) );
        return godzPracy;
    }

    private void dodajGodzinyPracy() {
        GodzinyPracy godzinyPracy = edytujGodzinyPracyNaPodstawieInputu(new GodzinyPracy());
        repos.getGodzinyPracyRepository().save(godzinyPracy);
        zeorwanieGodzinPracy();
        showMessageDialog(null, "Dodano godziny pracy pracownika:" + godzinyPracy.getId());
    }

    private void edytujGodzinyPracy() {
        Optional<GodzinyPracy> g = repos.getGodzinyPracyRepository().findByPracownik((Pracownik) comboBoxGodzinyPracyPracownicy.getSelectedItem());
        if (g.isEmpty()) {
            showMessageDialog(null, "GodzinPracy nie ma w bazie");
            return;
        }
        GodzinyPracy godzinyPracy = g.get();
        edytujGodzinyPracyNaPodstawieInputu(godzinyPracy);
        repos.getGodzinyPracyRepository().save(godzinyPracy);
        showMessageDialog(null, "Zedytowano godziny pracy pracownika:"+comboBoxGodzinyPracyPracownicy.getSelectedItem());
    }

    private void usunGodzinyPracy() {
        Optional<GodzinyPracy> godzinyPracyOptional = repos.getGodzinyPracyRepository().findByPracownik((Pracownik) comboBoxGodzinyPracyPracownicy.getSelectedItem());
        if(godzinyPracyOptional.isEmpty()){
            return;
        }
        repos.getGodzinyPracyRepository().deleteById(godzinyPracyOptional.get().getId());
        zeorwanieGodzinPracy();
        showMessageDialog(null, "Usunięto godziny pracy Pracownika o id:"+ comboBoxGodzinyPracyPracownicy.getSelectedItem());
    }


    private void czasPracy(int czasStart) {
        int czasKoncowy = czasStart + 8;

        textFieldPonidzialekOdGodzinyPracy.setText(String.valueOf(czasStart));
        textFieldWtorekOdGodzinyPracy.setText(String.valueOf(czasStart));
        textFieldSrodaOdGodzinyPracy.setText(String.valueOf(czasStart));
        textFieldCzwartekOdGodzinyPracy.setText(String.valueOf(czasStart));
        textFieldPiatekOdGodzinyPracy.setText(String.valueOf(czasStart));
        textFieldSobotaOdGodzinyPracy.setText(String.valueOf(czasStart));

        textFieldPonidzialekDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
        textFieldWtorekDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
        textFieldSrodaDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
        textFieldCzwartekDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
        textFieldPiatekDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
        textFieldSobotaDoGodzinyPracy.setText(String.valueOf(czasKoncowy));
    }

    private void ustawGodzinyPracy7_15() {
        czasPracy(7);
    }

    private void ustawGodzinyPracy8_16() {
        czasPracy(8);
    }

    private void ustawGodzinyPracy9_17() {
        czasPracy(9);
    }

    private void ustawGodzinyPracy10_18() {
        czasPracy(10);
    }
}
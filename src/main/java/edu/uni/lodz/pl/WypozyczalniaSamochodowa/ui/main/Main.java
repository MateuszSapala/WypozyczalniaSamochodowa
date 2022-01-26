package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Nadwozie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Paliwo;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Skrzynia;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class Main extends JFrame {
    private final Repos repos;
    private final AutoService autoService;
    private Pracownik zalogowanyPracownik;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JScrollPane scrollPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JTable tableAuta;
    private JButton buttonAutaDodaj;
    private JButton buttonAutaEdytuj;
    private JButton buttonAutaUsun;
    private JTextField textFieldAutoCenaZaDzien;
    private JTextField textFieldAutoMarka;
    private JTextField textFieldAutoModel;
    private JTextField textFieldAutoRokProdukcji;
    private JComboBox comboBoxAutoNadwozie;
    private JComboBox comboBoxAutoPaliwo;
    private JComboBox comboBoxAutoSkrzynia;
    private DefaultTableColumnModel model;
    private JLabel jlabel;

    public Main(Repos repos, Pracownik zalogowanyPracownik) {
        this.repos = repos;
        this.zalogowanyPracownik = zalogowanyPracownik;
        this.autoService = new AutoService(repos);
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 800));
        setResizable(false);
        add(panel);
        zaladujDane();
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);

        comboBoxAutoNadwozie.setModel(new DefaultComboBoxModel<Nadwozie>(Nadwozie.values()));
        comboBoxAutoPaliwo.setModel(new DefaultComboBoxModel<Paliwo>(Paliwo.values()));
        comboBoxAutoSkrzynia.setModel(new DefaultComboBoxModel<Skrzynia>(Skrzynia.values()));

        buttonAutaDodaj.addActionListener(e -> dodajAuto());
        buttonAutaEdytuj.addActionListener(e -> edytujAuto());
        buttonAutaUsun.addActionListener(e -> usunAuto());
    }

    private void zaladujDane() {
        zaladujDanePracownikow();
        zaladujDaneDlaAut();
    }

    public void zaladujDanePracownikow() {
        String[] columnNames = {"Imie", "Nazwisko"};
        Object[][] data = repos.getPracownikRepository()
                .findAll()
                .stream()
                .map(p -> new Object[]{p.getImie(), p.getNazwisko()})
                .toArray(Object[][]::new);
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
        table1.setModel(defaultTableModel);
    }

    private void zaladujDaneDlaAut() {
        tableAuta.setModel(autoService.tabelaAuta());
    }

    private void wyczyscInputDlaAut(){
        textFieldAutoCenaZaDzien.setText("");
        textFieldAutoMarka.setText("");
        textFieldAutoModel.setText("");
        textFieldAutoRokProdukcji.setText("");
        comboBoxAutoNadwozie.setSelectedIndex(0);
        comboBoxAutoPaliwo.setSelectedIndex(0);
        comboBoxAutoSkrzynia.setSelectedIndex(0);
    }

    private void dodajAuto() {
        Auto auto = new Auto();
        auto.setCenaZaDzien(Integer.parseInt(textFieldAutoCenaZaDzien.getText()));
        auto.setMarka(textFieldAutoMarka.getText());
        auto.setModel(textFieldAutoModel.getText());
        auto.setRokProdukcji(Integer.parseInt(textFieldAutoRokProdukcji.getText()));
        auto.setNadwozie((Nadwozie) comboBoxAutoNadwozie.getSelectedItem());
        auto.setPaliwo((Paliwo) comboBoxAutoPaliwo.getSelectedItem());
        auto.setSkrzynia((Skrzynia) comboBoxAutoSkrzynia.getSelectedItem());
        repos.getAutoRepository().save(auto);
        zaladujDaneDlaAut();
        wyczyscInputDlaAut();
    }

    private void edytujAuto() {
        zaladujDaneDlaAut();
    }

    private void usunAuto() {
        zaladujDaneDlaAut();
    }
}
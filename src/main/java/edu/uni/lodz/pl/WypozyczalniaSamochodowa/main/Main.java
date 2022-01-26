package edu.uni.lodz.pl.WypozyczalniaSamochodowa.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class Main extends JFrame {
    private final PracownikRepository pracownikRepository;
    private Pracownik zalogowanyPracownik;
    private JPanel panel;
    private JTabbedPane tabbedPane1;
    private JTable table1;
    private JScrollPane scrollPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private DefaultTableColumnModel model;
    private JLabel jlabel;

    public Main(PracownikRepository pracownikRepository, Pracownik zalogowanyPracownik) {
        this.pracownikRepository = pracownikRepository;
        this.zalogowanyPracownik = zalogowanyPracownik;
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 800));
        setResizable(false);
        add(panel);
        zaladujDane();
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void zaladujDane() {
        String[] columnNames = {"Imie", "Nazwisko"};
        Object[][] data = pracownikRepository
                .findAll()
                .stream()
                .map(p -> new Object[]{p.getImie(), p.getNazwisko()})
                .toArray(Object[][]::new);
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
        table1.setModel(defaultTableModel);
    }
}
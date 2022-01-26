package edu.uni.lodz.pl.WypozyczalniaSamochodowa.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;


public class Main extends JFrame {
    @Setter
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

    public Main(Pracownik zalogowanyPracownik) {
        setTitle("Wypożyczalnia samochodowa");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1500, 800));
        setResizable(false);
        add(panel);

        this.zalogowanyPracownik = zalogowanyPracownik;
        String[] columnNames = {"Imie", "Nazwisko"};
        Object[][] data = {
                {"Adam", "Nowak"},
                {"Kasia", "Kowalska"}
        };
        DefaultTableModel defaultTableModel = new DefaultTableModel(data, columnNames);
        table1.setModel(defaultTableModel);
        pack();
        setLocationRelativeTo(null);
        this.setVisible(true);


    }

}
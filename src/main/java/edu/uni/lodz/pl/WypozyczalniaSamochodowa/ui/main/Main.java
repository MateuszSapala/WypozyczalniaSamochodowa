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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Optional;


public class Main extends JFrame {
    private final Repos repos;
    private final AutoService autoService;
    private Pracownik zalogowanyPracownik;
    private Integer idWybranegoAuta;

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
    private JTextField textFieldAutoCenaZaDzien;
    private JTextField textFieldAutoMarka;
    private JTextField textFieldAutoModel;
    private JTextField textFieldAutoRokProdukcji;
    private JComboBox comboBoxAutoNadwozie;
    private JComboBox comboBoxAutoPaliwo;
    private JComboBox comboBoxAutoSkrzynia;
    private JTextField textFieldImie;
    private JTextField textFieldNazwisko;
    private JTextField textFieldPesel;
    private JTextField textFieldPlec;
    private JTextField textFieldGodziny;
    private JTextField textFieldLogin;
    private JTextField textFieldHaslo;
    private DefaultTableColumnModel model;
    private JLabel jlabel;
    //</editor-fold>

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

        //<editor-fold desc="ComboBoxes">
        comboBoxAutoNadwozie.setModel(new DefaultComboBoxModel<Nadwozie>(Nadwozie.values()));
        comboBoxAutoPaliwo.setModel(new DefaultComboBoxModel<Paliwo>(Paliwo.values()));
        comboBoxAutoSkrzynia.setModel(new DefaultComboBoxModel<Skrzynia>(Skrzynia.values()));
        //</editor-fold>

        //<editor-fold desc="Buttons">
        buttonAutaDodaj.addActionListener(e -> dodajAuto());
        buttonAutaEdytuj.addActionListener(e -> edytujAuto());
        buttonAutaUsun.addActionListener(e -> usunAuto());
        tableAuta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                zaladujDaneWybranegoAuta();
            }
        });
        //</editor-fold>
    }

    private void zaladujDane() {
        zaladujDanePracownikow();
        zaladujDaneDlaAut();
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
        tablePracownicy.setModel(defaultTableModel);
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
        textFieldAutoCenaZaDzien.setText("");
        textFieldAutoMarka.setText("");
        textFieldAutoModel.setText("");
        textFieldAutoRokProdukcji.setText("");
        comboBoxAutoNadwozie.setSelectedIndex(0);
        comboBoxAutoPaliwo.setSelectedIndex(0);
        comboBoxAutoSkrzynia.setSelectedIndex(0);
        idWybranegoAuta = null;
    }

    private void uzupelnijInputDlaAut(Auto auto) {
        textFieldAutoCenaZaDzien.setText(String.valueOf(auto.getCenaZaDzien()));
        textFieldAutoMarka.setText(auto.getMarka());
        textFieldAutoModel.setText(auto.getModel());
        textFieldAutoRokProdukcji.setText(String.valueOf(auto.getRokProdukcji()));
        comboBoxAutoNadwozie.setSelectedItem(auto.getNadwozie());
        comboBoxAutoPaliwo.setSelectedItem(auto.getPaliwo());
        comboBoxAutoSkrzynia.setSelectedItem(auto.getSkrzynia());
    }

    private boolean sprawdzInputUzytkownika() {
        try {
            int cena = Integer.parseInt(textFieldAutoCenaZaDzien.getText());
            if (cena < 0) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Podano nieprawidłową cenę za dzień");
            return false;
        }

        if (textFieldAutoMarka.getText().length() < 3) {
            JOptionPane.showMessageDialog(panel, "Marka ma zbyt mało znaków");
            return false;
        }
        if (textFieldAutoModel.getText().length() < 2) {
            JOptionPane.showMessageDialog(panel, "Model ma zbyt mało znaków");
            return false;
        }

        try {
            int rok = Integer.parseInt(textFieldAutoRokProdukcji.getText());
            if (rok < 1900 || rok > Calendar.getInstance().get(Calendar.YEAR)) {
                throw new Exception();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(panel, "Podano nieprawy rok produkcji");
            return false;
        }
        return true;
    }

    private Auto edytujDaneAutaNaPodstawieInputu(Auto auto) {
        if (!sprawdzInputUzytkownika()) {
            return null;
        }
        auto.setCenaZaDzien(Integer.parseInt(textFieldAutoCenaZaDzien.getText()));
        auto.setMarka(textFieldAutoMarka.getText());
        auto.setModel(textFieldAutoModel.getText());
        auto.setRokProdukcji(Integer.parseInt(textFieldAutoRokProdukcji.getText()));
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
}
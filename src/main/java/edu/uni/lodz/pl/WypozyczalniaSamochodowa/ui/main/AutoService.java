package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import lombok.RequiredArgsConstructor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Optional;

@RequiredArgsConstructor
public class AutoService {
    private final Repos repos;

    public DefaultTableModel tabelaAuta() {
        String[] columnNames = {"Id", "Cena za godzinę", "Marka", "Model", "Paliwo", "Rok produkcji", "Skrzynia"};
        Object[][] data = repos.getAutoRepository()
                .findAll()
                .stream()
                .map(a -> new Object[]{a.getId(), a.getCenaZaGodzine(), a.getMarka(), a.getModel(), a.getPaliwo(), a.getRokProdukcji(), a.getSkrzynia()})
                .toArray(Object[][]::new);
        return new DefaultTableModel(data, columnNames);
    }

    public Auto pobierzWybraneAutoZTabeli(JTable tableAuta, JPanel panel) {
        if (tableAuta.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(panel, "Nie wybrano wiersza");
            return null;
        }
        Integer id = (int) tableAuta.getValueAt(tableAuta.getSelectedRow(), 0);
        Optional<Auto> auto = repos.getAutoRepository().findById(id);
        if (auto.isEmpty()) {
            JOptionPane.showMessageDialog(panel, "Auta nie ma w bazie");
            return null;
        }
        return auto.get();
    }
}

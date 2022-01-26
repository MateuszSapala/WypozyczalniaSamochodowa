package edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.main;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import lombok.RequiredArgsConstructor;

import javax.swing.table.DefaultTableModel;

@RequiredArgsConstructor
public class AutoService {
    private final Repos repos;

    public DefaultTableModel tabelaAuta(){
        String[] columnNames = {"Id", "Cena za dzien", "Marka", "Model", "Paliwo", "Rok produkcji", "Skrzynia"};
        Object[][] data = repos.getAutoRepository()
                .findAll()
                .stream()
                .map(a -> new Object[]{a.getId(), a.getCenaZaDzien(), a.getMarka(), a.getModel(), a.getPaliwo(), a.getRokProdukcji(), a.getSkrzynia()})
                .toArray(Object[][]::new);
        return new DefaultTableModel(data, columnNames);
    }

    public void dodajAuto(){

    }

    public void edytujAuto(){

    }

    public void aktualizujAuto(){

    }

    public void czytajAuto(Integer id){

    };
}

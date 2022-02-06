package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
@Entity

public class GodzinyPracy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalTime poniedzialekOd;
    private LocalTime poniedzialekDo;
    private LocalTime wtorekOd;
    private LocalTime wtorekDo;
    private LocalTime srodaOd;
    private LocalTime srodaDo;
    private LocalTime czwartekOd;
    private LocalTime czwartekDo;
    private LocalTime piatekOd;
    private LocalTime piatekDo;
    private LocalTime sobotaOd;
    private LocalTime sobotaDo;
    @OneToOne
    private Pracownik pracownik;


    public GodzinyPracy(LocalTime poniedzialekOd, LocalTime poniedzialekDo, LocalTime wtorekOd, LocalTime wtorekDo, LocalTime srodaOd, LocalTime srodaDo, LocalTime czwartekOd, LocalTime czwartekDo, LocalTime piatekOd, LocalTime piatekDo, LocalTime sobotaOd, LocalTime sobotaDo, Pracownik pracownik) {
        this.poniedzialekOd = poniedzialekOd;
        this.poniedzialekDo = poniedzialekDo;
        this.wtorekOd = wtorekOd;
        this.wtorekDo = wtorekDo;
        this.srodaOd = srodaOd;
        this.srodaDo = srodaDo;
        this.czwartekOd = czwartekOd;
        this.czwartekDo = czwartekDo;
        this.piatekOd = piatekOd;
        this.piatekDo = piatekDo;
        this.sobotaOd = sobotaOd;
        this.sobotaDo = sobotaDo;
        this.pracownik = pracownik;
    }

    public GodzinyPracy(String czasPoczatkowy, String czasKoncowy) {
    }

    public GodzinyPracy(int czasPoczatkowy, int czasKoncowy) {
    }

    public static Integer getId(Integer idGodziny) {
        return idGodziny;
    }


    //Dopisać działanie
    public void setGodzinyPracy(String godzinyPracy) {
    }
    //public GodzinyPracy pobierzWybraneGodzinyZTabeli(JTable tableGodziny, JPanel panel) {
      //  if (tableGodziny.getSelectedRow() < 0) {
        //    JOptionPane.showMessageDialog(panel, "Nie wybrano wiersza");
          //  return null;
       // }
       // Integer id = (int) tableGodziny.getValueAt(tableGodziny.getSelectedRow(), 0);
       // Optional<GodzinyPracy> godzinyPracy = repos.getGodzinyPracyRepository().findById(id);
       // if (godzinyPracy.isEmpty()) {
         //   JOptionPane.showMessageDialog(panel, "Godziny nie ma w bazie");
          //  return null;
        //}
        //return godzinyPracy.get();
    //}



}
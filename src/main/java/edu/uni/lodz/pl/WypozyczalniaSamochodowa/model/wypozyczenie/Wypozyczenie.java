package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.wypozyczenie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Wypozyczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date dataPoczatkowa;
    private Date dataKoncowa;
    private Status status;
    @ManyToOne
    private Klient klient;
    @ManyToOne
    private Wypozyczenie wypozyczenie;

    public Wypozyczenie(Date dataPoczatkowa, Date dataKoncowa, Status status, Klient klient, Wypozyczenie wypozyczenie) {
        this.dataPoczatkowa = dataPoczatkowa;
        this.dataKoncowa = dataKoncowa;
        this.status = status;
        this.klient = klient;
        this.wypozyczenie = wypozyczenie;
    }
}
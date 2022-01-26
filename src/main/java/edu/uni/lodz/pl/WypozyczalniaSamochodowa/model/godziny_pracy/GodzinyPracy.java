package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class GodzinyPracy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time poniedzialekOd;
    private Time poniedzialekDo;
    private Time wtorekOd;
    private Time wtorekDo;
    private Time srodaOd;
    private Time srodaDo;
    private Time czwartekOd;
    private Time czwartekDo;
    private Time piatekOd;
    private Time piatekDo;
    private Time sobotaOd;
    private Time sobotaDo;

    public GodzinyPracy(Time poniedzialekOd, Time poniedzialekDo, Time wtorekOd, Time wtorekDo, Time srodaOd, Time srodaDo, Time czwartekOd, Time czwartekDo, Time piatekOd, Time piatekDo, Time sobotaOd, Time sobotaDo) {
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
    }
}
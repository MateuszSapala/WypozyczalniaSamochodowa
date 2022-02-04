package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;
import java.time.LocalTime;

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


    public GodzinyPracy(LocalTime poniedzialekOd, LocalTime poniedzialekDo, LocalTime wtorekOd, LocalTime wtorekDo, LocalTime srodaOd, LocalTime srodaDo, LocalTime czwartekOd, LocalTime czwartekDo, LocalTime piatekOd, LocalTime piatekDo, LocalTime sobotaOd, LocalTime sobotaDo) {
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
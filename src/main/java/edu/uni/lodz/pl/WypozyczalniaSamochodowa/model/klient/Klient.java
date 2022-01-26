package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String login;
    private String haslo;
    private Plec plec;

    public Klient(String imie, String nazwisko, String pesel, String login, String haslo, Plec plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
        this.plec = plec;
    }
}
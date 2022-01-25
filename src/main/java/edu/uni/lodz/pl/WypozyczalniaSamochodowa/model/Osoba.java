package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Osoba {
    @Id
    private Integer id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String login;
    private String haslo;
    private Plec plec;

    public Osoba(String imie, String nazwisko, String pesel, String login, String haslo, Plec plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
        this.plec = plec;
    }
}

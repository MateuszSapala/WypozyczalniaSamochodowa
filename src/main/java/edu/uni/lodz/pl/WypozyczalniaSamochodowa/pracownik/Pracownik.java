package edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Osoba;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;


public class Pracownik extends Osoba {
    public Pracownik(String imie, String nazwisko, String pesel, String login, String haslo, Plec plec) {
        super(imie, nazwisko, pesel, login, haslo, plec);
    }
}

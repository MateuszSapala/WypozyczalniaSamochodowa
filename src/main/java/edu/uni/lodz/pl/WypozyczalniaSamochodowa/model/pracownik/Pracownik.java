package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy.GodzinyPracy;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Pracownik {
    private String pesel;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imie;
    private String nazwisko;
    @Column(unique = true)
    private String login;
    private String haslo;
    private Plec plec;
    @OneToOne
    private GodzinyPracy godzinyPracy;

    public Pracownik(String imie, String nazwisko, String pesel, String login, String haslo, Plec plec, GodzinyPracy godzinyPracy) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
        this.plec = plec;
        this.godzinyPracy = godzinyPracy;
    }
}

package edu.uni.lodz.pl.WypozyczalniaSamochodowa;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Auto;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Nadwozie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Paliwo;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.auto.Skrzynia;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy.GodzinyPracy;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.logowanie.Logowanie;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Start implements CommandLineRunner {
    private final Repos repos;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Start.class).headless(false).run(args);
    }

    @Override
    public void run(String... args) {
        Logowanie logowanie = new Logowanie(repos);
        logowanie.setVisible(true);
       dodajDane();
    }

    private void dodajDane() {
        if (!repos.getPracownikRepository().findAll().isEmpty()) {
            return;
        }
        dodajPracownikow();
        dodajKlientow();
        dodajAuta();

    }

    private void dodajPracownikow() {
        LocalTime start = LocalTime.of(8,0,0);
        LocalTime end =  LocalTime.of(16, 0, 0);
        GodzinyPracy g1 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        GodzinyPracy g2 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        GodzinyPracy g3 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        Pracownik p1 = new Pracownik("Adam", "Nowak", "32454320034", "admin", "pass", Plec.MEZCZYZNA, g1);
        Pracownik p2 = new Pracownik("Tomasz", "Kowalski", "35653354198", "employee1", "pass1", Plec.MEZCZYZNA, g2);
        Pracownik p3 = new Pracownik("Anna", "Nowakowska", "32565348453", "employee2", "pass2", Plec.KOBIETA, g3);
        repos.getGodzinyPracyRepository().saveAll(List.of(g1, g2, g3));
        repos.getPracownikRepository().saveAll(List.of(p1, p2, p3));
    }

    private void dodajKlientow() {
        if (!repos.getKlientRepository().findAll().isEmpty()) {
            return;
        }
        Klient k1 = new Klient("Filip", "Sadowski", "5728540954", "user1", "passuser1", Plec.MEZCZYZNA);
        Klient k2 = new Klient("Jan", "Pach", "27650193845", "user2", "passuser2", Plec.MEZCZYZNA);
        repos.getKlientRepository().saveAll(List.of(k1, k2));
    }

    private void dodajAuta() {
        Auto a1 = new Auto("Audi", "A7", Nadwozie.SEDAN, Paliwo.BENZYNA, Skrzynia.AUTOMATYCZNA, 2020, 150);
        Auto a2 = new Auto("Volkswagen", "Passat", Nadwozie.SEDAN, Paliwo.DIESEL, Skrzynia.MANUALNA, 2015, 100);
        repos.getAutoRepository().saveAll(List.of(a1, a2));
    }


}

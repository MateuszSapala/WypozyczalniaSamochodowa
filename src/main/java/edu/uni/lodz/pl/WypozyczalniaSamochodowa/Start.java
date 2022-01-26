package edu.uni.lodz.pl.WypozyczalniaSamochodowa;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy.GodzinyPracy;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.ui.logowanie.Logowanie;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.sql.Time;
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
        dodajPracownikowJesliIchNieMa();
    }

    private void dodajPracownikowJesliIchNieMa() {
        if (!repos.getPracownikRepository().findAll().isEmpty()) {
            return;
        }
        Time start = new Time(8, 0, 0);
        Time end = new Time(16, 0, 0);
        GodzinyPracy g1 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        GodzinyPracy g2 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        GodzinyPracy g3 = new GodzinyPracy(start, end, start, end, start, end, start, end, start, end, start, end);
        Pracownik p1 = new Pracownik("Adam", "Nowak", "32454320034", "admin", "pass", Plec.MEZCZYZNA, g1);
        Pracownik p2 = new Pracownik("Tomasz", "kowalski", "35653354198", "user1", "pass1", Plec.MEZCZYZNA, g2);
        Pracownik p3 = new Pracownik("Anna", "Nowakowska", "32565348453", "user2", "pass2", Plec.KOBIETA, g3);
        repos.getGodzinyPracyRepository().saveAll(List.of(g1, g2, g3));
        repos.getPracownikRepository().saveAll(List.of(p1, p2, p3));
    }
}

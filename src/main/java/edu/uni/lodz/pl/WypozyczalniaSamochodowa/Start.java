package edu.uni.lodz.pl.WypozyczalniaSamochodowa;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie.Logowanie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.main.Main;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class Start implements CommandLineRunner {
    private final PracownikRepository pracownikRepository;

    public static void main(String[] args) {
        new SpringApplicationBuilder(Start.class).headless(false).run(args);
    }


    @Override
    public void run(String... args) {
        Logowanie logowanie = new Logowanie(pracownikRepository);
        logowanie.setVisible(true);
       //jeżeli chcecie dodać pracowników do bazy odkomentujcie linijke poniżej
        //dodajPracownikow();
    }

    private void dodajPracownikow() {
        Pracownik p1 = new Pracownik("Adam", "Nowak", "32454320034", "admin", "pass", Plec.MEZCZYZNA);
        Pracownik p2 = new Pracownik("Tomasz", "kowalski", "35653354198", "user1", "pass1", Plec.MEZCZYZNA);
        Pracownik p3 = new Pracownik("Anna", "Nowakowska", "32565348453", "user2", "pass2", Plec.KOBIETA);
        pracownikRepository.saveAll(List.of(p1, p2, p3));
    }
}

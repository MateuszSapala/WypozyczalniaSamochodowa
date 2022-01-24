package edu.uni.lodz.pl.WypozyczalniaSamochodowa;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie.Logowanie;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie.LogowanieService;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.main.Main;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Plec;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.awt.*;
import java.util.List;

@SpringBootApplication
public class Start implements CommandLineRunner {
    @Autowired
    LogowanieService logowanieService;
    @Autowired
    Main main;
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(Start.class)
                .headless(false).run(args);
        //var ctx = new SpringApplicationBuilder(Logowanie.class, LogowanieService.class, Main.class, PracownikRepository.class)
         //       .headless(false).run(args);

        //EventQueue.invokeLater(() -> {
       //     ctx.getBean(Logowanie.class);
        //    ctx.getBean(LogowanieService.class);
         //   ctx.getBean(Main.class);
       //     ctx.getBean(PracownikRepository.class);

       //     dodajPracownikow(ctx.getBean(PracownikRepository.class));

         //   Main main = ctx.getBean(Main.class);
        //    main.setVisible(false);
         //   Logowanie logowanie = ctx.getBean(Logowanie.class);
        //    logowanie.setVisible(true);
       // });
    }

    private static void dodajPracownikow(PracownikRepository repository) {
        Pracownik p1 = new Pracownik("Adam", "Nowak", "32454320034", "admin", "pass", Plec.MEZCZYZNA);
        repository.saveAll(List.of(p1));
    }

    @Override
    public void run(String... args) throws Exception {
        Logowanie logowanie= new Logowanie(main,logowanieService);
        logowanie.setVisible(true);

    }
}

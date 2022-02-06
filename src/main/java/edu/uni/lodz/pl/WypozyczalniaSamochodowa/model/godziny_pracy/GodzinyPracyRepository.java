package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.godziny_pracy;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.Repos;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient.Klient;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.pracownik.Pracownik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.*;
import java.sql.Time;
import java.util.List;
import java.util.Optional;



public interface GodzinyPracyRepository extends JpaRepository<GodzinyPracy, Integer> {



   // @Query("SELECT g FROM godziny_pracy g WHERE(g.id=:id)")
    Optional<GodzinyPracy> findById(    Integer id);



}


package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.wypozyczenie;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WypozyczenieRepository extends JpaRepository<Wypozyczenie, Integer> {
}
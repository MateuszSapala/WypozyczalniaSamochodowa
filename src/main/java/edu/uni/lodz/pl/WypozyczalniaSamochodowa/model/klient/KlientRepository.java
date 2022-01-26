package edu.uni.lodz.pl.WypozyczalniaSamochodowa.model.klient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KlientRepository extends JpaRepository<Klient, Integer> {
    Optional<Klient> findByLoginAndHaslo(String login, String haslo);
}
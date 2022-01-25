package edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
@EntityScan
public interface PracownikRepository extends JpaRepository<Pracownik, Integer> {
    @Query("SELECT p FROM Pracownik p WHERE(p.login=:login AND p.haslo=:haslo)")
    Optional<Pracownik> findAllByLoginAndPassword(@Param("login") String login, @Param("haslo") String haslo);
}

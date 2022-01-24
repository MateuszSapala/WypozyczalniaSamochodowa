package edu.uni.lodz.pl.WypozyczalniaSamochodowa.logowanie;

import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.Pracownik;
import edu.uni.lodz.pl.WypozyczalniaSamochodowa.pracownik.PracownikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LogowanieService {
    PracownikRepository pracownikRepository;

    public final Optional<Pracownik> zaloguj(String login, String password) {
        List<Pracownik> list = pracownikRepository.findAllByLoginAndPassword(login, password);
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(list.get(0));
    }
}

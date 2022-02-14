package src.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ApartmentsRepository apartmentsRepository, BuildingsRepository buildingsRepository) {

        return args -> {
            //apartmentsRepository.save(new Apartment("apartment-gardenia-2", "2-room", 60.0, true, null));
            //apartmentSet.add(new Apartment("apartment-gardenia-3", "3-room", 90.0, true, null));
            buildingsRepository.save(new Building(Long.valueOf(1), "GardeniaApartments", "ul. Kwiatowa 80, 70-110 Szczecin", 5000.0, 3000.0, null));
            //buildingsRepository.save(new Building("GardeniaApartments", "ul. Kwiatowa 80, 70-110 Szczecin", 5000.0, 3000.0, null));
            apartmentsRepository.save(new Apartment("apartment-gardenia-1", "1-room", 30.0, true, buildingsRepository.getById(Long.valueOf(1))));
            apartmentsRepository.save(new Apartment("apartment-gardenia-2", "2-room", 60.0, true, buildingsRepository.getById(Long.valueOf(1))));
            apartmentsRepository.save(new Apartment("apartment-gardenia-3", "3-room", 90.0, true, buildingsRepository.getById(Long.valueOf(1))));
            log.info("Gardenia Apartments loaded.");
        };
    }
}

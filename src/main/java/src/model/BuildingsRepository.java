package src.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingsRepository extends JpaRepository<Building, Long> {

    Building getBuildingByName(String name);
}

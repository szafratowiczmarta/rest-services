package src.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingsRepository extends JpaRepository<Building, Long> {

    List<Building> findByName(String name);

}

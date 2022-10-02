package src.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingsRepository extends JpaRepository<Building, Integer> {

    List<Building> findByName(String name);

}

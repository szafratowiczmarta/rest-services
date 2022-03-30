package src.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentsRepository extends JpaRepository<Apartment, Long> {

    List<Apartment> findByType(String type);

}
package src.model;

import org.springframework.data.jpa.repository.JpaRepository;
import src.factory.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Integer> {
}

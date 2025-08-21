package exercises.spring_la_mia_pizzeria_security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import exercises.spring_la_mia_pizzeria_security.model.*;

public interface PromoCodeRepository extends JpaRepository<PromoCode, Integer> {
}

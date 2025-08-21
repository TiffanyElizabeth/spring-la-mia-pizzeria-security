package exercises.spring_la_mia_pizzeria_security.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import exercises.spring_la_mia_pizzeria_security.model.*;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {
    public List<Pizza> findByNameContainingIgnoreCase(String name);
}

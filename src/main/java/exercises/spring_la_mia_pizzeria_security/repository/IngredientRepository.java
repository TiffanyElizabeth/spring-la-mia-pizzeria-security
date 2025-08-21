package exercises.spring_la_mia_pizzeria_security.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import exercises.spring_la_mia_pizzeria_security.model.*;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    public List<Ingredient> findByNameContainingIgnoreCase(String name);
}

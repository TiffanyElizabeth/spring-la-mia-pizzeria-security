package exercises.spring_la_mia_pizzeria_security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercises.spring_la_mia_pizzeria_security.model.Ingredient;
import exercises.spring_la_mia_pizzeria_security.model.Pizza;
import exercises.spring_la_mia_pizzeria_security.repository.IngredientRepository;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public Ingredient getById(Integer id) {
        return ingredientRepository.findById(id).get();
    }

    public List<Ingredient> findByNameContaining(String name) {
        return ingredientRepository.findByNameContainingIgnoreCase(name);
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredientToDelete) {
        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
    }

    public void deleteById(Integer id) {
        Ingredient ingredientToDelete = getById(id);

        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientRepository.delete(ingredientToDelete);
    }
}

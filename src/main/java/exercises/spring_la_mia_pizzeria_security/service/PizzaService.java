package exercises.spring_la_mia_pizzeria_security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercises.spring_la_mia_pizzeria_security.model.Pizza;
import exercises.spring_la_mia_pizzeria_security.model.PromoCode;
import exercises.spring_la_mia_pizzeria_security.repository.PizzaRepository;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PromoCodeService promoCodeService;

    public List<Pizza> findAll() {
        return pizzaRepository.findAll();
    }

    public Optional<Pizza> findById(Integer id) {
        return pizzaRepository.findById(id);
    }

    public Pizza getById(Integer id) {
        // if there is no pizza with that id?
        return pizzaRepository.findById(id).get();
    }

    public List<Pizza> findByName(String name) {
        return pizzaRepository.findByNameContainingIgnoreCase(name);
    }

    public Pizza create(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public Pizza update(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public void delete(Pizza pizza) {

        for (PromoCode promoCodeToDelete : pizza.getPromoCodes()) {
            promoCodeService.delete(promoCodeToDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public void deleteById(Integer id) {
        Pizza pizza = getById(id);

        for (PromoCode promoCodeToDelete : pizza.getPromoCodes()) {
            promoCodeService.delete(promoCodeToDelete);
        }

        pizzaRepository.delete(pizza);
    }

    public Boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public Boolean exists(Pizza pizza) {
        return existsById(pizza.getId());
    }

}

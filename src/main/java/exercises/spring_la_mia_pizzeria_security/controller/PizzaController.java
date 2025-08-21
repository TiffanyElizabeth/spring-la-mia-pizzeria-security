package exercises.spring_la_mia_pizzeria_security.controller;

import exercises.spring_la_mia_pizzeria_security.model.Pizza;
import exercises.spring_la_mia_pizzeria_security.model.PromoCode;
import exercises.spring_la_mia_pizzeria_security.service.IngredientService;
import exercises.spring_la_mia_pizzeria_security.service.PizzaService;
import exercises.spring_la_mia_pizzeria_security.service.PromoCodeService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private PromoCodeService promoCodeService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        List<Pizza> pizzas = pizzaService.findAll(); // SELECT * FROM `pizzas` and give me a list of pizza objects
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("hasPizzas", !pizzas.isEmpty());
        return "pizzas/index";

    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        return "pizzas/detail";
    }

    @GetMapping("/searchByName")
    public String searchByName(@RequestParam(name = "name") String name, Model model) {
        List<Pizza> pizzas = pizzaService.findByName(name);
        model.addAttribute("pizzas", pizzas);
        model.addAttribute("hasPizzas", !pizzas.isEmpty());
        return "pizzas/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizzas/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/create";
        }

        pizzaService.create(formPizza);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("pizza", pizzaService.getById(id));
        model.addAttribute("ingredients", ingredientService.findAll());
        return "pizzas/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredients", ingredientService.findAll());
            return "pizzas/edit";
        }

        pizzaService.update(formPizza);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        Pizza pizza = pizzaService.getById(id);

        for (PromoCode promoCodeToDelete : pizza.getPromoCodes()) {
            promoCodeService.delete(promoCodeToDelete);
        }

        pizzaService.delete(pizza);
        return "redirect:/pizzas";
    }

    @GetMapping("/{id}/promo")
    public String promo(@PathVariable Integer id, Model model) {
        PromoCode promoCode = new PromoCode();
        promoCode.setPizza(pizzaService.getById(id));

        model.addAttribute("promoCode", promoCode);
        return "promo_codes/create";
    }

}

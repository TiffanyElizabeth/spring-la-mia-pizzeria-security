package exercises.spring_la_mia_pizzeria_security.controller;

import exercises.spring_la_mia_pizzeria_security.model.*;
import exercises.spring_la_mia_pizzeria_security.service.IngredientService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        List<Ingredient> ingredients = ingredientService.findAll();
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("hasIngredients", !ingredients.isEmpty());
        return "ingredients/index";
    }

    @GetMapping("/searchByName")
    public String searchbyName(@RequestParam(name = "name") String name, Model model) {
        List<Ingredient> ingredients = ingredientService.findByNameContaining(name);
        model.addAttribute("ingredient", ingredients);
        model.addAttribute("hasIngredients", !ingredients.isEmpty());
        return "ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("ingredient", new Ingredient());
        return "ingredients/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }

        ingredientService.create(formIngredient);

        return "redirect:/ingredients";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("ingredient", ingredientService.getById(id));
        model.addAttribute("edit", true);
        return "ingredients/create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients/create";
        }

        ingredientService.update(formIngredient);

        return "redirect:/ingredients";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Ingredient ingredientToDelete = ingredientService.getById(id);

        for (Pizza pizza : ingredientToDelete.getPizzas()) {
            pizza.getIngredients().remove(ingredientToDelete);
        }

        ingredientService.delete(ingredientToDelete);

        return "redirect:/ingredients";
    }

}

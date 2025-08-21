package exercises.spring_la_mia_pizzeria_security.controller;

import exercises.spring_la_mia_pizzeria_security.model.*;
import exercises.spring_la_mia_pizzeria_security.service.PromoCodeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/promos")
public class PromoCodeController {

    @Autowired
    private PromoCodeService promoCodeService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("promoCode") PromoCode formPromoCode, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "promo_codes/create";
        }

        promoCodeService.create(formPromoCode);

        return "redirect:/pizzas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("promoCode", promoCodeService.getById(id));
        model.addAttribute("edit", true);
        return "promo_codes/create";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("promoCode") PromoCode formPromoCode,
            BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "promo_codes/create";
        }

        promoCodeService.update(formPromoCode);

        return "redirect:/pizzas";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        PromoCode promoCode = promoCodeService.getById(id);

        promoCodeService.delete(promoCode);

        return "redirect:/pizzas";
    }

}

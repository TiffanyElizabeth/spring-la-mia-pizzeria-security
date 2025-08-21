package exercises.spring_la_mia_pizzeria_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exercises.spring_la_mia_pizzeria_security.model.PromoCode;
import exercises.spring_la_mia_pizzeria_security.repository.PromoCodeRepository;

@Service
public class PromoCodeService {
    @Autowired
    private PromoCodeRepository promoCodeRepository;

    public PromoCode getById(Integer id) {
        return promoCodeRepository.findById(id).get();
    }

    public PromoCode create(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    public PromoCode update(PromoCode promoCode) {
        return promoCodeRepository.save(promoCode);
    }

    public void delete(PromoCode promoCode) {
        promoCodeRepository.delete(promoCode);
    }

    public void deleteById(Integer id) {
        PromoCode promoCode = getById(id);
        promoCodeRepository.delete(promoCode);
    }

}

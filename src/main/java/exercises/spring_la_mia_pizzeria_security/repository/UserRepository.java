package exercises.spring_la_mia_pizzeria_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import exercises.spring_la_mia_pizzeria_security.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}

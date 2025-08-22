package exercises.spring_la_mia_pizzeria_security.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import exercises.spring_la_mia_pizzeria_security.model.User;
import exercises.spring_la_mia_pizzeria_security.repository.UserRepository;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);

        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("Username '" + username + "'" + " not found");
        }

        return new DatabaseUserDetails(userAttempt.get());
    }
}

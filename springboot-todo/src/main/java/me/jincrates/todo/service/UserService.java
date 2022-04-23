package me.jincrates.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import me.jincrates.todo.domain.users.User;
import me.jincrates.todo.domain.users.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User create(final User user) {
        if (user == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }

        final String email = user.getEmail();

        if (userRepository.existsByEmail(email)) {
            log.warning("Email already exists " + email);
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    public User getByCredentials(final String email, final String password, final PasswordEncoder encoder) {
        final User originalUser = userRepository.findByEmail(email);

        //matches 메서드를 이용해 패스워드가 같은지 확인
        if (originalUser != null && encoder.matches(password, originalUser.getPassword())) {
            return originalUser;
        }

        return null;
    }
}

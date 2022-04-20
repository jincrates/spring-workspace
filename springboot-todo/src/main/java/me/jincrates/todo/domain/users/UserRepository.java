package me.jincrates.todo.domain.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);

    Boolean existsByEmail(String email);

    User findByEmailAndPassword(String email, String password);
}

package me.jincrates.work.repository;

import me.jincrates.work.entity.Annual;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnnualRepository extends JpaRepository<Annual, String> {
    //Optional<Annual> findByEmail(String email);
}

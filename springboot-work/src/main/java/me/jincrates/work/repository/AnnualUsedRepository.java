package me.jincrates.work.repository;

import me.jincrates.work.entity.AnnualUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface AnnualUsedRepository extends JpaRepository<AnnualUsed, Long> {

    @Query(value = " SELECT SUM(u.used) "
                 + " FROM AnnualUsed u "
                 + " WHERE u.member = :email ")
    Optional<Double> findUsedCount(@Param("email") String email);
}

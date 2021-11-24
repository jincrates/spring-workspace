package me.jincrates.work.repository;

import me.jincrates.work.entity.AnnualUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnualUsedRepository extends JpaRepository<AnnualUsed, String> {

    @Query(value = " SELECT SUM(u.used) "
                 + " FROM AnnualUsed u "
                 + " WHERE u.member = :email ")
    double findUsedCount(@Param("email") String email);
}

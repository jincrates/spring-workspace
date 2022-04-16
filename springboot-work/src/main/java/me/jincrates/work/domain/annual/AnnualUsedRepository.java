package me.jincrates.work.domain.annual;

import me.jincrates.work.domain.annual.AnnualUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface AnnualUsedRepository extends JpaRepository<AnnualUsed, Long> {

    @Query(value = " SELECT SUM(u.used) "
                 + " FROM AnnualUsed u "
                 + " WHERE u.member = :email ")
    Optional<Double> findUsedCount(@Param("email") String email);

    //영속성 컨텍스트를 무시
    @Modifying(clearAutomatically = true)
    @Query(value = " UPDATE AnnualUsed u "
                 + " SET u.reason       = :reason "
                 + "   , u.used         = :used "
                 + "   , u.usedFromDate = :fromDate "
                 + "   , u.usedToDate   = :toDate "
                 + " WHERE u.id = :id")
    int update(@Param("id") Long id,
               @Param("reason") String reason,
               @Param("used") double used,
               @Param("fromDate") Date fromDate,
               @Param("toDate") Date toDate);
}

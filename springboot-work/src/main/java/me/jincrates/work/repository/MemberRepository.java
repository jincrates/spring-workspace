package me.jincrates.work.repository;

import me.jincrates.work.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByEmail(String email);

    Boolean existsByEmail(String email);

    //Member findByEmailAndPassword(String email, String password);
}

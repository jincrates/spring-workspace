package me.jincrates.work.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.work.repository.AnnualUsedRepository;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnualUsedService {

    private final MemberRepository memberRepository;
    private final AnnualUsedRepository usedRepository;

    public double findUsedCount(String email) {
        Optional<Double> findUsed = usedRepository.findUsedCount(email);
        double result = 0;

        if (findUsed.isPresent()) {
            result = findUsed.get();
        }

        return result;
    }
}

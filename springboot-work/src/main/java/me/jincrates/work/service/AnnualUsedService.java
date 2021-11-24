package me.jincrates.work.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.work.repository.AnnualUsedRepository;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnualUsedService {

    private final MemberRepository memberRepository;
    private final AnnualUsedRepository usedRepository;

    public double findUsedCount(String email) {
        return usedRepository.findUsedCount(email);
    }
}

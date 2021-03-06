package me.jincrates.work.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.work.web.dto.AnnualUsedDTO;
import me.jincrates.work.domain.annual.AnnualUsed;
import me.jincrates.work.domain.annual.AnnualUsedRepository;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<AnnualUsed> findAll() {
        return usedRepository.findAll();
    }

    @Transactional
    public AnnualUsed usedAnnual(AnnualUsedDTO usedDTO) {
        return usedRepository.save(toEntity(usedDTO));
    }

    @Transactional
    public void remove(Long usedId) {
        usedRepository.deleteById(usedId);
    }

    @Transactional
    public void update(Long usedId, AnnualUsedDTO usedDTO) {
        usedRepository.update(usedId, usedDTO.getReason(), usedDTO.getUsed(), usedDTO.getUsedFromDate(), usedDTO.getUsedToDate());
    }

    public AnnualUsed toEntity(AnnualUsedDTO dto) {
        AnnualUsed entity = AnnualUsed.builder()
                .baseYear(2021L)
                .member("user1@jincrates.me")
                .reason(dto.getReason())
                .usedFromDate(dto.getUsedFromDate())
                .usedToDate(dto.getUsedToDate())
                .used(dto.getUsed())
                .build();

        return entity;
    }
}

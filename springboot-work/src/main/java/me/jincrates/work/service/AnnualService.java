package me.jincrates.work.service;

import lombok.RequiredArgsConstructor;
import me.jincrates.work.web.dto.AnnualDTO;
import me.jincrates.work.domain.annual.Annual;
import me.jincrates.work.entity.Member;
import me.jincrates.work.domain.annual.AnnualRepository;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnnualService {

    private final MemberRepository memberRepository;
    private final AnnualRepository annualRepository;

    public List<Annual> findAll() {
        return annualRepository.findAll();
    }

    public Annual createAnnual(AnnualDTO annualDTO) {
        Member member = memberRepository.findByEmail(annualDTO.getMember().getEmail());

        if (member == null) {
            throw new RuntimeException("없는 사용자입니다.");
        }

        Annual annual = toEntity(annualDTO);

        return annualRepository.save(annual);
    }

    public Annual toEntity(AnnualDTO dto) {
        Member member = memberRepository.findByEmail(dto.getMember().getEmail());

        Annual entity = Annual.builder()
                .baseYear(dto.getBaseYear())
                .member(member)
                .joinDate(member.getJoinDate())
                .annual(dto.getAnnual())
                .month(dto.getMonth())
                .adjusted(dto.getAdjusted())
                .used(dto.getUsed())
                .build();

        return entity;
    }
}

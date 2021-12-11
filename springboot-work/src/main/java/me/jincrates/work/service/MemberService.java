package me.jincrates.work.service;

import lombok.extern.slf4j.Slf4j;
import me.jincrates.work.dto.MemberDTO;
import me.jincrates.work.dto.PageRequestDTO;
import me.jincrates.work.dto.PageResultDTO;
import me.jincrates.work.entity.Member;
import me.jincrates.work.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

@Slf4j
@Service
@Transactional(readOnly = true)
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

//    public PageResultDTO<MemberDTO, Object[]> getList(PageRequestDTO pageRequestDTO) {
//        Function<Object[], MemberDTO> fn = (en -> entityToDTO((Member) en[0]));
//
//        Page<Object[]> result = memberRepository.searchPage(
//                pageRequestDTO.getType(),
//                pageRequestDTO.getKeyword(),
//                pageRequestDTO.getPageable(Sort.by("memberId").descending())
//        );
//
//        return new PageResultDTO<>(result, fn);
//    }

    @Transactional
    public Member create(Member member) {
        if(member == null || member.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }

        String email = member.getEmail();

        if(memberRepository.existsByEmail(email)) {
            log.warn("Email already exists {}", email);
            throw new RuntimeException("Email already exists");
        }

        return memberRepository.save(member);
    }

    public Member findByEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        if (memberRepository.existsByEmail(email)) {
            throw new RuntimeException(String.format("Email[%s] not found", email));
        }
        return  member;
    }

    public Member getByCredentials(String email, String password, PasswordEncoder encoder) {
        Member originMember = memberRepository.findByEmail(email);

        // matches 메서드를 이용해 패스워드가 같은지 확인
        if (originMember != null && encoder.matches(password, originMember.getPassword())) {
            return originMember;
        }

        return null;
    }

    public MemberDTO entityToDTO(Member entity) {
        MemberDTO dto = MemberDTO.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .joinDate(entity.getJoinDate())
                .username(entity.getUsername())
                .build();

        return dto;
    }
}


package com.jincrates.cogito.service;

import com.jincrates.cogito.exception.UserNotFoundException;
import com.jincrates.cogito.repository.MemberRepository;
import com.jincrates.cogito.entity.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;

    public List<Member> findAll() {
        return repository.findAll();
    }

    public Member findByEmail(String email) {

        Optional<Member> member = repository.findByEmail(email, false);

        if(member.isEmpty()) {
            throw  new UserNotFoundException(String.format("Email[%s] not found", email));
        }

        //HATEOAS 설정
        //EntityModel<Member> model = new EntityModel<>(member.get());
        //WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAll());
        //model.add(linkTo.withRel("all-users");
        //return model

        return member.get();
    }

    /*
    public void deleteByEmail(String email) {

        Optional<Member> member = repository.findByEmail(email, false);

        if(member.isEmpty()) {
            throw  new UserNotFoundException(String.format("Email[%s] not found", email));
        }

        repository.deleteByEmail(email);
    }
    */

    public Member save(Member member) {
        return repository.save(member);
    }
}

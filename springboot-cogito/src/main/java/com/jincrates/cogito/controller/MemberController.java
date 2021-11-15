package com.jincrates.cogito.controller;

import com.jincrates.cogito.dto.MemberDTO;
import com.jincrates.cogito.entity.Member;
import com.jincrates.cogito.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/users") @Api(tags = {"사용자 API"})
public class MemberController {

    @Autowired
    private MemberService service;

    @ApiOperation(value = "전체 사용자 조회")
    @GetMapping("")
    public List<Member> findAll() {
        return service.findAll();
    }

    @ApiOperation(value = "사용자 조회")
    @GetMapping("/{email}")
    public Member findByEmail(@PathVariable("email") String email) {
        return service.findByEmail(email);
    }

    @ApiOperation(value = "사용자 등록", response = MemberDTO.class)
    @PostMapping("")
    public ResponseEntity<MemberDTO> register(@Valid @RequestBody Member member) {

        Member savedMember = service.save(member);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(savedMember.getEmail())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    /*
    @DeleteMapping("{email}")
    public void deleteUser(@PathVariable("email") String email) {
        service.deleteByEmail(email);
    }
    */

}

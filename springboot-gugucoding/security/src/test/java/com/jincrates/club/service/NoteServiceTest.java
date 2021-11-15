package com.jincrates.club.service;

import com.jincrates.club.dto.NoteDTO;
import com.jincrates.club.repository.NoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NoteServiceTest {

    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void testRegister() {

        NoteDTO noteDTO = NoteDTO.builder()
                .title("Test....")
                .content("Test Content....")
                .writerEmail("user1@jincrates.com")
                .build();

        Long num = noteService.register(noteDTO);

        System.out.println("Note Num : " + num);
    }

    @Test
    public void testGet() {

        Long num = 2L;

        NoteDTO noteDTO = noteService.get(num);

        System.out.println("NoteDTO : " + noteDTO);
    }

    @Test
    public void testList() {

        String email = "user10@jincrates.com";

        List<NoteDTO> result = noteService.getAllWithWriter(email);

        for(NoteDTO noteDTO : result) {
            System.out.println("NoteDTO : " + noteDTO);
        }
    }
}
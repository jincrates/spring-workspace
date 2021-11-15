package com.jincrates.cogito.service;

import com.jincrates.cogito.dto.BoardDTO;
import com.jincrates.cogito.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardServiceImplTest {

    @Autowired
    private BoardService service;

    @Test
    public void testGetBoard() {

        Long bno = 108L;

        BoardDTO boardDTO = service.get(bno);

        System.out.println(boardDTO);
    }

    @Test
    public void testGetBoardList() {

    }

    @Test
    public void testRegisterBoard() {

        BoardDTO dto = BoardDTO.builder()
                .title("Test Title...")
                .content("Test Content...")
                .writerEmail("user20@cogito.com")  //현재 데이터베이스에 존재하는 회원 이메일
                .build();
        Long bno = service.register(dto);

        System.out.println("등록된 게시글 번호: " + bno);
    }

    @Test
    public void testModifyBoard() {

        BoardDTO dto = BoardDTO.builder()
                .bno(108L)
                .title("Changed Title...")
                .content("Changed Content...")
                .build();
        service.modify(dto);

        System.out.println(dto);
    }

    @Test
    public void testRemoveBoardWithReplies() {
        Long bno = 1L;

        service.removeWithReplies(bno);
    }
}
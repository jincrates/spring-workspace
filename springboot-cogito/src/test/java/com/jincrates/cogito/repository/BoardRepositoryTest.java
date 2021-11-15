package com.jincrates.cogito.repository;

import com.jincrates.cogito.entity.Board;
import com.jincrates.cogito.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository repository;

    @Test
    public void insertBoard() {

        IntStream.rangeClosed(1, 100).forEach(i -> {

            //MemberRepository에 미리 생성되어 있어야함
            Member member = Member.builder()
                    .email("member" + i + "@cogito.com")
                    //.password("1111")
                    //.name("사용자" + i)
                    .build();

            Board board = Board.builder()
                    .title("Title..." + i)
                    .content("Content..." + i)
                    .writer(member)
                    .build();

            repository.save(board);
        });
    }
}
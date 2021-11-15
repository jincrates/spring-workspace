package com.jincrates.cogito.service;

import com.jincrates.cogito.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReplyServiceImplTest {

    @Autowired
    private ReplyService service;

    @Test
    public void testGetReplyList() {

        Long bno = 2L;  // 데이터베이스에 존재하는 번호

        List<ReplyDTO> replyDTOList = service.getList(bno);

        replyDTOList.forEach(replyDTO -> System.out.println(replyDTO));
    }

    @Test
    public void testRegisterReply() {

    }

    @Test
    public void testModifyReply() {

    }

    @Test
    public void testRemoveReply() {

    }

}
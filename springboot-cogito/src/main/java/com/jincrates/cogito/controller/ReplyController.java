package com.jincrates.cogito.controller;

import com.jincrates.cogito.dto.BoardDTO;
import com.jincrates.cogito.dto.ReplyDTO;
import com.jincrates.cogito.service.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @Log4j2
@RequiredArgsConstructor
@RequestMapping("api/reply") @Api(tags = {"댓글 API"})
public class ReplyController {

    private final ReplyService service;  //자동주입을 위해 final

    @ApiOperation(value = "댓글 목록 조회")
    @GetMapping(value = "/board/{bno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDTO>> getListByBoard(@PathVariable("bno") Long bno) {
        return new ResponseEntity<>(service.getList(bno), HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 등록", response = ReplyDTO.class)
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO replyDTO) {
        Long rno = service.register(replyDTO);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정", response = ReplyDTO.class)
    @PutMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody ReplyDTO replyDTO) {
        service.modify(replyDTO);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제")
    @DeleteMapping(value = "/{rno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        service.remove(rno);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}

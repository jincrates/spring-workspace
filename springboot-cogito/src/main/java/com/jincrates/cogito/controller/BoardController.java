package com.jincrates.cogito.controller;

import com.jincrates.cogito.dto.BoardDTO;
import com.jincrates.cogito.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/board") @Api(tags = {"게시판 API"})
public class BoardController {

    private final BoardService service;  //final

    @ApiOperation(value = "게시글 목록 조회")
    @GetMapping(value = "/user/{email}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<List<BoardDTO>> getListByEmail(@PathVariable("email") String email) {
        return new ResponseEntity<>(service.getAllWithWriter(email), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 등록", response = BoardDTO.class)
    @PostMapping(value = "")
    public ResponseEntity<Long> register(@RequestBody BoardDTO dto) {
        Long bno = service.register(dto);

        return new ResponseEntity<>(bno, HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 조회")
    @GetMapping(value = "/{bno}", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
    public ResponseEntity<BoardDTO> read(@PathVariable("bno") Long bno) {
        return new ResponseEntity<>(service.get(bno), HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 수정", response = BoardDTO.class)
    @PutMapping(value = "/{bno}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> modify(@RequestBody BoardDTO dto) {
        service.modify(dto);

        return new ResponseEntity<>("modified", HttpStatus.OK);
    }

    @ApiOperation(value = "게시글 삭제")
    @DeleteMapping(value = "/{bno}", produces =  MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> remove(@PathVariable("bno") Long bno) {
        service.remove(bno);

        return new ResponseEntity<>("remove", HttpStatus.OK);
    }
}




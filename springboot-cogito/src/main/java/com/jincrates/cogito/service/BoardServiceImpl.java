package com.jincrates.cogito.service;

import com.jincrates.cogito.dto.BoardDTO;
import com.jincrates.cogito.entity.Board;
import com.jincrates.cogito.repository.BoardRepository;
import com.jincrates.cogito.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;  //자동 주입 final
    private final ReplyRepository replyRepository;

    @Transactional
    @Override
    public Long register(BoardDTO dto) {

        Board board = dtoToEntity(dto);

        repository.save(board);

        return board.getBno();
    }

    @Override
    public BoardDTO get(Long bno) {

        Optional<Board> result = repository.getBoardByBno(bno);

        if(result.isPresent()) {
            return entityToDTO(result.get());
        }

        return null;
    }

    @Override
    public void modify(BoardDTO boardDTO) {

        Long bno = boardDTO.getBno();

        Optional<Board> result = repository.findById(bno);

        if(result.isPresent()) {
            Board board = result.get();

            board.changeTitle(boardDTO.getTitle());
            board.changeContent(board.getContent());

            repository.save(board);
        }
    }

    @Override
    public void remove(Long bno) {

        repository.deleteById(bno);
    }

    @Override
    public void removeWithReplies(Long bno) { //삭제 기능 구현, 트랜잭션 추가

        //댓긃부터 삭제
        //replyRepository.deleteByBno(bno);

        repository.deleteById(bno);
    }


    @Override
    public List<BoardDTO> getAllWithWriter(String writerEmail) {

        List<Board> boardList = repository.getList(writerEmail);

        return boardList.stream().map(board -> entityToDTO(board)).collect(Collectors.toList());
    }
}

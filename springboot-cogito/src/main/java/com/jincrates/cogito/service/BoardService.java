package com.jincrates.cogito.service;

import com.jincrates.cogito.dto.BoardDTO;
import com.jincrates.cogito.entity.Board;
import com.jincrates.cogito.entity.Member;

import java.util.List;

public interface BoardService {

    Long register(BoardDTO dto);

    //PageResultDTO<BoardDTO, Object[]> getList(PageRequestDTO pageRequestDTO);

    BoardDTO get(Long bno);

    void modify(BoardDTO dto);

    void remove(Long bno);

    List<BoardDTO> getAllWithWriter(String writerEmail);

    void removeWithReplies(Long bno);

    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board board = Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        return board;
    }

    //BoardService 인터페이스에 추가하는 entityToDTO()
    default BoardDTO entityToDTO(Board board, Member member, Long replyCount) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCount(replyCount.intValue())  //long으로 나오므로 int로 형변환
                .build();

        return boardDTO;
    }

    default BoardDTO entityToDTO(Board board) {

        BoardDTO boardDTO = BoardDTO.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .regDate(board.getRegDate())
                .modDate(board.getModDate())
                .writerEmail(board.getWriter().getEmail())
                .writerName(board.getWriter().getName())
                //.replyCount(board.g)  //long으로 나오므로 int로 형변환
                .build();

        return boardDTO;
    }
}

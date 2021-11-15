package com.jincrates.board.repository;

import com.jincrates.board.entity.Board;
import com.jincrates.board.repository.search.SearchBoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    //한개의 로우(Objec) 내에 Object[ ]로 나옴
    @Query("SELECT b, w FROM Board b LEFT OUTER JOIN b.writer w WHERE b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno);

    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT OUTER JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b " +
            " GROUP BY b"
            , countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);  //목록 화면에 필요한 데이터

    //조회화면에서 필요한 JPQL 구성하기
    @Query("SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT OUTER JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b " +
            " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

}

package com.jincrates.cogito.repository;

import com.jincrates.cogito.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    /*
    @EntityGraph
    - 설명: EntityGraph는 JPA가 어떤 엔티티를 불러올 때 이 엔티티와 관계된 엔티티를 불러올 것인지에 대한 정보를 제공
    FetchType.LAZY 와 FetchType.EAGER로 연관 엔티티를 가져올 것인지를 결정할 수 있음.
    하지만 이 구문은 정적이며 런타임 시 이 설정을 변경하지 못하는 단점이 있음.
    EntityGraph는 이러한 점을 보완하고 연관 엔티티를 어떻게 로딩할 것인지에 대한 정보를 제공함으로서
    엔티티 로딩 속도를 높일 수 있는 장점이 있음
    */
    @EntityGraph(attributePaths = "writer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT b FROM Board b WHERE b.bno = :bno")
    Optional<Board> getWithWriter(@Param("bno") Long bno);

    @EntityGraph(attributePaths = "writer", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT b FROM Board b WHERE b.writer.email = :email")
    List<Board> getList(@Param("email") String email);

    @Query("SELECT b, r FROM Board b LEFT OUTER JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    @Query(value = "SELECT b, w, count(r) "
                 + "FROM Board b "
                 + "LEFT OUTER JOIN b.writer w "
                 + "LEFT OUTER JOIN Reply r ON r.board = b "
                 + "GROUP BY b",
           countQuery = "SELECT count(b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    @Query("SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT OUTER JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b " +
            " WHERE b.bno = :bno")
    Optional<Board> getBoardByBno(@Param("bno") Long bno);
}

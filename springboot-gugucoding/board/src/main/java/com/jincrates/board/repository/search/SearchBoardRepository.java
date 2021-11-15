package com.jincrates.board.repository.search;

import com.jincrates.board.dto.BoardDTO;
import com.jincrates.board.dto.PageRequestDTO;
import com.jincrates.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    Board search1();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);

}

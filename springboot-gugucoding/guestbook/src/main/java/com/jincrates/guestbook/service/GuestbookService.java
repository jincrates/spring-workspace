package com.jincrates.guestbook.service;

import com.jincrates.guestbook.dto.GuestbookDTO;
import com.jincrates.guestbook.dto.PageRequestDTO;
import com.jincrates.guestbook.dto.PageResultDTO;
import com.jincrates.guestbook.entity.Guestbook;

public interface GuestbookService {

    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    void modify(GuestbookDTO dto);

    void remove(Long gno);

    default Guestbook dtoToEntity(GuestbookDTO dto) {

        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity) {

        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}

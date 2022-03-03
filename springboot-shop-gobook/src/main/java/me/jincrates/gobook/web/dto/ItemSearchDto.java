package me.jincrates.gobook.web.dto;

import lombok.Data;
import me.jincrates.gobook.domain.items.ItemSellStatus;

@Data
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}

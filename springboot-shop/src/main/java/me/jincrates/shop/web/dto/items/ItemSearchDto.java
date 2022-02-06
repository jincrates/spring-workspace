package me.jincrates.shop.web.dto.items;

import lombok.Getter;
import lombok.Setter;
import me.jincrates.shop.domain.items.ItemSellStatus;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellStatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}

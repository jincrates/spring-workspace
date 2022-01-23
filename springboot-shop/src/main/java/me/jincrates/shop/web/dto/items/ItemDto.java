package me.jincrates.shop.web.dto.items;

import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.shop.domain.items.Item;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ItemDto {
    private Long id;
    private String itemNm;
    private Integer price;
    private String itemDetail;
    private String sellStatCd;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    public ItemDto(Item entity) {
        //this.id = entity.getId();
        this.itemNm = entity.getItemNm();
        this.price = entity.getPrice();
        this.itemDetail = entity.getItemDetail();
        //this.sellStatCd = entity.getItemSellStatus().toString();
        this.createdDate = LocalDateTime.now(); //entity.getCreatedDate();
        //this.modifiedDate = entity.getModifiedDate();
    }
}

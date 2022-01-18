package me.jincrates.shop.web.dto.item;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.jincrates.shop.domain.items.Item;

import java.time.LocalDateTime;

@Getter
@Setter
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
        this.createdDate = entity.getCreatedDate();
        //this.modifiedDate = entity.getModifiedDate();
    }
}

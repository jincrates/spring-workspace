package me.jincrates.shop.domain.items;

import me.jincrates.shop.web.dto.items.ItemSearchDto;
import me.jincrates.shop.web.dto.items.MainItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}

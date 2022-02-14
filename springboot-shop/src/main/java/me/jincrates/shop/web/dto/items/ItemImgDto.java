package me.jincrates.shop.web.dto.items;

import lombok.Getter;
import lombok.Setter;
import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.domain.items.ItemImg;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;

    private Item item;

    private static ModelMapper modelMapper = new ModelMapper();

    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}

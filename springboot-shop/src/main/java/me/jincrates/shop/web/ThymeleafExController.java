package me.jincrates.shop.web;

import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.web.dto.item.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value="/ex01")
    public String thymeleafExample01(Model model) {
        model.addAttribute("data", "타임리프 예제 입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value="/ex02")
    public String thymeleafExample02(Model model) {
        Item entity = Item.builder()
                .itemDetail("상품 상세 설명")
                .itemNm("테스트 상품1")
                .price(10000)
                .build();
        ItemDto itemDto = new ItemDto(entity);

        model.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value="/ex03")
    public String thymeleafExample03(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Item entity = Item.builder()
                    .itemDetail("상품 상세 설명" + i)
                    .itemNm("테스트 상품" +i)
                    .price(10000 * i)
                    .build();

            ItemDto itemDto = new ItemDto(entity);
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value="/ex04")
    public String thymeleafExample04(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Item entity = Item.builder()
                    .itemDetail("상품 상세 설명" + i)
                    .itemNm("테스트 상품" +i)
                    .price(10000 * i)
                    .build();

            ItemDto itemDto = new ItemDto(entity);
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value="/ex05")
    public String thymeleafExample05(Model model) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            Item entity = Item.builder()
                    .itemDetail("상품 상세 설명" + i)
                    .itemNm("테스트 상품" +i)
                    .price(10000 * i)
                    .build();

            ItemDto itemDto = new ItemDto(entity);
            itemDtoList.add(itemDto);
        }

        model.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx05";
    }
}

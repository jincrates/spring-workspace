package me.jincrates.shop.web;

import me.jincrates.shop.domain.items.Item;
import me.jincrates.shop.web.dto.item.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

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
}

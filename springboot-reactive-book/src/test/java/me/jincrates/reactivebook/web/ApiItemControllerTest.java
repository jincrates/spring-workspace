package me.jincrates.reactivebook.web;

import me.jincrates.reactivebook.domain.items.Item;
import me.jincrates.reactivebook.domain.items.ItemRepository;
import me.jincrates.reactivebook.service.InventoryService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static reactor.core.publisher.Mono.when;

@WebFluxTest(controllers = ApiItemController.class)
class ApiItemControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    InventoryService service;

    @MockBean
    ItemRepository itemRepository;

    @Disabled
    @Test
    void findingAllItem() {
        when(itemRepository.findAll()).thenReturn(Flux.just(new Item("item1", "TV tray", "Alf TV tray", 19.99)));

        this.webTestClient.get().uri("/api/items")
                .exchange()
                .expectStatus().isOk()
                .expectBody();
                //.consumeWith("findAll", preprocessResponse(prettyPrint()))); // <2>
    }
}
package me.jincrates.reactivebook.domain.items;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface ItemRepository extends ReactiveCrudRepository<Item, String> {
}

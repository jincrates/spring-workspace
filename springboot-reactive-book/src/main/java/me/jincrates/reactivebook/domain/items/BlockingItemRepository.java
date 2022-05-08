package me.jincrates.reactivebook.domain.items;

import me.jincrates.reactivebook.domain.items.Item;
import org.springframework.data.repository.CrudRepository;

public interface BlockingItemRepository extends CrudRepository<Item, String> {
}

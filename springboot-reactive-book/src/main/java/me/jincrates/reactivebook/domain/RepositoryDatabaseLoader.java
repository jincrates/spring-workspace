package me.jincrates.reactivebook.domain;

import me.jincrates.reactivebook.domain.items.BlockingItemRepository;
import me.jincrates.reactivebook.domain.items.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RepositoryDatabaseLoader {

    @Bean
    CommandLineRunner initialize(BlockingItemRepository repository) {
        return args -> {
            repository.save(new Item("Alf alarm clock", 19.99));
            repository.save(new Item("Smurf TV tray", 24.99));
        };
    }
}

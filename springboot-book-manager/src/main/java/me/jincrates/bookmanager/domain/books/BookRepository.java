package me.jincrates.bookmanager.domain.books;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String title);

    List<Book> findByTitleOrAuthor(String title, String author);

    List<Book> findByStockNumberLessThan(Integer stock);

    List<Book> findByStockNumberLessThanOrderByStockNumberDesc(Integer stock);
}


package me.jincrates.bookmanager.domain.books;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long>,
        QuerydslPredicateExecutor<Book> {

    List<Book> findByTitle(String title);

    List<Book> findByTitleOrAuthor(String title, String author);

    List<Book> findByTitleOrAuthorOrPublisher(String title, String author, String publisher);

    List<Book> findByStockNumberLessThan(Integer stock);

    List<Book> findByStockNumberLessThanOrderByStockNumberDesc(Integer stock);

    @Query("select b from Book b where b.publisher like %:publisher% order by b.id desc")
    List<Book> findByPublisher(@Param("publisher") String publisher);
}


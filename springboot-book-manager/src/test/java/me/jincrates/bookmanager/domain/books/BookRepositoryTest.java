package me.jincrates.bookmanager.domain.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    @DisplayName("도서 저장 테스트")
    public void createBookTest() {
        Book book = Book.builder()
                .title("철학적 탐구")
                .author("루트비히 비트겐슈타인")
                .publisher("책세상")
                .publicationDate("2019-04-05")
                .isbn("9791159313554")
                .stockNumber(10)
                .imagePath("")
                .build();
        Book savedBook = bookRepository.save(book);
        System.out.println(savedBook);
    }
}
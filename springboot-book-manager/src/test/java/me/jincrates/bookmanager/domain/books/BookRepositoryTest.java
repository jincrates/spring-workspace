package me.jincrates.bookmanager.domain.books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    public void createBookList() {
        IntStream.rangeClosed(1, 10).mapToObj(i -> Book.builder()
                .title("철학적 탐구" + i)
                .author("루트비히 비트겐슈타인")
                .publisher("책세상")
                .publicationDate("2019-04-05")
                .isbn("9791159313554")
                .stockNumber(10 + i)
                .imagePath("")
                .build()).forEach(book -> bookRepository.save(book));
    }

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

        assertNotNull(savedBook);
        assertEquals(book.getTitle(), savedBook.getTitle());
        assertEquals(book.getAuthor(), savedBook.getAuthor());
        assertEquals(book.getPublisher(), savedBook.getPublisher());
        assertEquals(book.getPublicationDate(), savedBook.getPublicationDate());
        assertEquals(book.getIsbn(), savedBook.getIsbn());
        assertEquals(book.getStockNumber(), savedBook.getStockNumber());
        assertEquals(book.getImagePath(), savedBook.getImagePath());
    }

    @Test
    @DisplayName("도서명 조회 테스트")
    public void findByTitle() {
        this.createBookList();
        List<Book> bookList = bookRepository.findByTitle("철학적 탐구2");

        String resultTitle = "";
        for (Book book : bookList) {
            //System.out.println(book.toString());
            resultTitle = book.getTitle();
        }

        assertNotNull(bookList);
        assertEquals("철학적 탐구2", resultTitle);
    }
}
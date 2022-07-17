package me.jincrates.bookmanager.domain.books;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.config.location=classpath:application-dev.yml"})
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @PersistenceContext
    EntityManager em;

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
    public void findByTitleTest() {
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

    @Test
    @DisplayName("도서명 or 저자 조회 테스트")
    public void findByTitleOrAuthorTest() {
        this.createBookList();
        List<Book> bookList = bookRepository.findByTitleOrAuthor("철학적 탐구2", "루트비히 비트겐슈타인5");

        print(bookList);

        assertNotNull(bookList);
        assertEquals(2, bookList.size());
    }

    @Test
    @DisplayName("재고 LessThan 테스트")
    public void findByStockNumberLessThanTest() {
        this.createBookList();
        List<Book> bookList = bookRepository.findByStockNumberLessThan(15);
        print(bookList);

        assertNotNull(bookList);
        assertEquals(4, bookList.size());
    }

    @Test
    @DisplayName("재고 LessThan + 내림차순 조회 테스트")
    public void findByStockNumberLessThanOrderByStockNumberDescTest() {
        this.createBookList();
        List<Book> bookList = bookRepository.findByStockNumberLessThanOrderByStockNumberDesc(15);
        print(bookList);

        assertNotNull(bookList);
        assertEquals(4, bookList.size());
        assertEquals(4, bookList.get(0).getId());
    }

    @Test
    @DisplayName("@Query를 이용한 출판사 조회 테스트")
    public void findByPublisherTest() {
        this.createBookList();
        List<Book> bookList = bookRepository.findByPublisher("책세상");
        print(bookList);

        assertNotNull(bookList);
        assertEquals(10, bookList.size());
    }

    @Test
    @DisplayName("QueryDsl 조회 테스트1")
    public void queryDslTest1() {
        this.createBookList();
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QBook qBook = QBook.book;
        JPAQuery<Book> query = queryFactory.selectFrom(qBook)
                .where(qBook.isbn.eq("9791159313554"))
                .where(qBook.title.like("%" + "철학적 탐구" + "%"))
                .orderBy(qBook.stockNumber.desc());
        List<Book> bookList = query.fetch();
        print(bookList);

        assertNotNull(bookList);
        assertEquals(10, bookList.size());
    }

    @Test
    @DisplayName("QueryDsl 조회 테스트2")
    public void queryDslTest2() {
        this.createBookList2();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QBook qbook = QBook.book;

        String title = "말과 사물";
        String author = "미셸 푸코";
        int stockNumber = 20;

        booleanBuilder.and(qbook.title.like("%" + title + "%"));
        booleanBuilder.and(qbook.author.like("%" + author + "%"));
        booleanBuilder.and(qbook.stockNumber.gt(stockNumber));

        Pageable pageable = PageRequest.of(0, 5);
        Page<Book> bookPagingResult = bookRepository.findAll(booleanBuilder, pageable);
        System.out.println("total elements : " + bookPagingResult.getTotalElements());

        List<Book> resultBookList = bookPagingResult.getContent();
        print(resultBookList);
    }

    public void createBookList() {
        IntStream.rangeClosed(1, 5).mapToObj(i -> Book.builder()
                .title("철학적 탐구" + i)
                .author("루트비히 비트겐슈타인" + i)
                .publisher("책세상" + i)
                .publicationDate("2019-04-05")
                .isbn("9791159313554")
                .stockNumber(10 + i)
                .imagePath("")
                .build()).forEach(book -> bookRepository.save(book));
    }

    public void createBookList2() {
        IntStream.rangeClosed(6, 10).mapToObj(i -> Book.builder()
                .title("철학적 탐구" + i)
                .author("루트비히 비트겐슈타인" + i)
                .publisher("책세상" + i)
                .publicationDate("2019-04-05")
                .isbn("9791159313554")
                .stockNumber(10 + i)
                .imagePath("")
                .build()).forEach(book -> bookRepository.save(book));

        IntStream.rangeClosed(11, 15).mapToObj(i -> Book.builder()
                .title("말과 사물" + i)
                .author("미셸 푸코" + i)
                .publisher("민음사" + i)
                .publicationDate("2012-04-29")
                .isbn("9788937484414")
                .stockNumber(20 + i)
                .imagePath("")
                .build()).forEach(book -> bookRepository.save(book));

        IntStream.rangeClosed(21, 30).mapToObj(i -> Book.builder()
                .title("방법서설" + i)
                .author("르네 데카르트" + i)
                .publisher("문예출판사" + i)
                .publicationDate("2022-05-30")
                .isbn("9788931022759")
                .stockNumber(30 + i)
                .imagePath("")
                .build()).forEach(book -> bookRepository.save(book));
    }

    private void print(List<Book> bookList) {
        for (Book book : bookList) {
            System.out.println(book.toString());
        }
    }
}
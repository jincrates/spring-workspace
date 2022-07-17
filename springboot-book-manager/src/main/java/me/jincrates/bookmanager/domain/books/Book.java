package me.jincrates.bookmanager.domain.books;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter @ToString
@NoArgsConstructor
@Table(name = "book")
@Entity
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 50)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column(nullable = false)
    private String publicationDate;

    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private int stockNumber;

    private String imagePath;

    @Builder
    public Book(Long id, String title, String author, String publisher, String publicationDate, String isbn, int stockNumber, String imagePath) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.isbn = isbn;
        this.stockNumber = stockNumber;
        this.imagePath = imagePath;
    }
}



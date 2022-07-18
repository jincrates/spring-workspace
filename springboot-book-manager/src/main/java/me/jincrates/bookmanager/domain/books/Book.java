package me.jincrates.bookmanager.domain.books;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

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

    @JsonProperty("publication_date")
    @Pattern(regexp = "^([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))$", message = "yyyy-MM-dd 형식으로 입력해야 합니다.")
    @Column(nullable = false)
    private String publicationDate;

    @Column(nullable = false)
    private String isbn;
    @JsonProperty("stock_number")
    @Column(nullable = false)
    private int stockNumber;

    @JsonProperty("image_path")
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



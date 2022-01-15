package me.jincrates.blog.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.blog.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500, nullable = false)
    private String category;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(length = 500, nullable = false)
    private String subtitle;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String category, String title, String subtitle, String content, String author) {
        this.category = category;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.author = author;
    }

    public void update(String category, String title, String subtitle, String content) {
        this.category = category;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
    }
}

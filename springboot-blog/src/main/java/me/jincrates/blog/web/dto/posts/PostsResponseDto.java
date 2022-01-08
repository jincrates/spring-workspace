package me.jincrates.blog.web.dto.posts;

import lombok.Getter;
import me.jincrates.blog.domain.posts.Posts;

import java.time.format.DateTimeFormatter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String subtitle;
    private String content;
    private String author;
    private String modifiedDate;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.subtitle = entity.getSubtitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh:mm"));
    }
}

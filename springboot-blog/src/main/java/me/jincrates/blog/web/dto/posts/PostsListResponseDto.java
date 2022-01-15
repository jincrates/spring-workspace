package me.jincrates.blog.web.dto.posts;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import me.jincrates.blog.domain.posts.Posts;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

@Getter
public class PostsListResponseDto {

    private Long id;
    private String category;
    private String title;
    private String subtitle;
    private String content;
    private String author;
    private String modifiedDate;

    public PostsListResponseDto(Posts entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.title = entity.getTitle();
        this.subtitle = entity.getSubtitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 hh:mm"));
        //this.modifiedDate = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(entity.getModifiedDate());
    }
}

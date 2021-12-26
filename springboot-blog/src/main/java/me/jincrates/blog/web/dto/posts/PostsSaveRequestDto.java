package me.jincrates.blog.web.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jincrates.blog.domain.posts.Posts;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String subtitle;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String subtitle, String content, String author) {
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .subtitle(subtitle)
                .content(content)
                .author(author)
                .build();
    }
}

package me.jincrates.blog.web.dto.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsUpdateRequestDto {

    private String category;
    private String title;
    private String subtitle;
    private String content;

    @Builder
    public PostsUpdateRequestDto(String category, String title, String subtitle, String content) {
        this.category = category;
        this.title = title;
        this.subtitle = subtitle;
        this.content = content;
    }
}

package me.jincrates.blog.service.posts;

import lombok.RequiredArgsConstructor;
import me.jincrates.blog.domain.posts.PostsRepository;
import me.jincrates.blog.web.dto.posts.PostsSaveRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}

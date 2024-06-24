package com.anant.social.dto;

import com.anant.social.models.Post;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String username;
    private Set<LikeDto> likes;
    private int likeCount;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
        this.likes = post.getLikes().stream()
                .map(LikeDto::new)
                .collect(Collectors.toSet());
        this.likeCount = post.getLikes().size();
    }
}


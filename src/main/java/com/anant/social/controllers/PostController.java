package com.anant.social.controllers;

import com.anant.social.dto.PostRequestDto;
import com.anant.social.dto.PostResponseDto;
import com.anant.social.models.Post;
import com.anant.social.services.LikeService;
import com.anant.social.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private LikeService likeService;

    @PostMapping
    public ResponseEntity<PostRequestDto> createPost(@RequestBody PostRequestDto post) {
        return ResponseEntity.ok(postService.createPost(post));
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList()));
    }

    @PostMapping("{postId}/like")
    public void likePost(@PathVariable Long postId){
        likeService.likePost(postId);
    }

    @PostMapping("{postId}/unlike")
    public void unLikePost(@PathVariable Long postId){
        likeService.unlikePost(postId);
    }

}
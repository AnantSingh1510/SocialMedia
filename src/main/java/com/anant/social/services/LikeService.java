package com.anant.social.services;

import com.anant.social.models.Like;
import com.anant.social.models.Post;
import com.anant.social.models.User;
import com.anant.social.repositories.LikeRepository;
import com.anant.social.repositories.PostRepository;
import com.anant.social.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void likePost(Long postId) {
        String username = userService.getCurrentUsername();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userService.findByUsername(username);

        if (!likeRepository.existsByPostAndUser(post, user)) {
            Like like = new Like();
            like.setPost(post);
            like.setUser(user);
            likeRepository.save(like);
        }
    }

    @Transactional
    public void unlikePost(Long postId) {
        String username = userService.getCurrentUsername();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userService.findByUsername(username);

        likeRepository.deleteByPostAndUser(post, user);
    }
}

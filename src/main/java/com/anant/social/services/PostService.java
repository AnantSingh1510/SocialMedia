package com.anant.social.services;

import com.anant.social.dto.PostRequestDto;
import com.anant.social.models.Post;
import com.anant.social.models.User;
import com.anant.social.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public PostRequestDto createPost(PostRequestDto postRequestDto){
        User user = userService.findByUsername(userService.getCurrentUsername());
        Post post = Post
                .builder()
                .createdAt(LocalDateTime.now())
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .user(user)
                .Author(user.getFirstName())
                .build();

        post = postRepository.save(post);
        return postRequestDto;
    }

//    private String getCurrentUsername() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null && authentication.isAuthenticated()) {
//            Object principal = authentication.getPrincipal();
//            if (principal instanceof UserDetails) {
//                return ((UserDetails) principal).getUsername();
//            } else {
//                return principal.toString();
//            }
//        }
//        return null;
//    }

    public List<Post> getAllPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc();
    }
}

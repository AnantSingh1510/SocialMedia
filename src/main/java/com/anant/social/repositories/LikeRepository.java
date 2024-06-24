package com.anant.social.repositories;

import com.anant.social.models.Like;
import com.anant.social.models.Post;
import com.anant.social.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPostAndUser(Post post, User user);
    void deleteByPostAndUser(Post post, User user);
}

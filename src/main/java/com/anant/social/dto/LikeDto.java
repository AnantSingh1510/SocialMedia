package com.anant.social.dto;

import com.anant.social.models.Like;
import lombok.Data;

@Data
class LikeDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;

    public LikeDto(Like like) {
        this.id = like.getId();
        this.username = like.getUser().getUsername();
        this.firstName = like.getUser().getFirstName();
        this.lastName = like.getUser().getLastName();
    }
}
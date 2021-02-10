package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostUserFacebookRes {
    private final Long userNo;
    private final Integer role;
    private final String nickName;
    private final String profileImage;
    private final String jwt;
}
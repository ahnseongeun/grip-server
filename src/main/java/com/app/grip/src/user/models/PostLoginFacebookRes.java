package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginFacebookRes {
    private final Boolean isMember;
    private final Long userNo;
    private final String jwt;
}
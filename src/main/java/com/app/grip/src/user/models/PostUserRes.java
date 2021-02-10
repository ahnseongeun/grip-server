package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostUserRes {
    private final Long userNo;
    private final String jwt;
    private final String response;
}

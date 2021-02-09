package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostLoginRes {
    private final String status;
    private final Long userNo;
    private final String jwt;
}

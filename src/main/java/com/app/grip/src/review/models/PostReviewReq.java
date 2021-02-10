package com.app.grip.src.review.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostReviewReq {
    private Integer star;
    private String content;
    private List<PictureReq> picture;
}

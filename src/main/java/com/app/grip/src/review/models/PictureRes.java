package com.app.grip.src.review.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PictureRes {
    private final Long pictureId;
    private final String pictureURL;
    private final String status;
}
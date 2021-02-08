package com.app.grip.src.vidioCategory.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetVideoCategory {

    private final Long id;
    private final String title;
    private final String imageURL;

}

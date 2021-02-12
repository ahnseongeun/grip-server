package com.app.grip.src.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostVideoAndThumbNail {

    private final String VideoURL;
    private final String thumbNailURL;

}

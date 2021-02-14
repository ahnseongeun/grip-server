package com.app.grip.src.video.videoLike.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostVideoLike {

    private final Long videoId;
    private final Integer LikeCount;

}

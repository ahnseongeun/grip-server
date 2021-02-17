package com.app.grip.src.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PatchVideo {

    private final Long videoId;
    private final Integer videoLikeCount;
    private final String endLiveStatus;
    private final LocalDateTime EndLiveTime;
}

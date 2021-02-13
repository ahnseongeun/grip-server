package com.app.grip.src.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PatchVideo {

    private final Long videoId;
    private final String endLiveStatus;
}

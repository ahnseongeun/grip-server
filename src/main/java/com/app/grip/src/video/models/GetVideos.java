package com.app.grip.src.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetVideos {

    private final Long videoId;
    private final String title;
    private final String liveCheck;
    private final String startLiveTime;
    private final String endLiveStatus;
    private final String videoURL;
    private final String thumbnailURL;
    private final Integer watchUserCount;
    private final Long categoryId;
    private final Long hostNo;

}

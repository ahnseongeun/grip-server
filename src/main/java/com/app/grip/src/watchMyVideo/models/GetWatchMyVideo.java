package com.app.grip.src.watchMyVideo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetWatchMyVideo {
    private final Long videoId;
    private final String LiveCheck;
    private final String thumbNailURL;
    private final String title;
    private final Integer watchVideoCount;
    private final Integer videoLike;
    private final String endLiveTime;
    private final String hostName;
    private final String hostProfileImageURL;
}

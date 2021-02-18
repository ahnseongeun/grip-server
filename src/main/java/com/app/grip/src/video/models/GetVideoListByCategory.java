package com.app.grip.src.video.models;

import com.app.grip.src.product.models.GetProductInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class GetVideoListByCategory {
    
    private final Long videoId;
    private final String title;
    private final String liveCheck;
    private final String startLiveTime;
    private final String thumbnailURL;
    private final String videoURL;
    private final Integer watchUserCount;
    private final String hostName;
    private final GetProductInfo productInfo;

}

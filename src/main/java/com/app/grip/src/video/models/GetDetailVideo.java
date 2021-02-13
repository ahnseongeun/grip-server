package com.app.grip.src.video.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetDetailVideo {

    private final String title;
    private final String hostName;
    private final String hostImageURL;
    private final String liveCheck;
    private final Integer watchUserCount;
    private final Integer videoLikeCount;
    private final Long storeId;
    private final Integer CouponCount;
}

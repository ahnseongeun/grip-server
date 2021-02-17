package com.app.grip.src.video.models;

import com.app.grip.src.product.models.GetProductInfo;
import com.app.grip.src.product.models.GetProductRes;
import com.app.grip.src.product.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetDetailVideo {

    private final String title;
    private final String hostName;
    private final String hostImageURL;
    private final String liveCheck;
    private final Integer startTime;
    private final Integer watchUserCount;
    private final Integer videoLikeCount;
    private final Long storeId;
    private final Integer productCount;
    private final Integer couponCount;
}

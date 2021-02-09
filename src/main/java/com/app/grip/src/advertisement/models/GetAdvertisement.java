package com.app.grip.src.advertisement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class GetAdvertisement {
    private final Long advertisementId;
    private final String description;
    private final String pictureURL;
}

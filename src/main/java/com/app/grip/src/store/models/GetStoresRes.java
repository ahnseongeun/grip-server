package com.app.grip.src.store.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetStoresRes {
    private final Long storeId;
    private final String name;
    private final String introduction;
    private final String pictureURL;
    private final Long userNo;
    private final String createDate;
    private final String status;
}
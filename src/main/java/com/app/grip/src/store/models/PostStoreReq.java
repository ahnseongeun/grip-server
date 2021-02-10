package com.app.grip.src.store.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostStoreReq {
    private String name;
    private String introduction;
    private String pictureURL;
}
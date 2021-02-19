package com.app.grip.src.product.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetProductInfo {

    private final Integer productPrice;
    private final String productContent;
    private final String productImageURL;

}

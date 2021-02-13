package com.app.grip.src.product.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetProductCategoryRes {
    private final String name;
    private final String status;
}
package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PatchUserRes {
    private final Long userNo;
}

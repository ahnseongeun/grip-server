package com.app.grip.src.user.models;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@Getter
@Builder
public class PatchUserReq {

    private final String nickname;
    private final String phoneNumber;
    private final String birthday;
    private final String imageDelete = "N";
}

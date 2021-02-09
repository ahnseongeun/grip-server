package com.app.grip.src.user.models;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostUserReq {
    private String nickname;
    private String phoneNumber;
    private String gender;
    private String birthday;

    // TODO 페이스북 api에서 확인 가능한지 확인하기
    private String name;
    private String mail;
    private String profileImage;
}
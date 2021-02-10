package com.app.grip.src.user.models;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
public class PostUserFacebookReq {
    private String userId;
    private String nickname;
    private String phoneNumber;
    private String gender;
    private String birthday;
    private String name;
    private String email;
    private String profileImage;
}
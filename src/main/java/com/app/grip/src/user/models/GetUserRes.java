package com.app.grip.src.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
public class GetUserRes {
    private final Long userNo;
    private final String name;
    private final String nickname;
    private final String profileImageURL;
    private final String birthday;
    private final String email;
    private final String phoneNumber;
    private final String gender;
}
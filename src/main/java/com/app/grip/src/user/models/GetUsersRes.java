package com.app.grip.src.user.models;

import lombok.*;

@Getter
@AllArgsConstructor
public class GetUsersRes {
    private final int userId;
    private final String email;
}

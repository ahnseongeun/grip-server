package com.app.grip.src.chatting.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetChattingRoomsRes {
    private final Long chattingRoomId;
    private final String status;
}
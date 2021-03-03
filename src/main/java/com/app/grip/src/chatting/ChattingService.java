package com.app.grip.src.chatting;

import com.app.grip.config.BaseException;
import com.app.grip.src.chatting.models.ChattingRoom;
import com.app.grip.src.chatting.models.PostChattingRoomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ChattingService {
    private final ChattingRoomRepository chattingRoomRepository;

    /**
     * 채팅방 생성
     * @return PostChattingRoomRes
     * @throws BaseException
     * @Auther shine
     */
    public PostChattingRoomRes createChattingRoom() throws BaseException {
        ChattingRoom newChattingRoom = new ChattingRoom();

        try {
            newChattingRoom = chattingRoomRepository.save(newChattingRoom);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_CHATTINGROOM);
        }

        return new PostChattingRoomRes(newChattingRoom.getId());
    }

}
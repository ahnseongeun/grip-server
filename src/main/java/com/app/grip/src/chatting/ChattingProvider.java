package com.app.grip.src.chatting;

import com.app.grip.config.BaseException;
import com.app.grip.src.chatting.models.ChattingRoom;
import com.app.grip.src.chatting.models.GetChattingRoomRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ChattingProvider {
    private final ChattingRoomRepository chattingRoomRepository;

    /**
     * 채팅방 전체조회
     * @return List<GetChattingRoomRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetChattingRoomRes> retrieveChattingRoomsByStatusY() throws BaseException {
        List<ChattingRoom> chattingRoomList;

        try {
            chattingRoomList = chattingRoomRepository.findByStatusOrderByIdDesc("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_CHATTINGROOM);
        }

        return chattingRoomList.stream().map(chattingRoom -> {
            return new GetChattingRoomRes(chattingRoom.getId());
        }).collect(Collectors.toList());
    }

    /**
     * 채팅방 조회
     * @return GetChattingRoomRes
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public GetChattingRoomRes retrieveChattingRoomByStatusY(Long chattingRoomId) throws BaseException {
        ChattingRoom chattingRoom;

        try {
            chattingRoom = chattingRoomRepository.findByStatusAndId("Y", chattingRoomId).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_CHATTINGROOM);
        }

        return new GetChattingRoomRes(chattingRoom.getId());
    }
}
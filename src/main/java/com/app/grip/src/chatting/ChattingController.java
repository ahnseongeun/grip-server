package com.app.grip.src.chatting;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.chatting.models.GetChattingRoomRes;
import com.app.grip.src.chatting.models.PostChattingRoomRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ChattingController {
    private final ChattingProvider chattingProvider;
    private final ChattingService chattingService;

    /**
     * 채팅방 전체조회 API
     * [GET] /api/chatting-rooms
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "채팅방 전체조회(클라용)", notes = "채팅방 전체조회(클라용)")
    @ResponseBody
    @GetMapping("/chatting-rooms")
    public BaseResponse<List<GetChattingRoomRes>> getChattingRooms() {
        try {
            List<GetChattingRoomRes> chattingRoomList = chattingProvider.retrieveChattingRoomsByStatusY();
            return new BaseResponse<>(SUCCESS, chattingRoomList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 채팅방 조회 API
     * [GET] /api/chatting-rooms/:chattingRoomId
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "채팅방 전체조회(클라용)", notes = "채팅방 전체조회(클라용)")
    @ResponseBody
    @GetMapping("/chatting-rooms/{chattingRoomId}")
    public BaseResponse<GetChattingRoomRes> getChattingRoom(@PathVariable Long chattingRoomId) {
        try {
            GetChattingRoomRes chattingRoom = chattingProvider.retrieveChattingRoomByStatusY(chattingRoomId);
            return new BaseResponse<>(SUCCESS, chattingRoom);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 채팅방 등록 API
     * [POST] /api/chatting-rooms
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "채팅방 등록", notes = "채팅방 등록")
    @ResponseBody
    @PostMapping("/chatting-rooms")
    public BaseResponse<PostChattingRoomRes> postChattingRoom() {
        try {
            PostChattingRoomRes chattingRoom = chattingService.createChattingRoom();
            return new BaseResponse<>(SUCCESS, chattingRoom);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }
}
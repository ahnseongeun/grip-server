package com.app.grip.src.chatting;

import com.app.grip.src.chatting.models.ChattingRoom;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChattingRoomRepository extends CrudRepository<ChattingRoom, Long> {
    List<ChattingRoom> findByOrderByIdDesc();
    List<ChattingRoom> findByStatusOrderByIdDesc(String status);
    List<ChattingRoom> findByStatusAndId(String status, Long id);
}
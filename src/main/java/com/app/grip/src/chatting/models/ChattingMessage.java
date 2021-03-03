package com.app.grip.src.chatting.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.user.models.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "chattingMessage")
public class ChattingMessage extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content",nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userNo",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "chattingRoomId",nullable = false)
    private ChattingRoom chattingRoom;
}
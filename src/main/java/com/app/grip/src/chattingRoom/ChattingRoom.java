package com.app.grip.src.chattingRoom;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.user.models.User;
import com.app.grip.src.videos.video.Video;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Data // from lombok
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "chattingRoom") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class ChattingRoom extends BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "videoId",nullable = false)
    private Video video;

}

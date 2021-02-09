package com.app.grip.src.video;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.vidioCategory.VideoCategory;
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
@Table(name = "video") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class Video extends BaseEntity {

    /**
     * 영상 id
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 영상 제목
     */
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    /**
     * 생방송 여부
     */
    @Column(name = "liveCheck", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String liveCheck;

    /**
     * 생방송 시작 시간
     */
    @Column(name = "startLiveTime", nullable = false, length = 100)
    private String startLiveTime;

    /**
     * 생방송 종료 시간
     */
    @Column(name = "endLiveTime", nullable = false, length = 100)
    private String endLiveTime;

    /**
     * 비디오 경로
     */
    @Column(name = "videoURL", nullable = false, columnDefinition = "TEXT")
    private String videoURL;

    /**
     * 섬네일 경로
     */
    @Column(name = "thumbnailURL", nullable = false, columnDefinition = "TEXT")
    private String thumbnailURL;

    /**
     * 조회수
     */
    @Column(name = "videoWatchUserCount", nullable = false, columnDefinition = "integer default 0")
    private Integer videoWatchUserCount;

    /**
     * 카테고리 id
     */
    @ManyToOne
    @JoinColumn(name = "videoCategoryId",nullable = false)
    private VideoCategory videoCategory;

    /**
     * 그래퍼 id
     */
//    @ManyToOne
//    @JoinColumn(name = "userNo",nullable = false)
//    private User user;
}

package com.app.grip.src.vidioCategory;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.video.VideoInfo;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC) // Unit Test 를 위해 PUBLIC
@EqualsAndHashCode(callSuper = false)
@Data // from lombok
@Entity // 필수, Class 를 Database Table화 해주는 것이다
@Table(name = "videoCategoryInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class VideoCategoryInfo extends BaseEntity {

    /**
     * 카테고리 id
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 카테고리 이름
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * 카테고리 이미지 경로
     */
    @Column(name = "pictureURL", nullable = false, columnDefinition = "TEXT")
    private String pictureURL;

    /**
     * 영상 리스트
     */
    @OneToMany(mappedBy = "videoCategoryInfo",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<VideoInfo> videoInfoList;

    @Builder
    public VideoCategoryInfo(Long id, String name, String pictureURL) {
        this.id = id;
        this.name = name;
        this.pictureURL = pictureURL;
    }


}

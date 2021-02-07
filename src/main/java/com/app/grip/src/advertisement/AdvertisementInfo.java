package com.app.grip.src.advertisement;

import com.app.grip.config.BaseEntity;
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
@Table(name = "advertisementInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class AdvertisementInfo extends BaseEntity {

    /**
     * 광고 ID
     */
    @Id // PK를 의미하는 어노테이션
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 사진 경로
     */
    @Column(name = "pictureURL", nullable = false, columnDefinition = "TEXT")
    private String pictureURL;

    /**
     * 설명
     */
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

}

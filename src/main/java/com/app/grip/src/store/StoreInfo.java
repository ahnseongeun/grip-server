package com.app.grip.src.store;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.follower.FollowingInfo;
import com.app.grip.src.following.FollowerInfo;
import com.app.grip.src.review.ReviewInfo;
import com.app.grip.src.review.ReviewPictureInfo;
import com.app.grip.src.user.models.UserInfo;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "Store")
public class StoreInfo extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "introduction", nullable = false, columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "pictureURL", columnDefinition = "TEXT")
    private String pictureURL;

    @OneToOne
    @JoinColumn(name = "userNo", referencedColumnName = "no")
    private UserInfo userNo;

    @OneToMany(mappedBy = "storeInfo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FollowingInfo> followingInfoList;

    @OneToMany(mappedBy = "storeInfo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<FollowerInfo> followerInfoList;

    @OneToMany(mappedBy = "storeInfo", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReviewInfo> reviewInfoList;

    public StoreInfo(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }
}
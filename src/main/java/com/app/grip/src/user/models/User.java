package com.app.grip.src.user.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.chattingMessage.models.ChattingMessage;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.video.videoLike.models.VideoLike;
import com.app.grip.src.video.videoParticipant.models.VideoParticipant;
import com.app.grip.src.watchMyVideo.models.WatchMyVideo;
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
@Table(name = "user")
public class User extends BaseEntity {
    /**
     * 유저 ID
     */
    @Id
    @Column(name = "no", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    /**
     * 이름
     */
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    /**
     * 닉네임
     */
    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    /**
     * 프로필사진
     */
    @Column(name = "profileImageURL", columnDefinition = "TEXT")
    private String profileImageURL;


    /**
     * 핸드폰 번호
     */
    @Column(name = "phoneNumber", length = 13)
    private String phoneNumber;


    /**
     * 생년월일
     */
    @Column(name = "birthday", length = 10)
    private String birthday;

    /**
     * 이메일
     */
    @Column(name = "email", length = 100)
    private String email;


    /**
     * 성별
     */
    @Column(name = "gender", length = 1)
    private String gender;

    /**
     * SNS 구분
     */
    @Column(name = "snsDiv", nullable = false, length = 1)
    private String snsDiv;

    /**
     * 역할
     */
    @Column(name = "role", nullable = false, columnDefinition = "integer default 1")
    private Integer role;

    /**
     * 이미지 상태
     */
    @Column(name = "image_status", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String imageStatus;

    /**
     * 발급받은 userId
     */
    @Column(name = "id", length = 100)
    private String id;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Coupon> couponList;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Video> videoList;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VideoParticipant> videoParticipantList ;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<VideoLike> videoLikeList;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<WatchMyVideo> watchMyVideoList;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ChattingMessage> chattingMessageList;

    public User(String name, String nickname,String profileImageURL ,String phoneNumber,
                String birthday,String email,String gender,String snsDiv,Integer role,String imageStatus,
                String id) {
        this.name = name;
        this.nickname = nickname;
        this.profileImageURL = profileImageURL;
        this.phoneNumber = phoneNumber;
        this.birthday = birthday;
        this.email = email;
        this.gender = gender;
        this.snsDiv = snsDiv;
        this.role = role;
        this.imageStatus = imageStatus;
        this.id = id;
    }
}
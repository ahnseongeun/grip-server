package com.app.grip.src.user.models;

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
@Table(name = "UserInfo") // Table 이름을 명시해주지 않으면 class 이름을 Table 이름으로 대체한다.
public class UserInfo extends BaseEntity {
    /**
     * 유저 ID
     */
    @Id // PK를 의미하는 어노테이션
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
    @Column(name = "profileImageURL", nullable = false, columnDefinition = "TEXT")
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
    @Column(name = "gender", nullable = false, length = 1)
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

//    /**
//     * 상태
//     */
//    @Column(name = "status", nullable = false, columnDefinition = "varchar(1) default 'Y'")
//    private String Status;

    /**
     * 이미지 상태
     */
    @Column(name = "image_status", nullable = false, columnDefinition = "varchar(1) default 'N'")
    private String imageStatus;

    /**
     * 발급받은 userId
     */
    @Column(name = "id",length = 100)
    private String id;


    public UserInfo(String email, String nickname, String phoneNumber) {
        this.email = email;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}

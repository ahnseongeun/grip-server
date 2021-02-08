//package com.app.grip.src.review;
//
//import com.app.grip.config.BaseEntity;
//import com.app.grip.src.store.StoreInfo;
//import com.app.grip.src.user.models.UserInfo;
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Accessors(chain = true)
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PUBLIC)
//@EqualsAndHashCode(callSuper = false)
//@Data
//@Entity
//@Table(name = "reviewInfo")
//public class ReviewInfo extends BaseEntity {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "store_id")
//    private StoreInfo storeInfo;
//
//    @ManyToOne
//    @JoinColumn(name = "user_no")
//    private UserInfo userInfo;
//
//    @Column(name = "star", nullable = false)
//    private Integer star;
//
//    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
//    private String content;
//
//    @OneToMany(mappedBy = "reviewInfo", orphanRemoval = true, cascade = CascadeType.ALL)
//    private List<ReviewPictureInfo> reviewPictureInfoList;
//
//    public ReviewInfo(Integer star, String content) {
//        this.star = star;
//        this.content = content;
//    }
//}

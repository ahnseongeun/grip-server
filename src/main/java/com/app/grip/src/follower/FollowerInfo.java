//package com.app.grip.src.following;
//
//import com.app.grip.config.BaseEntity;
//import com.app.grip.src.store.StoreInfo;
//import com.app.grip.src.user.models.UserInfo;
//import lombok.*;
//import lombok.experimental.Accessors;
//
//import javax.persistence.*;
//
//@Accessors(chain = true)
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor(access =  AccessLevel.PUBLIC)
//@EqualsAndHashCode(callSuper = true)
//@Data
//@Entity
//@Table(name = "following")
//public class FollowerInfo extends BaseEntity {
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
//    @JoinColumn(name = "store_no")
//    private UserInfo userInfo;
//}

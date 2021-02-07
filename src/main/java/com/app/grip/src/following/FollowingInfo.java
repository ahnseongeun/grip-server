package com.app.grip.src.follower;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.store.StoreInfo;
import com.app.grip.src.user.models.UserInfo;
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
@Table(name = "Following")
public class FollowingInfo extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private StoreInfo storeInfo;

    @ManyToOne
    @JoinColumn(name = "no")
    private UserInfo userInfo;
}

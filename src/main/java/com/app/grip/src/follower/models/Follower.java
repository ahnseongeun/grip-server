package com.app.grip.src.follower.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.models.User;
import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access =  AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "follower")
public class Follower extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
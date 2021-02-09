package com.app.grip.src.store.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.follower.models.Follower;
import com.app.grip.src.following.models.Following;
import com.app.grip.src.product.models.Product;
import com.app.grip.src.review.models.Review;
import com.app.grip.src.user.models.User;
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
@Table(name = "store")
public class Store extends BaseEntity {
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
    private User userNo;

    @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Following> followingList;

    @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Follower> followerList;

    @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Review> reviewList;

    @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> productList;

    public Store(String name, String introduction) {
        this.name = name;
        this.introduction = introduction;
    }
}
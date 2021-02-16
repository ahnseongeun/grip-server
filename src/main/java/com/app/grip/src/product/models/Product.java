package com.app.grip.src.product.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.review.models.Review;
import com.app.grip.src.store.models.Store;
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
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "pictureURL", columnDefinition = "TEXT")
    private String pictureURL;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;

    @OrderBy("id DESC")
    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Review> reviewList;

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;

        for(Review review : reviewList) {
            review.setProduct(this);
        }
    }

    public Product(String name, String content, Integer price, String pictureURL) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.pictureURL = pictureURL;
    }
}
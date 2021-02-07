package com.app.grip.src.product;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.store.StoreInfo;
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
@Table(name = "Product")
public class ProductInfo extends BaseEntity {
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

    @ManyToOne
    @JoinColumn(name = "id")
    private StoreInfo storeInfo;

    @Column(name = "pictureURL", nullable = false, columnDefinition = "TEXT")
    private String pictureURL;

    @ManyToOne
    @JoinColumn(name = "id")
    private ProductCategoryInfo productCategoryInfo;

    public ProductInfo(String name, String content, Integer price, String pictureURL) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.pictureURL = pictureURL;
    }
}
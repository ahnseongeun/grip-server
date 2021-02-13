package com.app.grip.src.product.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.store.models.Store;
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

    public Product(String name, String content, Integer price, String pictureURL) {
        this.name = name;
        this.content = content;
        this.price = price;
        this.pictureURL = pictureURL;
    }
}
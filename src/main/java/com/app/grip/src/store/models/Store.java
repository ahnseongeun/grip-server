package com.app.grip.src.store.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.product.models.Product;
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
    @JoinColumn(name = "userNo",nullable = false)
    private User user;

    @OneToMany(mappedBy = "store", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Product> productList;

    public void setProductList(List<Product> productList) {
        this.productList = productList;

        for (Product product : productList) {
            product.setStore(this);
        }
    }

    public Store(String name, String introduction, String pictureURL) {
        this.name = name;
        this.introduction = introduction;
        this.pictureURL = pictureURL;
    }
}
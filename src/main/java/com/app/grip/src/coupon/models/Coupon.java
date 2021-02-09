package com.app.grip.src.coupon.models;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.user.models.User;
import lombok.*;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name="coupon")
public class Coupon extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "minimumPrice", nullable = false)
    private Integer minimumPrice;

    @CreationTimestamp
    @Column(name = "effectiveDate", nullable = false)
    private Date effectiveDate;

    public Coupon(String content, Integer discount, Integer minimumPrice, Date effectiveDate) {
        this.content = content;
        this.discount = discount;
        this.minimumPrice = minimumPrice;
        this.effectiveDate = effectiveDate;
    }
}
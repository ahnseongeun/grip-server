package com.app.grip.src.coupon;

import com.app.grip.config.BaseEntity;
import com.app.grip.src.user.models.UserInfo;
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
@Table(name="couponInfo")
public class CouponInfo extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private UserInfo userInfo;

    @Column(name = "content", nullable = false, length = 100)
    private String content;

    @Column(name = "discount", nullable = false)
    private Integer discount;

    @Column(name = "minimumPrice", nullable = false)
    private Integer minimumPrice;

    @CreationTimestamp
    @Column(name = "effectiveDate", nullable = false)
    private Date effectiveDate;

    public CouponInfo(String content, Integer discount, Integer minimumPrice, Date effectiveDate) {
        this.content = content;
        this.discount = discount;
        this.minimumPrice = minimumPrice;
        this.effectiveDate = effectiveDate;
    }
}
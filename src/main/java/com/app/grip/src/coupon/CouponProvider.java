package com.app.grip.src.coupon;

import com.app.grip.config.BaseException;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.coupon.models.GetCouponRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_REVIEW;

@RequiredArgsConstructor
@Service
public class CouponProvider {
    private final CouponRepository couponRepository;
    /**
     * 쿠폰 전체 조회
     * @return List<GetCouponRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetCouponRes> retrieveCoupons() throws BaseException {
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);
        List<Coupon> couponList;

        try {
            couponList = couponRepository.findByStatus("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return couponList.stream().map(coupon -> {
            return new GetCouponRes(coupon.getId(), coupon.getUser().getNo(),
                    coupon.getContent(), coupon.getDiscount(), coupon.getMinimumPrice(),
                    outputDateFormat.format(coupon.getEffectiveDate()));
        }).collect(Collectors.toList());
    }
}

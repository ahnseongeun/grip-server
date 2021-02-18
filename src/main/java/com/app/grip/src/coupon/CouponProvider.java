package com.app.grip.src.coupon;

import com.app.grip.config.BaseException;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.coupon.models.GetCouponRes;
import com.app.grip.src.coupon.models.GetCouponsRes;
import com.app.grip.src.user.UserRepository;
import com.app.grip.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class CouponProvider {
    private final CouponRepository couponRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
    
    /**
     * 쿠폰 전체 조회(관리자용)
     * @return List<GetCouponRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetCouponsRes> retrieveCoupons() throws BaseException {
        List<Coupon> couponList;

        try {
            couponList = couponRepository.findAllByOrderByIdDesc();
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_COUPON);
        }

        return couponList.stream().map(coupon -> {
            return retrieveGetReviewsRes(coupon);
        }).collect(Collectors.toList());
    }

    /**
     * 쿠폰 전체 조회(클라 조회용)
     * @return List<GetCouponRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetCouponRes> retrieveCoupon() throws BaseException {
        List<Coupon> couponList;

        try {
            couponList = couponRepository.findByStatusOrderByIdDesc("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_COUPON);
        }

        return couponList.stream().map(coupon -> {
            return retrieveGetCouponRes(coupon);
        }).collect(Collectors.toList());
    }

    /**
     * Coupon -> GetCouponsRes 변경
     * @Param Coupon coupon
     * @return GetCouponsRes
     * @Auther shine
     */
    public GetCouponsRes retrieveGetReviewsRes(Coupon coupon) {
        return new GetCouponsRes(
                coupon.getId(),
                coupon.getUser().getNo(),
                coupon.getUser().getName(),
                coupon.getContent(),
                coupon.getDiscount(),
                coupon.getMinimumPrice(),
                dateFormat.format(coupon.getEffectiveDate()),
                dateFormat.format(coupon.getCreateDate()),
                dateFormat.format(coupon.getUpdateDate()),
                coupon.getStatus());
    }

    /**
     * Coupon -> GetCouponRes 변경
     * @Param Coupon coupon
     * @return GetCouponRes
     * @Auther shine
     */
    public GetCouponRes retrieveGetCouponRes(Coupon coupon) {
        return new GetCouponRes(
                coupon.getId(),
                coupon.getUser().getNo(),
                coupon.getUser().getName(),
                coupon.getContent(),
                coupon.getDiscount(),
                coupon.getMinimumPrice(),
                dateFormat.format(coupon.getEffectiveDate()),
                "세상에 모든 것이 다이소!",
                "https://spng.subpng.com/20180906/vqw/kisspng-earring-jewellery-necklace-kalyan-jewellers-gold-jewellery-transparent-png-arts-5b91ec7d692563.1314281415362899174307.jpg"
                );
    }

}
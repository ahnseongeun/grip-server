package com.app.grip.src.coupon;

import com.app.grip.config.BaseException;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.coupon.models.GetCouponRes;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
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

    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);
    
    /**
     * 쿠폰 전체 조회
     * @return List<GetCouponRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetCouponRes> retrieveCoupons() throws BaseException {
        List<Coupon> couponList;

        try {
            couponList = couponRepository.findByStatus("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_COUPON);
        }

        return couponList.stream().map(coupon -> {
            return new GetCouponRes(coupon.getId(), coupon.getUser().getNo(),
                    coupon.getContent(), coupon.getDiscount(), coupon.getMinimumPrice(),
                    outputDateFormat.format(coupon.getEffectiveDate()));
        }).collect(Collectors.toList());
    }

    /**
     * 내 쿠폰 전체 조회
     * @return List<GetCouponRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetCouponRes> retrieveCoupon() throws BaseException {
        List<Coupon> couponList;
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));

        try {
            couponList = couponRepository.findByStatusAndUser("Y", user);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_COUPON);
        }

        return couponList.stream().map(coupon -> {
            return new GetCouponRes(coupon.getId(), coupon.getUser().getNo(),
                    coupon.getContent(), coupon.getDiscount(), coupon.getMinimumPrice(),
                    outputDateFormat.format(coupon.getEffectiveDate()));
        }).collect(Collectors.toList());
    }
}

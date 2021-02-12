package com.app.grip.src.coupon;

import com.app.grip.config.BaseException;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.coupon.models.PostCouponReq;
import com.app.grip.src.coupon.models.PostCouponRes;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class CouponService {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final JwtService jwtService;

    /**
     * 쿠폰 생성
     * @param PostCouponReq parameters
     * @return PostCouponRes
     * @throws BaseException
     * @Auther shine
     */
    public PostCouponRes createCoupon(PostCouponReq parameters) throws BaseException {
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);
        Date effectiveDate = new Date();

        try {
            effectiveDate = inputDateFormat.parse(parameters.getEffectiveDate() + " 23:59:59");
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        System.out.println(effectiveDate);
        
        Coupon newCoupon = Coupon.builder()
                .user(user)
                .content(parameters.getContent())
                .discount(parameters.getDiscount())
                .minimumPrice(parameters.getMinimumPrice())
                .effectiveDate(effectiveDate)
                .build();

        try {
            newCoupon = couponRepository.save(newCoupon);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_COUPON);
        }

        return new PostCouponRes(newCoupon.getId(), newCoupon.getUser().getNo(), newCoupon.getContent(),
                newCoupon.getDiscount(), newCoupon.getMinimumPrice(), outputDateFormat.format(effectiveDate));
    }

}
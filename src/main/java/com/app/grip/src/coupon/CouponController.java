package com.app.grip.src.coupon;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.coupon.models.GetCouponRes;
import com.app.grip.src.coupon.models.GetCouponsRes;
import com.app.grip.src.coupon.models.PostCouponReq;
import com.app.grip.src.coupon.models.PostCouponRes;
import com.app.grip.utils.ValidationRegex;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CouponController {
    private final CouponService couponService;
    private final CouponProvider couponProvider;
    private final ValidationRegex validationRegex;

    /**
     * 쿠폰 전체조회 API
     * [GET] /api/admin/coupons
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "전체 쿠폰조회(관리자용)", notes = "전체 쿠폰조회")
    @ResponseBody
    @GetMapping("/admin/coupons")
    public BaseResponse<List<GetCouponsRes>> getAdminCoupons() {
        try {
            List<GetCouponsRes> couponList = couponProvider.retrieveCoupons();
            return new BaseResponse<>(SUCCESS, couponList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 전체조회 API
     * [GET] /api/coupons
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "쿠폰 전체조회(클라용)", notes = "내 쿠폰 조회(클라용)")
    @ResponseBody
    @GetMapping("/coupons")
    public BaseResponse<List<GetCouponRes>> getCoupon() {
        try {
            List<GetCouponRes> couponList = couponProvider.retrieveCoupon();
            return new BaseResponse<>(SUCCESS, couponList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 쿠폰 등록 API
     * [POST] /api/coupons
     * @RequestBody PostCouponReq parameters
     * @return BaseResponse<PostCouponRes>
     * @Auther shine
     */
    @ApiOperation(value = "쿠폰 등록", notes = "쿠폰 등록")
    @ResponseBody
    @PostMapping("/coupons")
    public BaseResponse<PostCouponRes> postCoupon(@RequestBody(required = false) PostCouponReq parameters) {
        if(parameters.getContent() == null || parameters.getContent().length() == 0) {
            return new BaseResponse<>(EMPTY_CONTENT);
        }
        if(parameters.getDiscount() == null) {
            return new BaseResponse<>(EMPTY_DISCOUNT);
        }
        if(parameters.getMinimumPrice() == null) {
            return new BaseResponse<>(EMPTY_MINIMUMPRICE);
        }
        if(parameters.getEffectiveDate() == null) {
            return new BaseResponse<>(EMPTY_EFFECTIVEDATE);
        }

        if(!validationRegex.isRegexEffectiveDate(parameters.getEffectiveDate())) {
            return new BaseResponse<>(INVALID_EFFECTIVEDATE);
        }

        try {
            PostCouponRes coupon = couponService.createCoupon(parameters);
            return new BaseResponse<>(SUCCESS, coupon);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

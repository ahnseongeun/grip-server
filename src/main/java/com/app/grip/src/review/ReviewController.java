package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.PostReviewReq;
import com.app.grip.src.review.models.PostReviewRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewProvider reviewProvider;
    private final ReviewService reviewService;

    /**
     * 리뷰 전체조회 API
     * [GET] /api/admin/reviews
     * @return BaseResponse<List<GetReviewRes>>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 전체조회", notes = "리뷰 전체조회")
    @ResponseBody
    @GetMapping("/admin/reviews")
    public BaseResponse<List<GetReviewRes>> getReviews() {
        try {
            List<GetReviewRes> reviewResList = reviewProvider.retrieveReviews();
            return new BaseResponse<>(SUCCESS, reviewResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 리뷰 조회 API
     * [GET] /api/reviews/:reviewId
     * @PathVariable Long reviewId
     * @return BaseResponse<GetReviewRes>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 조회", notes = "리뷰 조회")
    @ResponseBody
    @GetMapping("/reviews/{reviewId}")
    public BaseResponse<GetReviewRes> getReview(@PathVariable Long reviewId) {
        try {
            GetReviewRes reviewRes = reviewProvider.retrieveReview(reviewId);
            return new BaseResponse<>(SUCCESS, reviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 리뷰 등록 API
     * [POST] /api/reviews/:storeId
     * @RequestBody PostReviewReq parameters
     * @PathVariable Long storeId
     * @return BaseResponse<PostReviewRes>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 등록", notes = "리뷰 등록")
    @ResponseBody
    @PostMapping("/reviews/{storeId}")
    public BaseResponse<PostReviewRes> postReview(@RequestBody(required = false) PostReviewReq parameters, @PathVariable Long storeId) {
        if(parameters.getStar() == null) {
            return new BaseResponse<>(EMPTY_STAR);
        }
        if(parameters.getContent() == null || parameters.getContent().length() == 0) {
            return new BaseResponse<>(EMPTY_CONTENT);
        }

        try {
            PostReviewRes postReviewRes = reviewService.createReview(parameters, storeId);
            return new BaseResponse<>(SUCCESS, postReviewRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
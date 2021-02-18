package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.review.models.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
     * @return BaseResponse<List<GetReviewsRes>>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 전체조회(관리자용)", notes = "리뷰 전체조회")
    @ResponseBody
    @GetMapping("/admin/reviews")
    public BaseResponse<List<GetReviewsRes>> getAdminReviews() {
        try {
            List<GetReviewsRes> reviewResList = reviewProvider.retrieveReviews();
            return new BaseResponse<>(SUCCESS, reviewResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 리뷰 전체조회 API
     * [GET] /api/reviews
     * @return BaseResponse<List<GetReviewRes>>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 전체조회", notes = "리뷰 전체조회")
    @ResponseBody
    @GetMapping("/reviews")
    public BaseResponse<List<GetReviewRes>> getReviews() {
        try {
            List<Review> reviewList = reviewProvider.retrieveReviewsByStatusY();
            return new BaseResponse<>(SUCCESS, reviewList.stream().map(review -> {
                return reviewProvider.retrieveGetReviewRes(review);
            }).collect(Collectors.toList()));
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
            Review review = reviewProvider.retrieveReview(reviewId);
            return new BaseResponse<>(SUCCESS, reviewProvider.retrieveGetReviewRes(review));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 카테고리별 리뷰 조회 API
     * [GET] /api/reviews
     * @RequestBody String categoryName
     * @return BaseResponse<List<GetReviewRes>>
     * @Auther shine
     */
    @ApiOperation(value = "카테고리별 리뷰 조회", notes = "카테고리별 리뷰 조회")
    @ResponseBody
    @GetMapping("/reviews/category")
    public BaseResponse<List<GetReviewRes>> getReviewByCategoryName(@RequestParam(value = "name",required = false) String name) {
        String categoryName = "";
        try {
            categoryName = name;
        } catch (Exception exception) {
            return new BaseResponse<>(EMPTY_CATEGORY);
        }
        if(categoryName == null || categoryName.length() == 0) {
            return new BaseResponse<>(EMPTY_CATEGORY);
        }

        try {
            List<Review> reviewList = reviewProvider.retrieveReviewsByCategoryNameAndStatusY(categoryName);
            return new BaseResponse<>(SUCCESS, reviewList.stream().map(reviewProvider::retrieveGetReviewRes).collect(Collectors.toList()));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }


    /**
     * 리뷰 등록 API
     * [POST] /api/stores/:storeId/reviews
     * @RequestBody PostReviewReq parameters
     * @PathVariable Long storeId
     * @return BaseResponse<PostReviewRes>
     * @Auther shine
     */
    @ApiOperation(value = "리뷰 등록", notes = "리뷰 등록")
    @ResponseBody
    @PostMapping("/stores/{storeId}/reviews")
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
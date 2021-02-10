package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.review.models.PostReviewReq;
import com.app.grip.src.review.models.PostReviewRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

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
    @PostMapping("/{storeId}")
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

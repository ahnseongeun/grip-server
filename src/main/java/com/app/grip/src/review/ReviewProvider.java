package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.PictureRes;
import com.app.grip.src.review.models.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.grip.config.BaseResponseStatus.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewProvider {
    private final ReviewRepository reviewRepository;

    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);

    /**
     * 리뷰 전체 조회
     * @return List<GetReviewRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetReviewRes> retrieveReviews() throws BaseException {
        List<Review> reviewList;

        try {
            reviewList = reviewRepository.findByStatusOrderByCreateDateDesc("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList.stream().map(review -> {return retrieveReviewRes(review);}).collect(Collectors.toList());
    }

    /**
     * 리뷰 조회
     * @Param Long reviewId
     * @return Review
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public Review retrieveReview(Long reviewId) throws BaseException {
        Review review;

        try {
            review = reviewRepository.findById(reviewId).orElseThrow(() -> new BaseException(NOT_FOUND_REVIEW));

        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return review;
    }

    /**
     * Review -> GetReviewRes 변경
     * @Param Review review
     * @return GetReviewRes
     * @Auther shine
     */
    public GetReviewRes retrieveReviewRes(Review review) {
        return new GetReviewRes(review.getId(), review.getStore().getName(), review.getUser().getName(),
                review.getStar(), review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()), outputDateFormat.format(review.getCreateDate()), outputDateFormat.format(review.getUpdateDate()), review.getStatus());
    }

}
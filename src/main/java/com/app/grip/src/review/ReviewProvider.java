package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.PictureRes;
import com.app.grip.src.review.models.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.app.grip.config.BaseResponseStatus.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReviewProvider {
    private final ReviewRepository reviewRepository;

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
            reviewList = reviewRepository.findByStatus("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList.stream().map(review -> {
            return new GetReviewRes(review.getId(), review.getStore().getId(), review.getUser().getNo(),
                    review.getStar(), review.getContent(),
                    review.getReviewPictureList().stream().map(reviewPicture -> {
                        return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                    }).collect(Collectors.toList()));
        }).collect(Collectors.toList());
    }

    /**
     * 리뷰 조회
     * @Param Long reviewId
     * @return GetReviewRes
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public GetReviewRes retrieveReview(Long reviewId) throws BaseException {
        Review review;

        try {
            review = reviewRepository.findByStatusAndId("Y", reviewId).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return new GetReviewRes(review.getId(), review.getStore().getId(), review.getUser().getNo(),
                review.getStar(), review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()));
    }

}
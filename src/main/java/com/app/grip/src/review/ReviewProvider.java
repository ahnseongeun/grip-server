package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.ProductCategoryRepository;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.GetReviewsRes;
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
    private final ProductCategoryRepository productCategoryRepository;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

    /**
     * 리뷰 전체 조회
     * @return List<GetReviewsRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetReviewsRes> retrieveReviews() throws BaseException {
        List<Review> reviewList;

        try {
            reviewList = reviewRepository.findAllByOrderByIdDesc();
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList.stream().map(review -> {
            return retrieveGetReviewsRes(review);
        }).collect(Collectors.toList());
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
            review = reviewRepository.findById(reviewId).orElseThrow(() -> new BaseException(FAILED_TO_GET_REVIEW));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return review;
    }

    /**
     * 유효한 전체 리뷰 조회
     * @return List<Review>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<Review> retrieveReviewsByStatusY() throws BaseException {
        List<Review> reviewList;

        try {
            reviewList = reviewRepository.findByStatusOrderByIdDesc("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList;
    }

    /**
     * 카테고리별 리뷰 조회
     * @return List<Review>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<Review> retrieveReviewsByCategoryNameAndStatusY(String categoryName) throws BaseException {
        List<Review> reviewList;

        try {
            productCategoryRepository.findByStatusAndName("Y", categoryName).get(0);
            reviewList = reviewRepository.findByStatusAndProduct_ProductCategory_Name("Y", categoryName);
        } catch (IndexOutOfBoundsException exception) {
            throw new BaseException(FAILED_TO_GET_PRODUCTCATEGORY);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList;
    }


    /**
     * Review -> GetReviewsRes 변경
     * @Param Review review
     * @return GetReviewsRes
     * @Auther shine
     */
    public GetReviewsRes retrieveGetReviewsRes(Review review) {
        return new GetReviewsRes(
                review.getId(),
                review.getProduct().getId(),
                review.getUser().getName(),
                review.getUser().getProfileImageURL(),
                review.getStar(),
                review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()),
                dateFormat.format(review.getCreateDate()),
                dateFormat.format(review.getUpdateDate()),
                review.getStatus());
    }

    /**
     * Review -> GetReviewRes 변경
     * @Param Review review
     * @return GetReviewRes
     * @Auther shine
     */
    public GetReviewRes retrieveGetReviewRes(Review review) {
        return new GetReviewRes(
                review.getId(),
                review.getProduct().getId(),
                review.getUser().getNo(),
                review.getUser().getName(),
                review.getUser().getProfileImageURL(),
                review.getStar(),
                review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()),
                dateFormat.format(review.getCreateDate()));
    }

}
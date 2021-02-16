package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.src.review.models.GetReviewRes;
import com.app.grip.src.review.models.GetReviewsRes;
import com.app.grip.src.review.models.PictureRes;
import com.app.grip.src.review.models.Review;
import com.app.grip.src.store.StoreProvider;
import com.app.grip.src.store.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
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
    private final StoreProvider storeProvider;

    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);

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
            reviewList = reviewRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList.stream().map(review -> {
            return retrieveGetReviewsRes(review);
        }).collect(Collectors.toList());
    }

    /**
     * 상점에 대한 리뷰 조회
     * @return List<GetReviewRes>
     * @Param Long storeId
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetReviewRes> retrieveByStoreReviews(Long storeId) throws BaseException {
        Store store = storeProvider.retrieveStoreById(storeId);
        List<Review> reviewList;

        try {
            reviewList = reviewRepository.findByStoreOrderByCreateDateDesc(store);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return reviewList.stream().map(review -> {
            return retrieveGetReviewRes(review);
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
            review = reviewRepository.findById(reviewId).orElseThrow(() -> new BaseException(NOT_FOUND_REVIEW));
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_REVIEW);
        }

        return review;
    }

    /**
     * Review -> GetReviewsRes 변경
     * @Param Review review
     * @return GetReviewsRes
     * @Auther shine
     */
    public GetReviewsRes retrieveGetReviewsRes(Review review) {
        return new GetReviewsRes(review.getId(), review.getUser().getName(),
                review.getStar(), review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()), outputDateFormat.format(review.getCreateDate()), outputDateFormat.format(review.getUpdateDate()), review.getStatus());
    }

    /**
     * Review -> GetReviewRes 변경
     * @Param Review review
     * @return GetReviewRes
     * @Auther shine
     */
    public GetReviewRes retrieveGetReviewRes(Review review) {
        return new GetReviewRes(review.getId(),
                review.getUser().getName(), review.getUser().getProfileImageURL(),
                review.getStar(), review.getContent(),
                review.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()), outputDateFormat.format(review.getCreateDate()));
    }

}
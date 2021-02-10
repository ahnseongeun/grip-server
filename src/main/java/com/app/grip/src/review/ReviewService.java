package com.app.grip.src.review;

import com.app.grip.config.BaseException;
import com.app.grip.src.review.models.*;
import com.app.grip.src.store.StoreRepository;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class ReviewService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final JwtService jwtService;

    /**
     * 리뷰 생성
     * @param PostReviewReq parameters, Long storeId
     * @return PostReviewRes
     * @throws BaseException
     * @Auther shine
     */
    public PostReviewRes createReview(PostReviewReq parameters, Long storeId) throws BaseException {
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new BaseException(FAILED_TO_GET_STORE));

        List<ReviewPicture> reviewPictureList = new ArrayList<>();

        if(parameters.getPictureURL() != null) {
            for (PictureReq picture : parameters.getPictureURL()) {
                System.out.println(picture.toString());
                ReviewPicture reviewPicture = new ReviewPicture(picture.getPictureURL());
                reviewPictureList.add(reviewPicture);
            }
        }

        Review newReview = Review.builder()
                .store(store)
                .user(user)
                .star(parameters.getStar())
                .content(parameters.getContent())
                .reviewPictureList(reviewPictureList)
                .build();

        try {
            newReview = reviewRepository.save(newReview);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_REVIEW);
        }

        return new PostReviewRes(newReview.getId(), newReview.getStore().getId(), newReview.getUser().getNo(),
                newReview.getStar(), newReview.getContent(),
                newReview.getReviewPictureList().stream().map(reviewPicture -> {
                    return new PictureRes(reviewPicture.getId(), reviewPicture.getPictureURL(), reviewPicture.getStatus());
                }).collect(Collectors.toList()));
    }












}
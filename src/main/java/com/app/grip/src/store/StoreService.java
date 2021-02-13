package com.app.grip.src.store;

import com.app.grip.config.BaseException;
import com.app.grip.src.store.models.PostStoreReq;
import com.app.grip.src.store.models.PostStoreRes;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.utils.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final JwtService jwtService;

    /**
     * 상점 생성
     * @param PostStoreReq parameters
     * @return PostStoreRes
     * @throws BaseException
     * @Auther shine
     */
    public PostStoreRes createStore(PostStoreReq parameters) throws BaseException {
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));

        Store newStore = Store.builder()
                .name(parameters.getName())
                .introduction(parameters.getIntroduction())
                .pictureURL(parameters.getPictureURL())
                .user(user)
                .build();

        try {
            newStore = storeRepository.save(newStore);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_POST_STORE);
        }

        return new PostStoreRes(newStore.getId(), newStore.getName(),
                newStore.getIntroduction(), newStore.getPictureURL(), newStore.getUser().getNo());
    }

}
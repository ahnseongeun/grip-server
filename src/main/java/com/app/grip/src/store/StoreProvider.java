package com.app.grip.src.store;

import com.app.grip.config.BaseException;
import com.app.grip.src.store.models.GetStoreRes;
import com.app.grip.src.store.models.GetStoresRes;
import com.app.grip.src.store.models.Store;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.utils.jwt.JwtService;
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
public class StoreProvider {
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);

    /**
     * 관리자용 상점 전체 조회
     * @return List<GetStoresRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetStoresRes> retrieveStores() throws BaseException {
        User user = userRepository.findById(jwtService.getUserNo()).orElseThrow(() -> new BaseException(FAILED_TO_GET_USER));
        List<Store> storeList;

        if(user.getRole() != 100) {
            throw new BaseException(DO_NOT_AUTH_USER);
        }

        try {
            storeList = storeRepository.findAllByOrderByIdDesc();
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_STORE);
        }

        return storeList.stream().map(store -> {
            return new GetStoresRes(store.getId(), store.getName(),
                    store.getIntroduction(), store.getPictureURL(), store.getUser().getNo(),
                    dateFormat.format(store.getCreateDate()), dateFormat.format(store.getUpdateDate()),
                    store.getStatus());
        }).collect(Collectors.toList());
    }

    /**
     * 상점 조회
     * @Param Long storeId
     * @return Store
     * @throws BaseException
     * @comment storeId로 상점 조회
     * @Auther shine
     */
    @Transactional
    public Store retrieveStoreById(Long storeId) throws BaseException {
        Store store;

        try {
            store = storeRepository.findByStatusAndId("Y", storeId).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_STORE);
        }

        return store;
    }

    /**
     * 상점 조회
     * @Param User user
     * @return Store
     * @throws BaseException
     * @comment user로 상점 조회
     * @Auther shine
     */
    @Transactional
    public Store retrieveStoreByUser(User user) throws BaseException {
        Store store;

        try {
            store = storeRepository.findByUser(user).get(0);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_STORE);
        }

        return store;
    }

    /**
     * Store -> GetStoreRes 변경
     * @Param Store store
     * @return GetStoreRes
     * @Auther shine
     */
    public GetStoreRes retrieveGetStoreRes(Store store) {
        return new GetStoreRes(store.getId(), store.getName(),
                store.getIntroduction(), store.getPictureURL(), store.getUser().getName());
    }

}
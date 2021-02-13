package com.app.grip.src.store;

import com.app.grip.config.BaseException;
import com.app.grip.src.store.models.GetStoreRes;
import com.app.grip.src.store.models.GetStoresRes;
import com.app.grip.src.store.models.Store;
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

    SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초", Locale.KOREA);

    /**
     * 상점 전체 조회
     * @return List<GetStoresRes>
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public List<GetStoresRes> retrieveStores() throws BaseException {
        List<Store> storeList;

        try {
            storeList = storeRepository.findByStatusOrderByCreateDateDesc("Y");
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_STORE);
        }

        return storeList.stream().map(store -> {
            return new GetStoresRes(store.getId(), store.getName(),
                    store.getIntroduction(), store.getPictureURL(), store.getUser().getNo(),
                    outputDateFormat.format(store.getCreateDate()), store.getStatus());
        }).collect(Collectors.toList());
    }

    /**
     * 상점 조회
     * @Param Long storeId
     * @return GetStoreRes
     * @throws BaseException
     * @Auther shine
     */
    @Transactional
    public GetStoreRes retrieveStore(Long storeId) throws BaseException {
        Store store;

        try {
            store = storeRepository.findByStatusAndId("Y", storeId).get(0);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_GET_STORE);
        }

        return new GetStoreRes(store.getId(), store.getName(),
                store.getIntroduction(), store.getPictureURL(), store.getUser().getName());
    }

}
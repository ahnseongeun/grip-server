package com.app.grip.src.store;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.store.models.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StoreController {
    private final StoreService storeService;
    private final StoreProvider storeProvider;

    /**
     * 상점 전체조회 API
     * [GET] /api/admin/stores
     * @return BaseResponse<List<GetStoresRes>>
     * @Auther shine
     */
    @ApiOperation(value = "상점 전체조회", notes = "상점 전체조회")
    @ResponseBody
    @GetMapping("/admin/stores")
    public BaseResponse<List<GetStoresRes>> getStores() {
        try {
            List<GetStoresRes> storesResList = storeProvider.retrieveStores();
            return new BaseResponse<>(SUCCESS, storesResList);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 상점 조회 API
     * [GET] /api/stores/:storeId
     * @PathVariable Long storeId
     * @return BaseResponse<GetStoreRes>
     * @Auther shine
     */
    @ApiOperation(value = "상점 조회", notes = "상점 조회")
    @ResponseBody
    @GetMapping("/stores/{storeId}")
    public BaseResponse<GetStoreRes> getStore(@PathVariable Long storeId) {
        try {
            Store store = storeProvider.retrieveStoreById(storeId);
            return new BaseResponse<>(SUCCESS, new GetStoreRes(store.getId(), store.getName(),
                    store.getIntroduction(), store.getPictureURL(), store.getUser().getName()));
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 상점 등록 API
     * [POST] /api/stores
     * @RequestBody PostStoreReq parameters
     * @return BaseResponse<PostStoreRes>
     * @Auther shine
     */
    @ApiOperation(value = "상점 등록", notes = "상점 등록")
    @ResponseBody
    @PostMapping("/stores")
    public BaseResponse<PostStoreRes> postStore(@RequestBody(required = false) PostStoreReq parameters) {
        if(parameters.getName() == null || parameters.getName().length() == 0) {
            return new BaseResponse<>(EMPTY_NAME);
        }
        if(parameters.getIntroduction() == null || parameters.getIntroduction().length() == 0) {
            return new BaseResponse<>(EMPTY_INTRODUCTION);
        }

        try {
            PostStoreRes postStoreRes = storeService.createStore(parameters);
            return new BaseResponse<>(SUCCESS, postStoreRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

}
package com.app.grip.src.store;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.store.models.GetStoresRes;
import com.app.grip.src.store.models.PostStoreReq;
import com.app.grip.src.store.models.PostStoreRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/stores")
public class StoreController {
    private final StoreService storeService;
    private final StoreProvider storeProvider;

    /**
     * 상점 전체조회 API
     * [GET] /api/stores
     * @PathVariable userId
     * @return BaseResponse<GetUserRes>
     * @Auther shine
     */
    @ApiOperation(value = "상점 전체조회", notes = "상점 전체조회")
    @ResponseBody
    @GetMapping("")
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
     * @PathVariable
     * @return
     * @Auther shine
     */
    @ApiOperation(value = "상점 조회", notes = "상점 조회")
    @ResponseBody
    @GetMapping("/{storeId}")
    public BaseResponse<GetStoresRes> getStore(@PathVariable Long storeId) {
        try {
            GetStoresRes storesRes = storeProvider.retrieveStore(storeId);
            return new BaseResponse<>(SUCCESS, storesRes);
        } catch (BaseException exception) {
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 상점 등록 API
     * [POST] /api/stores/
     * @RequestBody 
     * @return 
     * @Auther shine
     */
    @ApiOperation(value = "상점 등록", notes = "상점 등록")
    @ResponseBody
    @PostMapping("")
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
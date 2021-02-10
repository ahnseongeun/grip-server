package com.app.grip.src.advertisement;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.advertisement.models.GetAdvertisement;
import com.app.grip.src.videoCategory.models.GetVideoCategory;
import com.app.grip.utils.S3Service;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = "/api")
public class AdvertisementController {

    private final S3Service s3Service;
    private final AdvertisementProvider advertisementProvider;

    /**
     * 광고 조회
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/advertisements",method = RequestMethod.GET)
    @ApiOperation(value = "광고 조회", notes = "광고 리스트 조회")
    public BaseResponse<List<GetAdvertisement>> GetAdvertisements() {

        try{
            log.info("광고 조회 ");

            List<GetAdvertisement> getAdvertisementList = advertisementProvider.retrieveAdvertisementList();
            return new BaseResponse<>(SUCCESS, getAdvertisementList);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

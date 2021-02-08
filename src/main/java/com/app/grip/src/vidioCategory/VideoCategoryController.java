package com.app.grip.src.vidioCategory;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.vidioCategory.models.GetVideoCategory;
import com.app.grip.utils.S3Service;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = "/api")
public class VideoCategoryController {

    private final S3Service s3Service;
    private final VideoCategoryService videoCategoryService;
    private final VideoCategoryProvider videoCategoryProvider;

    /**
     * 영상 카테고리 조회
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/video-category",method = RequestMethod.GET)
    public BaseResponse<List<GetVideoCategory>> GetVideoCategory() {

        try{
            log.info("영상 카테고리 조회 ");

            List<GetVideoCategory> getVideoCategoryList = videoCategoryProvider.retrieveVideoCategoryList();
            return new BaseResponse<>(SUCCESS, getVideoCategoryList);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 더미데이터 이미지 삽입할때 사용.
     * @param name
     * @param imageFile
     * @return
     * @throws IOException
     * @throws BaseException
     */
    @ResponseBody
    @RequestMapping(value = "/upload-category-image",method = RequestMethod.POST)
    public BaseResponse<VideoCategoryInfo> execWrite(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "imageFile") MultipartFile imageFile) throws IOException, BaseException {
        log.info("upload");
        String imgPath = s3Service.upload(imageFile);

        try{
            log.info("image 추가 ");

            VideoCategoryInfo videoCategoryInfo = videoCategoryService.savePost(name,imgPath);
            return new BaseResponse<>(SUCCESS, videoCategoryInfo);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

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
//@RequestMapping(value = "/api")
public class VideoCategoryController {

    private final S3Service s3Service;
    private final VideoCategoryService videoCategoryService;
    private final VideoCategoryProvider videoCategoryProvider;

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

    @GetMapping("/gallery")
    public String dispWrite() {

        return "/gallery";
    }

    @PostMapping("/gallery")
    public String execWrite(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "imageFile") MultipartFile imageFile) throws IOException {
        String imgPath = s3Service.upload(imageFile);

        videoCategoryService.savePost(name,imgPath);

        return "redirect:/gallery";
    }

}

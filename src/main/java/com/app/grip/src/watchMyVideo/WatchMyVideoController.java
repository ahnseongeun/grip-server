package com.app.grip.src.watchMyVideo;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.video.models.GetDetailVideo;
import com.app.grip.src.watchMyVideo.models.GetWatchMyVideo;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping(value = "/api")
public class WatchMyVideoController {

    private final WatchMyVideoProvider watchMyVideoProvider;

    @GetMapping("/watch-videos")
    @ResponseBody
    @ApiOperation(value = "내가 본 영상 조회(userNo 1,2,3의 jwt만 사용해주세요.)"
            , notes = "내가 본 영상 조회(userNo 1,2,3의 jwt만 사용해주세요.)")
    public BaseResponse<List<GetWatchMyVideo>> getWatchMyVideo(
            @RequestHeader(value = "jwt") String jwt)  {

        List<GetWatchMyVideo> getWatchMyVideo;
        try{
            getWatchMyVideo = watchMyVideoProvider.retrieveWatchMyVideo();
            return new BaseResponse<>(SUCCESS, getWatchMyVideo);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/watch-videos/{videoId}")
    @ResponseBody
    @ApiOperation(value = "내가 본 영상 삭제)", notes = "내가 본 영상 삭제")
    public BaseResponse<Void> getWatchMyVideo(
            @RequestHeader(value = "jwt") String jwt, @PathVariable Long videoId)  {


        try{
            watchMyVideoProvider.deleteWatchMyVideo(videoId);
            return new BaseResponse<>(SUCCESS);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

}

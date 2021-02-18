package com.app.grip.src.video.videoLike;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.video.videoLike.models.GetVideoLike;
import com.app.grip.src.video.videoLike.models.PostVideoLike;
import com.app.grip.utils.jwt.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@Controller
@RequestMapping(value = "/api")
public class videoLikeController {

    private final VideoLikeService videoLikeService;
    private final VideoLikeProvider videoLikeProvider;
    private final JwtService jwtService;

    @Autowired
    public videoLikeController(VideoLikeService videoLikeService,
                               VideoLikeProvider videoLikeProvider,
                               JwtService jwtService) {
        this.videoLikeService = videoLikeService;
        this.videoLikeProvider = videoLikeProvider;
        this.jwtService = jwtService;
    }

    /**
     * 비디오 좋아요 전체 조회
     */
    //HashMap을 이용해서 Inmemory DB로 구현하자.
    @ResponseBody
    @RequestMapping(value = "/admin/videos/like",method = RequestMethod.GET)
    @ApiOperation(value = "영상 좋아요 전체 조회", notes = "영상 좋아요 전체 조회")
    public BaseResponse<List<GetVideoLike>> GetVideoLikes() throws BaseException {

        List<GetVideoLike> getVideoLikeList;
        try{
            log.info("영상 좋아요 전체 조회");

            getVideoLikeList = videoLikeProvider.retrieveVideoLike();
            return new BaseResponse<>(SUCCESS, getVideoLikeList);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    /**
     * 비디오 좋아요 조회
     */
    //HashMap을 이용해서 Inmemory DB로 구현하자.
    @ResponseBody
    @RequestMapping(value = "/videos/{videoId}/like",method = RequestMethod.GET)
    @ApiOperation(value = "영상 좋아요 조회", notes = "영상 좋아요 조회")
    public BaseResponse<GetVideoLike> GetVideoLike(@PathVariable Long videoId) throws BaseException {

            GetVideoLike getVideoLike;
            log.info("영상 좋아요 전체 조회");
            getVideoLike = videoLikeProvider.retrieveVideoLikeById(videoId);
            return new BaseResponse<>(SUCCESS, getVideoLike);

    }

    /**
     * 비디오 좋아요 증가
     * 영상 종료할 때 DB에 저장
     */
    //HashMap을 이용해서 Inmemory DB로 구현하자.
    @ResponseBody
    @RequestMapping(value = "/videos/{videoId}/like",method = RequestMethod.POST)
    @ApiOperation(value = "영상 좋아요", notes = "영상 좋아요")
    public BaseResponse<PostVideoLike> PostVideoLikes(
            @PathVariable Long videoId) throws BaseException {

        PostVideoLike postVideoLike;
        try{
            log.info("영상 좋아요");

            postVideoLike = videoLikeService.createVideoLike(videoId);
            return new BaseResponse<>(SUCCESS, postVideoLike);
        }catch(BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


}

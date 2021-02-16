package com.app.grip.src.video;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.video.models.GetDetailVideo;
import com.app.grip.src.video.models.GetVideos;
import com.app.grip.src.video.models.PatchVideo;
import com.app.grip.src.video.models.PostVideoAndThumbNail;
import com.app.grip.utils.S3Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.JCodecException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoProvider videoProvider;
    private final VideoService videoService;
    private final S3Service s3Service;
    private final HashMap<String,Long> StreamingRepository;

    @Autowired
    public VideoController(VideoProvider videoProvider,
                           VideoService videoService,
                           S3Service s3Service,
                           HashMap<String, Long> streamingRepository) {
        this.videoProvider = videoProvider;
        this.videoService = videoService;
        this.s3Service = s3Service;
        StreamingRepository = streamingRepository;
    }

    @GetMapping("/admin/videos")
    @ApiOperation(value = "전체 영상 리스트 조회 (서버 용도)", notes = "전체 영상 리스트 조회(용도)")
    public BaseResponse<List<GetVideos>> getVideos()  {

        List<GetVideos> getVideoList;
        try{
            getVideoList = videoProvider.retrieveVideos();
            return new BaseResponse<>(SUCCESS, getVideoList);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/videos/{videoId}")
    @ApiOperation(value = "영상 상세 조회", notes = "영상 상세 조회")
    public BaseResponse<GetDetailVideo> getDetailVideo(
            @PathVariable Long videoId,
            @RequestHeader(value = "jwt") String jwt)  {

        GetDetailVideo getDetailVideo;
        try{
            getDetailVideo = videoProvider.retrieveVideoDetail(videoId);
            return new BaseResponse<>(SUCCESS, getDetailVideo);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @PatchMapping("/videos/{videoId}")
    @ApiOperation(value = "방송 종료", notes = "방송 종료")
    public BaseResponse<PatchVideo> updateVideo(@PathVariable Long videoId)  {

        PatchVideo patchVideo;
        try{
            patchVideo = videoService.updateVideo(videoId);
            return new BaseResponse<>(SUCCESS, patchVideo);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }


//    @GetMapping("/videos/thumb-nail/{name}")
//    @ApiOperation(value = "영상 썸네일 만들기(테스트 중)", notes = "영상 리스트 조회")
//    public String getSumNail(@PathVariable String name,
//                             @RequestHeader HttpHeaders headers) throws IOException, JCodecException {
//
//        log.info("getThumbNail");
//        String videoPath = "/home/ubuntu/video/";
//        String thumbNailName = name.substring(0,name.length()-4)+".png";
//        String thumbNailPath = "/home/ubuntu/image/"+thumbNailName;
//        File thumbNailFile = new File(thumbNailPath);
//        File videoFile = new File(videoPath + name);
//        log.info("test1");
//        File resultFile = getThumbnail(videoFile,thumbNailFile);
//        log.info("test2");
//        s3Service.uploadFile(resultFile);
//        log.info("test3");
//        return s3Service.uploadFile(resultFile);
//    }
    @PostMapping("/upload-video")
    @ApiOperation(value = "영상 업로드 및 썸네일 추출 (서버측 데이터 삽입 용도)", notes = "영상 업로드(서버측 데이터 삽입 용도)")
    public BaseResponse<PostVideoAndThumbNail> upload(
            @RequestParam(value = "file",required = true) MultipartFile multipartFile
    ) throws IOException, JCodecException {

        log.info("### upload");
        try{
            PostVideoAndThumbNail postVideoAndThumbNail = videoService.createVideoAndThumbNail(multipartFile);
            return new BaseResponse<>(SUCCESS, postVideoAndThumbNail);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/video-stream/{name}")
    @ApiOperation(value = "서버에서 영상 불러올때 사용(서버 용도)", notes = "서버에서 영상 불러올때 사용(서버 용도)")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable String name,
                                                   @RequestHeader HttpHeaders headers) throws IOException {
        String path = "/home/ubuntu/video/";
        //String path = "C:\\Users\\ahn\\OneDrive\\바탕 화면\\";
        UrlResource video = new UrlResource("file:"+path+name);
        //range를 담을 그릇과 현재 range를 나타내는 변수가 필요하다.
        log.info(headers.toString());

        ResourceRegion region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(region);
    }

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException {

        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        HttpRange httpRange = headers.getRange().stream().findFirst().get();
        if(httpRange != null) {
            long start = httpRange.getRangeStart(contentLength);
            log.info(String.valueOf(start));
            long end = httpRange.getRangeEnd(contentLength);
            log.info(String.valueOf(end));
            long rangeLength = Long.min(chunkSize, end - start + 1);
            log.info(String.valueOf(rangeLength));
            return new ResourceRegion(video, start, rangeLength);
        }
        else {
            log.info("when");
            long rangeLength = Long.min(chunkSize, contentLength);
            return new ResourceRegion(video, 0, rangeLength);
        }
    }

}
/*
try {
                imgPath = s3Service.upload(profileImage);
                user.setProfileImageURL(imgPath);
            }catch (IOException ioException){
                throw new BaseException(FAILED_TO_UPLOAD_IMAGE);
            }
 */
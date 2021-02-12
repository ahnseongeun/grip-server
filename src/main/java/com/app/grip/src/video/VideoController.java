package com.app.grip.src.video;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponse;
import com.app.grip.src.video.models.GetVideos;
import com.app.grip.src.video.models.PostVideoAndThumbNail;
import com.app.grip.utils.S3Service;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.app.grip.config.BaseResponseStatus.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/api")
public class VideoController {

    private final VideoProvider videoProvider;
    private final VideoService videoService;
    private final S3Service s3Service;

    @Autowired
    public VideoController(VideoProvider videoProvider,
                           VideoService videoService,
                           S3Service s3Service) {
        this.videoProvider = videoProvider;
        this.videoService = videoService;
        this.s3Service = s3Service;
    }

    @GetMapping("/videos/")
    @ApiOperation(value = "전체 영상 리스트 조회", notes = "전체 영상 리스트 조회")
    public BaseResponse<List<GetVideos>> getVideos()  {

        List<GetVideos> getVideoList;
        try{
            getVideoList = videoProvider.retrieveVideos();
            return new BaseResponse<>(SUCCESS, getVideoList);
        }catch (BaseException exception){
            return new BaseResponse<>(exception.getStatus());
        }
    }

    @GetMapping("/videos/{name}")
    @ApiOperation(value = "영상 불러오기(테스트 중)", notes = "영상 리스트 조회")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable String name,
                                                   @RequestHeader HttpHeaders headers) throws IOException {
        String path = "/home/ubuntu/video/";
        UrlResource video = new UrlResource("file:"+path+name);
        ResourceRegion region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                            .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                            .body(region);
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
    @ApiOperation(value = "영상 업로드 및 썸네일 추출", notes = "영상 업로드(테스트 중)")
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

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException {

        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        HttpRange httpRange = headers.getRange().stream().findFirst().get();
        if(httpRange != null) {
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            return new ResourceRegion(video, start, rangeLength);
        } else {
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
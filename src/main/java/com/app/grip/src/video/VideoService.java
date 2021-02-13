package com.app.grip.src.video;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponseStatus;
import com.app.grip.src.video.models.PatchVideo;
import com.app.grip.src.video.models.PostVideoAndThumbNail;
import com.app.grip.src.video.models.Video;
import com.app.grip.utils.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_VIDEO;

@Slf4j
@Service
public class VideoService {

    private static final String IMAGE_PNG_FORMAT = "png";
    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public VideoService(S3Service s3Service, VideoRepository videoRepository) {
        this.s3Service = s3Service;
        this.videoRepository = videoRepository;
    }

    public PostVideoAndThumbNail createVideoAndThumbNail(MultipartFile multipartFile)
            throws IOException, JCodecException, BaseException {
        File targetFile;

        String videoPath = "/home/ubuntu/video/";
        targetFile = new File(videoPath + multipartFile.getOriginalFilename());
        log.info(String.valueOf(targetFile));

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            log.info("저장에 성공했습니다.");
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            log.info("저장에 실패했습니다.");
            throw new BaseException(BaseResponseStatus.FAILED_TO_UPLOAD_IMAGE);
        }

        String name = multipartFile.getOriginalFilename();
        log.info("getThumbNail");
        String thumbNailName = name.substring(0,name.length()-4)+".png";
        String thumbNailPath = "/home/ubuntu/image/"+thumbNailName;
        File thumbNailFile = new File(thumbNailPath);
        File videoFile = new File(videoPath + name);
        File resultFile = getThumbnail(videoFile,thumbNailFile);
        s3Service.uploadFile(resultFile);
        String videoURL = "https://ahnbat.kr/stream/"+name;
        String imageURL = s3Service.uploadFile(resultFile);

        return PostVideoAndThumbNail.builder()
                .VideoURL(videoURL)
                .thumbNailURL(imageURL)
                .build();
    }

    public File getThumbnail(File source, File thumbnail) throws IOException, JCodecException {
        log.debug("extracting thumbnail from video");
        int frameNumber = 2;

        Picture picture = FrameGrab.getFrameFromFile(source, frameNumber);

        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
        ImageIO.write(bufferedImage, IMAGE_PNG_FORMAT, thumbnail);
        return thumbnail;
    }


    @Transactional
    public PatchVideo updateVideo(Long videoId) throws BaseException {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new BaseException(FAILED_TO_GET_VIDEO));
        video.setEndLiveStatus("Y");

        return PatchVideo.builder()
                .videoId(videoId)
                .endLiveStatus(video.getEndLiveStatus())
                .build();
    }
}

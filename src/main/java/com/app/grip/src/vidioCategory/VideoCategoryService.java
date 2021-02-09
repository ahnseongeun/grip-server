package com.app.grip.src.vidioCategory;

import com.app.grip.config.BaseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_POST_IMAGE;

@Slf4j
@Service
@AllArgsConstructor
public class VideoCategoryService {

    private VideoCategoryRepository videoCategoryRepository;

    public VideoCategory savePost(String name, String path) throws BaseException {

        VideoCategory videoCategory = VideoCategory.builder()
                .name(name)
                .pictureURL(path)
                .build();

        try{
            videoCategory = videoCategoryRepository.save(videoCategory);
        }catch (Exception e){
            throw new BaseException(FAILED_TO_POST_IMAGE);
        }

        log.info("uploading");
        return VideoCategory.builder()
                .id(videoCategory.getId())
                .name(videoCategory.getName())
                .pictureURL(videoCategory.getPictureURL())
                .build();
    }

}

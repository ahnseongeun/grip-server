package com.app.grip.src.vidioCategory;

import com.app.grip.config.BaseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_POST_IMAGE;

@Service
@AllArgsConstructor
public class VideoCategoryService {

    private VideoCategoryRepository videoCategoryRepository;

    public VideoCategoryInfo savePost(String name, String path) throws BaseException {

        VideoCategoryInfo videoCategoryInfo = VideoCategoryInfo.builder()
                .name(name)
                .pictureURL(path)
                .build();

        try{
            videoCategoryInfo = videoCategoryRepository.save(videoCategoryInfo);
        }catch (Exception e){
            throw new BaseException(FAILED_TO_POST_IMAGE);
        }

        return videoCategoryInfo;
    }

}

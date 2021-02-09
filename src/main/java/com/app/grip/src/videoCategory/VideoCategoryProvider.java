package com.app.grip.src.videoCategory;

import com.app.grip.config.BaseException;
import com.app.grip.src.videoCategory.models.GetVideoCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_VIDEO_CATEGORY;

@Service
public class VideoCategoryProvider {


    private final VideoCategoryRepository videoCategoryRepository;

    @Autowired
    public VideoCategoryProvider(VideoCategoryRepository videoCategoryRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
    }

    public List<GetVideoCategory> retrieveVideoCategoryList() throws BaseException {

            List<VideoCategory> videoCategoryList;
            try{
                videoCategoryList = (List<VideoCategory>) videoCategoryRepository.findAll();
            }catch (Exception exception){
                throw new BaseException(FAILED_TO_GET_VIDEO_CATEGORY);
            }
            return videoCategoryList.stream()
                    .map(videoCategoryInfo -> GetVideoCategory.builder()
                            .id(videoCategoryInfo.getId())
                            .title(videoCategoryInfo.getName())
                            .imageURL(videoCategoryInfo.getPictureURL())
                            .build()).collect(Collectors.toList());

    }
}

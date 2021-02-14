package com.app.grip.src.video.videoLike;

import com.app.grip.config.BaseException;
import com.app.grip.src.video.videoLike.models.GetVideoLike;
import com.app.grip.src.video.videoLike.models.VideoLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_VIDEO_LIKE;

@Service
public class VideoLikeProvider {

    private final VideoLikeRepository videoLikeRepository;

    @Autowired
    public VideoLikeProvider(VideoLikeRepository videoLikeRepository) {
        this.videoLikeRepository = videoLikeRepository;
    }

    public List<GetVideoLike> retrieveVideoLike() throws BaseException {

        List<VideoLike> videoLikeList = (List<VideoLike>) videoLikeRepository.findAll();

//        if (videoLikeList.size() == 0)
//            throw new BaseException(FAILED_TO_GET_VIDEO_LIKE);

        return videoLikeList.stream()
                .map(videoLike -> GetVideoLike.builder()
                        //.videoLikeId(videoLike.getId())
                        .videoId(videoLike.getVideo().getId())
                        .LikeCount(videoLike.getCount())
                        .build())
                .collect(Collectors.toList());
    }
}

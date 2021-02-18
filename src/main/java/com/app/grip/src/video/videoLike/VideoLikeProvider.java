package com.app.grip.src.video.videoLike;

import com.app.grip.config.BaseException;
import com.app.grip.src.video.videoLike.models.GetVideoLike;
import com.app.grip.src.video.videoLike.models.VideoLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_VIDEO_LIKE;

@Service
public class VideoLikeProvider {

    private final VideoLikeRepository videoLikeRepository;
    private final HashMap<Long, Integer> likeRepository;

    @Autowired
    public VideoLikeProvider(VideoLikeRepository videoLikeRepository,
                             HashMap<Long, Integer> likeRepository) {
        this.videoLikeRepository = videoLikeRepository;
        this.likeRepository = likeRepository;
    }

    public List<GetVideoLike> retrieveVideoLike() throws BaseException {


        //List<VideoLike> videoLikeList = (List<VideoLike>) videoLikeRepository.findAll();
        if (likeRepository.size() == 0)
            throw new BaseException(FAILED_TO_GET_VIDEO_LIKE);



        return likeRepository.keySet().stream().map(integer -> GetVideoLike.builder()
                .videoId(integer)
                .LikeCount(likeRepository.get(integer))
                .build())
                .collect(Collectors.toList());

//        return videoLikeList.stream()
//                .map(videoLike -> GetVideoLike.builder()
//                        //.videoLikeId(videoLike.getId())
//                        .videoId(videoLike.getVideo().getId())
//                        .LikeCount(videoLike.getCount())
//                        .build())
//                .collect(Collectors.toList());
    }

    public GetVideoLike retrieveVideoLikeById(Long videoId) {

        int likeCount = 0;
        if(likeRepository.get(videoId) != null){
            likeCount = likeRepository.get(videoId);
        }
        return GetVideoLike.builder()
                .videoId(videoId)
                .LikeCount(likeCount)
                .build();
    }
}

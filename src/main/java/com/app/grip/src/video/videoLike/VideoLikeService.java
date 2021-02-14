package com.app.grip.src.video.videoLike;

import com.app.grip.config.BaseException;
import com.app.grip.config.BaseResponseStatus;
import com.app.grip.src.video.videoLike.models.GetVideoLike;
import com.app.grip.src.video.videoLike.models.PostVideoLike;
import com.app.grip.src.video.videoLike.models.VideoLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class VideoLikeService {

    private final HashMap<Long, Integer> likeRepository;

    @Autowired
    public VideoLikeService(HashMap<Long, Integer> likeRepository) {
        this.likeRepository = likeRepository;
    }

    /**
     * 영상 좋아요 버튼
     * @param videoId
     * @return
     */
    public PostVideoLike createVideoLike(Long videoId) throws BaseException {

        likeRepository.put(videoId,likeRepository.getOrDefault(videoId,0)+1);

        return PostVideoLike.builder()
                .videoId(videoId)
                .LikeCount(likeRepository.get(videoId))
                .build();
    }
}

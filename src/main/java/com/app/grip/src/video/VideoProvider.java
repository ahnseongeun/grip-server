package com.app.grip.src.video;

import com.app.grip.config.BaseException;
import com.app.grip.src.video.models.GetVideos;
import com.app.grip.src.video.models.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.FAILED_TO_GET_VIDEO;

@Service
public class VideoProvider {

    private final VideoRepository videoRepository;

    @Autowired
    public VideoProvider(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public List<GetVideos> retrieveVideos() throws BaseException {

        List<Video> videoList;

        try {
            videoList = videoRepository.findByStatus("Y");
        }catch (Exception exception){
            throw new BaseException(FAILED_TO_GET_VIDEO);
        }


        return videoList.stream()
                .map(video -> GetVideos.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .endLiveStatus(video.getEndLiveStatus())
                        .videoURL(video.getVideoURL())
                        .thumbnailURL(video.getThumbnailURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .categoryId(video.getVideoCategory().getId())
                        .hostNo(video.getUser().getNo())
                        .build())
                .collect(Collectors.toList());
    }
}

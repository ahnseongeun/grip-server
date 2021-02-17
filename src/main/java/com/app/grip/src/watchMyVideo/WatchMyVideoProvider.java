package com.app.grip.src.watchMyVideo;

import com.app.grip.config.BaseException;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.src.video.VideoRepository;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.video.videoParticipant.VideoParticipantRepository;
import com.app.grip.src.video.videoParticipant.models.VideoParticipant;
import com.app.grip.src.watchMyVideo.models.GetWatchMyVideo;
import com.app.grip.utils.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@Service
public class WatchMyVideoProvider {

    private final UserRepository userRepository;
    private final VideoParticipantRepository videoParticipantRepository;
    private final VideoRepository videoRepository;
    private final JwtService jwtService;

    @Autowired
    public WatchMyVideoProvider(UserRepository userRepository,
                                VideoParticipantRepository videoParticipantRepository,
                                VideoRepository videoRepository,
                                JwtService jwtService) {
        this.userRepository = userRepository;
        this.videoParticipantRepository = videoParticipantRepository;
        this.videoRepository = videoRepository;
        this.jwtService = jwtService;
    }


    public List<GetWatchMyVideo> retrieveWatchMyVideo() throws BaseException {

        User user = userRepository.findByNoAndStatus(jwtService.getUserNo(),"Y")
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));

        List<VideoParticipant> videoParticipantList = videoParticipantRepository.findByUserAndStatus(user,"Y");

        if(videoParticipantList.size() == 0)
            throw new BaseException(NOT_FOUND_PARTICIPANT);

        List<Video> videoList = videoParticipantList.stream()
                .map(VideoParticipant::getVideo)
                .collect(Collectors.toList());

        return videoList.stream().map(video ->
                GetWatchMyVideo.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .LiveCheck(video.getLiveCheck())
                        .thumbNailURL(video.getThumbnailURL())
                        .videoLike(video.getVideoLike() == null? 1 : video.getVideoLike().getCount())
                        .watchVideoCount(video.getVideoWatchUserCount())
                        .endLiveTime("한시간전")
                        .hostName(video.getUser().getName())
                        .hostProfileImageURL(video.getUser().getProfileImageURL())
                        .build())
                .collect(Collectors.toList());
    }
}

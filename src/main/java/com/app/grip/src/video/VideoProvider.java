package com.app.grip.src.video;

import com.app.grip.config.BaseException;
import com.app.grip.src.coupon.CouponRepository;
import com.app.grip.src.coupon.models.Coupon;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.src.video.models.GetDetailVideo;
import com.app.grip.src.video.models.GetVideos;
import com.app.grip.src.video.models.Video;
import com.app.grip.utils.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@Service
public class VideoProvider {

    private final VideoRepository videoRepository;
    private final CouponRepository couponRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Autowired
    public VideoProvider(VideoRepository videoRepository, CouponRepository couponRepository,
                         JwtService jwtService, UserRepository userRepository) {
        this.videoRepository = videoRepository;
        this.couponRepository = couponRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
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

    public GetDetailVideo retrieveVideoDetail(Long videoId) throws BaseException {

        User user = userRepository.findByNoAndStatus(jwtService.getUserNo(),"Y")
                .orElseThrow(() -> new BaseException(NOT_FOUND_USER));

        Video video = videoRepository.findByIdAndStatus(videoId,"Y")
                .orElseThrow(() -> new BaseException(FAILED_TO_GET_VIDEO));

        int couponCount = couponRepository.findByStatusAndUser("Y",user).size();

        if(video.getLiveCheck().equals("N")){
                throw new BaseException(NOT_START_VIDEO);
        }

        if(video.getEndLiveStatus().equals("Y")){
            throw new BaseException(END_TO_VIDEO);
        }

        return GetDetailVideo.builder()
                .title(video.getTitle())
                .hostName(video.getUser().getName())
                .hostImageURL(video.getUser().getProfileImageURL())
                .CouponCount(couponCount)
                .liveCheck(video.getLiveCheck())
                //.storeId(video.getUser().getStore().getId())
                .storeId(2L)
                .videoLikeCount(video.getVideoLikeList().size())
                .watchUserCount(video.getVideoWatchUserCount())
                .build();
    }
}
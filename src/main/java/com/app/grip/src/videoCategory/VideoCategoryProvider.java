package com.app.grip.src.videoCategory;

import com.app.grip.config.BaseException;
import com.app.grip.src.video.VideoRepository;
import com.app.grip.src.video.models.GetVideoListByCategory;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.videoCategory.models.GetVideoCategory;
import com.app.grip.src.videoCategory.models.GetVideosCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.app.grip.config.BaseResponseStatus.*;

@Service
public class VideoCategoryProvider {


    private final VideoCategoryRepository videoCategoryRepository;
    private final VideoRepository videoRepository;

    @Autowired
    public VideoCategoryProvider(VideoCategoryRepository videoCategoryRepository,
                                 VideoRepository videoRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.videoRepository = videoRepository;
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
                            .pictureURL(videoCategoryInfo.getPictureURL())
                            .build()).collect(Collectors.toList());

    }
/*
내 팔로잉","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory3 = new VideoCategory("전체 LIVE","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory4 = new VideoCategory("소호몰 언니","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory5 = new VideoCategory("스타일링","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory6 = new VideoCategory("신인","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory7 = new VideoCategory("뷰티꿀팁","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory8 = new VideoCategory("먹방쿡방","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory9 = new VideoCategory("알쓸신템",
 */
    public List<GetVideosCategory> retrieveVideosCategoryList() throws BaseException{

        List<Video> videoList1;
        List<Video> videoList2;
        List<Video> videoList3;
        List<Video> videoList4;
        List<Video> videoList5;
        List<Video> videoList6;
        List<Video> videoList7;
        List<Video> videoList8;
        try {
            videoList1 = videoRepository.findByVideoCategory_IdAndStatus(1L, "Y");
            videoList2 = videoRepository.findByVideoCategory_IdAndStatus(3L, "Y");
            videoList3 = videoRepository.findByVideoCategory_IdAndStatus(4L, "Y");
            videoList4 = videoRepository.findByVideoCategory_IdAndStatus(5L, "Y");
            videoList5 = videoRepository.findByVideoCategory_IdAndStatus(6L, "Y");
            videoList6 = videoRepository.findByVideoCategory_IdAndStatus(7L, "Y");
            videoList7 = videoRepository.findByVideoCategory_IdAndStatus(8L, "Y");
            videoList8 = videoRepository.findByVideoCategory_IdAndStatus(9L, "Y");
        }catch (Exception e){
            throw new BaseException(FAILED_TO_GET_VIDEO);
        }

        GetVideosCategory getVideosCategory1 = new GetVideosCategory("라이브 예고",videoList1.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory2 = new GetVideosCategory("전체 LIVE",videoList2.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory3 = new GetVideosCategory("소호몰 언니",videoList3.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory4 = new GetVideosCategory("스타일링",videoList4.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory5 = new GetVideosCategory("신인",videoList5.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory6 = new GetVideosCategory("뷰티꿀팁",videoList6.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory7 = new GetVideosCategory("먹방쿡방",videoList7.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory8 = new GetVideosCategory("알쓸신템",videoList8.stream()
                .map(video -> GetVideoListByCategory.builder()
                        .videoId(video.getId())
                        .title(video.getTitle())
                        .liveCheck(video.getLiveCheck())
                        .startLiveTime(video.getStartLiveTime())
                        .thumbnailURL(video.getThumbnailURL())
                        .videoURL(video.getVideoURL())
                        .watchUserCount(video.getVideoWatchUserCount())
                        .hostName(video.getUser().getName())
                        .build()).collect(Collectors.toList()));

        List<GetVideosCategory> getVideosCategoryList = new ArrayList<>();
        getVideosCategoryList.add(getVideosCategory1);
        getVideosCategoryList.add(getVideosCategory2);
        getVideosCategoryList.add(getVideosCategory3);
        getVideosCategoryList.add(getVideosCategory4);
        getVideosCategoryList.add(getVideosCategory5);
        getVideosCategoryList.add(getVideosCategory6);
        getVideosCategoryList.add(getVideosCategory7);
        getVideosCategoryList.add(getVideosCategory8);

        return getVideosCategoryList;
    }
}

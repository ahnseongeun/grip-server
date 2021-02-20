package com.app.grip.src.videoCategory;

import com.app.grip.config.BaseException;
import com.app.grip.src.product.models.GetProductInfo;
import com.app.grip.src.video.VideoRepository;
import com.app.grip.src.video.models.GetVideoListByCategory;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.videoCategory.models.GetVideoCategory;
import com.app.grip.src.videoCategory.models.GetVideosCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        //List<Video> findByVideoCategory_IdAndStatusAndEndLiveStatus(Long id,String Status,String LiveStatus);
        //LIVE 중인 방송만 조회
        try {
            //라이브 예고
            videoList1 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(1L, "Y","N");
            //전체 라이브
            videoList2 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(3L, "Y","N");
            //소호몰 언니
            videoList3 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(4L, "Y","N");
            //신인 그리퍼
            videoList4 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(5L, "Y","N");
            //스타일링
            videoList5 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(6L, "Y","N");
            //뷰티꿀팁
            videoList6 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(7L, "Y","N");
            //먹방쿡방
            videoList7 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(8L, "Y","N");
            //알뜰신템
            videoList8 = videoRepository.findByVideoCategory_IdAndStatusAndEndLiveStatus(9L, "Y","N");
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
                        .productInfo(video.getUser().getStore().getProductList().stream().findFirst()
                                .map(product -> GetProductInfo.builder()
                                        .productPrice(product.getPrice())
                                        .productContent(product.getContent())
                                        .productImageURL(product.getPictureURL())
                                        .build())
                                .orElse(null))
//                        .productInfo(GetProductInfo.builder()
//                                .productPrice("30000원")
//                                .productImageURL("https://cdn.imweb.me/thumbnail/20200924/e1f059d0cb20b.jpg")
//                                .productContent("신상 팔아요")
//                                .build())
                        .build()).collect(Collectors.toList()));

        GetVideosCategory getVideosCategory3 = new GetVideosCategory("신인 그리퍼",videoList4.stream()
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

        GetVideosCategory getVideosCategory4 = new GetVideosCategory("스타일링",videoList5.stream()
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

        GetVideosCategory getVideosCategory5 = new GetVideosCategory("소호몰 언니",videoList3.stream()
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

    public List<GetVideoListByCategory> retrieveVideosByCategory(String categoryName) throws BaseException {

        List<Video> videoList = videoRepository.findByVideoCategory_NameAndStatus(categoryName,"Y");

        if(videoList.size() == 0)
            throw new BaseException(FAILED_TO_GET_VIDEO);

        return videoList.stream().map(video -> GetVideoListByCategory.builder()
                .hostName(video.getUser().getName())
                .videoId(video.getId())
                .videoURL(video.getVideoURL())
                .watchUserCount(video.getVideoWatchUserCount())
                .thumbnailURL(video.getThumbnailURL())
                .startLiveTime(video.getStartLiveTime())
                .title(video.getTitle())
                .liveCheck(video.getLiveCheck())
                .productInfo(video.getUser().getStore().getProductList().stream().findFirst()
                        .map(product -> GetProductInfo.builder()
                                .productPrice(product.getPrice())
                                .productContent(product.getContent())
                                .productImageURL(product.getPictureURL())
                                .build())
                        .orElse(null))
                .build()).collect(Collectors.toList());
    }
}

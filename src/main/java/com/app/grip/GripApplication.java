package com.app.grip;

import com.app.grip.src.advertisement.AdvertisementRepository;
import com.app.grip.src.advertisement.models.Advertisement;
import com.app.grip.src.user.UserRepository;
import com.app.grip.src.user.models.User;
import com.app.grip.src.video.VideoRepository;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.videoCategory.VideoCategory;
import com.app.grip.src.videoCategory.VideoCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;
import java.util.List;

@EnableJpaAuditing
@SpringBootApplication
public class GripApplication implements CommandLineRunner {

    private final VideoCategoryRepository videoCategoryRepository;
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;

    public GripApplication(VideoCategoryRepository videoCategoryRepository,
                           AdvertisementRepository advertisementRepository,
                           UserRepository userRepository,
                           VideoRepository videoRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GripApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * 유저 더미 데이터 삽입
         */
        User user1 = new User("김철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1111", "1995-03-10", "test1@naver.com", "M",
                "N", 1, "Y", "111111");

        User user2 = new User("안철수","철수입니다"
                        ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                        "010-1111-1112", "1995-03-12", "test2@naver.com", "M",
                        "N", 1, "Y", "111112");

        User user3 = new User("홍철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1113", "1995-03-13", "test3@naver.com", "W",
                "N", 1, "Y", "111113");

        User grapher1 = new User("이철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1114", "1995-03-14", "test4@naver.com", "M",
                "N", 50, "Y", "111114");

        User grapher2 = new User("이철박","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1115", "1995-03-15", "test5@naver.com", "M",
                "N", 50, "Y", "111115");

        User grapher3 = new User("박철이","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1116", "1995-03-16", "test6@naver.com", "M",
                "N", 50, "Y", "111116");

        User grapher4 = new User("김수철","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1117", "1995-03-17", "test7@naver.com", "M",
                "N", 50, "Y", "111117");

        User admin = new User("박철수","철수입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1118", "1995-03-18", "test8@naver.com", "M",
                "N", 100, "Y", "111118");



        final List<User> userList =
                Arrays.asList(user1,user2,user3,grapher1,grapher2,grapher3,grapher4,admin);

        List<User> savedUser = (List<User>) userRepository.saveAll(userList);

        /**
         * 영상 카테고리 더미 데이터 삽입
         */
        VideoCategory videoCategory1 = new VideoCategory("라이브 예고","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory2 = new VideoCategory("내 팔로잉","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory3 = new VideoCategory("전체 LIVE","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory4 = new VideoCategory("소호몰 언니","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory5 = new VideoCategory("스타일링","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory6 = new VideoCategory("신인","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory7 = new VideoCategory("뷰티꿀팁","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory8 = new VideoCategory("먹방쿡방","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory9 = new VideoCategory("알쓸신템","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");

            final List<VideoCategory> videoCategoryList =
                Arrays.asList(
                        videoCategory1,videoCategory2,videoCategory3,
                        videoCategory4,videoCategory5,videoCategory6,
                        videoCategory7,videoCategory8,videoCategory9);

        List<VideoCategory> savedVideoCategory = (List<VideoCategory>) videoCategoryRepository.saveAll(videoCategoryList);


        /**
         * 광고 더미 데이터 삽입
         */
        final List<Advertisement> advertisementList =
                Arrays.asList(
                        new Advertisement("광고1번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고2번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고3번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고4번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고5번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고6번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고7번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L),
                        new Advertisement("광고8번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg",1L));


        List<Advertisement> savedAdvertisement = (List<Advertisement>) advertisementRepository.saveAll(advertisementList);

        /**
         * 영상 더미 데이터 삽입
         */
        final List<Video> videoList =
                Arrays.asList(
                        //라이브 예고
                        new Video("곽스타","Y","2-12 20:00","22:00",""
                                ,"",0,videoCategory1,grapher1),
                        new Video("네이플 본사 공식몰","Y","2-12 20:00","23:00",""
                                ,"",0,videoCategory1,grapher2),
                        new Video("고랭고랭","Y","2-13 20:00","22:00",""
                                ,"",0,videoCategory1,grapher3),

                        //전체 라이브
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher1),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher3),

                        //소호몰 언니
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher1),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),

                        //스타일링링
                       new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher3),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),

                        //신인
                       new Video("","","","",""
                                ,"",0,videoCategory1,grapher1),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher3),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),

                        //뷰티 꿀팁
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher3),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),

                        //먹방쿡방
                       new Video("","","","",""
                                ,"",0,videoCategory1,grapher1),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),

                        //알쓸신템
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher4),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher2),
                        new Video("","","","",""
                                ,"",0,videoCategory1,grapher3)
                        );



        List<Video> savedVideo = (List<Video>) videoRepository.saveAll(videoList);

    }

    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}

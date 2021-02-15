package com.app.grip;

import com.app.grip.src.advertisement.AdvertisementRepository;
import com.app.grip.src.advertisement.models.Advertisement;
import com.app.grip.src.store.StoreRepository;
import com.app.grip.src.store.models.Store;
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
    private final StoreRepository storeRepository;

    public GripApplication(VideoCategoryRepository videoCategoryRepository,
                           AdvertisementRepository advertisementRepository,
                           UserRepository userRepository, VideoRepository videoRepository,
                           StoreRepository storeRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.storeRepository = storeRepository;
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

        User userFace1 = new User("김도윤","도윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1111", "1991-03-10", "test1@gmail.com", "M",
                "F", 1, "Y", "111111");

        User userFace2 = new User("윤서윤","서윤입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1113", "1996-03-13", "test3@gmail.com", "W",
                "F", 1, "Y", "111113");

        User grapherFace1 = new User("김하준","하준입니다"
                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/user.png",
                "010-1111-1112", "1993-03-12", "test2@gmail.com", "M",
                "F", 50, "Y", "111112");



        final List<User> userList =
                Arrays.asList(user1,user2,user3,grapher1,grapher2,grapher3,grapher4,admin,
                        userFace1,userFace2,grapherFace1);

        List<User> savedUser = (List<User>) userRepository.saveAll(userList);


        /**
         * 상점 더미 데이터 삽입
         */
        Store store1 = new Store("블링 블링 액세서리",
                "아름다운 액세서리들이 모여 있습니다.\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store1.setUser(grapher1);

        Store store2 = new Store("머리부터 발끝까지 핫이슈~",
                "핫하고 요즘 인기있는 옷과 아이템들이 구비되어 있습니다~\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store2.setUser(grapher2);

        Store store3 = new Store("핸드메이드 주스",
                "직접 재배한 유기농 채소와 과일로 만든 핸드메이드 주스!\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store3.setUser(grapher3);

        Store store4 = new Store("최신 전자기기 여기 다있다!",
                "세상에 있는 모든 최신 전자기기 모여있는 곳!\n세상에서 가장 핫한 기기! 구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store4.setUser(grapher4);

        Store store5 = new Store("하태 하태",
                "세상 모든 핫한 제품만 모아 모아 팝니다!\n구경하러 오세요~",
                "https://cdn.icon-icons.com/icons2/1993/PNG/512/ecommerce_home_market_mart_shop_shopping_store_icon_123207.png");
        store5.setUser(grapherFace1);

        final List<Store> storeList = Arrays.asList(store1,store2,store3,store4,store5);
        List<Store> savedStore = (List<Store>) storeRepository.saveAll(storeList);


        /**
         * 영상 카테고리 더미 데이터 삽입
         */
        VideoCategory videoCategory1 = new VideoCategory("라이브예고","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory2 = new VideoCategory("내팔로잉","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory3 = new VideoCategory("전체LIVE","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory4 = new VideoCategory("소호몰언니","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory5 = new VideoCategory("스타일링","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
        VideoCategory videoCategory6 = new VideoCategory("신인그리퍼","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg");
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
        //yyyy년 MM월 dd일 HH시 mm분 ss초
        final List<Video> videoList =
                Arrays.asList(
                        //라이브 예고
                        new Video("곽스타","N","2021년 02월 12일 20시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory1,grapher1),
                        new Video("네이플 본사 공식몰","N","2021년 02월 12일 20시 00분","N","https://ahnbat.kr/stream/video3.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory1,grapher2),
                        new Video("고랭고랭","N","2021년 02월 13일 20시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory1,grapher3),

                        //전체 라이브
                        new Video("2부시작5초전","Y","2021년 02월 12일 17시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory3,grapher1),
                        new Video("오버티 55-88까지 맨투맨","Y","2021년 02월 12일 15시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory3,grapher2),
                        new Video("짧은라방, 구두가방","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory3,grapher3),

                        //소호몰 언니
                        new Video("째즈언니","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory4,grapher1),
                        new Video("신사%세일해용, 10만원이상 선물","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory4,grapher2),
                        new Video("반지할인, 새해복_언니들","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video3.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory4,grapher4),

                        //스타일링
                       new Video("2부시작5초전","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory5,grapher2),
                        new Video("짧은라방, 구두가방","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory5,grapher3),
                        new Video("올해는 나 장가갈꺼래...","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory5,grapher4),

                        //신인
                       new Video("오늘도 수고했어~","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video3.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory6,grapher1),
                        new Video("다시 소통 방송 들어와요ㅠㅠ","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory6,grapher3),
                        new Video("소통과 판매 논녀와용...","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory6,grapher4),

                        //뷰티 꿀팁
                        new Video("복주머니!!!","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory7,grapher4),
                        new Video("셀로니아 줄기세포 샴푸!!","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video3.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory7,grapher3),
                        new Video("피부에 양보하세요","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory7,grapher2),

                        //먹방쿡방
                       new Video("크로플+누텔라","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory8,grapher1),
                        new Video("쭈꾸미 품절!!!일보직전!!","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory8,grapher4),
                        new Video("피자나라 치킨공주","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video3.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video3.png",0,videoCategory8,grapher2),

                        //알쓸신템
                        new Video("사라 사라 사라 양말좀 사라~~","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video2.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video2.png",0,videoCategory9,grapher4),
                        new Video("이걸 안들어와??","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/test.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/test.png",0,videoCategory9,grapher2),
                        new Video("롱패딩 딱대!!","Y","2021년 02월 12일 16시 00분","N","https://ahnbat.kr/stream/video1.mp4"
                                ,"https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/video1.png",0,videoCategory9,grapher3)
                        );



        List<Video> savedVideo = (List<Video>) videoRepository.saveAll(videoList);

    }

    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}

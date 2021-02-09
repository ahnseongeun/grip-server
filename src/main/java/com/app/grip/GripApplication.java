package com.app.grip;

import com.app.grip.src.advertisement.AdvertisementRepository;
import com.app.grip.src.advertisement.models.Advertisement;
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

    public GripApplication(VideoCategoryRepository videoCategoryRepository,
                           AdvertisementRepository advertisementRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
        this.advertisementRepository = advertisementRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(GripApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        /**
         * 영상 카테고리 더미 데이터 삽입
         */
        final List<VideoCategory> videoCategoryList =
                Arrays.asList(
                        new VideoCategory("카테고리1번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리2번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리3번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리4번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리5번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리6번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리7번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리8번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"),
                        new VideoCategory("카테고리9번","https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg"));

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

    }

    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}

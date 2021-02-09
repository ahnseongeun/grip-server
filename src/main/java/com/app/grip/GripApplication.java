package com.app.grip;

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

    public GripApplication(VideoCategoryRepository videoCategoryRepository) {
        this.videoCategoryRepository = videoCategoryRepository;
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

    }

    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}

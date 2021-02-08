package com.app.grip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GripApplication {

    public static void main(String[] args) {
        SpringApplication.run(GripApplication.class, args);
    }

    
    //https://grip-image-directory.s3.ap-northeast-2.amazonaws.com/pizza.jpg 사진 더미데이터







}

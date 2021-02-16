package com.app.grip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.HashMap;

@Configuration
public class MyConfig {

    @Bean
    public HashMap<Long, Integer> LikeRepository(){
        return new HashMap<>();
    }

    @Bean
    public HashMap<String, Long> StreamingRepository(){
        return new HashMap<>();
    }
}

package com.app.grip.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableScheduling
public class MyConfig {

    @Bean
    public HashMap<Long, Integer> LikeRepository(){
        return new HashMap<>();
    }

    @Bean
    @Qualifier("streaming")
    public HashMap<String, Integer> StreamingRepository(){
        return new HashMap<>();
    }

    @Bean
    @Qualifier("streamingSize")
    public HashMap<String, Integer> StreamingSizeRepository(){
        return new HashMap<>();
    }

    @Bean
    public TaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(1);
        return scheduler;
    }
}

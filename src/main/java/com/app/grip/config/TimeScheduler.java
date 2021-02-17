package com.app.grip.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class TimeScheduler {

    private final HashMap<String, List<Integer>> StreamingRepository;

    @Autowired
    public TimeScheduler(HashMap<String, List<Integer>> streamingRepository) {
        this.StreamingRepository = streamingRepository;
    }


    @Scheduled(fixedDelay = 1000)
    public void alert() {
        String video1 = "https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8";
        String video2 = "https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8";
        List<Integer> list1 = StreamingRepository.get(video1);
        List<Integer> list2 = StreamingRepository.get(video2);
        log.info("test");
        if(list1 == null)
            return;
        if(list2 == null)
            return;
        if(list1.get(0) <= list1.get(1)){
            list1.add(0,list1.get(0)+1);
        }else{
            list1.add(0,0);
        }
        if(list2.get(0) <= list2.get(1)){
            list2.add(0,list2.get(0)+1);
        }else{
            list2.add(0,0);
        }
        log.info(list1.get(0)+" "+ list2.get(0));
    }
}

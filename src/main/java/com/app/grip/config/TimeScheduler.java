package com.app.grip.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class TimeScheduler {

    private final HashMap<String, Integer> StreamingRepository;
    private final HashMap<String, Integer> StreamingSizeRepository;

    @Autowired
    public TimeScheduler(
            @Qualifier("streaming") HashMap<String, Integer> streamingRepository,
            @Qualifier("streamingSize") HashMap<String, Integer> streamingSizeRepository) {
        this.StreamingRepository = streamingRepository;
        this.StreamingSizeRepository = streamingSizeRepository;
    }


    @Scheduled(fixedDelay = 5000)
    public void alert() {
        //String video1 = "C:\\home1\\ffmpeg-4.3.2-2021-02-02-full_build\\ffmpeg-4.3.2-2021-02-02-full_build\\bin\\video1\\videotest1.m3u8";
        //String video2 = "C:\\home1\\ffmpeg-4.3.2-2021-02-02-full_build\\ffmpeg-4.3.2-2021-02-02-full_build\\bin\\video2\\videotest2.m3u8";
        String video1 = "https://subdomain.ahnbat.kr/video/video1/videotest1.m3u8";
        String video2 = "https://subdomain.ahnbat.kr/video/video2/videotest2.m3u8";
        if(StreamingRepository.get(video1) == null)
            return;
        if(StreamingRepository.get(video2) == null)
            return;
        int time1 = StreamingRepository.get(video1);
        int time1Size = StreamingSizeRepository.get(video1);
        int time2 = StreamingRepository.get(video2);
        int time2Size = StreamingSizeRepository.get(video2);

        StreamingRepository.put(video1,timeCheck(time1,time1Size));
        StreamingRepository.put(video2,timeCheck(time2,time2Size));
        //log.info(StreamingRepository.get(video1) +" "+StreamingRepository.get(video2));
    }

    private int timeCheck(int time, int timeSize) {
        if(time <= timeSize){
            return time+1;
        }else{
            return 0;
        }
    }
}

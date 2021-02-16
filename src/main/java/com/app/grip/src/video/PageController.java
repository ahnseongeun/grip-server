package com.app.grip.src.video;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.probe.FFmpegFormat;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import static java.time.LocalDateTime.now;

@Slf4j
@Controller
public class PageController {

//    @Value("${video.location}")
//    private String videoLocation;

    private final VideoProvider videoProvider;

    public PageController(VideoProvider videoProvider) {
        this.videoProvider = videoProvider;
    }

//    @GetMapping("/video-list")
//    @ApiOperation(value = "영상 리스트 조회(동작 안함)", notes = "영상 리스트 조회")
//    String index(Model model) throws IOException {
//
//        log.info(videoLocation);
//
//        ClassPathResource resource = new ClassPathResource(videoLocation);
//
//        Object[] videos = Files.list(Paths.get(resource.getURI()))
//                            .map(f -> f.getFileName().toString())
//                            .toArray();
//
//        model.addAttribute("videos", videos);
//        return "index";
//    }

    @GetMapping("/stream/{videoName}")
    @ApiOperation(value = "영상 시청", notes = "영상 시청")
    public String video(@PathVariable String videoName, Model model) throws MalformedURLException {
        log.info("test1");
        log.info("test2");
        //model.addAttribute("videoName", videoName);
        log.info("test3");
  //      //model.addAttribute("start", now().getSecond());
        return "video";
    }

}

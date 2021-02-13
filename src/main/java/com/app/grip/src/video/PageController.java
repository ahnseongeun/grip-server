package com.app.grip.src.video;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class PageController {

    @Value("${video.location}")
    private String videoLocation;

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
    public String video(@PathVariable String videoName, Model model){
        model.addAttribute("videoName", videoName);
        return "video";
    }

}
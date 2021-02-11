package com.app.grip.src.video;

import lombok.extern.slf4j.Slf4j;
import org.jcodec.api.FrameGrab;
import org.jcodec.api.JCodecException;
import org.jcodec.common.model.Picture;
import org.jcodec.scale.AWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@RestController
@RequestMapping("/api")
public class VideoController {

    private static final String IMAGE_PNG_FORMAT = "png";
    private final ResourceLoader resourceLoader;

    @Autowired
    public VideoController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/videos/{name}/full")
    public ResponseEntity<UrlResource> getFullVideo(@PathVariable String name) throws MalformedURLException {
        UrlResource video = new UrlResource("file:${video.location}/${name}");
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(video);
    }

    @GetMapping("/videos/{name}")
    public ResponseEntity<ResourceRegion> getVideo(@PathVariable String name,
                                                   @RequestHeader HttpHeaders headers) throws IOException {
        String path = "/home/ubuntu/videos/";
        //UrlResource
        System.out.println(path);
        UrlResource video = new UrlResource(path+name);
        log.info(video.toString());
        ResourceRegion region = resourceRegion(video, headers);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                            .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                            .body(region);
    }

    @GetMapping("/videos/getThumbNail/{name}")
    public File getSumNail(@PathVariable String name,
                                                   @RequestHeader HttpHeaders headers) throws IOException, JCodecException {

        log.info("getThumbNail");
        String videoPath = "/home/ubuntu/video/" + name;
        String thumbNailPath = "/home/ubuntu/image/test.png";
                //System.getProperty("user.home")+"/src/main/resources/thumbnails/";
        File thumbNailFile = new File(thumbNailPath);
        File videoFile = new File(videoPath + name);
        log.info("getThumbNail1");
        return getThumbnail(videoFile,thumbNailFile);
    }

    private ResourceRegion resourceRegion(UrlResource video, HttpHeaders headers) throws IOException {

        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        HttpRange httpRange = headers.getRange().stream().findFirst().get();
        if(httpRange != null) {
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            return new ResourceRegion(video, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            return new ResourceRegion(video, 0, rangeLength);
        }
    }

    public File getThumbnail(File source, File thumbnail) throws IOException, JCodecException {
        log.debug("extracting thumbnail from video");
        int frameNumber = 0;

        Picture picture = FrameGrab.getFrameFromFile(source, frameNumber);

        BufferedImage bufferedImage = AWTUtil.toBufferedImage(picture);
        ImageIO.write(bufferedImage, IMAGE_PNG_FORMAT, thumbnail);
        return thumbnail;
    }

}

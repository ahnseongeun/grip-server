package com.app.grip.src.vidioCategory;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VideoCategoryService {

    private VideoCategoryRepository videoCategoryRepository;

    public void savePost(String name, String path) {

        VideoCategoryInfo videoCategoryInfo = VideoCategoryInfo.builder()
                .name(name)
                .pictureURL(path)
                .build();
        videoCategoryRepository.save(videoCategoryInfo);
    }

}

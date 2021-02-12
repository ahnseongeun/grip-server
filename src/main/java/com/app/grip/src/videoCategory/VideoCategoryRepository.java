package com.app.grip.src.videoCategory;

import com.app.grip.src.video.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoCategoryRepository extends CrudRepository<VideoCategory, Long> {
    List<Video> findByNameAndStatus(String name, String Status);
}

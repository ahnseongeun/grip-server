package com.app.grip.src.video;

import com.app.grip.src.video.models.Video;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<Video, Long> {
}

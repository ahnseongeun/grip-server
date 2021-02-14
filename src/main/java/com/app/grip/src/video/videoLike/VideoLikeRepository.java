package com.app.grip.src.video.videoLike;

import com.app.grip.src.video.models.Video;
import com.app.grip.src.video.videoLike.models.VideoLike;
import org.springframework.data.repository.CrudRepository;

public interface VideoLikeRepository extends CrudRepository<VideoLike, Long> {
}

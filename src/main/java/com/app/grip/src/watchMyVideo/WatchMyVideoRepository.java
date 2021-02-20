package com.app.grip.src.watchMyVideo;

import com.app.grip.src.user.models.User;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.watchMyVideo.models.WatchMyVideo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WatchMyVideoRepository extends CrudRepository<WatchMyVideo, Long> {
    Optional<WatchMyVideo> findByUserAndVideoAndStatus(User user, Video video, String y);
}

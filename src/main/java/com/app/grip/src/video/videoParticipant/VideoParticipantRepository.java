package com.app.grip.src.video.videoParticipant;

import com.app.grip.src.user.models.User;
import com.app.grip.src.video.models.Video;
import com.app.grip.src.video.videoParticipant.models.VideoParticipant;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoParticipantRepository extends CrudRepository<VideoParticipant,Long> {
    Optional<VideoParticipant> findByUserAndVideo(User user, Video video);

    Optional<VideoParticipant> findByVideo(Video video);

    int countByVideo(Video video);

    List<VideoParticipant> findByUserAndStatus(User user, String y);
}

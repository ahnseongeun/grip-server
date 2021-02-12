package com.app.grip.src.video;

import com.app.grip.src.video.models.Video;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video, Long> {
    List<Video> findByStatus(String y);
    List<Video> findByVideoCategory_NameAndStatus(String name,String status);

    List<Video> findByVideoCategory_IdAndStatus(Long id, String y);
}

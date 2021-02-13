package com.app.grip.src.videoCategory.models;

import com.app.grip.src.video.models.GetVideoListByCategory;
import com.app.grip.src.video.models.GetVideos;
import com.app.grip.src.video.models.Video;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetVideosCategory {

    private final String categoryName;
    private final List<GetVideoListByCategory> videoList;
}

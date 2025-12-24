package com.exemple.mediaserver.dao;

import com.exemple.mediaserver.model.VideoModel;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class VideoDao {
    private final Map<String, VideoModel> videos = new ConcurrentHashMap<>();

    public VideoModel save(VideoModel video) {
        if (video.getId() == null) {
            video.setId(UUID.randomUUID().toString());
        }
        videos.put(video.getId(), video);
        return video;
    }

    public Optional<VideoModel> findById(String id) {
        return Optional.ofNullable(videos.get(id));
    }
    public List<VideoModel> findAll() {
        return new ArrayList<>(videos.values());
    }
    public List<VideoModel> findByCreatorId(String creatorId) {
        return videos.values().stream()
                .filter(v -> v.getCreator() != null &&
                        v.getCreator().getId().equals(creatorId))
                .collect(Collectors.toList());
    }

}

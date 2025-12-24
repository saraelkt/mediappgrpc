package com.exemple.mediaclient.Mapper;

import com.exemple.mediaclient.Dto.CreatorDto;
import com.exemple.mediaclient.Dto.VideoDto;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.Video;

@Component
public class VideoMapper {
    public VideoDto fromVideoProtoTovideoDto(Video video) {
        if (video == null) {
            return null;
        }

        Creator creatorProto = video.getCreator();

        CreatorDto creatorDto = new CreatorDto(
                creatorProto.getId(),
                creatorProto.getName(),
                creatorProto.getEmail()
        );

        VideoDto dto = new VideoDto();
        dto.setId(video.getId());
        dto.setTitle(video.getTitle());
        dto.setDescription(video.getDescription());
        dto.setUrl(video.getUrl());
        dto.setDurationSeconds(video.getDurationSeconds());
        dto.setCreator(creatorDto);

        return dto;
    }
}
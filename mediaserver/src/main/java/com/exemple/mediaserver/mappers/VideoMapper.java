package com.exemple.mediaserver.mappers;

import com.exemple.mediaserver.dao.CreatorDao;
import com.exemple.mediaserver.model.CreatorModel;
import com.exemple.mediaserver.model.VideoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;

@Component
public class VideoMapper {

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private CreatorDao creatorDao;

    public VideoModel fromUploadRequestToModel(UploadVideoRequest request) {
        VideoModel model = new VideoModel();
        model.setTitle(request.getTitle());
        model.setDescription(request.getDescription());
        model.setUrl(request.getUrl());
        model.setDurationSeconds(request.getDurationSeconds());

        // Sauvegarder le créateur aussi
        if (request.hasCreator()) {
            CreatorModel creatorModel = creatorMapper.fromProtoToModel(request.getCreator());
            creatorModel = creatorDao.save(creatorModel);
            model.setCreator(creatorModel);
        }

        return model;
    }

    public Video fromModelToProto(VideoModel model) {
        Video.Builder builder = Video.newBuilder()
                .setId(model.getId())
                .setTitle(model.getTitle())
                .setDescription(model.getDescription())
                .setUrl(model.getUrl())
                .setDurationSeconds((int) model.getDurationSeconds());

        if (model.getCreator() != null) {
            builder.setCreator(creatorMapper.fromModelToProto(model.getCreator()));
        }

        return builder.build();
    }
}
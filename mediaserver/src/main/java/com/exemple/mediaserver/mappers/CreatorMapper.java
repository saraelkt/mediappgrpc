package com.exemple.mediaserver.mappers;

import com.exemple.mediaserver.model.CreatorModel;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorMapper {

    // Convertir Creator proto -> CreatorModel
    public CreatorModel fromProtoToModel(Creator proto) {
        if (proto == null) {
            return null;
        }
        CreatorModel model = new CreatorModel();
        model.setId(proto.getId());
        model.setName(proto.getName());
        model.setEmail(proto.getEmail());
        return model;
    }

    // Convertir CreatorModel -> Creator proto
    public Creator fromModelToProto(CreatorModel model) {
        if (model == null) {
            return null;
        }
        return Creator.newBuilder()
                .setId(model.getId())
                .setName(model.getName())
                .setEmail(model.getEmail())
                .build();
    }
}
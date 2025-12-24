package com.exemple.mediaclient.Mapper;

import com.exemple.mediaclient.Dto.CreatorDto;
import org.springframework.stereotype.Component;
import org.xproce.lab.Creator;

@Component
public class CreatorMapper {

    public CreatorDto fromProtoToDto(Creator proto) {
        if (proto == null) {
            return null;
        }

        return new CreatorDto(
                proto.getId(),
                proto.getName(),
                proto.getEmail()
        );
    }

    public Creator fromDtoToProto(CreatorDto dto) {
        if (dto == null) {
            return null;
        }

        Creator.Builder builder = Creator.newBuilder()
                .setName(dto.getName())
                .setEmail(dto.getEmail());

        if (dto.getId() != null && !dto.getId().isEmpty()) {
            builder.setId(dto.getId());
        }

        return builder.build();
    }
}
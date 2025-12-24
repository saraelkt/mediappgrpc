package com.exemple.mediaclient.Service;

import com.exemple.mediaclient.Dto.CreatorDto;
import com.exemple.mediaclient.Dto.VideoDto;
import com.exemple.mediaclient.Mapper.CreatorMapper;
import com.exemple.mediaclient.Mapper.VideoMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.lab.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreatorServiceClient {

    @GrpcClient("mediaserver")
    private CreatorServiceGrpc.CreatorServiceBlockingStub stub;

    @Autowired
    private CreatorMapper creatorMapper;

    @Autowired
    private VideoMapper videoMapper;

    public CreatorDto getCreator(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(id)
                .build();

        Creator creator = stub.getCreator(request);
        return creatorMapper.fromProtoToDto(creator);
    }

    public List<VideoDto> getCreatorVideos(String id) {
        CreatorIdRequest request = CreatorIdRequest.newBuilder()
                .setId(id)
                .build();

        VideoStream videoStream = stub.getCreatorVideos(request);

        return videoStream.getVideosList().stream()
                .map(videoMapper::fromVideoProtoTovideoDto)
                .collect(Collectors.toList());
    }
}
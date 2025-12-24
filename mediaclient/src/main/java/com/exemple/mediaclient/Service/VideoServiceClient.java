package com.exemple.mediaclient.Service;

import com.exemple.mediaclient.Dto.VideoDto;
import com.exemple.mediaclient.Mapper.VideoMapper;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xproce.lab.*;

@Service
public class VideoServiceClient {

    @GrpcClient("mediaserver")
    private VideoServiceGrpc.VideoServiceBlockingStub stub;

    @Autowired
    private VideoMapper mapper;

    public VideoDto uploadVideo(UploadVideoRequest videoRequest) {
        Video video = stub.uploadVideo(videoRequest);
        return mapper.fromVideoProtoTovideoDto(video);
    }


    public VideoDto getVideo(String id) {
        VideoIdRequest request = VideoIdRequest.newBuilder()
                .setId(id)
                .build();

        Video video = stub.getVideo(request);
        return mapper.fromVideoProtoTovideoDto(video);
    }
}
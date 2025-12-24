package com.exemple.mediaserver.services;

import com.exemple.mediaserver.dao.CreatorDao;
import com.exemple.mediaserver.dao.VideoDao;
import com.exemple.mediaserver.mappers.CreatorMapper;
import com.exemple.mediaserver.mappers.VideoMapper;
import com.exemple.mediaserver.model.CreatorModel;
import com.exemple.mediaserver.model.VideoModel;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.*;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CreatorService extends CreatorServiceGrpc.CreatorServiceImplBase {

    @Autowired
    private VideoDao videoDao;

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private VideoMapper videoMapper;
    @Autowired
    private CreatorMapper creatorMapper;

    @Override
    public void getCreatorVideos(CreatorIdRequest request,
                                 StreamObserver<VideoStream> responseObserver) {  // ✅ VideoStream au lieu de VideoList
        try {
            // 1. Récupérer l'ID du créateur
            String creatorId = request.getId();

            // 2. Chercher toutes les vidéos
            List<VideoModel> allVideos = videoDao.findAll();

            // 3. Convertir les VideoModel en Video proto
            List<Video> videoProtos = allVideos.stream()
                    .map(videoMapper::fromModelToProto)
                    .collect(Collectors.toList());

            // 4. Créer la réponse VideoStream
            VideoStream videoStream = VideoStream.newBuilder()
                    .addAllVideos(videoProtos)
                    .build();

            // 5. Envoyer la réponse
            responseObserver.onNext(videoStream);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // Gérer les erreurs
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Error getting creator videos: " + e.getMessage())
                            .asRuntimeException()
            );
        }
    }
    @Override
    public void getCreator(CreatorIdRequest request,
                           StreamObserver<Creator> responseObserver) {
        try {
            // 1. Récupérer l'ID du créateur
            String creatorId = request.getId();

            // 2. Chercher le créateur dans la base de données
            CreatorModel creatorModel = creatorDao.findById(creatorId)
                    .orElseThrow(() -> new RuntimeException("Creator not found: " + creatorId));

            // 3. Convertir CreatorModel -> Creator proto
            Creator creator = creatorMapper.fromModelToProto(creatorModel);

            // 4. Envoyer la réponse
            responseObserver.onNext(creator);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // Gérer les erreurs
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Error getting creator: " + e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}
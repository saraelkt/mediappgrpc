package com.exemple.mediaserver.services;

import com.exemple.mediaserver.dao.VideoDao;
import com.exemple.mediaserver.mappers.VideoMapper;
import com.exemple.mediaserver.model.VideoModel;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.xproce.lab.UploadVideoRequest;
import org.xproce.lab.Video;
import org.xproce.lab.VideoIdRequest;
import org.xproce.lab.VideoServiceGrpc;

@GrpcService
public class VideoService extends VideoServiceGrpc.VideoServiceImplBase  {
    @Autowired
    private VideoDao videoDao;

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void uploadVideo(UploadVideoRequest request,
                            StreamObserver<Video> responseObserver) {

        // 1) Request PROTO -> Model
        VideoModel model = videoMapper.fromUploadRequestToModel(request);

        // 2) Sauvegarde en "dao"
        model = videoDao.save(model);

        // 3) Model -> Video PROTO pour la réponse
        Video response = videoMapper.fromModelToProto(model);

        // 4) Envoi au client
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    @Override
    public void getVideo(VideoIdRequest request,
                         StreamObserver<Video> responseObserver) {
        try {
            // 1. Récupérer l'ID de la requête
            String videoId = request.getId();

            // 2. Chercher la vidéo dans la base de données (retourne Optional<VideoModel>)
            VideoModel videoModel = videoDao.findById(videoId)
                    .orElseThrow(() -> new RuntimeException("Video not found: " + videoId));

            // 3. Utiliser le mapper pour convertir VideoModel -> Video proto
            Video video = videoMapper.fromModelToProto(videoModel);

            // 4. ✅ Envoyer la réponse
            responseObserver.onNext(video);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // 5. Gérer les erreurs
            responseObserver.onError(
                    Status.INTERNAL
                            .withDescription("Error getting video: " + e.getMessage())
                            .asRuntimeException()
            );
        }
    }
}

package com.exemple.mediaclient.Controllers;

import com.exemple.mediaclient.Dto.CreatorDto;
import com.exemple.mediaclient.Dto.VideoDto;
import com.exemple.mediaclient.Service.CreatorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CreatorController {

    @Autowired
    private CreatorServiceClient creatorService;

    @GetMapping("getCreator/{id}")
    public CreatorDto getCreator(@PathVariable String id) {
        return creatorService.getCreator(id);
    }

    @GetMapping("getCreatorVideos/{id}")
    public List<VideoDto> getCreatorVideos(@PathVariable String id) {
        return creatorService.getCreatorVideos(id);
    }
}

package com.atem.graphqldemo.web;

import com.atem.graphqldemo.dao.entities.Creator;
import com.atem.graphqldemo.dao.entities.Video;
import com.atem.graphqldemo.dao.repositories.CreatorRepository;
import com.atem.graphqldemo.dao.repositories.VideoRepository;
import com.atem.graphqldemo.dto.CreatorRequestDTO;
import com.atem.graphqldemo.dto.VideoRequestDTO;
import com.atem.graphqldemo.service.CreatorManager;
import com.atem.graphqldemo.service.mappers.CreatorMapper;
import com.atem.graphqldemo.service.mappers.VideoManager;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Controller
public class VideoGraphQlController {

    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;
    private final CreatorMapper creatorMapper;
    private final CreatorManager creatorManager;
    private final VideoManager videoManager;

    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository,
                           CreatorMapper creatorMapper,CreatorManager creatorManager, VideoManager videoManager)
    {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
        this.creatorMapper = creatorMapper;
        this.creatorManager = creatorManager;
        this.videoManager = videoManager;
    }

    @QueryMapping
    public List<Video> videoList(){
        return videoRepository.findAll();
    }
    @QueryMapping
    public Creator creatorById(@Argument Long id) {
        return creatorRepository.findById(id.intValue())
                .orElseThrow(()->new RuntimeException(String.format("Creator %s not found",id)));
    }
    @MutationMapping
    public Creator saveCreator(@Argument CreatorRequestDTO creator){
        Creator creatorx = Creator.builder().name(creator.getName()).email(creator.getEmail()).build();
        return creatorRepository.save(creatorx);
    }
    @MutationMapping
    public Video saveVideo(@Argument VideoRequestDTO video){
        Video videox = Video.builder().name(video.getName()).url(video.getUrl()).build();
        return videoRepository.save(videox) ;
    }
    @SubscriptionMapping
    public Flux<Video> notifyVideoChange() {
        return Flux.fromStream(
                Stream.generate(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    CreatorRequestDTO creatorRequest = CreatorRequestDTO.builder().name("x" +
                            new Random().nextInt()).email("x@gmail.com").build();
                    Creator creator = creatorManager.saveCreator(creatorMapper.dtoToCreator(creatorRequest));
                    Video video = videoManager.findVideoById(1);
                    video.setCreator(creator);
                    videoManager.updateVideo(video);
                    return video;
                }));
    }
}
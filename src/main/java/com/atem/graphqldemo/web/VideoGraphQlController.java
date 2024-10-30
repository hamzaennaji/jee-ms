package com.atem.graphqldemo.web;

import com.atem.graphqldemo.dao.entities.Creator;
import com.atem.graphqldemo.dao.entities.Video;
import com.atem.graphqldemo.dao.repositories.CreatorRepository;
import com.atem.graphqldemo.dao.repositories.VideoRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {
    private final CreatorRepository creatorRepository;
    private final VideoRepository videoRepository;
    VideoGraphQlController(CreatorRepository creatorRepository, VideoRepository videoRepository){
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
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
}
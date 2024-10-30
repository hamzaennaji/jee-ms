package com.atem.graphqldemo;

import com.atem.graphqldemo.dao.entities.Creator;
import com.atem.graphqldemo.dao.entities.Video;
import com.atem.graphqldemo.dao.repositories.CreatorRepository;
import com.atem.graphqldemo.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static graphql.introspection.IntrospectionQueryBuilder.build;

@SpringBootApplication
public class GraphqlDemoApplication{
	public static void main(String[] args) {
		SpringApplication.run(GraphqlDemoApplication.class, args);
	}
	@Autowired
	private CreatorRepository creatorRepository;
	@Autowired
	private VideoRepository videoRepository;
	@Bean
	public CommandLineRunner startup() {

		return args -> {
			List<Creator> creators = List.of(Creator.builder().id(1).name("x").email("x@xyz.com").build(),
					Creator.builder().id(2).name("y").email("y@xyz.com").build());
			creatorRepository.saveAll(creators);
			List<Video> videos = List.of(Video.builder().name("x's video").url("xyz.com").description("x's video description").publishDate(new java.util.Date()).creator(creators.get(0)).build(),
					Video.builder().name("y's video").url("xyz.com").description("y's video description").publishDate(new java.util.Date()).creator(creators.get(1)).build());
			videoRepository.saveAll(videos);
			System.out.println("Database initialized!");
		};

	}
}
package com.atem.graphqldemo.dao.repositories;

import com.atem.graphqldemo.dao.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
}

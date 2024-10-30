package com.atem.graphqldemo.dao.repositories;

import com.atem.graphqldemo.dao.entities.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatorRepository extends JpaRepository<Creator, Integer>  {
}

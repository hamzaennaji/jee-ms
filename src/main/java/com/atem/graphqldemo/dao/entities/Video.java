package com.atem.graphqldemo.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import com.atem.graphqldemo.dao.entities.Creator;
import lombok.*;

import java.util.Date;

@Table(name = "video")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name, url, description;
    private Date publishDate;
    @ManyToOne
    private Creator creator;

}

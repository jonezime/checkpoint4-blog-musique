package com.blogmusique.Blog.Musique.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String genre;

    private String cover;

    public Band() {

    }

    public Band(String name, String genre, String cover) {
        this.name = name;
        this.genre = genre;
        this.cover = cover;
    }
}

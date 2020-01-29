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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String role = "user";

    @NotNull
    private boolean active = true;

    @ManyToMany
    @JoinTable(name = "user_band",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id" ))
    private List<Band> bands = new ArrayList<>();

    public User() {

    }
}

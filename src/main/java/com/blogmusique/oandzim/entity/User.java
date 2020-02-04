package com.blogmusique.oandzim.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class User implements Serializable {

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

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "user_band",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id" ))
    private Set<Band> bands = new HashSet<>();

    public User() {

    }
}

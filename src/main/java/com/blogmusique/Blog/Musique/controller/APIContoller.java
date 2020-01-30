package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.entity.Band;
import com.blogmusique.Blog.Musique.entity.User;
import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class APIContoller {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/users")
    public List<User> getUsers() {

        List<User> users = userRepository.findAll();

        return users;
    }

    @GetMapping("/bands")
    public List<Band> getBands() {

        List<Band> bands = bandRepository.findAll();

        return bands;
    }
}

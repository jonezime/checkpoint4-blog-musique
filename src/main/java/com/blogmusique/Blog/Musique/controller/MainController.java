package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }
}

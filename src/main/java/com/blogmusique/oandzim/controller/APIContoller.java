package com.blogmusique.oandzim.controller;

import com.blogmusique.oandzim.entity.Band;
import com.blogmusique.oandzim.entity.User;
import com.blogmusique.oandzim.repository.BandRepository;
import com.blogmusique.oandzim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

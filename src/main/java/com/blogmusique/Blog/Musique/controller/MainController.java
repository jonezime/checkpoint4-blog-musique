package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/")
    public String index(HttpSession session) {

        if (session.getAttribute("userId") != null) {
            return "redirect:/list";
        }

        if (session.getAttribute("adminId") != null) {
            return "redirect:/admin";
        }

        return "index";
    }

    @GetMapping("/disconnect")
    public String disconnect(HttpSession session) {
        session.removeAttribute("adminId");
        session.removeAttribute("userId");

        return "redirect:/";
    }
}

package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.entity.User;
import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/")
    public String signPost(@RequestParam String email,
                           @RequestParam String password,
                           HttpSession session) {

        String encryptedPassword = Hashing.sha256()
                .hashString(password, StandardCharsets.UTF_8)
                .toString();

        User user = userRepository.findByEmailAndPassword(email, encryptedPassword);
        if (user.getRole().equals("user") & user.isActive()) {
            session.setAttribute("userId", user.getId());
            return "redirect:/list";
        }

        if (user.getRole().equals("admin")) {
            session.setAttribute("adminId", user.getId());
            return "redirect:/admin";
        }

        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(HttpSession session,
                                   @ModelAttribute User user) {

        String encryptedPassword = Hashing.sha256()
                .hashString(user.getPassword(), StandardCharsets.UTF_8)
                .toString();
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        session.setAttribute("userId", user.getId());

        return "redirect:/addBand";
    }

    @GetMapping("/disconnect")
    public String disconnect(HttpSession session) {

        session.removeAttribute("adminId");
        session.removeAttribute("userId");

        return "redirect:/";
    }
}

package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.entity.User;
import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BandRepository bandRepository;

    @GetMapping("/registration")
    public String registration(HttpSession session) {
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

        return "list";
    }

    @GetMapping("/sign")
    public String sign() {
        return "index";
    }

    @PostMapping("/sign")
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

    @GetMapping("/add")
    public String add(HttpSession session) {
        return "add";
    }

    @GetMapping("/list")
    public String list(HttpSession session) {
        return "list";
    }

    @GetMapping("/admin")
    public String admin(HttpSession session, Model out) {

        List<User> userList = new ArrayList<>();
        for (User user : userRepository.findAllByRoleEquals("user")) {
            userList.add(user);
        }

        out.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(HttpSession session,
                             @RequestParam(required = false) Long idUser) {

        userRepository.deleteById(idUser);
        return "redirect:/admin";
    }

    @GetMapping("/admin/disable")
    public String disableUser(HttpSession session,
                              @RequestParam(required = false) Long idUser) {

        User user = userRepository.findById(idUser).get();
        user.setActive(false);
        userRepository.save(user);

        return "redirect:/admin";
    }

    @GetMapping("/admin/activate")
    public String activateUser(HttpSession session,
                              @RequestParam(required = false) Long idUser) {

        User user = userRepository.findById(idUser).get();
        user.setActive(true);
        userRepository.save(user);

        return "redirect:/admin";
    }
}

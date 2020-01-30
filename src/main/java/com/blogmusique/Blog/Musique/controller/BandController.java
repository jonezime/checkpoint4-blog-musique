package com.blogmusique.Blog.Musique.controller;

import com.blogmusique.Blog.Musique.entity.Band;
import com.blogmusique.Blog.Musique.entity.User;
import com.blogmusique.Blog.Musique.repository.BandRepository;
import com.blogmusique.Blog.Musique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BandController {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addBand")
    public String addBand(HttpSession session) {
        return "add";
    }

    @PostMapping("/addBand")
    public String addBandPost(HttpSession session,
                              @RequestParam String name,
                              @RequestParam String genre,
                              @RequestParam(required = false) String cover) {

        if (cover.isEmpty()) {
            cover = "/img/cover-band.jpg";
        }

        User user = userRepository.findById((Long) session.getAttribute("userId")).get();

        List<Band> bandList = new ArrayList<>();
        bandList.add(new Band(name, genre, cover));
        user.setBands(bandList);

        userRepository.save(user);

        return "redirect:/addBand";
    }

    @GetMapping("/removeBand")
    public String removeBand(HttpSession session,
                             @RequestParam Long bandId) {

        User user = userRepository.findById((Long) session.getAttribute("userId")).get();

        List<Band> bandList = user.getBands();
        for (Band band : bandList) {
            System.out.println(band.getName().toString());
            if (band.getId() == bandId) {
                bandList.remove(band);
                break;
            }
        }

        userRepository.save(user);

        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(HttpSession session,
                       Model out) {

        User user = userRepository.findById((Long) session.getAttribute("userId")).get();
        List<Band> bandList = user.getBands();
        out.addAttribute("bandList", bandList);
        return "list";
    }
}

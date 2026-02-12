package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.demo.models.StaffRating;
import com.example.demo.models.StaffRatingRepository;


@Controller
public class StaffRatingController {

    @Autowired
    private StaffRatingRepository staffRepo;

    @GetMapping("/")
    public String index(Model model) {
        List<StaffRating> staff = staffRepo.findAll();
        model.addAttribute("staff", staff);
        return "index";
    }
    
}

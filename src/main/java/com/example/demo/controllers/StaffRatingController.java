package com.example.demo.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import com.example.demo.models.RoleType;
import com.example.demo.models.StaffRating;
import com.example.demo.models.StaffRatingRepository;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class StaffRatingController {

    @Autowired
    private StaffRatingRepository staffRepo;

    @GetMapping("/")
    public String index(Model model) {
        System.out.println("Getting users");
        List<StaffRating> staff = staffRepo.findAll();
        model.addAttribute("staff", staff);
        System.out.println("Got users");
        return "index";
    }
    
    @PostMapping("/create")
    public String addUser(@RequestParam Map<String, String> newreview, HttpServletResponse response) {
        System.out.println("CREATED review");
        String name = newreview.get("name");
        String email = newreview.get("email");
        int clarity = Integer.parseInt(newreview.get("clarity"));
        int niceness = Integer.parseInt(newreview.get("niceness"));
        int knowledgeableScore = Integer.parseInt(newreview.get("knowledgeableScore"));
        String comment = newreview.get("comment");
        RoleType role = RoleType.valueOf(newreview.get("role"));
        staffRepo.save(new StaffRating(name, email, role, clarity, niceness, knowledgeableScore, comment));
        response.setStatus(201);
        return "redirect:/";
    }
    
    
}

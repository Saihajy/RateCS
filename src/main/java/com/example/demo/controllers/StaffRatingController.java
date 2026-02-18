package com.example.demo.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/") // Endpoint for indexpage
    public String index(Model model) {
        System.out.println("Getting users");
        List<StaffRating> staff = staffRepo.findAll();
        model.addAttribute("staff", staff);
        System.out.println("Got users");
        return "index";
    }

    @GetMapping("/details/{id}") // Endpoint for specific entry
    public String getMethodName(@PathVariable int id, Model model) {
        System.out.println("Getting entry details");
        Optional<StaffRating> review = staffRepo.findById(id);
        StaffRating staff = review.get();
        model.addAttribute("staff", staff);
        System.out.println("Got entry details");
        return "details";
    }
    
    
    @PostMapping("/create") // Endpoint for creating new review entry
    public String addUser(@RequestParam Map<String, String> newreview, HttpServletResponse response) {
        System.out.println("CREATED review");
        String name = newreview.get("name");
        String email = newreview.get("email").trim();
        int clarity = Integer.parseInt(newreview.get("clarity"));
        int niceness = Integer.parseInt(newreview.get("niceness"));
        int knowledgeableScore = Integer.parseInt(newreview.get("knowledgeableScore"));
        String comment = newreview.get("comment");
        RoleType role = RoleType.valueOf(newreview.get("role"));
        staffRepo.save(new StaffRating(name, email, role, clarity, niceness, knowledgeableScore, comment));
        response.setStatus(201);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}") // Endpoint for editing a current entry by id
    public String edit(@PathVariable int id, Model model) {
        System.out.println("Getting edit details");
        Optional<StaffRating> review = staffRepo.findById(id);
        StaffRating staff = review.get();
        model.addAttribute("staff", staff);
        System.out.println("Got edit details");
        return "edit";
    }

    @PostMapping("/edit/{id}") // Endpoint for sending a post once entry has been updated
    public String editSubmit(@PathVariable int id, @ModelAttribute StaffRating staffForm) {
        StaffRating existing = staffRepo.findById(id).orElseThrow();
        existing.setName(staffForm.getName());
        existing.setEmail(staffForm.getEmail());
        existing.setRole(staffForm.getRole());
        existing.setClarity(staffForm.getClarity());
        existing.setNiceness(staffForm.getNiceness());
        existing.setKnowledgeableScore(staffForm.getKnowledgeableScore());
        existing.setComment(staffForm.getComment());

        staffRepo.save(existing);
        return "redirect:/details/" + id;
    }

    @GetMapping("/delete/{id}") // Endpoint for deleting entry
    public String confirmDelete(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "delete";
    }

    @PostMapping("/delete/{id}") // Endpoint to confirm deletion when it recieves post request from confirmation page (delete.html)
    public String doDelete(@PathVariable int id) {
        staffRepo.deleteById(id);
        return "redirect:/";
    }
    
    
    
    
}
